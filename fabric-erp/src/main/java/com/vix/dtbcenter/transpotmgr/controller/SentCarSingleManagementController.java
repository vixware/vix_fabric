/**
 * 
 */
package com.vix.dtbcenter.transpotmgr.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.dtbcenter.deliveryroute.entity.DispatchRoute;
import com.vix.dtbcenter.pickupds.entity.DeliveryReceipt;
import com.vix.dtbcenter.pickupds.entity.DeliveryReceiptToRoute;
import com.vix.dtbcenter.transpotmgr.domain.SentCarSingleManagementDomain;
import com.vix.dtbcenter.transpotmgr.entity.DelieryNotification;
import com.vix.dtbcenter.transpotmgr.entity.DeliveryReceiptToDelieryNotification;
import com.vix.dtbcenter.transpotmgr.entity.DeliveryReceiptToSalesOrder;
import com.vix.sales.order.entity.SalesOrder;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("sentCarSingleManagementController")
@Scope("prototype")
public class SentCarSingleManagementController {

	Logger logger = Logger.getLogger(SentCarSingleManagementController.class);

	@Autowired
	private SentCarSingleManagementDomain sentCarSingleManagementDomain;

	/**
	 * 
	 * @param deliveryReceipt
	 * @param stockTakingItemList
	 * @return
	 * @throws Exception
	 */
	public DeliveryReceipt doSaveDeliveryReceipt(DeliveryReceipt deliveryReceipt) throws Exception {
		DeliveryReceipt deliveryReceipt1 = null;
		try {
			// 执行保存操作
			deliveryReceipt.setName("派车单" + deliveryReceipt.getScheduleCode());
			deliveryReceipt1 = sentCarSingleManagementDomain.mergeDeliveryReceipt(deliveryReceipt);
			// 保存日志
			executeLogger(true, "保存：" + deliveryReceipt.getCode() + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存：" + deliveryReceipt.getCode() + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存失败", ex);
		}
		return deliveryReceipt1;
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

			// 执行保存操作
			p = sentCarSingleManagementDomain.findDeliveryReceiptPagerByHqlConditions(params, pager);

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
			p = sentCarSingleManagementDomain.findSalesOrderPagerByHqlConditions(params, pager);

			// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + ex.getMessage());
		}
		return p;
	}

	public Pager doListDispatchRoute(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {

			// 执行保存操作
			p = sentCarSingleManagementDomain.findDispatchRoutePagerByHqlConditions(params, pager);

			// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + ex.getMessage());
		}
		return p;
	}

	public Pager doListVehicle(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {

			// 执行保存操作
			p = sentCarSingleManagementDomain.findVehiclePagerByHqlConditions(params, pager);

			// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + ex.getMessage());
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

			// 3.执行查询操作
			p = sentCarSingleManagementDomain.findDeliveryReceiptPagerByOrHqlConditions(params, pager);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询派车单成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询派车单失败！失败原因：" + ex.getMessage());
		}
		return p;
	}

	/**
	 * 
	 * 
	 * @param deliveryReceipt
	 */
	public void doDeleteByDeliveryReceipt(DeliveryReceipt deliveryReceipt) {
		try {
			sentCarSingleManagementDomain.deleteByEntity(deliveryReceipt);
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + ex.getMessage());
		}
	}

	/**
	 * doDeleteByIds实现删除派车单的业务逻辑处理
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
			sentCarSingleManagementDomain.deleteByIds(ids);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public DeliveryReceipt doListDeliveryReceiptById(String id) {
		DeliveryReceipt deliveryReceipt = null;
		try {

			// 3.执行查询操作
			deliveryReceipt = sentCarSingleManagementDomain.findDeliveryReceiptById(id);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}

		return deliveryReceipt;
	}

	public SalesOrder doListSalesOrderById(String id) {
		SalesOrder salesOrder = null;
		try {

			// 3.执行查询操作
			salesOrder = sentCarSingleManagementDomain.findSalesOrderById(id);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}

		return salesOrder;
	}

	public DelieryNotification doListDelieryNotificationById(String id) {
		DelieryNotification delieryNotification = null;
		try {

			// 3.执行查询操作
			delieryNotification = sentCarSingleManagementDomain.findDelieryNotificationById(id);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}

		return delieryNotification;
	}

	public DispatchRoute doListDispatchRouteById(String id) {
		DispatchRoute dispatchRoute = null;
		try {
			// 3.执行查询操作
			dispatchRoute = sentCarSingleManagementDomain.findDispatchRouteById(id);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}

		return dispatchRoute;
	}

	/**
	 * 建立销售订单跟派车单的关系表
	 * 
	 * @param deliveryReceipt
	 * @param salesOrder
	 * @throws Exception
	 */
	public void convSalesOrderToDeliveryReceipt(DeliveryReceipt deliveryReceipt, SalesOrder salesOrder) throws Exception {
		if (salesOrder != null && deliveryReceipt != null) {
			// 生成派车单 发货通知单关联表 同时保存关联表到数据库
			DeliveryReceiptToSalesOrder deliveryReceiptToSalesOrder = new DeliveryReceiptToSalesOrder();
			deliveryReceiptToSalesOrder.setDeliveryReceiptId(deliveryReceipt.getId());
			deliveryReceiptToSalesOrder.setSalesOrderId(salesOrder.getId());
			sentCarSingleManagementDomain.mergeDeliveryReceiptToSalesOrder(deliveryReceiptToSalesOrder);
		}
	}

	public void convDispatchRouteToDeliveryReceipt(DeliveryReceipt deliveryReceipt, DispatchRoute dispatchRoute) throws Exception {
		if (dispatchRoute != null && deliveryReceipt != null) {
			DeliveryReceiptToRoute deliveryReceiptToRoute = new DeliveryReceiptToRoute();
			deliveryReceiptToRoute.setDeliveryreceiptid(deliveryReceipt.getId());
			deliveryReceiptToRoute.setRouteid(dispatchRoute.getId());
			sentCarSingleManagementDomain.mergeDeliveryReceiptToRoute(deliveryReceiptToRoute);
		}
	}

	public List<DeliveryReceiptToDelieryNotification> doListDeliveryReceiptToDelieryNotificationByParams(Map<String, Object> params) {
		List<DeliveryReceiptToDelieryNotification> deliveryReceiptToDelieryNotificationList = null;
		try {
			// 3.执行查询操作
			deliveryReceiptToDelieryNotificationList = sentCarSingleManagementDomain.findDeliveryReceiptToDelieryNotificationByParams(params);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}

		return deliveryReceiptToDelieryNotificationList;
	}

	public List<DeliveryReceiptToRoute> doListDeliveryReceiptToRouteByParams(Map<String, Object> params) {
		List<DeliveryReceiptToRoute> deliveryReceiptToSalesOrderList = null;
		try {
			// 3.执行查询操作
			deliveryReceiptToSalesOrderList = sentCarSingleManagementDomain.findDeliveryReceiptToRouteByParams(params);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}

		return deliveryReceiptToSalesOrderList;
	}

	public List<DeliveryReceiptToSalesOrder> doListDeliveryReceiptToSalesOrderByParams(Map<String, Object> params) {
		List<DeliveryReceiptToSalesOrder> deliveryReceiptToSalesOrderList = null;
		try {
			// 3.执行查询操作
			deliveryReceiptToSalesOrderList = sentCarSingleManagementDomain.findDeliveryReceiptToSalesOrderByParams(params);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "");
		} catch (Exception ex) {
			executeLogger(true, "" + "失败原因:" + ex.getMessage());
		}

		return deliveryReceiptToSalesOrderList;
	}

	/**
	 * 
	 * 
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public List<DeliveryReceipt> doListDeliveryReceiptList(Map<String, Object> params) throws Exception {
		List<DeliveryReceipt> deliveryReceipt = null;

		// 3. 执行查询操作
		deliveryReceipt = sentCarSingleManagementDomain.findDeliveryReceiptList(params);
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		executeLogger(true, "");
		return deliveryReceipt;
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
