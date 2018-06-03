/**
 * 
 */
package com.vix.dtbcenter.orderprocessing.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.share.entity.MeasureUnit;
import com.vix.core.web.Pager;
import com.vix.dtbcenter.orderprocessing.domain.OrderProcessingDomain;
import com.vix.mdm.item.entity.Item;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("orderProcessingController")
@Scope("prototype")
public class OrderProcessingController {

	Logger logger = Logger.getLogger(OrderProcessingController.class);

	@Autowired
	private OrderProcessingDomain orderProcessingDomain;

	/**
	 * 
	 * @param wimStockrecords
	 * @return
	 * @throws Exception
	 */
	public SalesOrder doSaveSalesOrder(SalesOrder salesOrder, List<SaleOrderItem> saleOrderItemsDeliveryPlanList) throws Exception {
		SalesOrder salesOrder1 = null;
		salesOrder.setUpdateTime(new Date());
		salesOrder1 = orderProcessingDomain.mergeSalesOrder(salesOrder);
		if (saleOrderItemsDeliveryPlanList != null && saleOrderItemsDeliveryPlanList.size() > 0) {
			for (SaleOrderItem saleOrderItemsDeliveryPlan : saleOrderItemsDeliveryPlanList) {
				saleOrderItemsDeliveryPlan.setSalesOrder(salesOrder1);
				orderProcessingDomain.mergeSaleOrderItem(saleOrderItemsDeliveryPlan);
			}
		}
		return salesOrder1;
	}

	public SaleOrderItem doSaveSaleOrderItem(SaleOrderItem saleOrderItem) throws Exception {
		SaleOrderItem saleOrderItem1 = null;
		saleOrderItem.setUpdateTime(new Date());
		if (saleOrderItem.getPrice() != null && !"".equals(saleOrderItem.getPrice()) && saleOrderItem.getAmount() != null && !"".equals(saleOrderItem.getAmount())) {
			saleOrderItem.setNetTotal(saleOrderItem.getPrice() * saleOrderItem.getAmount());
		}
		saleOrderItem1 = orderProcessingDomain.mergeSaleOrderItem(saleOrderItem);
		return saleOrderItem1;
	}

	/**
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager doListSalesOrder(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;

		// 执行查询操作
		p = orderProcessingDomain.findSalesOrderPagerByHqlConditions(params, pager);

		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return p;
	}

	/**
	 * 
	 * @param salesOrder
	 * @throws Exception
	 */
	public void doDeleteSalesOrder(SalesOrder salesOrder) throws Exception {
		// 执行删除操作
		orderProcessingDomain.deleteSalesOrderByEntity(salesOrder);
		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		executeLogger(true, "！");
	}

	public void doDeleteSaleOrderItem(SaleOrderItem salesOrder) throws Exception {
		// 执行删除操作
		orderProcessingDomain.deleteSaleOrderItemByEntity(salesOrder);
		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		executeLogger(true, "！");
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SalesOrder doListSalesOrderById(String id) throws Exception {
		SalesOrder salesOrder = null;

		// 3.执行查询操作
		salesOrder = orderProcessingDomain.findSalesOrderById(id);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return salesOrder;
	}

	public SaleOrderItem doListSaleOrderItemById(String id) throws Exception {
		SaleOrderItem saleOrderItem = null;

		// 3.执行查询操作
		saleOrderItem = orderProcessingDomain.findSaleOrderItemById(id);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return saleOrderItem;
	}

	public Item doListItemById(String id) throws Exception {
		Item item = null;

		// 3.执行查询操作
		item = orderProcessingDomain.findItemById(id);

		return item;
	}

	public void convItemToGoodsDetail(SalesOrder salesOrder, Item item) throws Exception {
		if (salesOrder != null) {
			// 将商品信息转化成货物信息
			if (item != null) {
				SaleOrderItem saleOrderItem = new SaleOrderItem();
				saleOrderItem.setItemCode(item.getCode());
				saleOrderItem.setName(item.getName());
				saleOrderItem.setSpecification(item.getSpecification());
				saleOrderItem.setSalesOrder(salesOrder);
				orderProcessingDomain.mergeSaleOrderItem(saleOrderItem);
			}
		}
	}

	public Pager getItem(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = orderProcessingDomain.findItem(params, pager);
		return p;
	}

	/**
	 * 
	 * 
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public List<SalesOrder> doListSalesOrderList(Map<String, Object> params) throws Exception {
		List<SalesOrder> salesOrder = null;

		// 3. 执行查询操作
		salesOrder = orderProcessingDomain.findSalesOrderList(params);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return salesOrder;
	}

	public List<MeasureUnit> doListMeasureUnitList(Map<String, Object> params) throws Exception {
		List<MeasureUnit> measureUnit = null;

		// 3. 执行查询操作
		measureUnit = orderProcessingDomain.findMeasureUnitList(params);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return measureUnit;
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
	 * 清除缓存对象
	 * 
	 * @param obj
	 */
	public void evict(Object obj) {
		orderProcessingDomain.evict(obj);
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
