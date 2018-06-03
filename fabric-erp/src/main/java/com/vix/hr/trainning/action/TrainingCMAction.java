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
import com.vix.hr.trainning.controller.TrainingCMController;
import com.vix.hr.trainning.entity.CourseNature;
import com.vix.hr.trainning.entity.QualityIndex;
import com.vix.hr.trainning.entity.TrainCategory;
import com.vix.hr.trainning.entity.TrainingCM;
import com.vix.hr.trainning.entity.TrainingChannel;
import com.vix.hr.trainning.entity.TrainingData;
import com.vix.hr.trainning.entity.TrainingLecturer;
import com.vix.hr.trainning.service.IDemandApplyService;
/**
 * 
 * @Description:教育培训课程管理
 * @author bobchen
 * @date 2015-8-28 下午2:13:20
 */
@Controller
@Scope("prototype")
public class TrainingCMAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(TrainingCM.class);

	/** 注入service */
	@Autowired
	private TrainingCMController trainingCMController;
	private List<TrainingCM> trainingCMList;
	private TrainingCM trainingCM;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private IDemandApplyService iDemandApplyService;
	/** 素质指标 */
	private List<QualityIndex> qualityIndexList;
	/** 课程性质 */
	private List<CourseNature> courseNatureList;
	/** 培训类别 */
	private List<TrainCategory> trainCategoryList;
	/** 培训讲师 */
	private TrainingLecturer trainingLecturer;
	/** 培训渠道 */
	private TrainingChannel trainingChannel;
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

	public List<TrainingCM> getTrainingCMList() {
		return trainingCMList;
	}

	public void setTrainingCMList(List<TrainingCM> trainingCMList) {
		this.trainingCMList = trainingCMList;
	}

	public TrainingCM getTrainingCM() {
		return trainingCM;
	}

	public void setTrainingCM(TrainingCM trainingCM) {
		this.trainingCM = trainingCM;
	}
	public TrainingCMController getTrainingCMController() {
		return trainingCMController;
	}

	public void setTrainingCMController(TrainingCMController trainingCMController) {
		this.trainingCMController = trainingCMController;
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

	public List<QualityIndex> getQualityIndexList() {
		return qualityIndexList;
	}

	public void setQualityIndexList(List<QualityIndex> qualityIndexList) {
		this.qualityIndexList = qualityIndexList;
	}

	public List<CourseNature> getCourseNatureList() {
		return courseNatureList;
	}

	public void setCourseNatureList(List<CourseNature> courseNatureList) {
		this.courseNatureList = courseNatureList;
	}

	public List<TrainCategory> getTrainCategoryList() {
		return trainCategoryList;
	}

	public void setTrainCategoryList(List<TrainCategory> trainCategoryList) {
		this.trainCategoryList = trainCategoryList;
	}

	public TrainingLecturer getTrainingLecturer() {
		return trainingLecturer;
	}

	public void setTrainingLecturer(TrainingLecturer trainingLecturer) {
		this.trainingLecturer = trainingLecturer;
	}

	public TrainingChannel getTrainingChannel() {
		return trainingChannel;
	}

	public void setTrainingChannel(TrainingChannel trainingChannel) {
		this.trainingChannel = trainingChannel;
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
			trainingCMList = trainingCMController.findTrainingCMIndex();
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
			/* 状态 */
			String isOpen = getRequestParameter("isOpen");
			if (null != isOpen && !"".equals(isOpen)) {
				params.put("isOpen," + SearchCondition.EQUAL, Integer.parseInt(isOpen));
			}
			/* 按最近使用 */
			String effectiveStartDate = getRequestParameter("effectiveStartDate");
			if (effectiveStartDate != null && !"".equals(effectiveStartDate)) {
				params.put("effectiveStartDate," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(effectiveStartDate));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("effectiveStartDate");
				getPager().setOrderBy("desc");
			}
			/* uploadPersonId包含当前登录人id */
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, employeeId);
			Pager pager = trainingCMController.goSingleList(params, getPager());
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
			/* 课程编码 */
			String courseCode = getRequestParameter("courseCode");
			if (null != courseCode && !"".equals(courseCode)) {
				courseCode = URLDecoder.decode(courseCode, "utf-8");
			}
			/* 课程名称 */
			String courseName = getRequestParameter("courseName");
			if (null != courseName && !"".equals(courseName)) {
				courseName = URLDecoder.decode(courseName, "utf-8");
			}
			/* 所属部门或参与人 */
			String xgdepartmentOrParticipants = getRequestParameter("xgdepartmentOrParticipants");
			if (null != xgdepartmentOrParticipants && !"".equals(xgdepartmentOrParticipants)) {
				xgdepartmentOrParticipants = URLDecoder.decode(xgdepartmentOrParticipants, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("courseCode," + SearchCondition.ANYLIKE, courseCode);
				pager = trainingCMController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != courseCode && !"".equals(courseCode)) {
					params.put("courseCode," + SearchCondition.ANYLIKE, courseCode);
				}
				if (null != courseName && !"".equals(courseName)) {
					params.put("courseName," + SearchCondition.ANYLIKE, courseName);
				}
				if (null != xgdepartmentOrParticipants && !"".equals(xgdepartmentOrParticipants)) {
					params.put("xgdepartmentOrParticipants," + SearchCondition.ANYLIKE, xgdepartmentOrParticipants);
				}
				pager = trainingCMController.goSingleList(params, getPager());
			}
			logger.info("获取活动计划搜索列表数据页");
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
			qualityIndexList = iDemandApplyService.findAllByEntityClass(QualityIndex.class);
			courseNatureList = iDemandApplyService.findAllByEntityClass(CourseNature.class);
			trainCategoryList = iDemandApplyService.findAllByEntityClass(TrainCategory.class);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				trainingCM = trainingCMController.doListEntityById(id);
				logger.info("");
			} else {
				trainingCM = new TrainingCM();
				trainingCM = trainingCMController.doSaveTrainingCM(trainingCM);
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
			if (StringUtils.isNotEmpty(trainingCM.getId()) && !"0".equals(trainingCM.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = trainingCM.getCourseName();
			String py = ChnToPinYin.getPYString(title);
			trainingCM.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(trainingCM);
			trainingCM = trainingCMController.doSaveTrainingCM(trainingCM);

			this.trainingCM.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.trainingCM.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			trainingCM.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.trainingCM);
			logger.info("对培训课程进行了修改！");
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
			TrainingCM trainingCM = trainingCMController.doListEntityById(id);
			if (null != trainingCM) {
				trainingCMController.doDeleteByEntity(trainingCM);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除订单信息");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * 获取教师列表数据
	 * 
	 * @return
	 */
	public String goSubRadioSingleList() {
		try {
			Map<String, Object> params = getParams();
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = trainingCMController.goSingleList2(params, getPager());
			logger.info("获取选择教师的数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSubRadioSingleList";
	}
	/**
	 * 
	 * 获取培训讲师json数据
	 */
	public void getTrainingCMJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				TrainingCM trainingCM = trainingCMController.findEntityById(id);
				json = convertListToJson(new ArrayList<TrainingLecturer>(trainingCM.getTrainingLecturer()), trainingCM.getTrainingLecturer().size(), "trainingCM");
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
	 * 培训讲师明细
	 * 
	 * @return
	 */
	public String saveOrUpdateTrainingLecturer() {
		try {
			String tcIdStr = getRequestParameter("tcId");
			if (null != tcIdStr) {
				trainingLecturer = trainingCMController.findTrainingLecturerById(tcIdStr);
				trainingCM = trainingCMController.doListEntityById(id);
				trainingLecturer.setTrainingCM(trainingCM);
				trainingCMController.doSaveTrainingLecturer(trainingLecturer);
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
			TrainingLecturer trainingLecturer = trainingCMController.doListTrainingLecturerById(id);
			if (null != trainingLecturer) {
				trainingCMController.deleteTrainingLecturerEntity(trainingLecturer);
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
	public String goAddTrainingCM() {
		try {
			String lineItemIdStr = getRequestParameter("lineItemId");
			if (null != lineItemIdStr) {
				trainingLecturer = trainingCMController.findTrainingLecturerById(lineItemIdStr);
			}
			logger.info("添加培训讲师");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddTrainingCM";
	}

	/** 跳转到培训讲师添加讲师明细列表页面 */
	public String goSubRadioSingleLists() {
		try {
			Map<String, Object> params = getParams();
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = trainingCMController.goSingleList3(params, getPager());
			logger.info("获取选择讲师的数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSubRadioSingleLists";
	}

	/******************************************************************
	 * 培训渠道
	 ********************************************************************************/
	/** 获取资源明细json数据 */
	public void getTrainingChannelJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				TrainingCM trainingCM = trainingCMController.findEntityById(id);
				json = convertListToJson(new ArrayList<TrainingChannel>(trainingCM.getTrainingChannel()), trainingCM.getTrainingChannel().size(), "trainingCM");
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
	 * 培训渠道明细
	 * 
	 * @return
	 */
	public String saveOrUpdateTrainingChannel() {
		try {
			String tdIdStr = getRequestParameter("tdId");
			if (null != tdIdStr) {
				trainingChannel = trainingCMController.findTrainingChannelById(tdIdStr);
				trainingCM = trainingCMController.doListEntityById(id);
				trainingChannel.setTrainingCM(trainingCM);
				trainingCMController.doSaveTrainingChannel(trainingChannel);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}

	/** 处理删除tab资源明细操作 */
	public String deleteTrainingChannelById() {
		try {
			TrainingChannel trainingChannel = trainingCMController.doListTrainingChannelById(id);
			if (null != trainingChannel) {
				trainingCMController.deleteTrainingChannelEntity(trainingChannel);
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

	/** 跳转到资源添加明细页面 */
	public String goAddTrainingChannel() {
		try {
			String lineItemIdStr = getRequestParameter("lineItemId");
			if (null != lineItemIdStr) {
				trainingChannel = trainingCMController.findTrainingChannelById(lineItemIdStr);
			}
			logger.info("添加资源明细");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddTrainingChannel";
	}

	/** 跳转到资源添加明细列表页面 */
	public String goTrainingChannelLists() {
		try {
			Map<String, Object> params = getParams();
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = trainingCMController.goSingleList4(params, getPager());
			logger.info("获取选择资源的数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goTrainingChannelLists";
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
				TrainingCM trainingCM = trainingCMController.findEntityById(id);
				json = convertListToJson(new ArrayList<TrainingData>(trainingCM.getTrainingData()), trainingCM.getTrainingData().size(), "trainingCM");
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
				trainingData = trainingCMController.findTrainingDataaById(tfIdStr);
				trainingCM = trainingCMController.doListEntityById(id);
				trainingData.setTrainingCM(trainingCM);
				trainingCMController.doSaveTrainingData(trainingData);
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
			TrainingData trainingData = trainingCMController.doListTrainingDataById(id);
			if (null != trainingData) {
				trainingCMController.deleteTrainingDataEntity(trainingData);
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
	public String goAddTrainingData() {
		try {
			String lineItemIdStr = getRequestParameter("lineItemId");
			if (null != lineItemIdStr) {
				trainingData = trainingCMController.findTrainingDataaById(lineItemIdStr);
			}
			logger.info("添加设施明细");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddTrainingData";
	}

	/** 跳转到设施添加明细列表页面 */
	public String goTrainingDataLists() {
		try {
			Map<String, Object> params = getParams();
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = trainingCMController.goSingleList5(params, getPager());
			logger.info("获取选择设施的数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goTrainingDataLists";
	}

	// 弹框选择组织
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
