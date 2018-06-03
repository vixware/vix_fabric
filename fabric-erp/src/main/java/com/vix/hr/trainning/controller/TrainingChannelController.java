package com.vix.hr.trainning.controller;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.hr.trainning.domain.TrainingChannelDomain;
import com.vix.hr.trainning.entity.TrainingChannel;
import com.vix.hr.trainning.entity.TrainingLecturer;

@Scope("prototype")
@Controller("trainingChannelController")
public class TrainingChannelController extends BaseAction {

	private static final long serialVersionUID = 1L;
	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";
	Logger logger = Logger.getLogger("TrainingChannelController");
	@Autowired
	private TrainingChannelDomain trainingChannelDomain;

	public TrainingChannelDomain getTrainingChannelDomain() {
		return trainingChannelDomain;
	}

	public void setTrainingChannelDomain(TrainingChannelDomain trainingChannelDomain) {
		this.trainingChannelDomain = trainingChannelDomain;
	}

	public List<TrainingChannel> findTrainingChannelIndex() throws Exception {
		return trainingChannelDomain.findTrainingChannelIndex();
	}

	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainingChannelDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	/** 获取列表数据 */
	public Pager goSingleList2(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainingChannelDomain.findPagerByHqlConditions2(params, pager);
		return p;
	}

	public TrainingChannel findEntityById(String id) throws Exception {
		return trainingChannelDomain.findEntityById(id);
	}

	public TrainingChannel merge(TrainingChannel trainingChannel) throws Exception {
		trainingChannelDomain.merge(trainingChannel);
		return null;
	}

	public void deleteByEntity(TrainingChannel trainingChannel) throws Exception {
		trainingChannelDomain.deleteByEntity(trainingChannel);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		trainingChannelDomain.deleteByIds(ids);
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
	public TrainingChannel doListEntityById(String id) {

		TrainingChannel trainingChannel = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			trainingChannel = trainingChannelDomain.findEntityById(id);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询培训渠道信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询培训渠道信息失败！" + "失败原因:" + ex.getMessage());
		}

		return trainingChannel;
	}

	// 保存
	public TrainingChannel doSaveTrainingChannel(TrainingChannel trainingChannel) throws Exception {
		TrainingChannel trainingChannel2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			trainingChannel2 = trainingChannelDomain.merge(trainingChannel);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存培训渠道：" + trainingChannel + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存培训渠道：" + trainingChannel + "失败！失败原因：" + ex.getMessage());

			throw new Exception(ex.getMessage());
		}
		return trainingChannel2;
	}

	/** 获取搜索列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainingChannelDomain.findPagerByOrHqlConditions(params, pager);
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
			p = trainingChannelDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询培训渠道信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询培训渠道信息失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}

	public void doDeleteByEntity(TrainingChannel trainingChannel) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			trainingChannelDomain.deleteByEntity(trainingChannel);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除培训渠道信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除培训渠道信息失败！失败原因：" + ex.getMessage());
		}
	}

	/**
	 * doSaveExecute实现删除培训渠道的业务逻辑处理
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
			trainingChannelDomain.deleteByIds(ids);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除培训渠道信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除培训渠道信息失败！" + "失败原因:" + ex.getMessage());
		}
	}

	public void deleteTrainingLecturerEntity(TrainingLecturer trainingLecturer) throws Exception {
		trainingChannelDomain.deleteTrainingLecturerEntity(trainingLecturer);
	}

	/** 获取讲师明细 */
	public TrainingLecturer doListTrainingLecturerById(String id) {
		TrainingLecturer detailsTemp = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			detailsTemp = trainingChannelDomain.findTrainingLecturerById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询培训讲师信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询培训讲师信息失败！" + "失败原因:" + ex.getMessage());
		}

		return detailsTemp;
	}

	// 讲师明细
	public TrainingLecturer doSaveTrainingLecturer(TrainingLecturer trainingLecturer) throws Exception {
		TrainingLecturer trainingLecturer2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			trainingLecturer2 = trainingChannelDomain.merge(trainingLecturer);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存讲师：" + trainingLecturer + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存讲师：" + trainingLecturer + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存讲师失败", ex);
		}
		return trainingLecturer2;
	}

	public TrainingChannel doSaveTrainingChannel(TrainingChannel trainingChannel, List<TrainingLecturer> dlList) throws Exception {

		TrainingChannel trainingChannel2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			if (null != trainingChannel) {
				trainingChannel2 = trainingChannelDomain.merge(trainingChannel);
				if (null != dlList) {
					for (TrainingLecturer da : dlList) {
						if (null != da) {
							da.setTrainingChannel(trainingChannel2);
							trainingChannelDomain.merge(da);
						}
					}
				}
			}
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存培训渠道：" + trainingChannel + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存培训渠道：" + trainingChannel + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存培训渠道失败", ex);
		}
		return trainingChannel2;

	}

	public TrainingLecturer findTrainingLecturerById(String id) throws Exception {
		return trainingChannelDomain.findTrainingLecturerById(id);
	}
}
