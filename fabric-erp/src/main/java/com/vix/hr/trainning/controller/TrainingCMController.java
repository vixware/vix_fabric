package com.vix.hr.trainning.controller;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.hr.trainning.domain.TrainingCMDomain;
import com.vix.hr.trainning.entity.TrainingCM;
import com.vix.hr.trainning.entity.TrainingChannel;
import com.vix.hr.trainning.entity.TrainingData;
import com.vix.hr.trainning.entity.TrainingLecturer;

@Scope("prototype")
@Controller("trainingCMController")
public class TrainingCMController extends BaseAction {

	private static final long serialVersionUID = 1L;
	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";
	Logger logger = Logger.getLogger("TrainingCMController");
	@Autowired
	private TrainingCMDomain trainingCMDomain;

	public TrainingCMDomain getTrainingCMDomain() {
		return trainingCMDomain;
	}

	public void setTrainingCMDomain(TrainingCMDomain trainingCMDomain) {
		this.trainingCMDomain = trainingCMDomain;
	}

	/** 获取列表数据 */
	public Pager goSingleList2(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainingCMDomain.findPagerByHqlConditions2(params, pager);
		return p;
	}

	/** 获取课程列表数据 */
	public Pager goSingleList3(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainingCMDomain.findPagerByHqlConditions3(params, pager);
		return p;
	}

	/** 获取资源列表数据 */
	public Pager goSingleList4(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainingCMDomain.findPagerByHqlConditions4(params, pager);
		return p;
	}

	/** 获取设施列表数据 */
	public Pager goSingleList5(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainingCMDomain.findPagerByHqlConditions5(params, pager);
		return p;
	}

	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainingCMDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public TrainingCM findEntityById(String id) throws Exception {
		return trainingCMDomain.findEntityById(id);
	}

	public TrainingCM merge(TrainingCM trainingCM) throws Exception {
		trainingCMDomain.merge(trainingCM);
		return null;
	}

	public void deleteByEntity(TrainingCM trainingCM) throws Exception {
		trainingCMDomain.deleteByEntity(trainingCM);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		trainingCMDomain.deleteByIds(ids);
	}

	public List<TrainingCM> findTrainingCMIndex() throws Exception {
		return trainingCMDomain.findTrainingCMIndex();
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
	public TrainingCM doListEntityById(String id) {

		TrainingCM trainingCM = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			trainingCM = trainingCMDomain.findEntityById(id);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询培训课程信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询培训课程信息失败！" + "失败原因:" + ex.getMessage());
		}

		return trainingCM;
	}

	// 保存
	public TrainingCM doSaveTrainingCM(TrainingCM trainingCM) throws Exception {
		TrainingCM trainingCM2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			trainingCM2 = trainingCMDomain.merge(trainingCM);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存培训课程：" + trainingCM + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存培训课程：" + trainingCM + "失败！失败原因：" + ex.getMessage());

			throw new Exception(ex.getMessage());
		}
		return trainingCM2;
	}

	/** 获取搜索列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainingCMDomain.findPagerByOrHqlConditions(params, pager);
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
			p = trainingCMDomain.findPagerByHqlConditions(params, pager);

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

	public void doDeleteByEntity(TrainingCM trainingCM) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			trainingCMDomain.deleteByEntity(trainingCM);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除培训课程成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除培训课程失败！失败原因：" + ex.getMessage());
		}
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
			trainingCMDomain.deleteByIds(ids);
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
		trainingCMDomain.deleteTrainingLecturerEntity(trainingLecturer);
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
			detailsTemp = trainingCMDomain.findTrainingLecturerById(id);
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
			trainingLecturer2 = trainingCMDomain.merge(trainingLecturer);
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

	public TrainingCM doSaveTrainingCM(TrainingCM trainingCM, List<TrainingLecturer> dlList) throws Exception {
		TrainingCM trainingCM2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			if (null != trainingCM) {
				trainingCM2 = trainingCMDomain.merge(trainingCM);
				if (null != dlList) {
					for (TrainingLecturer da : dlList) {
						if (null != da) {
							da.setTrainingCM(trainingCM2);
							trainingCMDomain.merge(da);
						}
					}
				}
			}
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存培训课程：" + trainingCM + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存培训课程：" + trainingCM + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存培训课程失败", ex);
		}
		return trainingCM2;

	}

	public TrainingLecturer findTrainingLecturerById(String id) throws Exception {
		return trainingCMDomain.findTrainingLecturerById(id);
	}

	/*******************************************
	 * 资料
	 ********************************************************************************/
	public void deleteTrainingChannelEntity(TrainingChannel trainingChannel) throws Exception {
		trainingCMDomain.deleteTrainingChannelEntity(trainingChannel);
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
			detailsTemp = trainingCMDomain.findTrainingChannelById(id);
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
		TrainingChannel trainingChannel2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			trainingChannel2 = trainingCMDomain.merge(trainingChannel);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + trainingChannel + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + trainingChannel + "失败！失败原因：" + ex.getMessage());

		}
		return trainingChannel2;
	}

	public TrainingChannel findTrainingChannelById(String id) throws Exception {
		return trainingCMDomain.findTrainingChannelById(id);
	}

	/*******************************************
	 * 设施
	 ********************************************************************************/
	public void deleteTrainingDataEntity(TrainingData trainingData) throws Exception {
		trainingCMDomain.deleteTrainingDataEntity(trainingData);
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
			detailsTemp = trainingCMDomain.findTrainingDataById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}

		return detailsTemp;
	}

	/** 设施明细 */
	public TrainingData doSaveTrainingData(TrainingData trainingData) throws Exception {
		TrainingData trainingData2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			trainingData2 = trainingCMDomain.merge(trainingData);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + trainingData + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + trainingData + "失败！失败原因：" + ex.getMessage());

		}
		return trainingData2;
	}

	public TrainingData findTrainingDataaById(String id) throws Exception {
		return trainingCMDomain.findTrainingDataById(id);
	}
}
