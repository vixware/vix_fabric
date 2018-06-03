package com.vix.oa.adminMg.conference.controller;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.conference.domain.ApplicationMeetingDomain;
import com.vix.oa.adminMg.conference.entity.ApplicationMg;
import com.vix.oa.adminMg.conference.entity.EquipmentDetails;

/**
 * @ClassName: ApplicationMeetingController
 * @Description: 完成对前端业务请求的参数处理和理解,并调用Domain层对象
 */
@Controller("applicationMeetingController")
@Scope("prototype")
public class ApplicationMeetingController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("ApplicationMeetingController");

	@Autowired
	private ApplicationMeetingDomain applicationMeetingDomain;
	
	/** 获取申请会议室列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = applicationMeetingDomain.findPagerByHqlConditions(params, pager);
		return p;
	}
	
	/** 获取申请会议室搜索列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = applicationMeetingDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}
	
	/**
	 * doSaveExecute实现获取申请会议室列表的业务逻辑处理
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
			p = applicationMeetingDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询申请会议室成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询申请会议室失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	
	/**
	 * doSaveExecute实现查询申请会议室的业务逻辑处理
	 */
	public List<ApplicationMg> doListSalesOrderIndex() {
		List<ApplicationMg> applicationMg = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行查询操作
			applicationMg = applicationMeetingDomain.findSalesOrderIndex();
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询申请会议室成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询申请会议室失败！" + "失败原因:" + ex.getMessage());
		}
		return applicationMg;
	}
	
	
	/**
	 * doSaveExecute实现保存申请会议室业务逻辑处理
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ApplicationMg doListEntityById(String id) throws Exception {
		ApplicationMg applicationMg = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			applicationMg = applicationMeetingDomain.findEntityById(id);

			/*
			 * // 3.执行查询操作 contractDomain.findEntityById(id);
			 */
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询申请会议室信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询申请会议室信息失败！" + "失败原因:" + ex.getMessage());
		}

		return applicationMg;
	}
	
	
	
	/**
	 * doSaveExecute实现保存申请会议室业务逻辑处理
	 */
	public ApplicationMg doSaveSalesOrder(ApplicationMg applicationMg)throws Exception {
		ApplicationMg applicationMg1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			/*taskDefinition.setUpdateTime(new Date());*/
			// 3. 执行保存操作
			applicationMg1 = applicationMeetingDomain.merge(applicationMg);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存申请会议室 ：" + applicationMg + "成功！");
		} catch (Exception ex) {
			executeLogger(true,"保存申请会议室：" + applicationMg + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存申请会议室", ex);
		}
		return applicationMg1;
	}
	
	/**删除*/
	public ApplicationMg findEntityById(String id) throws Exception {
		return applicationMeetingDomain.findEntityById(id);
	}
	
	/**
	 * doSaveExecute实现删除申请会议室的业务逻辑处理
	 */
	public void doDeleteByEntity(ApplicationMg applicationMg) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			applicationMeetingDomain.deleteByEntity(applicationMg);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除申请会议室成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除申请会议室失败！失败原因：" + ex.getMessage());
		}
	}
	
	//设备明细
	public EquipmentDetails doSaveEquipmentDetails(EquipmentDetails equipmentDetails) throws Exception {
		EquipmentDetails equipmentDetails1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			equipmentDetails1 = applicationMeetingDomain.merge1(equipmentDetails);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存：" + equipmentDetails + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存：" + equipmentDetails + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存失败", ex);
		}
		return equipmentDetails1;
	}
	
	/** 获取设备明细*/
	public EquipmentDetails doListEquipmentDetailsById(String id) {
		EquipmentDetails ed = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			ed = applicationMeetingDomain.findEquipmentDetailsById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询设备明细成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询设备明细失败！" + "失败原因:" + ex.getMessage());
		}

		return ed;
	}
	
	/**删除设备明细*/
	public void deleteEquipmentDetailsEntity(EquipmentDetails equipmentDetails) throws Exception {
		applicationMeetingDomain.deleteEquipmentDetailsEntity(equipmentDetails);
	}
	
	/** 获取设备列表数据 */
	public Pager goSingleList1(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = applicationMeetingDomain.findPagerByHqlConditions1(params, pager);
		return p;
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
