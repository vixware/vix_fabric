package com.vix.oa.adminMg.officeSupplies.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.officeSupplies.domain.OfficeSuppliesRegisterDomain;
import com.vix.oa.adminMg.officeSupplies.entity.OfficeSupplies;
import com.vix.oa.adminMg.officeSupplies.entity.OfficeSuppliesRegister;

/**
 * @ClassName: OfficeSuppliesController
 * @Description: 完成对前端业务请求的参数处理和理解,并调用Domain层对象
 */
@Controller("officeSuppliesRegisterController")
@Scope("prototype")
public class OfficeSuppliesRegisterController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("OfficeSuppliesController");

	@Autowired
	private OfficeSuppliesRegisterDomain officeSuppliesRegisterDomain;

	/** 获取列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = officeSuppliesRegisterDomain.findWimStockrecordsPagerByHqlConditions(params, pager);
		return p;
	}
	
	/** 获取办公用品搜索列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = officeSuppliesRegisterDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}

	/**
	 * doSaveExecute实现获取办公用品列表的业务逻辑处理
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doListWimStockrecords(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行保存操作
			p = officeSuppliesRegisterDomain.findWimStockrecordsPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询办公用品成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询办公用品失败！失败原因：" + ex.getMessage());
		}
		return p;
	}
	
	
	/**
	 * doSaveExecute实现获取办公用品借用列表的业务逻辑处理
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doBorrowList(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行保存操作
			p = officeSuppliesRegisterDomain.findBorrowListConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询办公用品借用列表成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询办公用品借用列表失败！失败原因：" + ex.getMessage());
		}
		return p;
	}

	/**
	 * doSaveExecute实现查询办公用品的业务逻辑处理
	 * 
	 * @param parameters
	 * @return
	 */
	public List<OfficeSuppliesRegister> doListWimStockrecordsIndex() {
		List<OfficeSuppliesRegister> officeSuppliesRegister = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行查询操作
			officeSuppliesRegister = officeSuppliesRegisterDomain.findWimStockrecordsIndex();
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询办公用品成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询办公用品失败！" + "失败原因:" + ex.getMessage());
		}
		return officeSuppliesRegister;
	}

	/**
	 * doSaveExecute实现保存办公用品的业务逻辑处理
	 * 
	 * @param id
	 * @return
	 */
	public OfficeSuppliesRegister doListEntityById(String id) {
		OfficeSuppliesRegister officeSuppliesRegister = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			officeSuppliesRegister = officeSuppliesRegisterDomain.findWimStockrecordsById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询办公用品信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询办公用品信息失败！" + "失败原因:" + ex.getMessage());
		}

		return officeSuppliesRegister;
	}
	
	/**删除*/
	public OfficeSupplies findEntityById(String id) throws Exception {
		return officeSuppliesRegisterDomain.findEntityById(id);
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
	protected void bizFlowExecute(String flowName, Map<String, Object> flowParameter) {
		// to do something
	}

	/**
	 * bizRuleExecute 的定义还没有考虑清楚,涉及业务时重构.
	 * 
	 * @param ruleName
	 * @param flowParameter
	 */
	protected void bizRuleExecute(String ruleName, Map<String, Object> flowParameter) {
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
