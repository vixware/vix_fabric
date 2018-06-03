package com.vix.hr.trainning.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.entity.UserAccount;
import com.vix.core.web.Pager;
import com.vix.hr.job.entity.HrAttachments;
import com.vix.hr.trainning.domain.TrainingPlanningDomain;
import com.vix.hr.trainning.entity.TrainingPlanning;
import com.vix.hr.trainning.entity.TrainingPlanningDetail;

@Scope("prototype")
@Controller("trainingplanningcontroller")
public class TrainingPlanningController extends BaseAction {

	private static final long serialVersionUID = 1L;
	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";
	Logger logger = Logger.getLogger("TrainingPlanningController");
	@Autowired
	private TrainingPlanningDomain trainingPlanningDomain;

	public TrainingPlanningDomain getTrainingPlanningDomain() {
		return trainingPlanningDomain;
	}

	public void setTrainingPlanningDomain(TrainingPlanningDomain trainingPlanningDomain) {
		this.trainingPlanningDomain = trainingPlanningDomain;
	}

	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		if (params.containsKey("name,anylike")) {
			if ("undefined".equals(params.get("name,anylike"))) {
				params.remove("name,anylike");
			}
		}
		Pager p = trainingPlanningDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public TrainingPlanning findEntityById(String id) throws Exception {
		return trainingPlanningDomain.findEntityById(id);
	}

	public void deleteHrAttachmentsEntity(HrAttachments attachments) throws Exception {
		trainingPlanningDomain.deleteHrAttachmentsEntity(attachments);
	}

	public HrAttachments findHrAttachmentsEntityById(String id) throws Exception {
		return trainingPlanningDomain.findHrAttachmentsEntityById(id);
	}

	public UserAccount findUserAccountById(String id) throws Exception {
		return trainingPlanningDomain.findUserAccountById(id);
	}

	public HrAttachments mergeAttachments(HrAttachments attachments) throws Exception {
		return trainingPlanningDomain.merge(attachments);
	}

	public TrainingPlanning merge(TrainingPlanning trainingPlanning) throws Exception {
		trainingPlanningDomain.merge(trainingPlanning);
		return null;
	}

	public void deleteByEntity(TrainingPlanning trainingPlanning) throws Exception {
		trainingPlanningDomain.deleteByEntity(trainingPlanning);
	}

	public void deleteTrainingPlanningDetailEntity(TrainingPlanningDetail trainingPlanningDetail) throws Exception {
		trainingPlanningDomain.deleteTrainingPlanningDetailEntity(trainingPlanningDetail);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		trainingPlanningDomain.deleteByIds(ids);
	}

	public List<TrainingPlanning> findTrainingPlanningIndex() throws Exception {
		return trainingPlanningDomain.findTrainingPlanningIndex();
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

	/** 获取计划 */
	public TrainingPlanning doListEntityById(String id) {
		TrainingPlanning trainingPlanning = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			trainingPlanning = trainingPlanningDomain.findEntityById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询培训计划信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询培训计划信息失败！" + "失败原因:" + ex.getMessage());
		}

		return trainingPlanning;
	}

	/** 获取计划明细 */
	public TrainingPlanningDetail doListTrainingPlanningDetailById(String id) {
		TrainingPlanningDetail detailsTemp = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			detailsTemp = trainingPlanningDomain.findTrainingPlanningDetailById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询培训计划信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询培训计划信息失败！" + "失败原因:" + ex.getMessage());
		}

		return detailsTemp;
	}

	// 处理保存方法
	public TrainingPlanning doSaveTrainingPlanning(TrainingPlanning trainingPlanning) {
		TrainingPlanning trainingPlanning2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			trainingPlanning2 = trainingPlanningDomain.merge(trainingPlanning);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存培训计划：" + trainingPlanning + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存培训计划：" + trainingPlanning + "失败！失败原因：" + ex.getMessage());
		}
		return trainingPlanning2;
	}

	// 计划明细
	public TrainingPlanningDetail doSaveTrainingPlanningDetail(TrainingPlanningDetail trainingPlanningDetail) throws Exception {
		TrainingPlanningDetail trainingPlanningDetail2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			trainingPlanningDetail2 = trainingPlanningDomain.merge(trainingPlanningDetail);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存培训计划：" + trainingPlanningDetail + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存培训计划：" + trainingPlanningDetail + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存培训计划失败", ex);
		}
		return trainingPlanningDetail2;
	}

	public TrainingPlanning doSaveTrainingPlanning(TrainingPlanning trainingPlanning, List<TrainingPlanningDetail> dlList) throws Exception {

		TrainingPlanning trainingPlanning2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			if (null != trainingPlanning) {
				trainingPlanning2 = trainingPlanningDomain.merge(trainingPlanning);
				if (null != dlList) {
					for (TrainingPlanningDetail da : dlList) {
						if (null != da) {
							da.setTrainingPlanning(trainingPlanning2);
							trainingPlanningDomain.merge(da);
						}
					}
				}
			}
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存培训计划：" + trainingPlanning + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存培训计划：" + trainingPlanning + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存培训计划失败", ex);
		}
		return trainingPlanning2;

	}

	/** 获取搜索列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainingPlanningDomain.findPagerByOrHqlConditions(params, pager);
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
			p = trainingPlanningDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询培训计划信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询培训计划信息失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}

	public void doDeleteByEntity(TrainingPlanning trainingPlanning) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			trainingPlanningDomain.deleteByEntity(trainingPlanning);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除培训计划信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除培训计划信息失败！失败原因：" + ex.getMessage());
		}
	}

	/**
	 * doSaveExecute实现删除培训计划的业务逻辑处理
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
			trainingPlanningDomain.deleteByIds(ids);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除培训计划信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除培训计划信息失败！" + "失败原因:" + ex.getMessage());
		}
	}
}
