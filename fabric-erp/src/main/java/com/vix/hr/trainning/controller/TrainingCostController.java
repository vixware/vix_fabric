package com.vix.hr.trainning.controller;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.hr.trainning.domain.TrainingCostDomain;
import com.vix.hr.trainning.entity.TrainingChannel;
import com.vix.hr.trainning.entity.TrainingCost;
import com.vix.hr.trainning.entity.TrainingData;
import com.vix.hr.trainning.entity.TrainingLecturer;

@Scope("prototype")
@Controller("trainingCostController")
public class TrainingCostController extends BaseAction {

	private static final long serialVersionUID = 1L;
	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";
	Logger logger = Logger.getLogger("TrainingCostController");
	@Autowired
	private TrainingCostDomain trainingCostDomain;

	public TrainingCostDomain getTrainingCostDomain() {
		return trainingCostDomain;
	}

	public void setTrainingCostDomain(TrainingCostDomain trainingCostDomain) {
		this.trainingCostDomain = trainingCostDomain;
	}

	public List<TrainingCost> findTrainingCostIndex() throws Exception {
		return trainingCostDomain.findTrainingCostIndex();
	}

	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainingCostDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainingCostDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}

	/** 删除 */
	public TrainingCost doListEntityById(String id) {
		TrainingCost trainingCost = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			trainingCost = trainingCostDomain.findEntityById(id);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询培训费用成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询培训费用失败！" + "失败原因:" + ex.getMessage());
		}
		return trainingCost;
	}

	/** 保存 */
	public TrainingCost doSaveTrainingCost(TrainingCost trainingCost) throws Exception {
		TrainingCost trainingCosts = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			trainingCosts = trainingCostDomain.merge(trainingCost);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存培训费用：" + trainingCost + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存培训费用：" + trainingCost + "失败！失败原因：" + ex.getMessage());

			throw new Exception(ex.getMessage());
		}
		return trainingCosts;
	}

	/** 删除培训费用 */
	public void doDeleteByEntity(TrainingCost trainingCost) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			trainingCostDomain.deleteByEntity(trainingCost);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除培训费用成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除培训费用失败！失败原因：" + ex.getMessage());
		}
	}

	/******************************************************************
	 * 培训讲师
	 ********************************************************************************/
	/** 获取课程列表数据 */
	public Pager goSingleLists(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainingCostDomain.findPagerByHqlConditionss(params, pager);
		return p;
	}

	/** 获取资源列表数据 */
	public Pager goSingleListss(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainingCostDomain.findPagerByHqlConditionsss(params, pager);
		return p;
	}

	/** 获取设施列表数据 */
	public Pager goSingleListsss(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainingCostDomain.findPagerByHqlConditionssss(params, pager);
		return p;
	}

	public TrainingCost findEntityById(String id) throws Exception {
		return trainingCostDomain.findEntityById(id);
	}

	public TrainingCost merge(TrainingCost trainingCost) throws Exception {
		trainingCostDomain.merge(trainingCost);
		return null;
	}

	public void deleteByEntity(TrainingCost trainingCost) throws Exception {
		trainingCostDomain.deleteByEntity(trainingCost);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		trainingCostDomain.deleteByIds(ids);
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

	public Pager doSubSingleList(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = trainingCostDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询培训课程成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询培训课程失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}

	/**
	 * doSaveExecute实现删除培训课程的业务逻辑处理
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
			trainingCostDomain.deleteByIds(ids);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除培训课程成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除培训课程失败！" + "失败原因:" + ex.getMessage());
		}
	}

	public void deleteTrainingLecturerEntity(TrainingLecturer trainingLecturer) throws Exception {
		trainingCostDomain.deleteTrainingLecturerEntity(trainingLecturer);
	}

	/** 获取培训讲师明细 */
	public TrainingLecturer doListTrainingLecturerById(String id) {
		TrainingLecturer detailsTemp = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			detailsTemp = trainingCostDomain.findTrainingLecturerById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询培训讲师成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询培训讲师失败！" + "失败原因:" + ex.getMessage());
		}

		return detailsTemp;
	}

	// 培训讲师明细
	public TrainingLecturer doSaveTrainingLecturer(TrainingLecturer trainingLecturer) throws Exception {
		TrainingLecturer trainingLecturer2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			trainingLecturer2 = trainingCostDomain.merge(trainingLecturer);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存培训讲师：" + trainingLecturer + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存培训讲师：" + trainingLecturer + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存培训讲师失败", ex);
		}
		return trainingLecturer2;
	}

	public TrainingLecturer findTrainingLecturerById(String id) throws Exception {
		return trainingCostDomain.findTrainingLecturerById(id);
	}

	/*******************************************
	 * 资料
	 ********************************************************************************/
	public void deleteTrainingChannelEntity(TrainingChannel trainingChannel) throws Exception {
		trainingCostDomain.deleteTrainingChannelEntity(trainingChannel);
	}

	/** 获取资料明细 */
	public TrainingChannel doListTrainingChannelById(String id) {
		TrainingChannel detailsTemp = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			detailsTemp = trainingCostDomain.findTrainingChannelById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}

		return detailsTemp;
	}

	/** 资料明细 */
	public TrainingChannel doSaveTrainingChannel(TrainingChannel trainingChannel) throws Exception {
		TrainingChannel trainingChannels = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			trainingChannels = trainingCostDomain.merge(trainingChannel);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + trainingChannel + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + trainingChannel + "失败！失败原因：" + ex.getMessage());

		}
		return trainingChannels;
	}

	public TrainingChannel findTrainingChannelById(String id) throws Exception {
		return trainingCostDomain.findTrainingChannelById(id);
	}

	/*******************************************
	 * 设施
	 ********************************************************************************/
	public void deleteTrainingDataEntity(TrainingData trainingData) throws Exception {
		trainingCostDomain.deleteTrainingDataEntity(trainingData);
	}

	/** 获取设施明细 */
	public TrainingData doListTrainingDataById(String id) {
		TrainingData detailsTemp = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			detailsTemp = trainingCostDomain.findTrainingDataById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}

		return detailsTemp;
	}

	/** 培训资料 */
	public TrainingData doSaveTrainingData(TrainingData trainingData) throws Exception {
		TrainingData trainingDatas = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			trainingDatas = trainingCostDomain.merge(trainingData);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存培训资料：" + trainingData + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存培训资料：" + trainingData + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存培训资料失败", ex);
		}
		return trainingDatas;
	}

	public TrainingData findTrainingDataaById(String id) throws Exception {
		return trainingCostDomain.findTrainingDataById(id);
	}
}
