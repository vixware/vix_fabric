package com.vix.hr.trainning.controller;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.hr.trainning.domain.TrainingActivityDomain;
import com.vix.hr.trainning.entity.TrainingActivity;
import com.vix.hr.trainning.entity.TrainingPlanningDetail;

@Scope("prototype")
@Controller("trainingActivityController")
public class TrainingActivityController extends BaseAction {

	private static final long serialVersionUID = 1L;
	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";
	Logger logger = Logger.getLogger("DemandApplyController");
	@Autowired
	private TrainingActivityDomain trainingActivityDomain;

	public TrainingActivityDomain getTrainingActivityDomain() {
		return trainingActivityDomain;
	}

	public void setTrainingActivityDomain(TrainingActivityDomain trainingActivityDomain) {
		this.trainingActivityDomain = trainingActivityDomain;
	}

	public List<TrainingActivity> findTrainingActivityIndex() throws Exception {
		return trainingActivityDomain.findTrainingActivityIndex();
	}

	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainingActivityDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainingActivityDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}

	public TrainingActivity doListEntityById(String id) {
		TrainingActivity trainingActivity = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			trainingActivity = trainingActivityDomain.findEntityById(id);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询计划培训计划明细信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询计划培训计划明细信息失败！" + "失败原因:" + ex.getMessage());
		}

		return trainingActivity;
	}

	// 保存
	public TrainingActivity doSaveTrainingActivity(TrainingActivity trainingActivity) throws Exception {
		TrainingActivity trainingActivity2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			trainingActivity2 = trainingActivityDomain.merge(trainingActivity);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存计划培训计划明细：" + trainingActivity + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存计划培训计划明细：" + trainingActivity + "失败！失败原因：" + ex.getMessage());

			throw new Exception(ex.getMessage());
		}
		return trainingActivity2;
	}

	public Pager doSubSingleList(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = trainingActivityDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询计划培训计划明细成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询计划培训计划明细失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}

	public void doDeleteByEntity(TrainingActivity trainingActivity) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			trainingActivityDomain.deleteByEntity(trainingActivity);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除计划培训计划明细成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除计划培训计划明细失败！失败原因：" + ex.getMessage());
		}
	}

	/**
	 * doSaveExecute实现删除计划培训计划明细的业务逻辑处理
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
			trainingActivityDomain.deleteByIds(ids);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除计划培训计划明细成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除计划培训计划明细失败！" + "失败原因:" + ex.getMessage());
		}
	}

	public TrainingActivity merge(TrainingActivity trainingActivity) throws Exception {
		trainingActivityDomain.merge(trainingActivity);
		return null;
	}

	public void deleteByEntity(TrainingActivity trainingActivity) throws Exception {
		trainingActivityDomain.deleteByEntity(trainingActivity);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		trainingActivityDomain.deleteByIds(ids);
	}

	public TrainingActivity findEntityById(String id) throws Exception {
		return trainingActivityDomain.findEntityById(id);
	}

	/*******************************************
	 * 培训计划明细
	 ********************************************************************************/

	public TrainingPlanningDetail findTrainingPlanningDetailById(String id) throws Exception {
		return trainingActivityDomain.findTrainingPlanningDetailById(id);
	}

	/* 培训计划明细明细 */
	public TrainingPlanningDetail doSaveTrainingPlanningDetail(TrainingPlanningDetail trainingPlanningDetail) throws Exception {
		TrainingPlanningDetail trainingPlanningDetail2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			trainingPlanningDetail2 = trainingActivityDomain.merge(trainingPlanningDetail);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存培训计划明细：" + trainingPlanningDetail + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存培训计划明细：" + trainingPlanningDetail + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存培训计划明细失败", ex);
		}
		return trainingPlanningDetail2;
	}

	/** 获取计划明细列表数据 */
	public Pager goTrainingPlanningDetailList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainingActivityDomain.findPagerByTrainingPlanningDetail(params, pager);
		return p;
	}

	/** 获取培训计划明细明细 */
	public TrainingPlanningDetail doListTrainingPlanningDetailById(String id) {
		TrainingPlanningDetail detailsTemp = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			detailsTemp = trainingActivityDomain.findTrainingPlanningDetailById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询培训计划明细成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询培训计划明细失败！" + "失败原因:" + ex.getMessage());
		}

		return detailsTemp;
	}

	public void deleteTrainingPlanningDetailEntity(TrainingPlanningDetail trainingPlanningDetail) throws Exception {
		trainingActivityDomain.deleteTrainingPlanningDetailEntity(trainingPlanningDetail);
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
