package com.vix.hr.trainning.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.hr.trainning.domain.TrainingFacilitiesDomain;
import com.vix.hr.trainning.entity.TrainingFacilities;

@Scope("prototype")
@Controller("trainingfacilitiescontroller")
public class TrainingFacilitiesController extends BaseAction {

	private static final long serialVersionUID = 1L;
	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";
	Logger logger = Logger.getLogger("TrainingFacilitiesController");
	@Autowired
	private TrainingFacilitiesDomain trainingFacilitiesDomain;

	public TrainingFacilitiesDomain getTrainingFacilitiesDomain() {
		return trainingFacilitiesDomain;
	}

	public void setTrainingFacilitiesDomain(TrainingFacilitiesDomain trainingFacilitiesDomain) {
		this.trainingFacilitiesDomain = trainingFacilitiesDomain;
	}

	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainingFacilitiesDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public TrainingFacilities findEntityById(String id) throws Exception {
		return trainingFacilitiesDomain.findEntityById(id);
	}

	public TrainingFacilities merge(TrainingFacilities trainingFacilities) throws Exception {
		trainingFacilitiesDomain.merge(trainingFacilities);
		return null;
	}

	public void deleteByEntity(TrainingFacilities trainingFacilities) throws Exception {
		trainingFacilitiesDomain.deleteByEntity(trainingFacilities);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		trainingFacilitiesDomain.deleteByIds(ids);
	}

	public List<TrainingFacilities> findTrainingFacilitiesIndex() throws Exception {
		return trainingFacilitiesDomain.findTrainingFacilitiesIndex();
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

	// 删除
	public TrainingFacilities doListEntityById(String id) {
		TrainingFacilities trainingFacilities = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			trainingFacilities = trainingFacilitiesDomain.findEntityById(id);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询培训设施成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询培训设施失败！" + "失败原因:" + ex.getMessage());
		}

		return trainingFacilities;
	}

	// 保存
	public TrainingFacilities doSaveTrainingFacilities(TrainingFacilities trainingFacilities) throws Exception {
		TrainingFacilities trainingFacilities2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			trainingFacilities2 = trainingFacilitiesDomain.merge(trainingFacilities);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存培训设施：" + trainingFacilities + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存培训设施：" + trainingFacilities + "失败！失败原因：" + ex.getMessage());

			throw new Exception(ex.getMessage());
		}
		return trainingFacilities2;
	}

	/** 获取搜索列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainingFacilitiesDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}

	public Pager doSubSingleList(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = trainingFacilitiesDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询培训设施成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询培训设施失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}

	public void doDeleteByEntity(TrainingFacilities trainingFacilities) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			trainingFacilitiesDomain.deleteByEntity(trainingFacilities);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除培训设施成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除培训设施失败！失败原因：" + ex.getMessage());
		}
	}

	/**
	 * doSaveExecute实现删除培训设施的业务逻辑处理
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
			trainingFacilitiesDomain.deleteByIds(ids);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除培训设施成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除培训设施失败！" + "失败原因:" + ex.getMessage());
		}
	}
}
