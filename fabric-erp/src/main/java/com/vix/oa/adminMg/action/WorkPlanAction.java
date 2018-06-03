package com.vix.oa.adminMg.action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.controller.WorkController;
import com.vix.oa.adminMg.controller.WorkPlanController;
import com.vix.oa.adminMg.entity.Postil;
import com.vix.oa.adminMg.entity.ProgressLog;
import com.vix.oa.adminMg.entity.ProjectManagement;
import com.vix.oa.adminMg.entity.WorkPlanType;

/**
 * 
 * @ClassName: WorkPlanAction
 * @Description: 工作日志查询
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-4-25 下午5:23:40
 */
@Controller
@Scope("prototype")
public class WorkPlanAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(WorkPlanController.class);
	@Autowired
	private WorkController workController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	private List<ProjectManagement> projectManagementList;
	private ProjectManagement projectManagement;
	private Postil postil;
	private String id;
	public Integer isPublish;
	private String pageNo;

	/** 获取到工作计划与批注内容时将阅读次数加一，保存阅读次数 */
	public String goLookPostilOrPlan() {
		try {
			this.projectManagement = baseHibernateService.findEntityById(ProjectManagement.class, id);
			System.out.println(projectManagement.getReadTimes() + "=====");
			if (projectManagement.getReadTimes() == null) {
				projectManagement.setReadTimes("1l");
			} else {
				projectManagement.setReadTimes(projectManagement.getReadTimes() + 1);
			}
			baseHibernateService.save(projectManagement);
			logger.info("获取工作计划与批注内容列表数据");
			// setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goLookPostilOrPlan";
	}

	/** 查看工作计划与批注 */
	public String lookPostilOrPlan() {

		return "lookPostilOrPlan";
	}

	/** 计划类型 */
	private List<WorkPlanType> workPlanTypeList;

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			projectManagementList = workController.doListSalesOrderIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
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
			// pubids包含当前登录人id
			String employeeId = SecurityUtil.getCurrentEmpId();
			params.put("pubIds," + SearchCondition.ANYLIKE, "," + employeeId + ",");
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("initiateTime");
				getPager().setOrderBy("desc");
			}
			Pager pager = workController.doSubSingleList(params, getPager());
			logger.info("获取计划列表数据");
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
				pager = workController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != bizOrgNames && !"".equals(bizOrgNames)) {
					params.put("bizOrgNames," + SearchCondition.ANYLIKE, bizOrgNames);
				}
				if (null != proposalTitle && !"".equals(proposalTitle)) {
					params.put("proposalTitle," + SearchCondition.ANYLIKE, proposalTitle);
				}
				pager = workController.goSingleList(params, getPager());
			}
			logger.info("获取计划搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			ProgressLog pb = workController.findEntityById3(id);
			if (null != pb) {
				workController.doDeleteByEntity3(pb);
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

	public InitEntityBaseController getInitEntityBaseController() {
		return initEntityBaseController;
	}

	public void setInitEntityBaseController(InitEntityBaseController initEntityBaseController) {
		this.initEntityBaseController = initEntityBaseController;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
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

	public List<WorkPlanType> getWorkPlanTypeList() {
		return workPlanTypeList;
	}

	public void setWorkPlanTypeList(List<WorkPlanType> workPlanTypeList) {
		this.workPlanTypeList = workPlanTypeList;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

}
