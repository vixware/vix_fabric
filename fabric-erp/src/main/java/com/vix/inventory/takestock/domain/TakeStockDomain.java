/**
 * 
 */
package com.vix.inventory.takestock.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.takestock.entity.StockTaking;
import com.vix.inventory.takestock.entity.StockTakingItem;
import com.vix.inventory.warehouse.entity.InvWarehouse;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("takeStockDomain")
@Transactional
public class TakeStockDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findStockTakingPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, StockTaking.class, params);
		return p;
	}
	public Pager findStockTakingItemPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, StockTakingItem.class, params);
		return p;
	}

	/** 获取列表数据 */
	public Pager findStockTakingPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, StockTaking.class, params);
		return p;
	}

	public StockTaking findStockTakingById(String id) throws Exception {
		return baseHibernateService.findEntityById(StockTaking.class, id);
	}

	public InvWarehouse findInvWarehouseById(String id) throws Exception {
		return baseHibernateService.findEntityById(InvWarehouse.class, id);
	}

	public InventoryCurrentStock mergeInventoryCurrentStock(InventoryCurrentStock inventoryCurrentStock) throws Exception {
		return baseHibernateService.merge(inventoryCurrentStock);
	}

	public InventoryCurrentStock findInventoryCurrentStockByAttribute(String itemcode) throws Exception {
		return baseHibernateService.findEntityByAttribute(InventoryCurrentStock.class, "itemcode", itemcode);
	}

	public List<InventoryCurrentStock> findInventoryCurrentStock() throws Exception {
		return baseHibernateService.findAllByEntityClass(InventoryCurrentStock.class);
	}

	public List<InventoryCurrentStock> findInventoryCurrentStockList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(InventoryCurrentStock.class, params);
	}

	public List<InvWarehouse> findInvWarehouseList() throws Exception {
		return baseHibernateService.findAllByEntityClass(InvWarehouse.class);
	}

	public StockTakingItem findStockTakingItemById(String id) throws Exception {
		return baseHibernateService.findEntityById(StockTakingItem.class, id);
	}

	public void deleteStockTakingItemEntity(StockTakingItem stockTakingItem) throws Exception {
		baseHibernateService.deleteByEntity(stockTakingItem);
	}

	public StockTaking mergeStockTaking(StockTaking stockTaking) throws Exception {
		return baseHibernateService.merge(stockTaking);
	}

	public StockTakingItem mergeStockTakingItem(StockTakingItem stockTakingItem) throws Exception {
		return baseHibernateService.merge(stockTakingItem);
	}

	public void deleteByEntity(StockTaking stockTaking) throws Exception {
		baseHibernateService.deleteByEntity(stockTaking);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(StockTaking.class, ids);
	}

	/** 索引对象列表 */
	public List<StockTaking> findStockTakingList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(StockTaking.class, params);
	}

	public List<StockTakingItem> findStockTakingItemList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(StockTakingItem.class, params);
	}

}
