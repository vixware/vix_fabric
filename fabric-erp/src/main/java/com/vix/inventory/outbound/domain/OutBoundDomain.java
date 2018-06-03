/**
 * 
 */
package com.vix.inventory.outbound.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.ebusiness.entity.Order;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.inbound.entity.StockRecordLines;
import com.vix.inventory.inbound.entity.StockRecords;
import com.vix.inventory.option.entity.InventoryParameters;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.standingbook.entity.MasterInventoryCurrentStock;
import com.vix.mdm.item.entity.Item;
import com.vix.sales.order.entity.SalesOrder;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("outBoundDomain")
@Transactional
public class OutBoundDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findStockRecordsPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, StockRecords.class, params);
		return p;
	}

	public Pager findStockRecordLinesPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, StockRecordLines.class, params);
		return p;
	}

	public InventoryParameters findInventoryParametersByAttribute(String attribute, String value) throws Exception {
		return baseHibernateService.findEntityByAttribute(InventoryParameters.class, attribute, value);
	}

	public Pager findMasterInventoryCurrentStockPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, MasterInventoryCurrentStock.class, params);
		return p;
	}

	public Pager findInventoryCurrentStockPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, InventoryCurrentStock.class, params);
		return p;
	}

	public SalesOrder saveOrUpdateSalesOrder(SalesOrder salesOrder) throws Exception {
		return baseHibernateService.merge(salesOrder);
	}

	public StockRecords findStockRecordsById(String id) throws Exception {
		return baseHibernateService.findEntityById(StockRecords.class, id);
	}

	public List<StockRecordLines> findStockRecordLinesList(Map<String, Object> params) throws Exception {

		return baseHibernateService.findAllByConditions(StockRecordLines.class, params);
	}

	public MasterInventoryCurrentStock findMasterInventoryCurrentStockById(String id) throws Exception {
		return baseHibernateService.findEntityById(MasterInventoryCurrentStock.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return baseHibernateService.findEntityById(Employee.class, id);
	}

	/**
	 * 
	 * @param stockRecordLines
	 * @throws Exception
	 */
	public void deleteStockRecordLines(StockRecordLines stockRecordLines) throws Exception {
		baseHibernateService.deleteByEntity(stockRecordLines);
	}

	public Pager findSalesOrder(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, SalesOrder.class, params);
		return p;
	}

	public SalesOrder findSalesOrderById(String id) throws Exception {
		return baseHibernateService.findEntityById(SalesOrder.class, id);
	}

	public List<SalesOrder> findSalesOrderList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(SalesOrder.class, params);
	}

	public List<InventoryCurrentStock> findInventoryCurrentStockList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(InventoryCurrentStock.class, params);
	}

	public Order findOrderById(String id) throws Exception {
		return baseHibernateService.findEntityById(Order.class, id);
	}

	public StockRecordLines findStockRecordLinesById(String id) throws Exception {
		return baseHibernateService.findEntityById(StockRecordLines.class, id);
	}

	public StockRecords mergeStockRecords(StockRecords stockRecords) throws Exception {
		return baseHibernateService.merge(stockRecords);
	}

	public InventoryCurrentStock findInventoryCurrentStockByHql(String sql, Map<String, Object> params) throws Exception {
		return baseHibernateService.findObjectByHql(sql, params);
	}

	public StockRecordLines mergeStockRecordLines(StockRecordLines stockRecordLines) throws Exception {
		return baseHibernateService.merge(stockRecordLines);
	}

	public Item findItem(String itemcode) throws Exception {
		return baseHibernateService.findEntityByAttribute(Item.class, "code", itemcode);
	}

	public Order mergeOrder(Order order) throws Exception {
		return baseHibernateService.merge(order);
	}

	public InventoryCurrentStock saveOrUpdateInventoryCurrentStock(InventoryCurrentStock inventoryCurrentStock) throws Exception {
		return baseHibernateService.merge(inventoryCurrentStock);
	}

	/* 获取库存信息 */
	public InventoryCurrentStock findInventoryCurrentStockByAttribute(String attribute, String value) throws Exception {
		return baseHibernateService.findEntityByAttribute(InventoryCurrentStock.class, attribute, value);
	}

	public void deleteStockRecords(StockRecords stockRecords) throws Exception {
		baseHibernateService.deleteByEntity(stockRecords);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(StockRecords.class, ids);
	}

	/** 索引对象列表 */
	public List<StockRecords> findStockRecordsList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(StockRecords.class, params);
	}

}
