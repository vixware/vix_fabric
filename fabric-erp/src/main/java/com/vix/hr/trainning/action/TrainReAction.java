package com.vix.hr.trainning.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.job.entity.HrCategory;
import com.vix.hr.trainning.controller.TrainingResourcesController;
import com.vix.hr.trainning.entity.TrainingCourse;
import com.vix.hr.trainning.entity.TrainingData;
import com.vix.hr.trainning.entity.TrainingFacilities;
import com.vix.hr.trainning.entity.TrainingLecturer;
import com.vix.hr.trainning.entity.TrainingResources;
import com.vix.hr.trainning.service.ITrainingResourcesService;

@Controller
@Scope("prototype")
public class TrainReAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(TrainingResources.class);
	/** 注入service */
	@Autowired
	private TrainingResourcesController trainingResourcesController;
	private List<TrainingResources> trainingResourcesList;
	private TrainingResources trainingResources;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private ITrainingResourcesService iTrainingResourcesService;
	/** 培训课程 */
	private TrainingCourse trainingCourse;
	/** 培训资料 */
	private TrainingData trainingData;
	/** 培训设施 */
	private TrainingFacilities trainingFacilities;
	/** 培训教师 */
	private TrainingLecturer trainingLecturer;

	private String id;

	@Override
	public String goList() {
		try {
			trainingResourcesList = trainingResourcesController.findTrainingResourcesIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			String code = getRequestParameter("code");
			String status = getRequestParameter("status");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			// 按状态查询
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			Pager pager = trainingResourcesController.goSingleList(params, getPager());
			logger.info("获取列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 获取搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 内容
			String searchContent = getRequestParameter("searchContent");
			// 计划名称
			String pName = getRequestParameter("pName");
			// 职位名称
			String contacts = getRequestParameter("contacts");
			if (null != contacts && !"".equals(contacts)) {
				contacts = URLDecoder.decode(contacts, "utf-8");
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("programName," + SearchCondition.ANYLIKE, searchContent);
				params.put("org," + SearchCondition.ANYLIKE, searchContent);
				params.put("responsibleForOrgUnit," + SearchCondition.ANYLIKE, searchContent);
				params.put("schemer," + SearchCondition.ANYLIKE, searchContent);
				params.put("leadingOfficial," + SearchCondition.ANYLIKE, searchContent);
				pager = trainingResourcesController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != contacts && !"".equals(contacts)) {
					params.put("schemer," + SearchCondition.ANYLIKE, contacts);
				}
				if (null != pName && !"".equals(pName)) {
					params.put("programName," + SearchCondition.ANYLIKE, pName);
				}
				params.remove("name");
				pager = trainingResourcesController.goSingleList(params, getPager());
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
			Pager pager = trainingResourcesController.doSubSingleList(params, getPager());
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
			if (null != id) {
				trainingResources = trainingResourcesController.doListEntityById(id);
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
			if (null != trainingResources.getId()) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(trainingResources);
			trainingResources = trainingResourcesController.doSaveTrainingResources(trainingResources);
			logger.info("对订单进行了修改！");
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
			TrainingResources trainingResources = trainingResourcesController.doListEntityById(id);
			if (null != trainingResources) {
				trainingResourcesController.doDeleteByEntity(trainingResources);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/** 跳转到培训课程页面 */
	public String goAddTrainingCourse() {
		try {
			if (null != id) {
				trainingCourse = trainingResourcesController.doListTrainingCourseById(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddTrainingCourse";
	}

	/** 跳转到培训资料页面 */
	public String goAddTrainingData() {
		try {
			if (null != id) {
				trainingData = trainingResourcesController.doListTrainingDataById(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddTrainingData";
	}

	/** 跳转到培训设施页面 */
	public String goAddTrainingFacilities() {
		try {
			if (null != id) {
				trainingFacilities = trainingResourcesController.doListTrainingFacilitiesById(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddTrainingFacilities";
	}

	/** 跳转到培训教师页面 */
	public String goAddTrainingLecturer() {
		try {
			if (null != id) {
				trainingLecturer = trainingResourcesController.doListTrainingLecturerById(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddTrainingLecturer";
	}

	/** 获取培训课程json数据 */
	public void getTrainingCourseJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				TrainingResources trainingResources = trainingResourcesController.findEntityById(id);
				json = convertListToJson(new ArrayList<TrainingCourse>(trainingResources.getTrainingCourses()), trainingResources.getTrainingCourses().size(), "trainingResources");
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

	/** 获取培训资料json数据 */
	public void getTrainingDataJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				TrainingResources trainingResources = trainingResourcesController.findEntityById(id);
				json = convertListToJson(new ArrayList<TrainingData>(trainingResources.getTrainingDatas()), trainingResources.getTrainingDatas().size(), "trainingResources");
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

	/** 获取培训设施json数据 */
	public void getTrainingFacilitiesJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				TrainingResources trainingResources = trainingResourcesController.findEntityById(id);
				json = convertListToJson(new ArrayList<TrainingFacilities>(trainingResources.getTrainingFacilities()), trainingResources.getTrainingFacilities().size(), "trainingResources");
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

	/** 获取培训教师json数据 */
	public void getTrainingLecturerJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				TrainingResources trainingResources = trainingResourcesController.findEntityById(id);
				json = convertListToJson(new ArrayList<TrainingLecturer>(trainingResources.getTrainingLecturers()), trainingResources.getTrainingLecturers().size(), "trainingResources");
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
	 * 保存培训课程明细
	 * 
	 * @return
	 */
	public String saveOrUpdateTrainingCourse() {
		try {
			if (null != id) {
				trainingResources = trainingResourcesController.doListEntityById(id);
				trainingCourse.setTrainingResources(trainingResources);
				trainingResourcesController.doSaveTrainingCourse(trainingCourse);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}

	/**
	 * 保存培训资料明细
	 * 
	 * @return
	 */
	public String saveOrUpdateTrainingData() {
		try {
			if (null != id) {
				trainingResources = trainingResourcesController.doListEntityById(id);
				trainingData.setTrainingResources(trainingResources);
				trainingResourcesController.doSaveTrainingData(trainingData);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}

	/**
	 * 保存培训设施明细
	 * 
	 * @return
	 */
	public String saveOrUpdateTrainingFacilities() {
		try {
			if (null != id) {
				trainingResources = trainingResourcesController.doListEntityById(id);
				trainingFacilities.setTrainingResources(trainingResources);
				trainingResourcesController.doSaveTrainingFacilities(trainingFacilities);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}

	/**
	 * 保存培训教师明细
	 * 
	 * @return
	 */
	public String saveOrUpdateTrainingLecturer() {
		try {
			if (null != id) {
				trainingResources = trainingResourcesController.doListEntityById(id);
				trainingLecturer.setTrainingResources(trainingResources);
				trainingResourcesController.doSaveTrainingLecturer(trainingLecturer);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}

	/** 删除培训课程明细 */
	public String deleteTrainingCourseById() {
		try {
			TrainingCourse trainingCourse = trainingResourcesController.doListTrainingCourseById(id);
			if (null != trainingCourse) {
				trainingResourcesController.deleteTrainingCourseEntity(trainingCourse);
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

	/** 删除培训资料明细 */
	public String deleteTrainingDataById() {
		try {
			TrainingData trainingData = trainingResourcesController.doListTrainingDataById(id);
			if (null != trainingData) {
				trainingResourcesController.deleteTrainingDataEntity(trainingData);
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

	/** 删除培训设施明细 */
	public String deleteTrainingFacilitiesById() {
		try {
			TrainingFacilities trainingFacilities = trainingResourcesController.doListTrainingFacilitiesById(id);
			if (null != trainingFacilities) {
				trainingResourcesController.deleteTrainingFacilitiesEntity(trainingFacilities);
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

	/** 删除培训教师明细 */
	public String deleteTrainingLecturerById() {
		try {
			TrainingLecturer trainingLecturer = trainingResourcesController.doListTrainingLecturerById(id);
			if (null != trainingLecturer) {
				trainingResourcesController.deleteTrainingLecturerEntity(trainingLecturer);
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

	// 弹框选择组织
	public void findTreeToJson() {
		try {
			List<HrCategory> listCategory = new ArrayList<HrCategory>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listCategory = iTrainingResourcesService.findAllSubEntity(HrCategory.class, "parentCategory.id", id, params);
			} else {
				listCategory = iTrainingResourcesService.findAllSubEntity(HrCategory.class, "parentCategory.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			for (int i = 0; i < listCategory.size(); i++) {
				HrCategory cc = listCategory.get(i);
				if (cc.getSubCategorys().size() > 0) {
					strSb.append("{id:");
					strSb.append(cc.getId());
					strSb.append(",name:\"");
					strSb.append(cc.getName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:");
					strSb.append(cc.getId());
					strSb.append(",name:\"");
					strSb.append(cc.getName());
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

	public String goChooseCategory() {
		return "goChooseCategory";
	}

	public List<TrainingResources> getTrainingResourcesList() {
		return trainingResourcesList;
	}

	public void setTrainingResourcesList(List<TrainingResources> trainingResourcesList) {
		this.trainingResourcesList = trainingResourcesList;
	}

	public TrainingResources getTrainingResources() {
		return trainingResources;
	}

	public void setTrainingResources(TrainingResources trainingResources) {
		this.trainingResources = trainingResources;
	}

	public ITrainingResourcesService getiTrainingResourcesService() {
		return iTrainingResourcesService;
	}

	public void setiTrainingResourcesService(ITrainingResourcesService iTrainingResourcesService) {
		this.iTrainingResourcesService = iTrainingResourcesService;
	}

	public TrainingCourse getTrainingCourse() {
		return trainingCourse;
	}

	public void setTrainingCourse(TrainingCourse trainingCourse) {
		this.trainingCourse = trainingCourse;
	}

	public TrainingData getTrainingData() {
		return trainingData;
	}

	public void setTrainingData(TrainingData trainingData) {
		this.trainingData = trainingData;
	}

	public TrainingFacilities getTrainingFacilities() {
		return trainingFacilities;
	}

	public void setTrainingFacilities(TrainingFacilities trainingFacilities) {
		this.trainingFacilities = trainingFacilities;
	}

	public TrainingLecturer getTrainingLecturer() {
		return trainingLecturer;
	}

	public void setTrainingLecturer(TrainingLecturer trainingLecturer) {
		this.trainingLecturer = trainingLecturer;
	}

}
