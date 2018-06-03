package com.vix.hr.job.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.org.entity.Organization;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.hr.job.controler.InterviewManagementController;
import com.vix.hr.job.entity.HrInterviewManagement;
import com.vix.hr.job.service.IInterviewManagementService;

/**
 * @Description: 面试管理
 * @author 李大鹏
 * @date 2013-08-23
 */
@Controller
@Scope("prototype")
public class AudiManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(HrInterviewManagement.class);

	@Autowired
	private InterviewManagementController interviewManagementController;
	private List<HrInterviewManagement> interviewManagementList;
	private HrInterviewManagement hrInterviewManagement;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private IInterviewManagementService iInterviewManagementService;
	private String id;
	private String pageNo;
	private List<HrInterviewManagement> interviewManagements;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public List<HrInterviewManagement> getInterviewManagementList() {
		return interviewManagementList;
	}

	public void setInterviewManagementList(List<HrInterviewManagement> interviewManagementList) {
		this.interviewManagementList = interviewManagementList;
	}

	public HrInterviewManagement getHrInterviewManagement() {
		return hrInterviewManagement;
	}

	public void setHrInterviewManagement(HrInterviewManagement hrInterviewManagement) {
		this.hrInterviewManagement = hrInterviewManagement;
	}

	public IInterviewManagementService getiInterviewManagementService() {
		return iInterviewManagementService;
	}

	public void setiInterviewManagementService(IInterviewManagementService iInterviewManagementService) {
		this.iInterviewManagementService = iInterviewManagementService;
	}

	public List<HrInterviewManagement> getInterviewManagements() {
		return interviewManagements;
	}

	public void setInterviewManagements(List<HrInterviewManagement> interviewManagements) {
		this.interviewManagements = interviewManagements;
	}

	@Override
	public String goList() {
		try {
			interviewManagementList = interviewManagementController.findInterviewManagementIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 加载顶端工具栏
	 * 
	 * @return
	 */

	public String goTopDynamicMenuContent() {
		return "goTopDynamicMenuContent";
	}

	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			/* 录用情况 */
			String employmentSituation = getRequestParameter("employmentSituation");
			if (null != employmentSituation && !"".equals(employmentSituation)) {
				params.put("employmentSituation," + SearchCondition.ANYLIKE, employmentSituation);
			}
			/* 按最近使用 */
			String createTime = getRequestParameter("createTime");
			if (createTime != null && !"".equals(createTime)) {
				params.put("createTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(createTime));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("createTime");
				getPager().setOrderBy("desc");
			}
			/* uploadPersonId包含当前登录人id */
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, employeeId);
			Pager pager = interviewManagementController.goSingleList(params, getPager());
			logger.info("获取列表数据");
			setPager(pager);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 获取搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 应聘人姓名
			String candidateName = getRequestParameter("candidateName");
			if (null != candidateName && !"".equals(candidateName)) {
				candidateName = URLDecoder.decode(candidateName, "utf-8");
			}
			// 应聘人联系方式
			String contact = getRequestParameter("contact");
			if (null != contact && !"".equals(contact)) {
				contact = URLDecoder.decode(contact, "utf-8");
			}
			// 应聘职位
			String employmentObjective = getRequestParameter("employmentObjective");
			if (null != employmentObjective && !"".equals(employmentObjective)) {
				employmentObjective = URLDecoder.decode(employmentObjective, "utf-8");
			}
			// 应聘部门
			String applicantsDepartment = getRequestParameter("applicantsDepartment");
			if (null != applicantsDepartment && !"".equals(applicantsDepartment)) {
				applicantsDepartment = URLDecoder.decode(applicantsDepartment, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("candidateName," + SearchCondition.ANYLIKE, candidateName);
				pager = interviewManagementController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != candidateName && !"".equals(candidateName)) {
					params.put("candidateName," + SearchCondition.ANYLIKE, candidateName);
				}
				if (null != contact && !"".equals(contact)) {
					params.put("contact," + SearchCondition.ANYLIKE, contact);
				}
				if (null != employmentObjective && !"".equals(employmentObjective)) {
					params.put("employmentObjective," + SearchCondition.ANYLIKE, employmentObjective);
				}
				if (null != applicantsDepartment && !"".equals(applicantsDepartment)) {
					params.put("applicantsDepartment," + SearchCondition.ANYLIKE, applicantsDepartment);
				}
				pager = interviewManagementController.goSingleList(params, getPager());
			}
			logger.info("获取列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 获取列表数据，跳转 */
	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = interviewManagementController.doSubSingleList(params, getPager());
			logger.info("");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				hrInterviewManagement = interviewManagementController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改/保存操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(hrInterviewManagement.getId()) && !"0".equals(hrInterviewManagement.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = hrInterviewManagement.getCandidateName();
			String py = ChnToPinYin.getPYString(title);
			hrInterviewManagement.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(hrInterviewManagement);
			hrInterviewManagement = interviewManagementController.doSaveInterviewManagement(hrInterviewManagement);

			this.hrInterviewManagement.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.hrInterviewManagement.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			hrInterviewManagement.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.hrInterviewManagement);
			logger.info("对面试管理进行了修改！");
			if (isSave) {
				setMessage(SAVE_SUCCESS);
			} else {
				setMessage(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				this.setMessage(SAVE_FAIL);
			} else {
				this.setMessage(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			HrInterviewManagement hrInterviewManagement = interviewManagementController.doListEntityById(id);
			if (null != hrInterviewManagement) {
				interviewManagementController.doDeleteByEntity(hrInterviewManagement);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除面试管理信息");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	// 弹框选择组织
	public void findTreeToJson() {
		try {
			List<Organization> listCategory = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listCategory = iInterviewManagementService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listCategory = iInterviewManagementService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			for (int i = 0; i < listCategory.size(); i++) {
				Organization sc = listCategory.get(i);
				if (sc.getSubOrganizations().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(sc.getId());
					strSb.append("\",name:\"");
					strSb.append(sc.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(sc.getId());
					strSb.append("\",name:\"");
					strSb.append(sc.getOrgName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < listCategory.size() - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 弹出选择部门，职位窗体 */
	public String goChooseCategory() {
		return "goChooseCategory";
	}

}
