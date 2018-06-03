package com.vix.oa.adminMg.action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.controller.WorkPlanController;
import com.vix.oa.adminMg.entity.Postil;
import com.vix.oa.adminMg.entity.ProjectManagement;
import com.vix.oa.adminMg.entity.WorkPlanType;

@Controller
@Scope("prototype")
public class ProjectManagementAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(WorkPlanController.class);
	@Autowired
	private WorkPlanController workPlanController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	private List<ProjectManagement> projectManagementList;
	private ProjectManagement projectManagement;
	private Postil postil;
	private String id;
	public Integer isPublish;
	private String pageNo;

	/** 计划类型 */
	private List<WorkPlanType> workPlanTypeList;

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			projectManagementList = workPlanController.doListSalesOrderIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			/* params.put("votingType.code," + SearchCondition.ANYLIKE, "1"); */
			// 计划类型
			String workPlan = getRequestParameter("workPlan");
			if (null != workPlan && !"".equals(workPlan)) {
				params.put("workPlan," + SearchCondition.EQUAL, Integer.parseInt(workPlan));
			}
			// 按最近使用
			String initiateTime = getRequestParameter("initiateTime");
			if (initiateTime != null && !"".equals(initiateTime)) {
				params.put("initiateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(initiateTime));
			}
			// uploadPersonId包含当前登录人id
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, employeeId);
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("initiateTime");
				getPager().setOrderBy("desc");
			}
			Pager pager = workPlanController.doSubSingleList(params, getPager());
			logger.info("获取工作计划列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 获取计划搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 计划名称
			String proposalTitle = getRequestParameter("proposalTitle");
			if (null != proposalTitle && !"".equals(proposalTitle)) {
				proposalTitle = URLDecoder.decode(proposalTitle, "utf-8");
			}
			// 发布范围
			String bizOrgNames = getRequestParameter("bizOrgNames");
			if (null != bizOrgNames && !"".equals(bizOrgNames)) {
				bizOrgNames = URLDecoder.decode(bizOrgNames, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("proposalTitle," + SearchCondition.ANYLIKE, proposalTitle);
				pager = workPlanController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != bizOrgNames && !"".equals(bizOrgNames)) {
					params.put("bizOrgNames," + SearchCondition.ANYLIKE, bizOrgNames);
				}
				if (null != proposalTitle && !"".equals(proposalTitle)) {
					params.put("proposalTitle," + SearchCondition.ANYLIKE, proposalTitle);
				}
				pager = workPlanController.goSingleList(params, getPager());
			}
			logger.info("获取计划搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 工作计划 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				projectManagement = workPlanController.doListEntityById1(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return "goSaveOrUpdate";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != projectManagement.getId()) {
				isSave = false;
			}
			/** 索引 */
			String proposalTitle = projectManagement.getProposalTitle();
			String py = ChnToPinYin.getPYString(proposalTitle);
			projectManagement.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(projectManagement);
			projectManagement = workPlanController.doSaveSalesOrder(projectManagement);
			this.projectManagement.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.projectManagement.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			projectManagement.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.projectManagement);
			logger.info("新增！");
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 跳转至批注修改页面 */
	public String goPostilOrPlan() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				postil = workPlanController.doListPostilOrPlan(id);
				this.saveBaseEntity(this.projectManagement);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return "goPostilOrPlan";
	}

	/** 处理批注新增修改操作 */
	public String postilOrPlan() {
		boolean isSave = true;
		try {
			if (null != postil.getId()) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(postil);
			postil = workPlanController.doSavePostilOrPlan(postil);

			this.postil.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.postil.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			postil.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			postil.setCreateTime(new Date());
			this.saveBaseEntity(this.postil);
			logger.info("新增！");
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/**
	 * 对工作计划状态进行操作，根据业务需求选择是继续或终止
	 * 
	 * @return
	 */
	public void updateIsPublish() {
		try {
			ProjectManagement pb = workPlanController.findEntityById(id);
			if (pb.getIsPublish() == 1) {
				isPublish = 0;
			} else if (pb.getIsPublish() == 0) {
				isPublish = 1;
			}
			pb.setIsPublish(isPublish);
			projectManagement = workPlanController.doSaveSalesOrder(pb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 处理新闻删除操作 */
	public String deleteById() {
		try {
			ProjectManagement pb = workPlanController.findEntityById(id);
			if (null != pb) {
				workPlanController.doDeleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public WorkPlanController getWorkPlanController() {
		return workPlanController;
	}

	public void setWorkPlanController(WorkPlanController workPlanController) {
		this.workPlanController = workPlanController;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ProjectManagement> getProjectManagementList() {
		return projectManagementList;
	}

	public void setProjectManagementList(List<ProjectManagement> projectManagementList) {
		this.projectManagementList = projectManagementList;
	}

	public ProjectManagement getProjectManagement() {
		return projectManagement;
	}

	public void setProjectManagement(ProjectManagement projectManagement) {
		this.projectManagement = projectManagement;
	}

	public List<WorkPlanType> getWorkPlanTypeList() {
		return workPlanTypeList;
	}

	public void setWorkPlanTypeList(List<WorkPlanType> workPlanTypeList) {
		this.workPlanTypeList = workPlanTypeList;
	}

	public Postil getPostil() {
		return postil;
	}

	public void setPostil(Postil postil) {
		this.postil = postil;
	}

	public Integer getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
}
