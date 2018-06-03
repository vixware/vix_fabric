package com.vix.ebusiness.order.printexpresslist.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.ebusiness.entity.Order;
import com.vix.ebusiness.entity.OrderBatch;
import com.vix.ebusiness.entity.Shipping;
import com.vix.ebusiness.option.entity.Logistics;
import com.vix.ebusiness.order.printexpresslist.domain.PrintExpressListDomain;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("printExpressListController")
@Scope("prototype")
public class PrintExpressListController {

	@Autowired
	private PrintExpressListDomain printExpressListDomain;

	/**
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager doListShipping(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行保存操作
		p = printExpressListDomain.findShippingPagerByHqlConditions(params, pager);

		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		return p;
	}

	public List<Order> doListOrderList(Map<String, Object> params) throws Exception {
		List<Order> orderList = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		orderList = printExpressListDomain.findOrderList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		return orderList;
	}

	public Order doSaveOrder(Order order) throws Exception {
		Order order1 = printExpressListDomain.saveOrUpdateOrder(order);
		return order1;
	}

	public Shipping doSaveShipping(Shipping shipping) throws Exception {
		Shipping shipping1 = printExpressListDomain.saveOrUpdateShipping(shipping);
		return shipping1;
	}

	/**
	 * 保存订单批次
	 * 
	 * @param orderBatch
	 * @return
	 * @throws Exception
	 */
	public OrderBatch doSaveOrderBatch(OrderBatch orderBatch) throws Exception {
		OrderBatch orderBatch1 = printExpressListDomain.saveOrUpdateOrderBatch(orderBatch);
		return orderBatch1;
	}

	/**
	 * 查询订单批次
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public OrderBatch doListOrderBatchById(String id) throws Exception {
		OrderBatch orderBatch = printExpressListDomain.findOrderBatchById(id);
		return orderBatch;
	}

	public Logistics doListLogisticsById(String id) throws Exception {
		Logistics orderBatch = printExpressListDomain.findLogisticsById(id);
		return orderBatch;
	}

	public Shipping doListShippingById(String id) throws Exception {
		Shipping shipping = printExpressListDomain.findShippingById(id);
		return shipping;
	}

	public Order doListOrderById(String id) throws Exception {
		Order shipping = printExpressListDomain.findOrderById(id);
		return shipping;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Order> doListOrder(Map<String, Object> params) throws Exception {
		List<Order> orderList = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		orderList = printExpressListDomain.findOrder(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		return orderList;
	}

	public List<Logistics> doListLogistics(Map<String, Object> params) throws Exception {
		List<Logistics> logisticsList = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		logisticsList = printExpressListDomain.findLogisticsList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		return logisticsList;
	}

	public List<Shipping> doListShipping(Map<String, Object> params) throws Exception {
		List<Shipping> shippingList = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		shippingList = printExpressListDomain.findShipping(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		return shippingList;
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
