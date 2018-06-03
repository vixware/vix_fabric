package com.vix.hr.trainning.action;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.job.entity.HrCategory;
import com.vix.hr.trainning.controller.TrainningImplementController;
import com.vix.hr.trainning.entity.TrainingPlanning;
import com.vix.hr.trainning.entity.TrainingPlanningDetail;
import com.vix.hr.trainning.entity.TrainningImplement;
import com.vix.hr.trainning.service.ITrainningImplementService;

/**
 * @Description: 培训活动
 * @author 李大鹏
 */
@Controller
@Scope("prototype")
public class TrainImpAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(TrainningImplement.class);
	/** 注入service */
	@Autowired
	private TrainningImplementController trainningImplementController;
	private List<TrainningImplement> trainningImplementList;
	private TrainningImplement trainningImplement;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private ITrainningImplementService iTrainningImplementService;
	private String id;
	/** 附件 */
	private File fileToUpload;
	/** 附件的名称 */
	private String fileToUploadFileName;

	// 培训计划
	private TrainingPlanning trainingPlanning;
	// 培训计划列表
	private List<TrainingPlanning> trainingPlanningList;
	// 培训计划明细
	private TrainingPlanningDetail trainingPlanningDetail;
	// 培训计划明细列表
	private List<TrainingPlanningDetail> trainingPlanningDetailList;

	@Override
	public File getFileToUpload() {
		return fileToUpload;
	}

	@Override
	public void setFileToUpload(File fileToUpload) {
		this.fileToUpload = fileToUpload;
	}

	@Override
	public String getFileToUploadFileName() {
		return fileToUploadFileName;
	}

	@Override
	public void setFileToUploadFileName(String fileToUploadFileName) {
		this.fileToUploadFileName = fileToUploadFileName;
	}

	@Override
	public String goList() {
		try {
			trainningImplementList = trainningImplementController.findTrainningImplementIndex();
		} catch (Exception e) {
			// TODO: handle exception
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
			String code = getRequestParameter("code");
			String status = getRequestParameter("status");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			// 按状态查询
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			params.put("trainingName," + SearchCondition.NOEQUAL, "null");
			Pager pager = trainningImplementController.goSingleList(params, getPager());
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
				pager = trainningImplementController.goSearchList(params, getPager());
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
				pager = trainningImplementController.goSingleList(params, getPager());
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
			Pager pager = trainningImplementController.doSubSingleList(params, getPager());
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
				trainningImplement = trainningImplementController.doListEntityById(id);
				logger.info("");
			} else {
				trainningImplement = new TrainningImplement();
				trainningImplement = trainningImplementController.doSaveTrainningImplement(trainningImplement);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 跳转到培训计划明细页面 */
	public String goAddTrainningImplementDetail() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				trainingPlanning = trainningImplementController.doListTrainningImplementDetailById(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddTrainningImplementDetail";
	}

	/** 处理修改/保存操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(trainningImplement.getId()) && !"0".equals(trainningImplement.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(trainningImplement);
			trainningImplement = trainningImplementController.doSaveTrainningImplement(trainningImplement);
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
			TrainningImplement trainningImplement = trainningImplementController.doListEntityById(id);
			if (null != trainningImplement) {
				trainningImplementController.doDeleteByEntity(trainningImplement);
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

	/** 获取明细json数据 */
	public void getTrainningImplementDetailJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				TrainningImplement ti = trainningImplementController.findEntityById(id);
				if (ti != null) {
					json = convertListToJson(new ArrayList<TrainingPlanning>(ti.getTrainingPlanning()), ti.getTrainingPlanning().size());
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
	 * 培训计划明细
	 * 
	 * @return
	 */
	public String saveOrUpdateTrainningImplementDetail() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				trainningImplement = trainningImplementController.doListEntityById(id);
				trainingPlanning.setTrainningImplement(trainningImplement);
				trainningImplementController.doSaveTrainningImplementDetail(trainingPlanning);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
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
				listCategory = iTrainningImplementService.findAllSubEntity(HrCategory.class, "parentCategory.id", id, params);
			} else {
				listCategory = iTrainningImplementService.findAllSubEntity(HrCategory.class, "parentCategory.id", null, params);
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

	/** 处理删除tab明细操作 */
	public String deleteTrainningImplementDetailById() {
		try {
			TrainingPlanning trainingPlanning = trainningImplementController.doListTrainningImplementDetailById(id);
			if (null != trainingPlanning) {
				trainningImplementController.deleteTrainningImplementDetailEntity(trainingPlanning);
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

	/** 跳转到选择培训计划页面 */
	public String goChoosePlanning() {
		return "goChoosePlanning";
	}

	public String converterPlanToOrder() {
		String trainingPlanids = getRequestParameter("trainingPlanid");
		trainningImplement = trainningImplementController.doListEntityById(id);
		String[] trainingPlanidArr = trainingPlanids.split(",");
		if (trainingPlanidArr != null && !"".equals(trainingPlanidArr) && trainingPlanidArr.length > 0) {
			for (String trainingPlanid : trainingPlanidArr) {
				if (trainingPlanid != null && !"".equals(trainingPlanid)) {
					// 根据id查询培训计划
					TrainingPlanning trainingPlanning = trainningImplementController.doListTrainingPlanningById(trainingPlanid);
					if (trainingPlanning != null) {
						try {
							trainningImplementController.converterPlanToOrder(trainningImplement, trainingPlanning);
							logger.info("将培训计划明细转化成培训活动明细");
							setMessage(SAVE_SUCCESS);
						} catch (Exception e) {
							this.setMessage(SAVE_FAIL);
							e.printStackTrace();
						}
					}
				}
			}
		}
		return UPDATE;
	}

	/** 获取培训计划列表数据 */
	public String goPlanningList() {
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
			// 按最近使用
			Pager pager = trainningImplementController.goTrainingPlannings(params, getPager());
			logger.info("获取培训计划数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPlanningList";
	}

	public String gettrainingPlanningDetailListList() {
		String trainingPlanid = getRequestParameter("trainingPlanid");
		try {
			if (null != trainingPlanid && !"".equals(trainingPlanid)) {
				trainingPlanning = trainningImplementController.doListTrainingPlanningById(trainingPlanid);
				Set<TrainingPlanningDetail> trainingPlanningDetailTemp = trainingPlanning.getTrainingPlanningDetails();
				if (trainingPlanningDetailTemp.size() > 0) {
					trainingPlanningDetailList = new ArrayList<TrainingPlanningDetail>(trainingPlanningDetailTemp);
					logger.info("获取培训计划明细数据");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "gettrainingPlanningDetailListList";
	}

	public List<TrainningImplement> getTrainningImplementList() {
		return trainningImplementList;
	}

	public void setTrainningImplementList(List<TrainningImplement> trainningImplementList) {
		this.trainningImplementList = trainningImplementList;
	}

	public TrainningImplement getTrainningImplement() {
		return trainningImplement;
	}

	public void setTrainningImplement(TrainningImplement trainningImplement) {
		this.trainningImplement = trainningImplement;
	}

	public ITrainningImplementService getiTrainningImplementService() {
		return iTrainningImplementService;
	}

	public void setiTrainningImplementService(ITrainningImplementService iTrainningImplementService) {
		this.iTrainningImplementService = iTrainningImplementService;
	}

	public TrainingPlanning getTrainingPlanning() {
		return trainingPlanning;
	}

	public void setTrainingPlanning(TrainingPlanning trainingPlanning) {
		this.trainingPlanning = trainingPlanning;
	}

	public List<TrainingPlanning> getTrainingPlanningList() {
		return trainingPlanningList;
	}

	public void setTrainingPlanningList(List<TrainingPlanning> trainingPlanningList) {
		this.trainingPlanningList = trainingPlanningList;
	}

	public TrainingPlanningDetail getTrainingPlanningDetail() {
		return trainingPlanningDetail;
	}

	public void setTrainingPlanningDetail(TrainingPlanningDetail trainingPlanningDetail) {
		this.trainingPlanningDetail = trainingPlanningDetail;
	}

	public List<TrainingPlanningDetail> getTrainingPlanningDetailList() {
		return trainingPlanningDetailList;
	}

	public void setTrainingPlanningDetailList(List<TrainingPlanningDetail> trainingPlanningDetailList) {
		this.trainingPlanningDetailList = trainingPlanningDetailList;
	}

}
