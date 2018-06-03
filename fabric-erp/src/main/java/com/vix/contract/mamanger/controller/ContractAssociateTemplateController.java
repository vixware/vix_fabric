package com.vix.contract.mamanger.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.contract.mamanger.domain.ContractAssociateTemplateDomain;
import com.vix.contract.mamanger.entity.ContractAssociateTemplate;
import com.vix.contract.mamanger.entity.ProtocolTemplete;
import com.vix.core.web.Pager;

/**
 * @ClassName: 
 * @Description: 完成对前端业务请求的参数处理和理解,并调用Domain层对象
 */
@Controller("contractAssociateTemplateController")
@Scope("prototype")
public class ContractAssociateTemplateController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("ContractAssociateTemplateController");

	@Autowired
	private ContractAssociateTemplateDomain contractAssociateTemplateDomain;
	
	/** 获取列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = contractAssociateTemplateDomain.findPagerByHqlConditions(params, pager);
		return p;
	}
	/** 获取列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = contractAssociateTemplateDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}

	public ContractAssociateTemplate findEntityById(String id) throws Exception {
		return contractAssociateTemplateDomain.findEntityById(id);
	}
	public ProtocolTemplete findEntityById1(String id) throws Exception {
		return contractAssociateTemplateDomain.findEntityById1(id);
	}

	/**
	 * doSaveExecute实现保存合同的业务逻辑处理
	 * 
	 * @param salesOrder
	 * @return
	 */
	public ContractAssociateTemplate doSaveSalesOrder(ContractAssociateTemplate contractAssociateTemplate) {
		ContractAssociateTemplate contractAssociateTemplate1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			contractAssociateTemplate1 = contractAssociateTemplateDomain.merge(contractAssociateTemplate);			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存合同模板：" + contractAssociateTemplate + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存合同模板：" + contractAssociateTemplate + "失败！失败原因：" + ex.getMessage());
		}
		return contractAssociateTemplate1;
	}
	/**
	 * doSaveExecute实现保存合同的业务逻辑处理
	 * 
	 * @param salesOrder
	 * @return
	 */
	public ProtocolTemplete doSaveSalesOrder1(ProtocolTemplete protocolTemplete) {
		ProtocolTemplete protocolTemplete1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			protocolTemplete1 = contractAssociateTemplateDomain.merge(protocolTemplete);			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存合同模板：" + protocolTemplete + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存合同模板：" + protocolTemplete + "失败！失败原因：" + ex.getMessage());
		}
		return protocolTemplete1;
	}

	/**
	 * doSaveExecute实现获取合同列表的业务逻辑处理
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doListSalesOrder(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行保存操作
			p = contractAssociateTemplateDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询合同模板成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询合同模板失败！失败原因：" + ex.getMessage());
		}
		return p;
	}

	/**
	 * doSaveExecute实现删除合同的业务逻辑处理
	 * 
	 * @param salesOrder
	 */
	public void doDeleteByEntity(ContractAssociateTemplate contractAssociateTemplate) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			contractAssociateTemplateDomain.deleteByEntity(contractAssociateTemplate);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除合同模板成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除合同模板失败！失败原因：" + ex.getMessage());
		}
	}
	/**
	 * doSaveExecute实现删除合同的业务逻辑处理
	 * 
	 * @param salesOrder
	 */
	public void doDeleteByEntity1(ProtocolTemplete protocolTemplete) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3. 执行删除操作
			contractAssociateTemplateDomain.deleteByEntity1(protocolTemplete);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除合同模板成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除合同模板失败！失败原因：" + ex.getMessage());
		}
	}

	/**
	 * doSaveExecute实现删除合同的业务逻辑处理
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
			contractAssociateTemplateDomain.deleteByIds(ids);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除合同模板成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除合同模板失败！" + "失败原因:" + ex.getMessage());
		}
	}

	/**
	 * doSaveExecute实现获取合同列表的业务逻辑处理
	 * 
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
			p = contractAssociateTemplateDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询合同模板成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询合同模板失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	/**
	 * doSaveExecute实现获取合同列表的业务逻辑处理
	 * 
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
			p = contractAssociateTemplateDomain.findPagerByHqlConditions1(params, pager);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询合同模板成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询合同模板失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}

	/**
	 * doSaveExecute实现保存合同业务逻辑处理
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ContractAssociateTemplate doListEntityById(String id) throws Exception {
		ContractAssociateTemplate contractAssociateTemplate = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			contractAssociateTemplate = contractAssociateTemplateDomain.findEntityById(id);

			/*
			 * // 3.执行查询操作 contractDomain.findEntityById(id);
			 */
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询合同模板成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询合同模板失败！" + "失败原因:" + ex.getMessage());
		}

		return contractAssociateTemplate;
	}
	/**
	 * doSaveExecute实现保存合同业务逻辑处理
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ProtocolTemplete doListEntityById1(String id) throws Exception {
		ProtocolTemplete protocolTemplete = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			protocolTemplete = contractAssociateTemplateDomain.findEntityById1(id);
			
			/*
			 * // 3.执行查询操作 contractDomain.findEntityById(id);
			 */
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询合同模板成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询合同模板失败！" + "失败原因:" + ex.getMessage());
		}
		
		return protocolTemplete;
	}

	/**
	 * doSaveExecute实现查询合同的业务逻辑处理
	 * 
	 * @param parameters
	 * @return
	 */
	public List<ContractAssociateTemplate> doListSalesOrderIndex() {
		List<ContractAssociateTemplate> contractAssociateTemplate = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行查询操作
			contractAssociateTemplate = contractAssociateTemplateDomain.findSalesOrderIndex();
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询合同模板成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询合同模板失败！" + "失败原因:" + ex.getMessage());
		}
		return contractAssociateTemplate;
	}
	/**
	 * doSaveExecute实现查询合同的业务逻辑处理
	 * 
	 * @param parameters
	 * @return
	 */
	public List<ProtocolTemplete> doListSalesOrderIndex1() {
		List<ProtocolTemplete> protocolTemplete = null;
		
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行查询操作
			protocolTemplete = contractAssociateTemplateDomain.findSalesOrderIndex1();
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询合同模板成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询合同模板失败！" + "失败原因:" + ex.getMessage());
		}
		return protocolTemplete;
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
