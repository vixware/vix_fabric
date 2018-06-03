package com.vix.hr.trainning.action;
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
import com.vix.hr.trainning.controller.TrainingActivityController;
import com.vix.hr.trainning.entity.TrainingActivity;
import com.vix.hr.trainning.entity.TrainingPlanningDetail;
import com.vix.hr.trainning.service.IDemandApplyService;
/**
 * 
 * @Description:培训活动
 * @author bobchen
 * @date 2015-9-17 下午2:39:18
 */
@Controller
@Scope("prototype")
public class TrainingActivityAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(TrainingActivity.class);

	/** 注入service */
	@Autowired
	private TrainingActivityController trainingActivityController;
	private List<TrainingActivity> trainingActivityList;
	private TrainingActivity trainingActivity;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private IDemandApplyService iDemandApplyService;
	/** 培训计划 */
	private TrainingPlanningDetail trainingPlanningDetail;

	private String id;
	private String pageNo;
	private String parentId;

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TrainingActivityController getTrainingActivityController() {
		return trainingActivityController;
	}

	public void setTrainingActivityController(TrainingActivityController trainingActivityController) {
		this.trainingActivityController = trainingActivityController;
	}

	public List<TrainingActivity> getTrainingActivityList() {
		return trainingActivityList;
	}

	public void setTrainingActivityList(List<TrainingActivity> trainingActivityList) {
		this.trainingActivityList = trainingActivityList;
	}

	public TrainingActivity getTrainingActivity() {
		return trainingActivity;
	}

	public void setTrainingActivity(TrainingActivity trainingActivity) {
		this.trainingActivity = trainingActivity;
	}

	public InitEntityBaseController getInitEntityBaseController() {
		return initEntityBaseController;
	}

	public void setInitEntityBaseController(InitEntityBaseController initEntityBaseController) {
		this.initEntityBaseController = initEntityBaseController;
	}

	public IDemandApplyService getiDemandApplyService() {
		return iDemandApplyService;
	}

	public void setiDemandApplyService(IDemandApplyService iDemandApplyService) {
		this.iDemandApplyService = iDemandApplyService;
	}

	public TrainingPlanningDetail getTrainingPlanningDetail() {
		return trainingPlanningDetail;
	}

	public void setTrainingPlanningDetail(TrainingPlanningDetail trainingPlanningDetail) {
		this.trainingPlanningDetail = trainingPlanningDetail;
	}

	@Override
	public String goList() {
		try {
			trainingActivityList = trainingActivityController.findTrainingActivityIndex();
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

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			/* 考核方式 */
			String examinationMethod = getRequestParameter("examinationMethod");
			if (null != examinationMethod && !"".equals(examinationMethod)) {
				params.put("examinationMethod," + SearchCondition.EQUAL, Integer.parseInt(examinationMethod));
			}
			/* 按最近活动 */
			String activityStartDate = getRequestParameter("activityStartDate");
			if (activityStartDate != null && !"".equals(activityStartDate)) {
				params.put("activityStartDate," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(activityStartDate));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("activityStartDate");
				getPager().setOrderBy("desc");
			}
			/* uploadPersonId包含当前登录人id */
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, employeeId);
			Pager pager = trainingActivityController.goSingleList(params, getPager());
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

	/**
	 * 获取搜索列表数据
	 * 
	 * @return
	 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			/* 活动编码 */
			String activityCode = getRequestParameter("activityCode");
			if (null != activityCode && !"".equals(activityCode)) {
				activityCode = URLDecoder.decode(activityCode, "utf-8");
			}
			/* 活动名称 */
			String activityName = getRequestParameter("activityName");
			if (null != activityName && !"".equals(activityName)) {
				activityName = URLDecoder.decode(activityName, "utf-8");
			}
			/* 活动部门或人员 */
			String activityDepartmentOrLeadings = getRequestParameter("activityDepartmentOrLeadings");
			if (null != activityDepartmentOrLeadings && !"".equals(activityDepartmentOrLeadings)) {
				activityDepartmentOrLeadings = URLDecoder.decode(activityDepartmentOrLeadings, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("activityCode," + SearchCondition.ANYLIKE, activityCode);
				pager = trainingActivityController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != activityCode && !"".equals(activityCode)) {
					params.put("activityCode," + SearchCondition.ANYLIKE, activityCode);
				}
				if (null != activityName && !"".equals(activityName)) {
					params.put("activityName," + SearchCondition.ANYLIKE, activityName);
				}
				if (null != activityDepartmentOrLeadings && !"".equals(activityDepartmentOrLeadings)) {
					params.put("activityDepartmentOrLeadings," + SearchCondition.ANYLIKE, activityDepartmentOrLeadings);
				}
				pager = trainingActivityController.goSingleList(params, getPager());
			}
			logger.info("获取培训活动搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 跳转至用户修改页面
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				trainingActivity = trainingActivityController.doListEntityById(id);
				logger.info("");
			} else {
				trainingActivity = new TrainingActivity();
				trainingActivity = trainingActivityController.doSaveTrainingActivity(trainingActivity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	/**
	 * 
	 * 处理修改/保存操作
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(trainingActivity.getId()) && !"0".equals(trainingActivity.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = trainingActivity.getActivityName();
			String py = ChnToPinYin.getPYString(title);
			trainingActivity.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(trainingActivity);
			trainingActivity = trainingActivityController.doSaveTrainingActivity(trainingActivity);

			this.trainingActivity.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.trainingActivity.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			trainingActivity.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.trainingActivity);
			logger.info("对培训活动进行了修改！");
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
	/**
	 * 
	 * 处理删除操作
	 * 
	 * @return
	 */
	public String deleteById() {
		try {
			TrainingActivity trainingActivity = trainingActivityController.doListEntityById(id);
			if (null != trainingActivity) {
				trainingActivityController.doDeleteByEntity(trainingActivity);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除计划培训计划明细");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	/*******************************************
	 * 培训计划
	 ********************************************************************************/
	/**
	 * 
	 * 获取培训计划明细json数据
	 */
	public void getTrainingPlanningDetailJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				TrainingActivity ta = trainingActivityController.findEntityById(id);
				if (ta != null) {
					json = convertListToJson(new ArrayList<TrainingPlanningDetail>(ta.getTrainingPlanningDetail()), ta.getTrainingPlanningDetail().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 培训计划明细明细
	 * 
	 * @return
	 */
	public String saveOrUpdateTrainingPlanningDetail() {
		try {
			String tcIdStr = getRequestParameter("tcId");
			if (null != tcIdStr) {
				trainingPlanningDetail = trainingActivityController.findTrainingPlanningDetailById(tcIdStr);
				trainingActivity = trainingActivityController.doListEntityById(id);
				trainingPlanningDetail.setTrainingActivity(trainingActivity);
				trainingActivityController.doSaveTrainingPlanningDetail(trainingPlanningDetail);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}
	/**
	 * 处理删除tab明细操作
	 * 
	 * @return
	 */
	public String deleteTrainingPlanningDetailById() {
		try {
			TrainingPlanningDetail trainingPlanningDetail = trainingActivityController.doListTrainingPlanningDetailById(id);
			if (null != trainingPlanningDetail) {
				trainingActivityController.deleteTrainingPlanningDetailEntity(trainingPlanningDetail);
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 跳转到计划明细添加明细页面 */
	public String goChooseTrainingPlanningDetail() {
		try {
			String lineItemIdStr = getRequestParameter("lineItemId");
			if (null != lineItemIdStr) {
				trainingPlanningDetail = trainingActivityController.findTrainingPlanningDetailById(lineItemIdStr);
			}
			logger.info("添加培训计划明细");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goChooseTrainingPlanningDetail";
	}

	/** 跳转到计划培训计划明细添加计划明细明细列表页面 */
	public String goTrainingPlanningDetailList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			String status = getRequestParameter("status");
			// 按状态
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			// 按状态
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = trainingActivityController.goTrainingPlanningDetailList(params, getPager());
			logger.info("获取选择培训计划明细的数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goTrainingPlanningDetailList";
	}

	public void findTreeToJson() {
		try {
			List<Organization> listOrganization = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				listOrganization = iDemandApplyService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = iDemandApplyService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = listOrganization.size();
			for (int i = 0; i < count; i++) {
				Organization org = listOrganization.get(i);
				if (org.getSubOrganizations().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
