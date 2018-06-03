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
import com.vix.hr.trainning.controller.TrainingCostController;
import com.vix.hr.trainning.entity.TrainingChannel;
import com.vix.hr.trainning.entity.TrainingCost;
import com.vix.hr.trainning.entity.TrainingData;
import com.vix.hr.trainning.entity.TrainingLecturer;
import com.vix.hr.trainning.service.IDemandApplyService;
/**
 * 
 * @ClassName: TrainingCostAction
 * @Description: 培训费用
 * @author bobchen
 * @date 2015年10月9日 下午2:50:56
 *
 */
@Controller
@Scope("prototype")
public class TrainingCostAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(TrainingCost.class);

	/** 注入service */
	@Autowired
	private TrainingCostController trainingCostController;
	private List<TrainingCost> trainingCostList;
	private TrainingCost trainingCost;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private IDemandApplyService iDemandApplyService;
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

	public List<TrainingCost> getTrainingCostList() {
		return trainingCostList;
	}

	public void setTrainingCostList(List<TrainingCost> trainingCostList) {
		this.trainingCostList = trainingCostList;
	}

	public TrainingCost getTrainingCost() {
		return trainingCost;
	}

	public void setTrainingCost(TrainingCost trainingCost) {
		this.trainingCost = trainingCost;
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
			trainingCostList = trainingCostController.findTrainingCostIndex();
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
			String hroughApproval = getRequestParameter("hroughApproval");
			if (null != hroughApproval && !"".equals(hroughApproval)) {
				params.put("hroughApproval," + SearchCondition.EQUAL, Integer.parseInt(hroughApproval));
			}
			/* 按最近提出 */
			String costStartDate = getRequestParameter("costStartDate");
			if (costStartDate != null && !"".equals(costStartDate)) {
				params.put("costStartDate," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(costStartDate));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("costStartDate");
				getPager().setOrderBy("desc");
			}
			/* uploadPersonId包含当前登录人id */
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, employeeId);
			Pager pager = trainingCostController.goSingleList(params, getPager());
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
			/* 培训项目 */
			String projectTraining = getRequestParameter("projectTraining");
			if (null != projectTraining && !"".equals(projectTraining)) {
				projectTraining = URLDecoder.decode(projectTraining, "utf-8");
			}
			/* 费用提出人 */
			String costPeople = getRequestParameter("costPeople");
			if (null != costPeople && !"".equals(costPeople)) {
				costPeople = URLDecoder.decode(costPeople, "utf-8");
			}
			/* 审核人 */
			String costAudit = getRequestParameter("costAudit");
			if (null != costAudit && !"".equals(costAudit)) {
				costAudit = URLDecoder.decode(costAudit, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("projectTraining," + SearchCondition.ANYLIKE, projectTraining);
				pager = trainingCostController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != projectTraining && !"".equals(projectTraining)) {
					params.put("projectTraining," + SearchCondition.ANYLIKE, projectTraining);
				}
				if (null != costPeople && !"".equals(costPeople)) {
					params.put("orgUnitAndLeadingOfficial," + SearchCondition.ANYLIKE, costPeople);
				}
				if (null != costAudit && !"".equals(costAudit)) {
					params.put("orgUnitAndLeadingOfficial," + SearchCondition.ANYLIKE, costAudit);
				}
				pager = trainingCostController.goSingleList(params, getPager());
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
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				trainingCost = trainingCostController.doListEntityById(id);
			} else {
				trainingCost = new TrainingCost();
				trainingCost.setIsTemp("1");
				trainingCost = trainingCostController.doSaveTrainingCost(trainingCost);
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
			if (StringUtils.isNotEmpty(trainingCost.getId()) && !"0".equals(trainingCost.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = trainingCost.getProjectTraining();
			String py = ChnToPinYin.getPYString(title);
			trainingCost.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(trainingCost);
			trainingCost = trainingCostController.doSaveTrainingCost(trainingCost);
			trainingCost.setIsTemp("");

			this.trainingCost.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.trainingCost.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			trainingCost.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.trainingCost);
			logger.info("对培训费用进行了修改！");
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
			TrainingCost trainingCost = trainingCostController.doListEntityById(id);
			if (null != trainingCost) {
				trainingCostController.doDeleteByEntity(trainingCost);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除培训费用");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	/******************************************************************
	 * 培训讲师
	 ********************************************************************************/
	/**
	 * 
	 * 获取培训讲师json数据
	 */
	public void getTrainingPlanJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				TrainingCost tc = trainingCostController.doListEntityById(id);
				if (tc != null) {
					json = convertListToJson(new ArrayList<TrainingLecturer>(tc.getTrainingLecturer()), tc.getTrainingLecturer().size());
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
	 * 培训讲师明细
	 * 
	 * @return
	 */
	public String saveOrUpdateTrainingPlan() {
		try {
			String tcIdStr = getRequestParameter("tcId");
			if (null != tcIdStr) {
				trainingLecturer = trainingCostController.findTrainingLecturerById(tcIdStr);
				trainingCost = trainingCostController.doListEntityById(id);
				trainingLecturer.setTrainingCost(trainingCost);
				trainingCostController.doSaveTrainingLecturer(trainingLecturer);
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
			TrainingLecturer trainingLecturer = trainingCostController.doListTrainingLecturerById(id);
			if (null != trainingLecturer) {
				trainingCostController.deleteTrainingLecturerEntity(trainingLecturer);
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
	public String goChooseTrainingPlan() {
		try {
			String lineItemIdStr = getRequestParameter("lineItemId");
			if (null != lineItemIdStr) {
				trainingLecturer = trainingCostController.findTrainingLecturerById(lineItemIdStr);
			}
			logger.info("添加培训讲师");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goChooseTrainingPlan";
	}

	/** 跳转到培训讲师添加讲师明细列表页面 */
	public String goTrainingPlanLists() {
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
			Pager pager = trainingCostController.goSingleLists(params, getPager());
			logger.info("获取选择讲师的数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goTrainingPlanLists";
	}

	/******************************************************************
	 * 培训渠道
	 ********************************************************************************/
	/** 获取资源明细json数据 */
	public void getTrainingProgramJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				TrainingCost tc = trainingCostController.doListEntityById(id);
				if (tc != null) {
					json = convertListToJson(new ArrayList<TrainingChannel>(tc.getTrainingChannel()), tc.getTrainingChannel().size());
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
	 * 培训渠道明细
	 * 
	 * @return
	 */
	public String saveOrUpdateTrainingProgram() {
		try {
			String tdIdStr = getRequestParameter("tdId");
			if (null != tdIdStr) {
				trainingChannel = trainingCostController.findTrainingChannelById(tdIdStr);
				trainingCost = trainingCostController.doListEntityById(id);
				trainingChannel.setTrainingCost(trainingCost);
				trainingCostController.doSaveTrainingChannel(trainingChannel);
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
			TrainingChannel trainingChannel = trainingCostController.doListTrainingChannelById(id);
			if (null != trainingChannel) {
				trainingCostController.deleteTrainingChannelEntity(trainingChannel);
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
	public String goChooseTrainingProgram() {
		try {
			String lineItemIdStr = getRequestParameter("lineItemId");
			if (null != lineItemIdStr) {
				trainingChannel = trainingCostController.findTrainingChannelById(lineItemIdStr);
			}
			logger.info("添加资源明细");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goChooseTrainingProgram";
	}

	/** 跳转到资源添加明细列表页面 */
	public String goTrainingProgramLists() {
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
			Pager pager = trainingCostController.goSingleListss(params, getPager());
			logger.info("获取选择资源的数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goTrainingProgramLists";
	}

	/******************************************************************
	 * 培训资料
	 ********************************************************************************/
	/** 获取设施明细json数据 */
	public void getTrainingActivityJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				TrainingCost tc = trainingCostController.doListEntityById(id);
				if (tc != null) {
					json = convertListToJson(new ArrayList<TrainingData>(tc.getTrainingData()), tc.getTrainingData().size());
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
	 * 培训设施明细
	 * 
	 * @return
	 */
	public String saveOrUpdateTrainingActivity() {
		try {
			String tfIdStr = getRequestParameter("tfId");
			if (null != tfIdStr) {
				trainingData = trainingCostController.findTrainingDataaById(tfIdStr);
				trainingCost = trainingCostController.doListEntityById(id);
				trainingData.setTrainingCost(trainingCost);
				trainingCostController.doSaveTrainingData(trainingData);
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
			TrainingData trainingData = trainingCostController.doListTrainingDataById(id);
			if (null != trainingData) {
				trainingCostController.deleteTrainingDataEntity(trainingData);
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
	public String goChooseTrainingActivity() {
		try {
			String lineItemIdStr = getRequestParameter("lineItemId");
			if (null != lineItemIdStr) {
				trainingData = trainingCostController.findTrainingDataaById(lineItemIdStr);
			}
			logger.info("添加设施明细");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goChooseTrainingActivity";
	}

	/** 跳转到设施添加明细列表页面 */
	public String goTrainingActivityLists() {
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
			Pager pager = trainingCostController.goSingleListsss(params, getPager());
			logger.info("获取选择设施的数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goTrainingActivityLists";
	}

	public void findTreeToJson() {
		try {
			List<Organization> listCategory = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listCategory = iDemandApplyService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listCategory = iDemandApplyService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			for (int i = 0; i < listCategory.size(); i++) {
				Organization cc = listCategory.get(i);
				if (cc.getSubOrganizations().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(cc.getId());
					strSb.append("\",name:\"");
					strSb.append(cc.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(cc.getId());
					strSb.append("\",name:\"");
					strSb.append(cc.getOrgName());
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
}
