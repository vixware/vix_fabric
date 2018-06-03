/**
 * 
 */
package com.vix.inventory.outbound.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.ebusiness.entity.Order;
import com.vix.ebusiness.entity.OrderDetail;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.inbound.entity.StockRecordLines;
import com.vix.inventory.inbound.entity.StockRecords;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.inventory.option.entity.InventoryParameters;
import com.vix.inventory.outbound.domain.OutBoundDomain;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.standingbook.entity.MasterInventoryCurrentStock;
import com.vix.mdm.item.entity.Item;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("outBoundController")
@Scope("prototype")
public class OutBoundController {

	Logger logger = Logger.getLogger(OutBoundController.class);

	@Autowired
	private OutBoundDomain outBoundDomain;
	@Resource(name = "standingBookHqlProvider")
	private StandingBookHqlProvider standingBookHqlProvider;

	/**
	 * 
	 * 
	 * @param stockRecords
	 * @return
	 * @throws Exception
	 */
	public StockRecords doSaveOrUpdateStockRecords(StockRecords stockRecords) throws Exception {
		StockRecords stockRecords1 = outBoundDomain.mergeStockRecords(stockRecords);
		if (stockRecords1 != null) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("stockRecords.id," + SearchCondition.EQUAL, stockRecords1.getId());
			List<StockRecordLines> stockRecordLinesList = outBoundDomain.findStockRecordLinesList(params);
			if (stockRecordLinesList != null && stockRecordLinesList.size() > 0) {
				for (StockRecordLines stockRecordLines : stockRecordLinesList) {
					stockRecordLines = dealInventory(stockRecords1, stockRecordLines);
				}
			}
		}
		return stockRecords1;
	}

	/**
	 * @param stockRecords
	 * @param stockRecordLines
	 * @return
	 * @throws Exception
	 */
	private StockRecordLines dealInventory(StockRecords stockRecords, StockRecordLines stockRecordLines) throws Exception {
		InventoryCurrentStock inventoryCurrentStock = null;
		if (stockRecordLines != null) {
			if (!"2".equals(stockRecordLines.getIsUpdateStore())) {
				if (stockRecordLines.getSkuCode() != null) {
					// 需要通过店铺,仓库,商品编码,skuCode查询库存信息
					Map<String, Object> params = new HashMap<String, Object>();
					// 过滤临时数据
					params.put("isQualfied", 1);
					if (StringUtils.isNotEmpty(stockRecordLines.getSkuCode())) {
						params.put("skuCode", stockRecordLines.getSkuCode());
					}
					if (StringUtils.isNotEmpty(stockRecordLines.getItemcode())) {
						params.put("itemcode", stockRecordLines.getItemcode());
					}
					if (StringUtils.isNotEmpty(stockRecordLines.getBatchcode())) {
						params.put("batchcode", stockRecordLines.getBatchcode());
					}
					if (stockRecords.getInvWarehouse() != null && stockRecords.getInvWarehouse().getId() != null) {
						params.put("invWarehouseId", stockRecords.getInvWarehouse().getId());
					}
					if (stockRecords.getChannelDistributor() != null && stockRecords.getChannelDistributor().getId() != null) {
						params.put("channelDistributorId", stockRecords.getChannelDistributor().getId());
					}
					StringBuilder hql = standingBookHqlProvider.findInventoryCurrentStockBySkuCode(params);
					inventoryCurrentStock = outBoundDomain.findInventoryCurrentStockByHql(hql.toString(), params);
				}
			}
			if (inventoryCurrentStock != null) {
				if (stockRecordLines.getQuantity() != null) {
					if (inventoryCurrentStock.getAvaquantity() != null) {
						inventoryCurrentStock.setAvaquantity(inventoryCurrentStock.getAvaquantity() - stockRecordLines.getQuantity());
					}
					if (inventoryCurrentStock.getQuantity() != null) {
						inventoryCurrentStock.setQuantity(inventoryCurrentStock.getQuantity() - stockRecordLines.getQuantity());
					}
				}
				inventoryCurrentStock = outBoundDomain.saveOrUpdateInventoryCurrentStock(inventoryCurrentStock);
				stockRecordLines.setIsUpdateStore("2");
				stockRecordLines = outBoundDomain.mergeStockRecordLines(stockRecordLines);
			}
		}
		return stockRecordLines;
	}

	public Item doListItem(String itemcode) throws Exception {
		Item item = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		item = outBoundDomain.findItem(itemcode);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return item;
	}

	public StockRecordLines doSaveStockRecordLines(StockRecordLines stockRecordLines) throws Exception {
		// 计算金额 ：金额 = 单价 * 数量
		Double price = null;
		if (stockRecordLines.getQuantity() != null && stockRecordLines.getUnitcost() != null) {
			price = stockRecordLines.getQuantity() * stockRecordLines.getUnitcost();
		}
		if (price != null) {
			stockRecordLines.setPrice(price);
		} else {
			stockRecordLines.setPrice(0d);
		}
		return outBoundDomain.mergeStockRecordLines(stockRecordLines);
	}

	public SalesOrder doSaveSalesOrder(SalesOrder salesOrder) throws Exception {
		return outBoundDomain.saveOrUpdateSalesOrder(salesOrder);
	}

	/**
	 * 
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager doListStockrecords(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行保存操作
		p = outBoundDomain.findStockRecordsPager(params, pager);

		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return p;
	}

	public Pager doListStockRecordLinesPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		p = outBoundDomain.findStockRecordLinesPager(params, pager);

		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return p;
	}

	public InventoryParameters doListInventoryParametersByAttribute(String attribute, String value) throws Exception {
		InventoryParameters inventoryParameters = outBoundDomain.findInventoryParametersByAttribute(attribute, value);
		return inventoryParameters;
	}

	public Pager doListMasterInventoryCurrentStockPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行保存操作
		p = outBoundDomain.findMasterInventoryCurrentStockPager(params, pager);

		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return p;
	}

	public Pager doListInventoryCurrentStockPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行保存操作
		p = outBoundDomain.findInventoryCurrentStockPager(params, pager);

		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return p;
	}

	/**
	 * 简单查询
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager doListStockRecordsPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		p = outBoundDomain.findStockRecordsPager(params, pager);

		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return p;
	}

	/**
	 * 
	 * 
	 * @param stockRecords
	 * @throws Exception
	 */
	public void doDeleteStockRecords(StockRecords stockRecords) throws Exception {
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3. 执行删除操作
		outBoundDomain.deleteStockRecords(stockRecords);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
	}

	/**
	 * 
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void doDeleteByIds(List<String> ids) throws Exception {
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3. 执行删除操作
		outBoundDomain.deleteByIds(ids);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
	}

	/**
	 * 
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager doSubSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3.执行查询操作
		p = outBoundDomain.findStockRecordsPager(params, pager);

		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return p;
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public StockRecords doListStockRecordsById(String id) throws Exception {
		StockRecords stockRecords = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		stockRecords = outBoundDomain.findStockRecordsById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return stockRecords;
	}

	public List<StockRecordLines> doListStockRecordLinesList(Map<String, Object> params) throws Exception {
		List<StockRecordLines> stockRecords = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		stockRecords = outBoundDomain.findStockRecordLinesList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return stockRecords;
	}

	public MasterInventoryCurrentStock doListMasterInventoryCurrentStockById(String id) throws Exception {
		MasterInventoryCurrentStock masterInventoryCurrentStock = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		masterInventoryCurrentStock = outBoundDomain.findMasterInventoryCurrentStockById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return masterInventoryCurrentStock;
	}

	public Employee doListEmployeeById(String id) throws Exception {
		Employee employee = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		employee = outBoundDomain.findEmployeeById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return employee;
	}

	public void deleteStockRecordLines(StockRecordLines stockRecordLines) throws Exception {
		outBoundDomain.deleteStockRecordLines(stockRecordLines);
	}

	/** 获取采购订单列表数据 */
	public Pager goPurchaseOrder(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = outBoundDomain.findSalesOrder(params, pager);
		return p;
	}

	public SalesOrder findSalesOrder(String id) throws Exception {
		SalesOrder salesOrder = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		salesOrder = outBoundDomain.findSalesOrderById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return salesOrder;
	}

	public List<SalesOrder> doListSalesOrderList(Map<String, Object> params) throws Exception {
		List<SalesOrder> salesOrderList = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		salesOrderList = outBoundDomain.findSalesOrderList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return salesOrderList;
	}

	public List<InventoryCurrentStock> doListInventoryCurrentStockList(Map<String, Object> params) throws Exception {
		List<InventoryCurrentStock> inventoryCurrentStockList = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		inventoryCurrentStockList = outBoundDomain.findInventoryCurrentStockList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return inventoryCurrentStockList;
	}

	public Order findOrder(String id) throws Exception {
		Order order = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		order = outBoundDomain.findOrderById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return order;
	}

	/**
	 * 
	 * @param stockRecords
	 * @param salesOrder
	 * @throws Exception
	 */
	public void convPurchaseOrderToStockrecords(StockRecords stockRecords, SalesOrder salesOrder) throws Exception {
		// 销售订单明细
		Set<SaleOrderItem> saleOrderItems = new HashSet<SaleOrderItem>();
		// 入库单明细
		List<StockRecordLines> stockrecordlinesList = new ArrayList<StockRecordLines>();
		if (salesOrder != null) {
			saleOrderItems = salesOrder.getSaleOrderItems();
			for (SaleOrderItem saleOrderItem : saleOrderItems) {
				// 将采购订单明细转化成入库单明细
				if (saleOrderItem != null) {
					StockRecordLines stockrecordlines = new StockRecordLines();
					stockrecordlines.setPoCode(salesOrder.getCode());
					stockrecordlines.setPurchaseOrderItemCode(saleOrderItem.getCode());
					stockrecordlines.setItemcode(saleOrderItem.getItemCode());
					stockrecordlines.setItemname(saleOrderItem.getName());
					stockrecordlines.setSpecification(saleOrderItem.getSpecification());
					stockrecordlines.setWarehouseCode(stockRecords.getWarehousecode());
					if (stockRecords.getInvWarehouse() != null) {
						stockrecordlines.setInvWarehouse(stockRecords.getInvWarehouse());
					}
					stockrecordlines.setSkuCode(saleOrderItem.getSkuCode());
					stockrecordlines.setUnit(saleOrderItem.getUnit());
					stockrecordlines.setUnitcost(saleOrderItem.getPrice());
					stockrecordlines.setQuantity(saleOrderItem.getAmount());
					Double price = null;
					if (saleOrderItem.getPrice() != null && saleOrderItem.getAmount() != null) {
						price = saleOrderItem.getPrice() * saleOrderItem.getAmount();
					}
					if (saleOrderItem.getNetPrice() != null) {
						stockrecordlines.setPrice(saleOrderItem.getNetPrice());
					} else {
						stockrecordlines.setPrice(price);
					}
					stockrecordlinesList.add(stockrecordlines);
				}
			}
		}
		if (stockrecordlinesList != null && stockrecordlinesList.size() > 0) {
			for (StockRecordLines stockrecordlines : stockrecordlinesList) {
				if (stockrecordlines != null) {
					stockrecordlines.setStockRecords(stockRecords);
					outBoundDomain.mergeStockRecordLines(stockrecordlines);
				}
			}
		}
	}

	public void businessOrderToStockrecords(StockRecords stockRecords, Order order) throws Exception {
		// 网店订单明细
		Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();
		if (order != null) {
			orderDetails = order.getOrderDetailList();
			for (OrderDetail orderDetail : orderDetails) {
				// 将网店订单明细转化成入库单明细
				if (orderDetail != null) {
					StockRecordLines stockrecordlines = new StockRecordLines();
					stockrecordlines.setPoCode(order.getOuterId());
					stockrecordlines.setCode(orderDetail.getOuterId());
					stockrecordlines.setItemcode(orderDetail.getOuterGoodsId());
					stockrecordlines.setItemname(orderDetail.getTitle());
					stockrecordlines.setSpecification(orderDetail.getSkuPropertiesName());
					if (orderDetail.getPrice() != null) {
						stockrecordlines.setUnitcost(orderDetail.getPrice());
					}
					if (orderDetail.getNum() != null) {
						stockrecordlines.setQuantity(orderDetail.getNum());
					}
					stockrecordlines.setStatus("未拣货");
					Double price = null;
					if (orderDetail.getPrice() != null && orderDetail.getNum() != null) {
						price = orderDetail.getPrice() * orderDetail.getNum();
					}
					stockrecordlines.setPrice(price);
					stockrecordlines.setStockRecords(stockRecords);
					outBoundDomain.mergeStockRecordLines(stockrecordlines);
					/* 同时减掉库存 */
					InventoryCurrentStock inventoryCurrentStock = outBoundDomain.findInventoryCurrentStockByAttribute("itemcode", orderDetail.getOuterGoodsId());
					if (inventoryCurrentStock.getQuantity() != null && orderDetail.getNum() != null) {
						inventoryCurrentStock.setQuantity(inventoryCurrentStock.getQuantity() - orderDetail.getNum());
					}
					outBoundDomain.saveOrUpdateInventoryCurrentStock(inventoryCurrentStock);
				}
			}
			order.setDealState(2);
			outBoundDomain.mergeOrder(order);
		}
	}

	public StockRecordLines doListStockRecordLinesById(String id) throws Exception {
		StockRecordLines stockRecordLines = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		stockRecordLines = outBoundDomain.findStockRecordLinesById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return stockRecordLines;
	}

	/**
	 * 保存入库单明细
	 * 
	 * @param wimStockrecords
	 * @param wimStockrecordlinesList
	 * @param biztype
	 * @return
	 * @throws Exception
	 */

	/**
	 * 
	 * 
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public List<StockRecords> doListStockRecordsList(Map<String, Object> params) throws Exception {
		List<StockRecords> stockRecordsList = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		stockRecordsList = outBoundDomain.findStockRecordsList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return stockRecordsList;
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
