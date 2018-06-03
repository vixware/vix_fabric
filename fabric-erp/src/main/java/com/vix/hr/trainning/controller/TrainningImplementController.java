package com.vix.hr.trainning.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.hr.job.entity.HrAttachments;
import com.vix.hr.trainning.domain.TrainningImplementDomain;
import com.vix.hr.trainning.entity.TrainingPlanning;
import com.vix.hr.trainning.entity.TrainingPlanningDetail;
import com.vix.hr.trainning.entity.TrainningImplement;

@Scope("prototype")
@Controller("trainningimplementcontroller")
public class TrainningImplementController extends BaseAction {

	private static final long serialVersionUID = 1L;
	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";
	Logger logger = Logger.getLogger("TrainningImplementController");
	@Autowired
	private TrainningImplementDomain trainningImplementDomain;

	public TrainningImplementDomain getTrainningImplementDomain() {
		return trainningImplementDomain;
	}

	public void setTrainningImplementDomain(TrainningImplementDomain trainningImplementDomain) {
		this.trainningImplementDomain = trainningImplementDomain;
	}

	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		if (params.containsKey("name,anylike")) {
			if ("undefined".equals(params.get("name,anylike"))) {
				params.remove("name,anylike");
			}
		}
		Pager p = trainningImplementDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public TrainningImplement findEntityById(String id) throws Exception {
		return trainningImplementDomain.findEntityById(id);
	}

	public void deleteHrAttachmentsEntity(HrAttachments attachments) throws Exception {
		trainningImplementDomain.deleteHrAttachmentsEntity(attachments);
	}

	public HrAttachments findHrAttachmentsEntityById(String id) throws Exception {
		return trainningImplementDomain.findHrAttachmentsEntityById(id);
	}

	public HrAttachments mergeAttachments(HrAttachments attachments) throws Exception {
		return trainningImplementDomain.merge(attachments);
	}

	public TrainningImplement merge(TrainningImplement trainningImplement) throws Exception {
		trainningImplementDomain.merge(trainningImplement);
		return null;
	}

	public void deleteByEntity(TrainningImplement trainningImplement) throws Exception {
		trainningImplementDomain.deleteByEntity(trainningImplement);
	}

	public void deleteTrainningImplementDetailEntity(TrainingPlanning trainingPlanning) throws Exception {
		trainningImplementDomain.deleteTrainningImplementDetailEntity(trainingPlanning);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		trainningImplementDomain.deleteByIds(ids);
	}

	/** 获取培训计划列表数据 */
	public Pager goTrainingPlannings(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainningImplementDomain.findTrainingPlanning(params, pager);
		return p;
	}

	public List<TrainningImplement> findTrainningImplementIndex() throws Exception {
		return trainningImplementDomain.findTrainningImplementIndex();
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

	/** 获取培训活动 */
	public TrainningImplement doListEntityById(String id) {
		TrainningImplement trainningImplement = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			trainningImplement = trainningImplementDomain.findEntityById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询培训活动成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询培训活动失败！" + "失败原因:" + ex.getMessage());
		}

		return trainningImplement;
	}

	/** 获取实施明细 */
	public TrainingPlanning doListTrainningImplementDetailById(String id) {
		TrainingPlanning detailsTemp = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			detailsTemp = trainningImplementDomain.findTrainningImplementDetailById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询培训活动成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询培训活动失败！" + "失败原因:" + ex.getMessage());
		}

		return detailsTemp;
	}

	// 处理保存方法
	public TrainningImplement doSaveTrainningImplement(TrainningImplement trainningImplement) {
		TrainningImplement trainningImplement2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			trainningImplement2 = trainningImplementDomain.merge(trainningImplement);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存培训活动：" + trainningImplement + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存培训活动：" + trainningImplement + "失败！失败原因：" + ex.getMessage());
		}
		return trainningImplement2;
	}

	// 实施明细
	public TrainingPlanning doSaveTrainningImplementDetail(TrainingPlanning trainingPlanning) throws Exception {
		TrainingPlanning trainingPlanning2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			trainingPlanning2 = trainningImplementDomain.merge(trainingPlanning);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存培训活动：" + trainingPlanning + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存培训活动：" + trainingPlanning + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存培训活动失败", ex);
		}
		return trainingPlanning2;
	}

	public TrainningImplement doSaveTrainningImplement(TrainningImplement trainningImplement, List<TrainingPlanning> dlList) throws Exception {

		TrainningImplement trainningImplement2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			if (null != trainningImplement) {
				trainningImplement2 = trainningImplementDomain.merge(trainningImplement);
				if (null != dlList) {
					for (TrainingPlanning da : dlList) {
						if (null != da) {
							da.setTrainningImplement(trainningImplement2);
							trainningImplementDomain.merge(da);
						}
					}
				}
			}
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存培训活动：" + trainningImplement + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存培训活动：" + trainningImplement + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存培训活动失败", ex);
		}
		return trainningImplement2;

	}

	public void converterPlanToOrder(TrainningImplement trainningImplement, TrainingPlanning trainingPlanning) throws Exception {
		// 培训计划明细
		Set<TrainingPlanningDetail> trainingPlanningDetailSet = new HashSet<TrainingPlanningDetail>();
		// 培训活动明细
		List<TrainingPlanning> trainingPlanningList = new ArrayList<TrainingPlanning>();
		if (trainingPlanning != null) {
			trainingPlanningDetailSet = trainingPlanning.getTrainingPlanningDetails();
			for (TrainingPlanningDetail entity : trainingPlanningDetailSet) {
				if (entity != null) {
					TrainingPlanning temp = new TrainingPlanning();
					temp.setApplicationName(trainingPlanning.getApplicationName());
					temp.setOrgUnitAndLeadingOfficial(entity.getGoalOfTraining());
					/*
					 * temp.setTrainingSite(entity.getTrainingSite());
					 * temp.setTrainingContent(entity.getTrainingContent());
					 * temp.setTrainingCourse(entity.getTrainingCourse());
					 * temp.setCurriculumClassHours(entity.
					 * getCurriculumClassHours());
					 * temp.setPlanStartDate(entity.getPlanStartDate());
					 * temp.setPlanEndDate(entity.getPlanEndDate());
					 */
					trainingPlanningList.add(temp);
				}
			}
		}
		try {
			if (trainingPlanningList != null && trainingPlanningList.size() > 0) {
				for (TrainingPlanning var : trainingPlanningList) {
					if (var != null) {
						var.setTrainningImplement(trainningImplement);
						trainningImplementDomain.merge(var);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 获取搜索列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = trainningImplementDomain.findPagerByOrHqlConditions(params, pager);
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
			p = trainningImplementDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询培训活动成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询培训活动失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}

	public void doDeleteByEntity(TrainningImplement trainningImplement) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			trainningImplementDomain.deleteByEntity(trainningImplement);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除培训活动成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除培训活动失败！失败原因：" + ex.getMessage());
		}
	}

	public TrainingPlanning doListTrainingPlanningById(String id) {
		TrainingPlanning trainingPlanning = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			trainingPlanning = trainningImplementDomain.findTrainingPlanningById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询培训活动成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询培训活动失败！" + "失败原因:" + ex.getMessage());
		}

		return trainingPlanning;
	}

	/**
	 * doSaveExecute实现删除培训活动的业务逻辑处理
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
			trainningImplementDomain.deleteByIds(ids);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除培训活动成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除培训活动失败！" + "失败原因:" + ex.getMessage());
		}
	}
}
