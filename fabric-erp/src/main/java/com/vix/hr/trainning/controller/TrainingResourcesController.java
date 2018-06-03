package com.vix.hr.trainning.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.hr.trainning.domain.TrainingResourcesDomain;
import com.vix.hr.trainning.entity.TrainingCourse;
import com.vix.hr.trainning.entity.TrainingData;
import com.vix.hr.trainning.entity.TrainingFacilities;
import com.vix.hr.trainning.entity.TrainingLecturer;
import com.vix.hr.trainning.entity.TrainingResources;

@Scope("prototype")
@Controller("trainingresourcescontroller")
public class TrainingResourcesController extends BaseAction {

	private static final long serialVersionUID = 1L;
	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";
	Logger logger = Logger.getLogger("TrainingResourcesController");
	@Autowired
	private TrainingResourcesDomain trainingResourcesDomain;

	public TrainingResourcesDomain getTrainingResourcesDomain() {
		return trainingResourcesDomain;
	}

	public void setTrainingResourcesDomain(TrainingResourcesDomain trainingResourcesDomain) {
		this.trainingResourcesDomain = trainingResourcesDomain;
	}

	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		if (params.containsKey("name,anylike")) {
			if ("undefined".equals(params.get("name,anylike"))) {
				params.remove("name,anylike");
			}
		}
		Pager p = trainingResourcesDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public TrainingResources findEntityById(String id) throws Exception {
		return trainingResourcesDomain.findEntityById(id);
	}

	public TrainingResources merge(TrainingResources trainingResources) throws Exception {
		trainingResourcesDomain.merge(trainingResources);
		return null;
	}

	public void deleteByEntity(TrainingResources trainingResources) throws Exception {
		trainingResourcesDomain.deleteByEntity(trainingResources);
	}

	/** 删除培训课程 */
	public void deleteTrainingCourseEntity(TrainingCourse trainingCourse) throws Exception {
		trainingResourcesDomain.deleteTrainingCourseEntity(trainingCourse);
	}

	/** 删除培训资料 */
	public void deleteTrainingDataEntity(TrainingData trainingData) throws Exception {
		trainingResourcesDomain.deleteTrainingDataEntity(trainingData);
	}

	/** 删除培训设施 */
	public void deleteTrainingFacilitiesEntity(TrainingFacilities trainingFacilities) throws Exception {
		trainingResourcesDomain.deleteTrainingFacilitiesEntity(trainingFacilities);
	}

	/** 删除培训教师 */
	public void deleteTrainingLecturerEntity(TrainingLecturer trainingLecturer) throws Exception {
		trainingResourcesDomain.deleteTrainingLecturerEntity(trainingLecturer);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		trainingResourcesDomain.deleteByIds(ids);
	}

	public List<TrainingResources> findTrainingResourcesIndex() throws Exception {
		return trainingResourcesDomain.findTrainingResourcesIndex();
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

	/**
	 * beforeEventTrigger 用于触发在执行业务逻辑前的事件产生, 将产生一个业务对象执行动作的before事件.
	 * 使用Esper引擎处理,事件体执行过程为异步.afterEventTrigger采用相同的处理方式.
	 * 
	 * @param eventName
	 *            根据业务对象来定义
	 */
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

	/** 获取培训资源 */
	public TrainingResources doListEntityById(String id) {
		TrainingResources trainingResources = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			trainingResources = trainingResourcesDomain.findEntityById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}

		return trainingResources;
	}

	/** 获取培训课程明细 */
	public TrainingCourse doListTrainingCourseById(String id) {
		TrainingCourse trainingCourse = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			trainingCourse = trainingResourcesDomain.findTrainingCourseById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}

