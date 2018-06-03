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
import com.vix.hr.job.controler.RecruitApplicationController;
import com.vix.hr.job.entity.HrRecruitApplication;
import com.vix.hr.job.service.IRecruitApplicationService;

/**
 * @Description: 用人申请
 * @author 李大鹏
 * @date 2013-08-16
 */
@Controller
@Scope("prototype")
public class EmpAAppAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(HrRecruitApplication.class);
	/** 注入service */
	@Autowired
	private RecruitApplicationController recruitApplicationController;
	private List<HrRecruitApplication> hrRecruitApplicationList;
	private HrRecruitApplication hrRecruitApplication;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private IRecruitApplicationService iRecruitApplicationService;

	private String id;
	private String pageNo;
	private List<HrRecruitApplication> hrRecruitApplications;

	public List<HrRecruitApplication> getHrRecruitApplicationList() {
		return hrRecruitApplicationList;
	}

	public void setHrRecruitApplicationList(List<HrRecruitApplication> hrRecruitApplicationList) {
		this.hrRecruitApplicationList = hrRecruitApplicationList;
	}

	public HrRecruitApplication getHrRecruitApplication() {
		return hrRecruitApplication;
	}

	public void setHrRecruitApplication(HrRecruitApplication hrRecruitApplication) {
		this.hrRecruitApplication = hrRecruitApplication;
	}

	public IRecruitApplicationService getiRecruitApplicationService() {
		return iRecruitApplicationService;
	}

	public void setiRecruitApplicationService(IRecruitApplicationService iRecruitApplicationService) {
		this.iRecruitApplicationService = iRecruitApplicationService;
	}

	public List<HrRecruitApplication> getHrRecruitApplications() {
		return hrRecruitApplications;
	}

	public void setHrRecruitApplications(List<HrRecruitApplication> hrRecruitApplications) {
		this.hrRecruitApplications = hrRecruitApplications;
	}

	public void executeDrools() throws Exception {

	}

	public void executeEsper() throws Exception {

	}

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

	/**
	 * 加载顶端工具栏
	 * 
	 * @return
	 */

	public String goTopDynamicMenuContent() {
		return "goTopDynamicMenuContent";
	}

	@Override
	public String goList() {
		try {
			hrRecruitApplicationList = recruitApplicationController.findRecruitApplicationIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			/* 状态 */
			String approvalStatus = getRequestParameter("approvalStatus");
			if (null != approvalStatus && !"".equals(approvalStatus)) {
				params.put("approvalStatus," + SearchCondition.ANYLIKE, approvalStatus);
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
			Pager pager = recruitApplicationController.goSingleList(params, getPager());
			logger.info("获取列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 获取用人申请列表页搜索数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 申请编号
			String applicationNumber = getRequestParameter("applicationNumber");
			if (null != applicationNumber && !"".equals(applicationNumber)) {
				applicationNumber = URLDecoder.decode(applicationNumber, "utf-8");
			}
			// 申请名称
			String applicationName = getRequestParameter("applicationName");
			if (null != applicationName && !"".equals(applicationName)) {
				applicationName = URLDecoder.decode(applicationName, "utf-8");
			}
			// 申请部门
			String applicationDepartment = getRequestParameter("applicationDepartment");
			if (null != applicationDepartment && !"".equals(applicationDepartment)) {
				applicationDepartment = URLDecoder.decode(applicationDepartment, "utf-8");
			}
			// 申请职位
			String jobApplication = getRequestParameter("jobApplication");
			if (null != jobApplication && !"".equals(jobApplication)) {
				jobApplication = URLDecoder.decode(jobApplication, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("applicationNumber," + SearchCondition.ANYLIKE, applicationNumber);
				pager = recruitApplicationController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != applicationNumber && !"".equals(applicationNumber)) {
					params.put("applicationNumber," + SearchCondition.ANYLIKE, applicationNumber);
				}
				if (null != applicationName && !"".equals(applicationName)) {
					params.put("applicationName," + SearchCondition.ANYLIKE, applicationName);
				}
				if (null != applicationDepartment && !"".equals(applicationDepartment)) {
					params.put("applicationDepartment," + SearchCondition.ANYLIKE, applicationDepartment);
				}
				if (null != jobApplication && !"".equals(jobApplication)) {
					params.put("jobApplication," + SearchCondition.ANYLIKE, jobApplication);
				}
				pager = recruitApplicationController.goSingleList(params, getPager());
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
			Pager pager = recruitApplicationController.doSubSingleList(params, getPager());
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
				hrRecruitApplication = recruitApplicationController.doListEntityById(id);
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
			if (StringUtils.isNotEmpty(hrRecruitApplication.getId()) && !"0".equals(hrRecruitApplication.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = hrRecruitApplication.getApplicationName();
			String py = ChnToPinYin.getPYString(title);
			hrRecruitApplication.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(hrRecruitApplication);
			hrRecruitApplication = recruitApplicationController.doSaveRecruitApplication(hrRecruitApplication);

			this.hrRecruitApplication.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.hrRecruitApplication.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			hrRecruitApplication.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.hrRecruitApplication);
			logger.info("对用人申请进行了修改！");
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
			HrRecruitApplication hrRecruitApplication = recruitApplicationController.doListEntityById(id);
			if (null != hrRecruitApplication) {
				recruitApplicationController.doDeleteByEntity(hrRecruitApplication);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除用人申请");
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
				listCategory = iRecruitApplicationService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listCategory = iRecruitApplicationService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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
