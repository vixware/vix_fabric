/**
 * 
 */
package com.vix.dtbcenter.dispatchingreceipt.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.dtbcenter.dispatchingreceipt.domain.DispatchingReceiptDomain;
import com.vix.dtbcenter.dispatchingreceipt.entity.DispatchingReceipt;
import com.vix.dtbcenter.dispatchingreceipt.entity.DispatchingReceiptToSalesOrder;
import com.vix.dtbcenter.pickupds.entity.DeliveryReceipt;
import com.vix.dtbcenter.transpotmgr.entity.DelieryNotification;
import com.vix.dtbcenter.transpotmgr.entity.DeliveryReceiptToSalesOrder;
import com.vix.sales.order.entity.SalesOrder;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("dispatchingReceiptController")
@Scope("prototype")
public class DispatchingReceiptController {

	Logger logger = Logger.getLogger(DispatchingReceiptController.class);

	@Autowired
	private DispatchingReceiptDomain dispatchingReceiptDomain;

	/**
	 * 
	 * 
	 * @param wimStockrecords
	 * @return
	 * @throws Exception
	 */
	public DispatchingReceipt doSaveDeliveryReceipt(DispatchingReceipt dispatchingReceipt) throws Exception {
		DispatchingReceipt dispatchingReceipt1 = null;
		try {
			// 执行保存操作
			dispatchingReceipt.setName("配送回执单" + dispatchingReceipt.getCode());
			dispatchingReceipt1 = dispatchingReceiptDomain.mergeDeliveryReceipt(dispatchingReceipt);
			executeLogger(true, "保存：" + dispatchingReceipt.getName() + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存：" + dispatchingReceipt.getName() + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存失败", ex);
		}
		return dispatchingReceipt1;
	}

	/**
	 * 
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doListDeliveryReceipt(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 执行保存操作
			p = dispatchingReceiptDomain.findDeliveryReceiptPagerByHqlConditions(params, pager);

			// 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}
		return p;
	}

	public Pager doListDelieryNotification(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 执行保存操作
			p = dispatchingReceiptDomain.findDelieryNotificationPagerByHqlConditions(params, pager);

			// 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}
		return p;
	}

	/**
	 * 简单查询
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doListDeliveryReceiptByCon(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			p = dispatchingReceiptDomain.findDeliveryReceiptPagerByOrHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询派车单成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询派车单失败！失败原因：" + ex.getMessage());
		}
		return p;
	}

	/**
	 * 
	 * @param dispatchingReceipt
	 */
	public void doDeleteDispatchingReceipt(DispatchingReceipt dispatchingReceipt) {
		try {

			// 3. 执行删除操作
			dispatchingReceiptDomain.deleteByEntity(dispatchingReceipt);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}
	}

	/**
	 * doDeleteByIds实现删除派车单的业务逻辑处理
	 * 
	 * @param ids
	 */
	public void doDeleteByIds(List<String> ids) {
		try {
			// 3. 执行删除操作
			dispatchingReceiptDomain.deleteByIds(ids);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除失败！" + "失败原因:" + ex.getMessage());
		}
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public DispatchingReceipt doListDispatchingReceiptById(String id) {
		DispatchingReceipt dispatchingReceipt = null;
		try {
			// 3.执行查询操作
			dispatchingReceipt = dispatchingReceiptDomain.findDispatchingReceiptById(id);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}
		return dispatchingReceipt;
	}

	public DeliveryReceipt doListDeliveryReceiptById(String id) {
		DeliveryReceipt deliveryReceipt = null;
		try {
			// 3.执行查询操作
			deliveryReceipt = dispatchingReceiptDomain.findDeliveryReceiptById(id);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}
		return deliveryReceipt;
	}

	public List<DispatchingReceiptToSalesOrder> doListDispatchingReceiptToSalesOrderByParams(Map<String, Object> params) {
		List<DispatchingReceiptToSalesOrder> deliveryReceiptToSalesOrderList = null;
		try {
			// 3.执行查询操作
			deliveryReceiptToSalesOrderList = dispatchingReceiptDomain.findDispatchingReceiptToSalesOrderByParams(params);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}

		return deliveryReceiptToSalesOrderList;
	}

	public List<DeliveryReceiptToSalesOrder> DeliveryReceiptToSalesOrder(Map<String, Object> params) {
		List<DeliveryReceiptToSalesOrder> deliveryReceiptToSalesOrderList = null;
		try {
			// 3.执行查询操作
			deliveryReceiptToSalesOrderList = dispatchingReceiptDomain.findDeliveryReceiptToSalesOrderByParams(params);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}

		return deliveryReceiptToSalesOrderList;
	}

	/**
	 * 建立销售订单跟派车单的关系表
	 * 
	 * @param dispatchingReceipt
	 * @param salesOrder
	 * @throws Exception
	 */
	public void convSalesOrderToDeliveryReceipt(DispatchingReceipt dispatchingReceipt, SalesOrder salesOrder) throws Exception {
		if (salesOrder != null && dispatchingReceipt != null) {
			// 生成派车单 发货通知单关联表 同时保存关联表到数据库
			DispatchingReceiptToSalesOrder deliveryReceiptToSalesOrder = new DispatchingReceiptToSalesOrder();
			deliveryReceiptToSalesOrder.setSalesOrderId(salesOrder.getId());
			deliveryReceiptToSalesOrder.setDispatchingReceiptId(dispatchingReceipt.getId());
			dispatchingReceiptDomain.mergeDispatchingReceiptToSalesOrder(deliveryReceiptToSalesOrder);
		}
	}

	public SalesOrder doListSalesOrderById(String id) {
		SalesOrder salesOrder = null;
		try {

			// 3.执行查询操作
			salesOrder = dispatchingReceiptDomain.findSalesOrderById(id);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}

		return salesOrder;
	}

	public Pager doListgoTruckingOrder(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {

			// 执行保存操作
			p = dispatchingReceiptDomain.findVehiclePagerByHqlConditions(params, pager);

			// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + ex.getMessage());
		}
		return p;
	}

	public Pager doListSalesOrder(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {

			// 执行保存操作
			p = dispatchingReceiptDomain.findSalesOrderPagerByHqlConditions(params, pager);

			// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + ex.getMessage());
		}
		return p;
	}

	public List<DispatchingReceiptToSalesOrder> doListDeliveryReceiptToDelieryNotificationByParams(Map<String, Object> params) {
		List<DispatchingReceiptToSalesOrder> deliveryReceiptToDelieryNotificationList = null;
		try {
			// 3.执行查询操作
			deliveryReceiptToDelieryNotificationList = dispatchingReceiptDomain.findDeliveryReceiptToDelieryNotificationByParams(params);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}

		return deliveryReceiptToDelieryNotificationList;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<DispatchingReceipt> doListDispatchingReceiptList(Map<String, Object> params) throws Exception {
		List<DispatchingReceipt> dispatchingReceipt = null;
		// 3. 执行查询操作
		dispatchingReceipt = dispatchingReceiptDomain.findDispatchingReceiptList(params);
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		executeLogger(true, "");
		return dispatchingReceipt;
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

	/**
	 * @param delieryNotificationId
	 * @return
	 */
	public DelieryNotification doListDelieryNotificationById(String delieryNotificationId) {
		DelieryNotification delieryNotification = null;
		try {
			// 3.执行查询操作
			delieryNotification = dispatchingReceiptDomain.findDelieryNotificationById(delieryNotificationId);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}
		return delieryNotification;
	}

}