		return trainingCourse;
	}

	/** 获取培训资料明细 */
	public TrainingData doListTrainingDataById(String id) {
		TrainingData trainingData = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			trainingData = trainingResourcesDomain.findTrainingDataById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}

		return trainingData;
	}

	/** 获取培训设施明细 */
	public TrainingFacilities doListTrainingFacilitiesById(String id) {
		TrainingFacilities trainingFacilities = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			trainingFacilities = trainingResourcesDomain.findTrainingFacilitiesById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}

		return trainingFacilities;
	}

	/** 获取培训教师明细 */
	public TrainingLecturer doListTrainingLecturerById(String id) {
		TrainingLecturer trainingLecturer = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			trainingLecturer = trainingResourcesDomain.findTrainingLecturerById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}

		return trainingLecturer;
	}

	/** 保存培训课程明细 */
	public TrainingCourse doSaveTrainingCourse(TrainingCourse trainingCourse) throws Exception {
		TrainingCourse trainingCourse2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			trainingCourse2 = trainingResourcesDomain.merge(trainingCourse);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + trainingCourse + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + trainingCourse + "失败！失败原因：" + ex.getMessage());

		}
		return trainingCourse2;
	}

	/** 保存培训资料明细 */
	public TrainingData doSaveTrainingData(TrainingData trainingData) throws Exception {
		TrainingData trainingData2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			trainingData2 = trainingResourcesDomain.merge(trainingData);
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

	/** 保存培训设施明细 */
	public TrainingFacilities doSaveTrainingFacilities(TrainingFacilities trainingFacilities) throws Exception {
		TrainingFacilities trainingFacilities2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			trainingFacilities2 = trainingResourcesDomain.merge(trainingFacilities);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + trainingFacilities + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + trainingFacilities + "失败！失败原因：" + ex.getMessage());

		}
		return trainingFacilities2;
	}

	/** 保存培训教师明细 */
	public TrainingLecturer doSaveTrainingLecturer(TrainingLecturer trainingLecturer) throws Exception {
		TrainingLecturer trainingLecturer2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			trainingLecturer2 = trainingResourcesDomain.merge(trainingLecturer);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + trainingLecturer + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + trainingLecturer + "失败！失败原因：" + ex.getMessage());

		}
		return trainingLecturer2;
	}

	/** 处理保存培训资源 */
	public TrainingResources doSaveTrainingResources(TrainingResources trainingResources) {
		TrainingResources trainingResources2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			trainingResources2 = trainingResourcesDomain.merge(trainingResources);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + trainingResources + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + trainingResources + "失败！失败原因：" + ex.getMessage());
		}
		return trainingResources2;
	}

	/** 保存培训课程List */
	public TrainingResources doSaveTrainingResources(TrainingResources trainingResources, List<TrainingCourse> dlList, List<TrainingData> tdList, List<TrainingFacilities> tfList, List<TrainingLecturer> ttList) throws Exception {

		TrainingResources trainingResources2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			if (null != trainingResources) {
				trainingResources2 = trainingResourcesDomain.merge(trainingResources);
				if (null != dlList) {
					for (TrainingCourse da : dlList) {
						if (null != da) {
							da.setTrainingResources(trainingResources);
							trainingResourcesDomain.merge(da);
						}
					}
				}
				if (null != tdList) {
					for (TrainingData td : tdList) {
						if (null != td) {
							td.setTrainingResources(trainingResources);
							trainingResourcesDomain.merge(td);
						}
					}
				}
				if (null != tfList) {
					for (TrainingFacilities tf : tfList) {
						if (null != tf) {
							tf.setTrainingResources(trainingResources);
							trainingResourcesDomain.merge(tf);
						}
					}
				}
				if (null != ttList) {
					for (TrainingLecturer tt : ttList) {
						if (null != tt) {
							tt.setTrainingResources(trainingResources);
							trainingResourcesDomain.merge(tt);
						}
					}
				}
			}
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + trainingResources + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + trainingResources + "失败！失败原因：" + ex.getMessage());

		}
		return trainingResources2;

	}

	/** 获取搜索列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainingResourcesDomain.findPagerByOrHqlConditions(params, pager);
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
			p = trainingResourcesDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}
		return p;
	}

	public void doDeleteByEntity(TrainingResources trainingResources) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			trainingResourcesDomain.deleteByEntity(trainingResources);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}
	}

	/**
	 * 
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
			trainingResourcesDomain.deleteByIds(ids);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {
			executeLogger(true, "删除订单信息失败！" + "失败原因:" + ex.getMessage());
		}
	}
}
