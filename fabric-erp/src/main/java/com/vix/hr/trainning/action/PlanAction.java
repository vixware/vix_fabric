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
import com.vix.hr.trainning.controller.PlanController;
import com.vix.hr.trainning.entity.Plan;
import com.vix.hr.trainning.service.TrainingPlanService;

@Controller
@Scope("prototype")
public class PlanAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(Plan.class);
	/** 注入service */
	@Autowired
	private PlanController planController;
	private List<Plan> planList;
	private Plan plan;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private TrainingPlanService trainingPlanService;
	private String id;
	private String pageNo;

	@Override
	public String goList() {
		try {
			planList = planController.findPlanIndex();
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
			/* 按最近计划 */
			String trainingStartDate = getRequestParameter("trainingStartDate");
			if (trainingStartDate != null && !"".equals(trainingStartDate)) {
				params.put("trainingStartDate," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(trainingStartDate));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("trainingStartDate");
				getPager().setOrderBy("desc");
			}
			/* uploadPersonId包含当前登录人id */
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, employeeId);
			Pager pager = planController.goSingleList(params, getPager());
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
			/* 计划名称 */
			String planName = getRequestParameter("planName");
			if (null != planName && !"".equals(planName)) {
				planName = URLDecoder.decode(planName, "utf-8");
			}
			/* 部门 */
			String planDepartment = getRequestParameter("planDepartment");
			if (null != planDepartment && !"".equals(planDepartment)) {
				planDepartment = URLDecoder.decode(planDepartment, "utf-8");
			}
			/* 计划总费用 */
			String totalCost = getRequestParameter("totalCost");
			if (null != totalCost && !"".equals(totalCost)) {
				totalCost = URLDecoder.decode(totalCost, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("planName," + SearchCondition.ANYLIKE, planName);
				pager = planController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != planName && !"".equals(planName)) {
					params.put("planName," + SearchCondition.ANYLIKE, planName);
				}
				if (null != planDepartment && !"".equals(planDepartment)) {
					params.put("planDepartment," + SearchCondition.ANYLIKE, planDepartment);
				}
				if (null != totalCost && !"".equals(totalCost)) {
					params.put("totalCost," + SearchCondition.ANYLIKE, totalCost);
				}
				pager = planController.goSingleList(params, getPager());
			}
			logger.info("获取填报计划搜索列表数据页");
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
			Pager pager = planController.doSubSingleList(params, getPager());
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
				plan = planController.doListEntityById(id);
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
			if (StringUtils.isNotEmpty(plan.getId()) && !"0".equals(plan.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = plan.getPlanName();
			String py = ChnToPinYin.getPYString(title);
			plan.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(plan);

			this.plan.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.plan.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			plan.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			plan = planController.doSavePlan(plan);
			logger.info("对填报计划进行了修改！");
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
			Plan trainingPlan = planController.doListEntityById(id);
			if (null != trainingPlan) {
				planController.doDeleteByEntity(trainingPlan);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除填报计划信息");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	// 弹框选择组织
	public void findTreeToJson() {
		try {
			List<Organization> listOrganization = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				listOrganization = trainingPlanService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = trainingPlanService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Plan> getPlanList() {
		return planList;
	}

	public void setPlanList(List<Plan> planList) {
		this.planList = planList;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public TrainingPlanService getTrainingPlanService() {
		return trainingPlanService;
	}

	public void setTrainingPlanService(TrainingPlanService trainingPlanService) {
		this.trainingPlanService = trainingPlanService;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
}
