/**
 * 
 */
package com.vix.inventory.inbound.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.batchStandingBook.entity.InvMainBatch;
import com.vix.inventory.inbound.entity.StockRecordLines;
import com.vix.inventory.inbound.entity.StockRecords;
import com.vix.inventory.inventorytype.entity.InventoryType;
import com.vix.inventory.option.entity.InventoryParameters;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.standingbook.entity.MasterInventoryCurrentStock;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemInventoryProperties;
import com.vix.mdm.item.entity.StoreItem;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.srm.share.entity.Supplier;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("inboundWarehouseDomain")
@Transactional
public class InboundWarehouseDomain extends BaseDomain {

	/** 获取列表数据 */
	public Pager findStockRecordsPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, StockRecords.class, params);
		return p;
	}

	public Pager findStockRecordLinesPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, StockRecordLines.class, params);
		return p;
	}

	/** 获取列表数据 */
	public Pager findStockRecordsPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, StockRecords.class, params);
		return p;
	}

	public StockRecords findStockRecordsById(String id) throws Exception {
		return baseHibernateService.findEntityById(StockRecords.class, id);
	}
	public ItemInventoryProperties findItemInventoryPropertiesByItemId(String id) throws Exception {
		return baseHibernateService.findEntityByAttribute(ItemInventoryProperties.class, "item.id", id);
	}

	public InventoryCurrentStock findInventoryCurrentStock(String itemcode) throws Exception {
		return baseHibernateService.findEntityByAttribute(InventoryCurrentStock.class, "itemcode", itemcode);
	}

	public Item findItem(String itemcode) throws Exception {
		return baseHibernateService.findEntityByAttribute(Item.class, "code", itemcode);
	}

	public PurchaseOrder findPurchaseOrderById(String id) throws Exception {
		return baseHibernateService.findEntityById(PurchaseOrder.class, id);
	}

	public StockRecordLines findStockRecordLinesById(String id) throws Exception {
		return baseHibernateService.findEntityById(StockRecordLines.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return baseHibernateService.findEntityById(Employee.class, id);
	}

	public Pager findSupplierPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Supplier.class, params);
		return p;
	}

	public Pager findPurchaseOrderPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PurchaseOrder.class, params);
		return p;
	}

	/**
	 * 
	 * @param stockRecordLines
	 * @throws Exception
	 */
	public void deleteStockRecordLines(StockRecordLines stockRecordLines) throws Exception {
		baseHibernateService.deleteByEntity(stockRecordLines);
	}

	/**
	 * 保存入库单
	 */
	public StockRecords merge(StockRecords stockRecords) throws Exception {
		return baseHibernateService.merge(stockRecords);
	}

	/**
	 * 保存批次主文件
	 */
	public InvMainBatch saveOrUpdateInvMainBatch(InvMainBatch invMainBatch) throws Exception {
		return baseHibernateService.merge(invMainBatch);
	}

	/**
	 * 查询
	 */
	public InvMainBatch findInvMainBatch(String attribute, Object value) throws Exception {
		return baseHibernateService.findEntityByAttribute(InvMainBatch.class, attribute, value);
	}

	/**
	 * 保存入库单明细
	 */
	public StockRecordLines mergeStockRecordLines(StockRecordLines stockRecordLines) throws Exception {
		return baseHibernateService.merge(stockRecordLines);
	}

	public PurchaseOrder saveOrUpdatePurchaseOrder(PurchaseOrder purchaseOrder) throws Exception {
		return baseHibernateService.merge(purchaseOrder);
	}

	public InventoryCurrentStock findInventoryCurrentStockByHql(String sql, Map<String, Object> params) throws Exception {
		return baseHibernateService.findObjectByHql(sql, params);
	}

	public MasterInventoryCurrentStock findMasterInventoryCurrentStockByHql(String sql, Map<String, Object> params) throws Exception {
		return baseHibernateService.findObjectByHql(sql, params);
	}

	public StoreItem findStoreItemByHql(String sql, Map<String, Object> params) throws Exception {
		return baseHibernateService.findObjectByHql(sql, params);
	}

	/* 获取库存信息 */
	public InventoryCurrentStock findInventoryCurrentStockByAttribute(String attribute, String value) throws Exception {
		return baseHibernateService.findEntityByAttribute(InventoryCurrentStock.class, attribute, value);
	}

	public InventoryParameters findInventoryParametersByAttribute(String attribute, String value) throws Exception {
		return baseHibernateService.findEntityByAttribute(InventoryParameters.class, attribute, value);
	}

	public InventoryCurrentStock saveOrUpdateInventoryCurrentStock(InventoryCurrentStock inventoryCurrentStock) throws Exception {
		return baseHibernateService.merge(inventoryCurrentStock);
	}

	public MasterInventoryCurrentStock saveOrUpdateMasterInventoryCurrentStock(MasterInventoryCurrentStock masterInventoryCurrentStock) throws Exception {
		return baseHibernateService.merge(masterInventoryCurrentStock);
	}

	public void deleteStockRecordsByEntity(StockRecords stockRecords) throws Exception {
		baseHibernateService.deleteByEntity(stockRecords);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(StockRecords.class, ids);
	}

	/** 索引对象列表 */
	public List<StockRecords> findStockRecordsList(Map<String, Object> params) throws Exception {

		return baseHibernateService.findAllByConditions(StockRecords.class, params);
	}

	public List<StockRecordLines> findStockRecordLinesList(Map<String, Object> params) throws Exception {

		return baseHibernateService.findAllByConditions(StockRecordLines.class, params);
	}

	public List<PurchaseOrder> findPurchaseOrderList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(PurchaseOrder.class, params);
	}

	public List<MeasureUnit> findMeasureUnitList() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		return baseHibernateService.findAllByConditions(MeasureUnit.class, params);
	}

	public List<InventoryType> findInventoryTypeList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(InventoryType.class, params);
	}

	/**
	 * 清除缓存对象
	 * 
	 * @param obj
	 */
	public void evict(Object obj) {
		baseHibernateService.evict(obj);
	}
}
