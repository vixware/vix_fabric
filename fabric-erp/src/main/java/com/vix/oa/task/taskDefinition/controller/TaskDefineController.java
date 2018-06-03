package com.vix.oa.task.taskDefinition.controller;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.oa.task.taskDefinition.domain.TaskDefineDomain;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;
import com.vix.oa.task.taskDefinition.entity.ExecutionFeedback;
import com.vix.oa.task.taskDefinition.entity.Uploader;
import com.vix.oa.task.taskDefinition.entity.VixTask;

/**
 * @ClassName: TaskDefineController
 * @Description: 完成对前端业务请求的参数处理和理解,并调用Domain层对象
 */
@Controller("taskDefineController")
@Scope("prototype")
public class TaskDefineController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("TaskDefineController");

	@Autowired
	private TaskDefineDomain taskDefineDomain;
	
	/** 获取列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = taskDefineDomain.findPagerByHqlConditions(params, pager);
		return p;
	}
	
	/** 获取列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = taskDefineDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}
	
	/**
	 * doSaveExecute实现获取任务定义列表的业务逻辑处理
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doSubSingleList(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = taskDefineDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询任务定义成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询任务定义失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	/**
	 * doSaveExecute实现获取附件列表数据业务逻辑处理
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doUploader(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = taskDefineDomain.findPagerByHqlUploader(params, pager);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询附件列表数据成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询附件列表数据失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	
	/**
	 * doSaveExecute实现获取执行反馈列表的业务逻辑处理
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doSubSingleList1(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = taskDefineDomain.findPagerByHqlConditions1(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询执行反馈成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询执行反馈失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	
	/**
	 * doSaveExecute实现获取评论/评估列表的业务逻辑处理
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doSubSingleList2(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = taskDefineDomain.findPagerByHqlConditions2(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询评论/评估成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询评论/评估失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	
	/**
	 * doSaveExecute实现查询任务定义的业务逻辑处理
	 */
	public List<VixTask> doListSalesOrderIndex() {
		List<VixTask> taskDefinition = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行查询操作
			taskDefinition = taskDefineDomain.findSalesOrderIndex();
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询任务定义成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询任务定义失败！" + "失败原因:" + ex.getMessage());
		}
		return taskDefinition;
	}
	
	
	/**
	 * doSaveExecute实现保存任务定义业务逻辑处理
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public VixTask doListEntityById(String id) throws Exception {
		VixTask taskDefinition = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			taskDefinition = taskDefineDomain.findEntityById(id);

			/*
			 * // 3.执行查询操作 contractDomain.findEntityById(id);
			 */
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询任务定义信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询任务定义信息失败！" + "失败原因:" + ex.getMessage());
		}

		return taskDefinition;
	}
	
	/**
	 * doSaveExecute实现上传附件业务逻辑处理
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Uploader doUploader(String id) throws Exception {
		Uploader uploader = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			uploader = taskDefineDomain.findUploader(id);

			/*
			 * // 3.执行查询操作 contractDomain.findEntityById(id);
			 */
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询上传附件信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询上传附件信息失败！" + "失败原因:" + ex.getMessage());
		}

		return uploader;
	}
	
	/**
	 * doSaveExecute实现保存执行反馈业务逻辑处理
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ExecutionFeedback doExecutionFeedback(String id) throws Exception {
		ExecutionFeedback executionFeedback = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			executionFeedback = taskDefineDomain.findExecutionFeedback(id);
			
			/*
			 * // 3.执行查询操作 contractDomain.findEntityById(id);
			 */
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询执行反馈信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询执行反馈信息失败！" + "失败原因:" + ex.getMessage());
		}
		
		return executionFeedback;
	}
	
	/**
	 * doSaveExecute实现保存评论/评估业务逻辑处理
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public EvaluationReview doListEntityById2(String id) throws Exception {
		EvaluationReview evaluationReview = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			evaluationReview = taskDefineDomain.findEntityById2(id);
			
			/*
			 * // 3.执行查询操作 contractDomain.findEntityById(id);
			 */
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询执行评论/评估信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询执行评论/评估信息失败！" + "失败原因:" + ex.getMessage());
		}
		
		return evaluationReview;
	}
	
	
	/**
	 * doSaveExecute实现保存任务定义业务逻辑处理
	 */
	public VixTask doSaveSalesOrder(VixTask taskDefinition)throws Exception {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			/*taskDefinition.setUpdateTime(new Date());*/
			// 3. 执行保存操作
			taskDefinition = taskDefineDomain.merge(taskDefinition);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存任务定义：" + taskDefinition + "成功！");
		} catch (Exception ex) {
			executeLogger(true,"保存任务定义：" + taskDefinition + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存任务定义", ex);
		}
		return taskDefinition;
	}
	/**
	 * doSaveExecute实现保存附件业务逻辑处理
	 */
	public Uploader doSaveUploader(Uploader uploader)throws Exception {
		Uploader uploader1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			/*taskDefinition.setUpdateTime(new Date());*/
			// 3. 执行保存操作
			uploader1 = taskDefineDomain.merge(uploader);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存附件：" + uploader + "成功！");
		} catch (Exception ex) {
			executeLogger(true,"保存附件：" + uploader + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存附件", ex);
		}
		return uploader1;
	}
	/**
	 * doSaveExecute实现保存评论/评估业务逻辑处理
	 */
	public EvaluationReview doSaveSalesOrder(EvaluationReview evaluationReview)throws Exception {
		EvaluationReview evaluationReview1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			/*taskDefinition.setUpdateTime(new Date());*/
			// 3. 执行保存操作
			evaluationReview1 = taskDefineDomain.merge(evaluationReview);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存评论/评估：" + evaluationReview + "成功！");
		} catch (Exception ex) {
			executeLogger(true,"保存评论/评估：" + evaluationReview + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存评论/评估", ex);
		}
		return evaluationReview1;
	}
	/**
	 * doSaveExecute实现保存执行反馈业务逻辑处理
	 */
	public ExecutionFeedback doSaveSalesOrder(ExecutionFeedback executionFeedback)throws Exception {
		ExecutionFeedback executionFeedback1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			/*taskDefinition.setUpdateTime(new Date());*/
			// 3. 执行保存操作
			executionFeedback1 = taskDefineDomain.merge(executionFeedback);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存执行反馈：" + executionFeedback + "成功！");
		} catch (Exception ex) {
			executeLogger(true,
					"保存执行反馈：" + executionFeedback + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存执行反馈", ex);
		}
		return executionFeedback1;
	}
	
	/**删除*/
	public VixTask findEntityById(String id) throws Exception {
		return taskDefineDomain.findEntityById(id);
	}
	
	/**
	 * doSaveExecute实现删除任务定义的业务逻辑处理
	 */
	public void doDeleteByEntity(VixTask taskDefinition) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			taskDefineDomain.deleteByEntity(taskDefinition);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除任务定义成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除任务定义失败！失败原因：" + ex.getMessage());
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
	 * 异常错误日志方便程序员调试
	 * 
	 * @param message
	 */
	public void executeErrorLogger(String... errorMessage) {
	}

	/**
	 * bizFlowExecute 需要对流程进行操作时调用
	 * 
	 * @param flowName
	 * @param flowParameter
	 */
	protected void bizFlowExecute(String flowName,
			Map<String, Object> flowParameter) {
		// to do something
	}

	/**
	 * bizRuleExecute 的定义还没有考虑清楚,涉及业务时重构.
	 * 
	 * @param ruleName
	 * @param flowParameter
	 */
	protected void bizRuleExecute(String ruleName,
			Map<String, Object> flowParameter) {
		// to do something

	}

	/**
 * 
 */
	public void doProcessEvent() {
		// to do something

	}

	/**
	 * 
	 */
	public void doExecute() {
		// to do something
	}

	/**
	 * 
	 * @return
	 */
	public Object getIndustryOrderAction() {
		return null;
	}

	/**
	 * 
	 */
	public void doPrint() {
		// to do something
	}

	/**
	 * 
	 */
	public void doDrools() {
		// to do something
	}
}
