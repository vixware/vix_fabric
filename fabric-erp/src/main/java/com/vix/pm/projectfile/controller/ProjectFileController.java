package com.vix.pm.projectfile.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.pm.projectfile.domain.ProjectFileDomain;
import com.vix.pm.projectfile.entity.ProjectFile;

@Controller("projectFileController")
@Scope("prototype")
public class ProjectFileController extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("ProjectFileController");
	
	@Autowired
	private ProjectFileDomain projectFileDomain;
	
	/** 获取列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = projectFileDomain.findPagerByHqlConditions(params, pager);
		return p;
	}
	
	/** 获取列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager)
			throws Exception {
		Pager p = projectFileDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}
	
	/**
	 * doSaveExecute实现查询项目文档的业务逻辑处理
	 * 
	 * @param parameters
	 * @return
	 */
	public List<ProjectFile> doListSalesOrderIndex() {
		List<ProjectFile> sortingDevice = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行查询操作
			sortingDevice = projectFileDomain.findSalesOrderIndex();
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询项目文档信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询项目文档信息失败！" + "失败原因:" + ex.getMessage());
		}
		return sortingDevice;
	}
	
	/** doSaveExecute实现获取项目文档列表的业务逻辑处理*/
	public Pager doSubSingleList(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = projectFileDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询项目文档信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询项目文档信息失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	
	/** doSaveExecute实现保存项目文档逻辑处理*/
	public ProjectFile doListEntityById(String id) throws Exception {
		ProjectFile projectFile = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			projectFile = projectFileDomain.findEntityById(id);

			/*
			 * // 3.执行查询操作 contractDomain.findEntityById(id);
			 */
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询项目文档信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询项目文档信息失败！" + "失败原因:" + ex.getMessage());
		}

		return projectFile;
	}
	
	/**
	 * doSaveExecute实现保存项目文档的业务逻辑处理
	 * 
	 * @param 
	 * @return
	 */
	public ProjectFile doSaveSalesOrder(ProjectFile projectFile) {
		ProjectFile projectFile1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			projectFile.setUpdateTime(new Date());
			// 3. 执行保存操作
			projectFile1 = projectFileDomain.merge(projectFile);			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存项目文档：" + projectFile + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存项目文档：" + projectFile + "失败！失败原因：" + ex.getMessage());
		}
		return projectFile1;
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
	}

	/**
	 * bizRuleExecute 的定义还没有考虑清楚,涉及业务时重构.
	 * 
	 * @param ruleName
	 * @param flowParameter
	 */
	protected void bizRuleExecute(String ruleName,
			Map<String, Object> flowParameter) {
	}

	public void doProcessEvent() {

	}

	public void doExecute() {
		
	}

	public Object getIndustryOrderAction() {
		return null;
	}

	public void doPrint() {
		
	}

	public void doDrools() {
		
	}
}