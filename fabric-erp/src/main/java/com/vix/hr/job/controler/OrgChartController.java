package com.vix.hr.job.controler;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.hr.trainning.domain.DemandApplyDomain;
import com.vix.hr.trainning.entity.DemandApply;
import com.vix.hr.trainning.entity.TrainingCourse;
import com.vix.hr.trainning.entity.TrainingData;
import com.vix.hr.trainning.entity.TrainingFacilities;

@Scope("prototype")
@Controller("orgchartcontroller")
public class OrgChartController extends BaseAction {

	private static final long serialVersionUID = 1L;
	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";
	Logger logger = Logger.getLogger("OrgChartController");
	@Autowired
	private DemandApplyDomain demandApplicationDomain;

	public DemandApplyDomain getDemandApplicationDomain() {
		return demandApplicationDomain;
	}

	public void setDemandApplicationDomain(DemandApplyDomain demandApplicationDomain) {
		this.demandApplicationDomain = demandApplicationDomain;
	}

	/** 获取列表数据 */
	public Pager goSingleList2(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = demandApplicationDomain.findPagerByHqlConditions2(params, pager);
		return p;
	}

	/** 获取课程列表数据 */
	public Pager goSingleList3(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = demandApplicationDomain.findPagerByHqlConditions3(params, pager);
		return p;
	}

	/** 获取资源列表数据 */
	/*
	 * public Pager goSingleList4(Map<String, Object> params, Pager pager)
	 * throws Exception { Pager p =
	 * demandApplicationDomain.findPagerByHqlConditions4(params, pager); return
	 * p; }
	 */

	/** 获取设施列表数据 */
	/*
	 * public Pager goSingleList5(Map<String, Object> params, Pager pager)
	 * throws Exception { Pager p =
	 * demandApplicationDomain.findPagerByHqlConditions5(params, pager); return
	 * p; }
	 */

	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = demandApplicationDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public DemandApply findEntityById(String id) throws Exception {
		return demandApplicationDomain.findEntityById(id);
	}

	public DemandApply merge(DemandApply demandApply) throws Exception {
		demandApplicationDomain.merge(demandApply);
		return null;
	}

	public void deleteByEntity(DemandApply demandApply) throws Exception {
		demandApplicationDomain.deleteByEntity(demandApply);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		demandApplicationDomain.deleteByIds(ids);
	}

	/*
	 * public List<DemandApply> findDemandApplyIndex() throws Exception { return
	 * demandApplicationDomain.findDemandApplyIndex(); }
	 */

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
	public DemandApply doListEntityById(String id) {

		DemandApply demandApply = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			demandApply = demandApplicationDomain.findEntityById(id);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}

