/**
 * 
 */
package com.vix.inventory.standingbook.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.standingbook.entity.InventoryCurrentStockHistory;
import com.vix.inventory.standingbook.entity.InventoryCurrentStockView;
import com.vix.inventory.takestock.entity.StockTaking;
import com.vix.inventory.takestock.entity.StockTakingItem;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("standingBookDomain")
@Transactional
public class StandingBookDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findInventoryCurrentStockPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, InventoryCurrentStock.class, params);
		return p;
	}

	public Pager findInventoryCurrentStockViewPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, InventoryCurrentStockView.class, params);
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

	public InventoryCurrentStock findInventoryCurrentStockByAttribute(String itemcode) throws Exception {
		return baseHibernateService.findEntityByAttribute(InventoryCurrentStock.class, "itemcode", itemcode);
	}

	public StockTakingItem findStockTakingItemById(String id) throws Exception {
		return baseHibernateService.findEntityById(StockTakingItem.class, id);
	}
	public InventoryCurrentStock findInventoryCurrentStockById(String id) throws Exception {
		return baseHibernateService.findEntityById(InventoryCurrentStock.class, id);
	}

	public void deleteStockTakingItemEntity(StockTakingItem stockTakingItem) throws Exception {
		baseHibernateService.deleteByEntity(stockTakingItem);
	}
	public void deleteInventoryCurrentStockEntity(InventoryCurrentStock inventoryCurrentStock) throws Exception {
		baseHibernateService.deleteByEntity(inventoryCurrentStock);
	}

	public StockTaking mergeStockTaking(StockTaking stockTaking) throws Exception {
		return baseHibernateService.merge(stockTaking);
	}

	public StockTakingItem mergeStockTakingItem(StockTakingItem stockTakingItem) throws Exception {
		return baseHibernateService.merge(stockTakingItem);
	}

	public InventoryCurrentStock mergeInventoryCurrentStock(InventoryCurrentStock inventoryCurrentStock) throws Exception {
		return baseHibernateService.merge(inventoryCurrentStock);
	}
	public InventoryCurrentStockHistory mergeInventoryCurrentStockHistory(InventoryCurrentStockHistory inventoryCurrentStockHistory) throws Exception {
		return baseHibernateService.merge(inventoryCurrentStockHistory);
	}

	public void deleteByEntity(StockTaking stockTaking) throws Exception {
		baseHibernateService.deleteByEntity(stockTaking);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(StockTaking.class, ids);
	}

	/** 索引对象列表 */
	public List<InventoryCurrentStock> findInventoryCurrentStockIndex(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(InventoryCurrentStock.class, params);
	}

}
