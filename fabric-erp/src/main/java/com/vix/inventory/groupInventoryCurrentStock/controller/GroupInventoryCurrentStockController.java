/**
 * 
 */
package com.vix.inventory.groupInventoryCurrentStock.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.core.web.Pager;
import com.vix.inventory.groupInventoryCurrentStock.domain.GroupInventoryCurrentStockDomain;
import com.vix.inventory.standingbook.entity.GroupInventoryCurrentStock;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.mdm.item.entity.Item;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("groupInventoryCurrentStockController")
@Scope("prototype")
public class GroupInventoryCurrentStockController {

	Logger logger = Logger.getLogger(GroupInventoryCurrentStockController.class);

	@Autowired
	private GroupInventoryCurrentStockDomain groupInventoryCurrentStockDomain;
	/**
	 * 初始化基础数据
	 */
	@Autowired
	public InitEntityBaseController initEntityBaseController;

	/**
	 * 
	 * @param groupInventoryCurrentStock
	 * @return
	 * @throws Exception
	 */
	public GroupInventoryCurrentStock doSaveOrUpdateGroupInventoryCurrentStock(GroupInventoryCurrentStock groupInventoryCurrentStock) throws Exception {
		GroupInventoryCurrentStock groupInventoryCurrentStock1 = null;
		// 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 执行保存操作
		groupInventoryCurrentStock1 = groupInventoryCurrentStockDomain.saveOrUpdateGroupInventoryCurrentStock(groupInventoryCurrentStock);
		// 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return groupInventoryCurrentStock1;
	}

	public InventoryCurrentStock doSaveOrUpdateInventoryCurrentStock(InventoryCurrentStock inventoryCurrentStock) throws Exception {
		InventoryCurrentStock inventoryCurrentStock1 = null;
		// 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 执行保存操作
		inventoryCurrentStock1 = groupInventoryCurrentStockDomain.saveOrUpdateInventoryCurrentStock(inventoryCurrentStock);
		// 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return inventoryCurrentStock1;
	}

	public Item doSaveItem(Item item) throws Exception {
		Item item1 = null;
		// 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 执行保存操作
		item1 = groupInventoryCurrentStockDomain.saveItem(item);
		// 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return item1;
	}

	/**
	 * 
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager doListGroupInventoryCurrentStockPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;
		// 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 执行保存操作
		p = groupInventoryCurrentStockDomain.findGroupInventoryCurrentStockPager(params, pager);

		// 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return p;
	}

	public Pager doListInventoryCurrentStockPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;
		// 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 执行保存操作
		p = groupInventoryCurrentStockDomain.findInventoryCurrentStockPager(params, pager);

		// 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return p;
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public GroupInventoryCurrentStock doListGroupInventoryCurrentStockById(String id) throws Exception {
		GroupInventoryCurrentStock groupInventoryCurrentStock = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		groupInventoryCurrentStock = groupInventoryCurrentStockDomain.findGroupInventoryCurrentStockById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return groupInventoryCurrentStock;
	}

	public void doDeleteByIds(List<String> ids) throws Exception {
		groupInventoryCurrentStockDomain.deleteByIds(ids);
	}

	public InventoryCurrentStock doListInventoryCurrentStockById(String id) throws Exception {
		InventoryCurrentStock inventoryCurrentStockStock = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		inventoryCurrentStockStock = groupInventoryCurrentStockDomain.findInventoryCurrentStockById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return inventoryCurrentStockStock;
	}

	public List<InventoryCurrentStock> doListInventoryCurrentStock() throws Exception {
		List<InventoryCurrentStock> inventoryCurrentStockList = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		inventoryCurrentStockList = groupInventoryCurrentStockDomain.findInventoryCurrentStock();
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return inventoryCurrentStockList;
	}

	/**
	 * 
	 * 
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public List<GroupInventoryCurrentStock> doListGroupInventoryCurrentStockList(Map<String, Object> params) throws Exception {
		List<GroupInventoryCurrentStock> stockTaking = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		stockTaking = groupInventoryCurrentStockDomain.findGroupInventoryCurrentStockList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return stockTaking;
	}

	public void doDeleteByEntity(GroupInventoryCurrentStock groupInventoryCurrentStock) throws Exception {
		groupInventoryCurrentStockDomain.deleteGroupInventoryCurrentStockByEntity(groupInventoryCurrentStock);
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
