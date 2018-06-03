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
import com.vix.hr.trainning.controller.PlanTrainCourseController;
import com.vix.hr.trainning.entity.Plan;
import com.vix.hr.trainning.entity.PlanTrainCourse;
import com.vix.hr.trainning.service.IDemandApplyService;

/**
 * 
 * @Description:计划培训课程
 * @author bobchen
 * @date 2015-9-17 下午2:39:18
 */
@Controller
@Scope("prototype")
public class PlanTrainCourseAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(PlanTrainCourse.class);

	/** 注入service */
	@Autowired
	private PlanTrainCourseController planTrainCourseController;
	private List<PlanTrainCourse> planTrainCourseList;
	private PlanTrainCourse planTrainCourse;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private IDemandApplyService iDemandApplyService;
	/** 培训讲师 */
	private Plan plan;

	private String id;
	private String pageNo;

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public PlanTrainCourseController getPlanTrainCourseController() {
		return planTrainCourseController;
	}

	public void setPlanTrainCourseController(PlanTrainCourseController planTrainCourseController) {
		this.planTrainCourseController = planTrainCourseController;
	}

	public List<PlanTrainCourse> getPlanTrainCourseList() {
		return planTrainCourseList;
	}

	public void setPlanTrainCourseList(List<PlanTrainCourse> planTrainCourseList) {
		this.planTrainCourseList = planTrainCourseList;
	}

	public PlanTrainCourse getPlanTrainCourse() {
		return planTrainCourse;
	}

	public void setPlanTrainCourse(PlanTrainCourse planTrainCourse) {
		this.planTrainCourse = planTrainCourse;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	@Override
	public String goList() {
		try {
			planTrainCourseList = planTrainCourseController.findPlanTrainCourseIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	public String goChooseCategory() {
		return "goChooseCategory";
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
			String planStartDate = getRequestParameter("planStartDate");
			if (planStartDate != null && !"".equals(planStartDate)) {
				params.put("planStartDate," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(planStartDate));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("planStartDate");
				getPager().setOrderBy("desc");
			}
			/* uploadPersonId包含当前登录人id */
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, employeeId);
			Pager pager = planTrainCourseController.goSingleList(params, getPager());
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
			/* 负责人 */
			String leadings = getRequestParameter("leadings");
			if (null != leadings && !"".equals(leadings)) {
				leadings = URLDecoder.decode(leadings, "utf-8");
			}
			/* 计划学时 */
			String planHours = getRequestParameter("planHours");
			if (null != planHours && !"".equals(planHours)) {
				planHours = URLDecoder.decode(planHours, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("leadings," + SearchCondition.ANYLIKE, leadings);
				pager = planTrainCourseController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != leadings && !"".equals(leadings)) {
					params.put("leadings," + SearchCondition.ANYLIKE, leadings);
				}
				if (null != planHours && !"".equals(planHours)) {
					params.put("planHours," + SearchCondition.ANYLIKE, planHours);
				}
				pager = planTrainCourseController.goSingleList(params, getPager());
			}
			logger.info("获取计划汇总搜索列表数据页");
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
				planTrainCourse = planTrainCourseController.doListEntityById(id);
			} else {
				planTrainCourse = new PlanTrainCourse();
				planTrainCourse.setIsTemp("1");
				planTrainCourse = planTrainCourseController.doSavePlanTrainCourse(planTrainCourse);
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
			if (StringUtils.isNotEmpty(planTrainCourse.getId()) && !"0".equals(planTrainCourse.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = planTrainCourse.getPlanHours();
			String py = ChnToPinYin.getPYString(title);
			planTrainCourse.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(planTrainCourse);
			planTrainCourse = planTrainCourseController.doSavePlanTrainCourse(planTrainCourse);
			planTrainCourse.setIsTemp("");

			this.planTrainCourse.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.planTrainCourse.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			planTrainCourse.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.planTrainCourse);
			logger.info("对计划汇总进行了修改！");
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
			PlanTrainCourse planTrainCourse = planTrainCourseController.doListEntityById(id);
			if (null != planTrainCourse) {
				planTrainCourseController.doDeleteByEntity(planTrainCourse);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除计划汇总");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	/*******************************************
	 * 填报计划
	 ********************************************************************************/
	/** 跳转到填报计划添加明细页面 */
	public String goChoosePlan() {
		try {
			String lineItemIdStr = getRequestParameter("lineItemId");
			if (null != lineItemIdStr) {
				plan = planTrainCourseController.findPlanById(lineItemIdStr);
			}
			logger.info("添加填报计划");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goChoosePlan";
	}

	/** 跳转到计划培训课程添加课程明细列表页面 */
	public String goPlanList() {
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
			Pager pager = planTrainCourseController.goPlanList(params, getPager());
			logger.info("获取选择培训课程的数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPlanList";
	}

	/**
	 * 
	 * 获取填报计划json数据
	 */
	public void getPlanJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				PlanTrainCourse tc = planTrainCourseController.findEntityById(id);
				if (tc != null) {
					json = convertListToJson(new ArrayList<Plan>(tc.getPlan()), tc.getPlan().size());
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
	 * 填报计划明细
	 * 
	 * @return
	 */
	public String saveOrUpdatePlan() {
		try {
			String tcIdStr = getRequestParameter("tcId");
			if (null != tcIdStr) {
				plan = planTrainCourseController.findPlanById(tcIdStr);
				planTrainCourse = planTrainCourseController.doListEntityById(id);
				plan.setPlanTrainCourse(planTrainCourse);
				planTrainCourseController.doSavePlan(plan);
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
	public String deletePlanById() {
		try {
			Plan plan = planTrainCourseController.doListPlanById(id);
			if (null != plan) {
				planTrainCourseController.deletePlanEntity(plan);
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
