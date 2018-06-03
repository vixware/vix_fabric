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
import com.vix.hr.trainning.controller.DemandApplyController;
import com.vix.hr.trainning.entity.DemandApply;
import com.vix.hr.trainning.entity.TrainingCourse;
import com.vix.hr.trainning.entity.TrainingData;
import com.vix.hr.trainning.entity.TrainingFacilities;
import com.vix.hr.trainning.service.IDemandApplyService;

/**
 * 
 * @Description:培训需求申请
 * @author bobchen
 * @date 2015-8-28 下午2:13:20
 */
@Controller
@Scope("prototype")
public class DemandApplyAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(DemandApply.class);

	/** 注入service */
	@Autowired
	private DemandApplyController demandApplyController;
	private List<DemandApply> demandApplyList;
	private DemandApply demandApply;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private IDemandApplyService iDemandApplyService;
	/** 培训讲师 */
	private TrainingCourse trainingCourse;
	/** 培训设施 */
	private TrainingFacilities trainingFacilities;
	/** 培训资料 */
	private TrainingData trainingData;
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

	public DemandApplyController getDemandApplyController() {
		return demandApplyController;
	}

	public void setDemandApplyController(DemandApplyController demandApplyController) {
		this.demandApplyController = demandApplyController;
	}

	public List<DemandApply> getDemandApplyList() {
		return demandApplyList;
	}

	public void setDemandApplyList(List<DemandApply> demandApplyList) {
		this.demandApplyList = demandApplyList;
	}

	public DemandApply getDemandApply() {
		return demandApply;
	}

	public void setDemandApply(DemandApply demandApply) {
		this.demandApply = demandApply;
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

	public TrainingCourse getTrainingCourse() {
		return trainingCourse;
	}

	public void setTrainingCourse(TrainingCourse trainingCourse) {
		this.trainingCourse = trainingCourse;
	}

	public TrainingFacilities getTrainingFacilities() {
		return trainingFacilities;
	}

	public void setTrainingFacilities(TrainingFacilities trainingFacilities) {
		this.trainingFacilities = trainingFacilities;
	}

	public TrainingData getTrainingData() {
		return trainingData;
	}

	public void setTrainingData(TrainingData trainingData) {
		this.trainingData = trainingData;
	}

	@Override
	public String goList() {
		try {
			demandApplyList = demandApplyController.findTrainingCMIndex();
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
			/* 按最近使用 */
			String applicationDate = getRequestParameter("applicationDate");
			if (applicationDate != null && !"".equals(applicationDate)) {
				params.put("applicationDate," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(applicationDate));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("applicationDate");
				getPager().setOrderBy("desc");
			}
			/* uploadPersonId包含当前登录人id */
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, employeeId);
			Pager pager = demandApplyController.goSingleList(params, getPager());
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

	/** 获取搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			/* 申请名称 */
			String applicationName = getRequestParameter("applicationName");
			if (null != applicationName && !"".equals(applicationName)) {
				applicationName = URLDecoder.decode(applicationName, "utf-8");
			}
			/* 申请部门或人员 */
			String departmentOrPersonnel = getRequestParameter("departmentOrPersonnel");
			if (null != departmentOrPersonnel && !"".equals(departmentOrPersonnel)) {
				departmentOrPersonnel = URLDecoder.decode(departmentOrPersonnel, "utf-8");
			}
			/* 建议培训讲师 */
			String courseDescription = getRequestParameter("courseDescription");
			if (null != courseDescription && !"".equals(courseDescription)) {
				courseDescription = URLDecoder.decode(courseDescription, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("applicationName," + SearchCondition.ANYLIKE, applicationName);
				pager = demandApplyController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != applicationName && !"".equals(applicationName)) {
					params.put("applicationName," + SearchCondition.ANYLIKE, applicationName);
				}
				if (null != departmentOrPersonnel && !"".equals(departmentOrPersonnel)) {
					params.put("departmentOrPersonnel," + SearchCondition.ANYLIKE, departmentOrPersonnel);
				}
				if (null != courseDescription && !"".equals(courseDescription)) {
					params.put("courseDescription," + SearchCondition.ANYLIKE, courseDescription);
				}
				pager = demandApplyController.goSingleList(params, getPager());
			}
			logger.info("获取培训需求申请搜索列表数据页");
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
				demandApply = demandApplyController.doListEntityById(id);
			} else {
				demandApply = new DemandApply();
				demandApply.setIsTemp("1");
				demandApply = demandApplyController.doSaveTrainingCM(demandApply);
				logger.info("");
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
			if (StringUtils.isNotEmpty(demandApply.getId()) && !"".equals(demandApply.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = demandApply.getApplicationName();
			String py = ChnToPinYin.getPYString(title);
			demandApply.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(demandApply);
			demandApply = demandApplyController.doSaveTrainingCM(demandApply);
			demandApply.setIsTemp("");

			this.demandApply.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.demandApply.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			demandApply.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.demandApply);
			logger.info("对培训需求申请进行了修改！");
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
			DemandApply demandApply = demandApplyController.doListEntityById(id);
			if (null != demandApply) {
				demandApplyController.doDeleteByEntity(demandApply);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除培训需求申请信息");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * 
	 * 获取培训课程json数据
	 */
	public void getTrainingCourseJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				DemandApply demandApply = demandApplyController.findEntityById(id);
				json = convertListToJson(new ArrayList<TrainingCourse>(demandApply.getTrainingCourse()), demandApply.getTrainingCourse().size(), "demandApply");
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
	 * 培训课程明细
	 * 
	 * @return
	 */
	public String saveOrUpdateTrainingCourse() {
		try {
			String tcIdStr = getRequestParameter("tcId");
			if (null != tcIdStr) {
				trainingCourse = demandApplyController.findTrainingLecturerById(tcIdStr);
				demandApply = demandApplyController.doListEntityById(id);
				trainingCourse.setDemandApply(demandApply);
				demandApplyController.doSaveTrainingLecturer(trainingCourse);
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
	public String deleteTrainingLecturerById() {
		try {
			TrainingCourse trainingCourse = demandApplyController.doListTrainingLecturerById(id);
			if (null != trainingCourse) {
				demandApplyController.deleteTrainingLecturerEntity(trainingCourse);
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

	/** 跳转到课程添加明细页面 */
	public String goAddDemandCourse() {
		try {
			String lineItemIdStr = getRequestParameter("lineItemId");
			if (null != lineItemIdStr) {
				String lineItemId = lineItemIdStr;
				trainingCourse = demandApplyController.findTrainingLecturerById(lineItemId);
			}
			logger.info("添加培训讲师");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddDemandCourse";
	}

	/** 跳转到培训设施添加设施明细列表页面 */
	public String goDemandCourseLists() {
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
			Pager pager = demandApplyController.goSingleList1(params, getPager());
			logger.info("获取选择设施的数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goDemandCourseLists";
	}

	/******************************************************************
	 * 培训设施
	 ********************************************************************************/
	/** 获取培训设施明细json数据 */
	public void getTrainingFacilitiesJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				DemandApply demandApply = demandApplyController.findEntityById(id);
				json = convertListToJson(new ArrayList<TrainingFacilities>(demandApply.getTrainingFacilities()), demandApply.getTrainingFacilities().size(), "demandApply");
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
	 * 培训设施明细
	 * 
	 * @return
	 */
	public String saveOrUpdateTrainingFacilities() {
		try {
			String tdIdStr = getRequestParameter("tdId");
			if (null != tdIdStr) {
				trainingFacilities = demandApplyController.findTrainingChannelById(tdIdStr);
				demandApply = demandApplyController.doListEntityById(id);
				trainingFacilities.setDemandApply(demandApply);
				demandApplyController.doSaveTrainingFacilities(trainingFacilities);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}

	/** 处理删除tab资源明细操作 */
	public String deleteTrainingFacilitiesById() {
		try {
			TrainingFacilities trainingFacilities = demandApplyController.doListTrainingChannelById(id);
			if (null != trainingFacilities) {
				demandApplyController.deleteTrainingChannelEntity(trainingFacilities);
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

	/** 跳转到培训设施明细页面 */
	public String goAddDemandFacilities() {
		try {
			String lineItemIdStr = getRequestParameter("lineItemId");
			if (null != lineItemIdStr) {
				trainingFacilities = demandApplyController.findTrainingChannelById(lineItemIdStr);
			}
			logger.info("添加培训设施明细");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddDemandFacilities";
	}

	/** 跳转到培训设施添加明细列表页面 */
	public String goDemandFacilitiesLists() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			String status = getRequestParameter("status");
			/* String updateTime = getRequestParameter("updateTime"); */
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
			Pager pager = demandApplyController.goSingleList2(params, getPager());
			logger.info("获取选择培训设施的数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goDemandFacilitiesLists";
	}

	/******************************************************************
	 * 培训资料
	 ********************************************************************************/
	/** 获取设施明细json数据 */
	public void getTrainingDataJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				DemandApply demandApply = demandApplyController.findEntityById(id);
				json = convertListToJson(new ArrayList<TrainingData>(demandApply.getTrainingDatas()), demandApply.getTrainingDatas().size(), "demandApply");
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
	 * 培训设施明细
	 * 
	 * @return
	 */
	public String saveOrUpdateTrainingData() {
		try {
			String tfIdStr = getRequestParameter("tfId");
			if (null != tfIdStr) {
				trainingData = demandApplyController.findTrainingDataaById(tfIdStr);
				demandApply = demandApplyController.doListEntityById(id);
				trainingData.setDemandApply(demandApply);
				demandApplyController.doSaveTrainingData(trainingData);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}

	/** 处理删除tab设施明细操作 */
	public String deleteTrainingDataById() {
		try {
			TrainingData trainingData = demandApplyController.doListTrainingDataById(id);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				demandApplyController.deleteTrainingDataEntity(trainingData);
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

	/** 跳转到设施添加明细页面 */
	public String goAddDemandData() {
		try {
			String lineItemIdStr = getRequestParameter("lineItemId");
			if (null != lineItemIdStr) {
				trainingData = demandApplyController.findTrainingDataaById(lineItemIdStr);
			}
			logger.info("添加设施明细");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddDemandData";
	}

	/** 跳转到设施添加明细列表页面 */
	public String goDemandDataLists() {
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
			Pager pager = demandApplyController.goSingleList3(params, getPager());
			logger.info("获取选择设施的数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goDemandDataLists";
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