		return demandApply;
	}

	// 保存
	public DemandApply doSaveDemandApply(DemandApply demandApply) throws Exception {
		DemandApply demandApply2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			demandApply2 = demandApplicationDomain.merge(demandApply);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + demandApply + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + demandApply + "失败！失败原因：" + ex.getMessage());

			throw new Exception(ex.getMessage());
		}
		return demandApply2;
	}

	/** 获取搜索列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = demandApplicationDomain.findPagerByOrHqlConditions(params, pager);
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
			p = demandApplicationDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}
		return p;
	}

	public void doDeleteByEntity(DemandApply demandApply) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			demandApplicationDomain.deleteByEntity(demandApply);
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
			demandApplicationDomain.deleteByIds(ids);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {
			executeLogger(true, "删除订单信息失败！" + "失败原因:" + ex.getMessage());
		}
	}

	/*
	 * public void deleteTrainingCourseEntity(TrainingCourse trainingCourse)
	 * throws Exception {
	 * demandApplicationDomain.deleteTrainingCourseEntity(trainingCourse); }
	 */

	/** 获取课程明细 */
	/*
	 * public TrainingCourse doListTrainingCourseById(String id) {
	 * TrainingCourse detailsTemp = null;
	 * 
	 * try { // 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载； // Map
	 * parameters = baExecutor.executeParameterProcess();
	 * 
	 * // 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
	 * beforeEventTrigger("PO_CREATE_BEFORE");
	 * 
	 * // 3.执行查询操作 detailsTemp =
	 * demandApplicationDomain.findTrainingCourseById(id); // 4. 触发事件
	 * afterEventSaveOrder(parameter); 抽象方法，需要重载；
	 * afterEventTrigger("PO_CREATE_AFTER");
	 * 
	 * // 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
	 * 
	 * // 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
	 * 
	 * } catch (Exception ex) {
	 * 
	 * }
	 * 
	 * return detailsTemp; }
	 */

	// 课程明细
	public TrainingCourse doSaveTrainingCourse(TrainingCourse trainingCourse) throws Exception {
		TrainingCourse trainingCourse2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			trainingCourse2 = demandApplicationDomain.merge(trainingCourse);
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

	public DemandApply doSaveDemandApply(DemandApply demandApply, List<TrainingCourse> dlList, List<TrainingData> tdList, List<TrainingFacilities> tfList) throws Exception {

		DemandApply demandApply2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			if (null != demandApply) {
				demandApply2 = demandApplicationDomain.merge(demandApply);
				if (null != dlList) {
					for (TrainingCourse da : dlList) {
						if (null != da) {
							da.setDemandApply(demandApply2);
							demandApplicationDomain.merge(da);
						}
					}
				}
				if (null != tdList) {
					for (TrainingData da : tdList) {
						if (null != da) {
							da.setDemandApply(demandApply2);
							demandApplicationDomain.merge(da);
						}
					}
				}
				if (null != tfList) {
					for (TrainingFacilities da : tfList) {
						if (null != da) {
							da.setDemandApply(demandApply2);
							demandApplicationDomain.merge(da);
						}
					}
				}
			}
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + demandApply + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + demandApply + "失败！失败原因：" + ex.getMessage());

		}
		return demandApply2;

	}

	/*
	 * public TrainingCourse findTrainingCourseById(String id) throws Exception
	 * { return demandApplicationDomain.findTrainingCourseById(id); }
	 */

	/*******************************************
	 * 资料
	 ********************************************************************************/
	public void deleteTrainingDataEntity(TrainingData trainingData) throws Exception {
		demandApplicationDomain.deleteTrainingDataEntity(trainingData);
	}

	/** 获取资料明细 */
	public TrainingData doListTrainingDataById(String id) {
		TrainingData detailsTemp = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			detailsTemp = demandApplicationDomain.findTrainingDataById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}

		return detailsTemp;
	}

	/** 资料明细 */
	public TrainingData doSaveTrainingData(TrainingData trainingData) throws Exception {
		TrainingData trainingData2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			trainingData2 = demandApplicationDomain.merge(trainingData);
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

	public TrainingData findTrainingDataById(String id) throws Exception {
		return demandApplicationDomain.findTrainingDataById(id);
	}

	/*******************************************
	 * 设施
	 ********************************************************************************/
	/*
	 * public void deleteTrainingFacilitiesEntity(TrainingFacilities
	 * trainingFacilities) throws Exception {
	 * demandApplicationDomain.deleteTrainingFacilitiesEntity(trainingFacilities
	 * ); }
	 */

	/** 获取设施明细 */
	/*
	 * public TrainingFacilities doListTrainingFacilitiesById(String id) {
	 * TrainingFacilities detailsTemp = null;
	 * 
	 * try { // 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载； // Map
	 * parameters = baExecutor.executeParameterProcess();
	 * 
	 * // 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
	 * beforeEventTrigger("PO_CREATE_BEFORE");
	 * 
	 * // 3.执行查询操作 detailsTemp =
	 * demandApplicationDomain.findTrainingFacilitiesById(id); // 4. 触发事件
	 * afterEventSaveOrder(parameter); 抽象方法，需要重载；
	 * afterEventTrigger("PO_CREATE_AFTER");
	 * 
	 * // 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
	 * 
	 * // 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
	 * 
	 * } catch (Exception ex) {
	 * 
	 * }
	 * 
	 * return detailsTemp; }
	 */

	/** 设施明细 */
	public TrainingFacilities doSaveTrainingFacilities(TrainingFacilities trainingFacilities) throws Exception {
		TrainingFacilities trainingFacilities2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			trainingFacilities2 = demandApplicationDomain.merge(trainingFacilities);
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

	/*
	 * public TrainingFacilities findTrainingFacilitiesaById(String id) throws
	 * Exception { return
	 * demandApplicationDomain.findTrainingFacilitiesById(id); }
	 */
}
