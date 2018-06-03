package com.vix.hr.trainning.controller;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.hr.trainning.domain.PlanTrainCourseDomain;
import com.vix.hr.trainning.entity.Plan;
import com.vix.hr.trainning.entity.PlanTrainCourse;

@Scope("prototype")
@Controller("planTrainCourseController")
public class PlanTrainCourseController extends BaseAction {

	private static final long serialVersionUID = 1L;
	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";
	Logger logger = Logger.getLogger("DemandApplyController");
	@Autowired
	private PlanTrainCourseDomain planTrainCourseDomain;

	public PlanTrainCourseDomain getPlanTrainCourseDomain() {
		return planTrainCourseDomain;
	}

	public void setPlanTrainCourseDomain(PlanTrainCourseDomain planTrainCourseDomain) {
		this.planTrainCourseDomain = planTrainCourseDomain;
	}

	public List<PlanTrainCourse> findPlanTrainCourseIndex() throws Exception {
		return planTrainCourseDomain.findPlanTrainCourseIndex();
	}

	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = planTrainCourseDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = planTrainCourseDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}

	public PlanTrainCourse doListEntityById(String id) {
		PlanTrainCourse planTrainCourse = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			planTrainCourse = planTrainCourseDomain.findEntityById(id);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询计划培训课程信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询计划培训课程信息失败！" + "失败原因:" + ex.getMessage());
		}

		return planTrainCourse;
	}

	// 保存
	public PlanTrainCourse doSavePlanTrainCourse(PlanTrainCourse planTrainCourse) throws Exception {
		PlanTrainCourse planTrainCourse2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			planTrainCourse2 = planTrainCourseDomain.merge(planTrainCourse);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存计划培训课程：" + planTrainCourse + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存计划培训课程：" + planTrainCourse + "失败！失败原因：" + ex.getMessage());

			throw new Exception(ex.getMessage());
		}
		return planTrainCourse2;
	}

	public Pager doSubSingleList(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = planTrainCourseDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询计划培训课程成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询计划培训课程失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}

	public void doDeleteByEntity(PlanTrainCourse planTrainCourse) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			planTrainCourseDomain.deleteByEntity(planTrainCourse);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除计划培训课程成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除计划培训课程失败！失败原因：" + ex.getMessage());
		}
	}

	/**
	 * doSaveExecute实现删除计划培训课程的业务逻辑处理
	 * 
	 * @param ids
	 */
	public void doDeleteByIds(List<String> ids) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			planTrainCourseDomain.deleteByIds(ids);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除计划培训课程成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除计划培训课程失败！" + "失败原因:" + ex.getMessage());
		}
	}

	public PlanTrainCourse merge(PlanTrainCourse planTrainCourse) throws Exception {
		planTrainCourseDomain.merge(planTrainCourse);
		return null;
	}

	public void deleteByEntity(PlanTrainCourse planTrainCourse) throws Exception {
		planTrainCourseDomain.deleteByEntity(planTrainCourse);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		planTrainCourseDomain.deleteByIds(ids);
	}

	public PlanTrainCourse findEntityById(String id) throws Exception {
		return planTrainCourseDomain.findEntityById(id);
	}

	/*******************************************
	 * 填报计划
	 ********************************************************************************/

	public Plan findPlanById(String id) throws Exception {
		return planTrainCourseDomain.findPlanById(id);
	}

	/* 培训课程明细 */
	public Plan doSavePlan(Plan plan) throws Exception {
		Plan plans = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			plans = planTrainCourseDomain.merge(plan);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存填报计划：" + plan + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存填报计划：" + plan + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存填报计划失败", ex);
		}
		return plans;
	}

	/** 获取填报计划列表数据 */
	public Pager goPlanList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = planTrainCourseDomain.findPagerByPlan(params, pager);
		return p;
	}

	/** 获取填报计划明细 */
	public Plan doListPlanById(String id) {
		Plan detailsTemp = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			detailsTemp = planTrainCourseDomain.findPlanById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询填报计划成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询填报计划失败！" + "失败原因:" + ex.getMessage());
		}

		return detailsTemp;
	}

	public void deletePlanEntity(Plan plan) throws Exception {
		planTrainCourseDomain.deletePlanEntity(plan);
	}

	/**
	 * 输入信息到日志文件中,可以考虑是用Helper类提供服务.
	 * 
	 * @param message
	 */
	public void executeLogger(boolean isShow, String... message) {
		if (isShow) {
			logger.info(message);
		}
	}

	private void beforeEventTrigger(String eventName) {
		// to do something
	}

	/**
	 * afterEventTrigger 用于触发在执行业务逻辑后的事件产生, 将产生一个业务对象执行动作的after事件.
	 * 
	 * @param eventName
	 *            根据业务对象来定义
	 */
	private void afterEventTrigger(String eventName) {
		// to do something
	}
}
