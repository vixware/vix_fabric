/**
 * 
 */
package com.vix.inventory.positionadjustment.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.inventory.positionadjustment.entity.WimAdjustpvouch;
import com.vix.inventory.positionadjustment.entity.WimAdjustpvouchItem;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.warehouse.entity.InvShelf;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("positionAdjustmentDomain")
@Transactional
public class PositionAdjustmentDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findWimAdjustpvouchPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, WimAdjustpvouch.class, params);
		return p;
	}

	public List<WimAdjustpvouchItem> findWimAdjustpvouchItemList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(WimAdjustpvouchItem.class, params);
	}

	/** 获取列表数据 */
	public Pager findWimAdjustpvouchPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, WimAdjustpvouch.class, params);
		return p;
	}

	public Pager findInventoryCurrentStockPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, InventoryCurrentStock.class, params);
		return p;
	}

	public WimAdjustpvouch findWimAdjustpvouchById(String id) throws Exception {
		return baseHibernateService.findEntityById(WimAdjustpvouch.class, id);
	}

	public InventoryCurrentStock findInventoryCurrentStockById(String id) throws Exception {
		return baseHibernateService.findEntityById(InventoryCurrentStock.class, id);
	}

	public InvShelf findInvShelfById(String id) throws Exception {
		return baseHibernateService.findEntityById(InvShelf.class, id);
	}

	public WimAdjustpvouchItem findWimAdjustpvouchItemById(String id) throws Exception {
		return baseHibernateService.findEntityById(WimAdjustpvouchItem.class, id);
	}

	public void deleteWimAdjustpvouchItemEntity(WimAdjustpvouchItem wimAdjustpvouchItem) throws Exception {
		baseHibernateService.deleteByEntity(wimAdjustpvouchItem);
	}

	public WimAdjustpvouch mergeWimAdjustpvouch(WimAdjustpvouch wimAdjustpvouch) throws Exception {
		return baseHibernateService.merge(wimAdjustpvouch);
	}

	public InventoryCurrentStock mergeInventoryCurrentStock(InventoryCurrentStock inventoryCurrentStock) throws Exception {
		return baseHibernateService.merge(inventoryCurrentStock);
	}

	public WimAdjustpvouchItem mergeWimAdjustpvouchItem(WimAdjustpvouchItem wimAdjustpvouchItem) throws Exception {
		return baseHibernateService.merge(wimAdjustpvouchItem);
	}

	public void deleteWimAdjustpvouchByEntity(WimAdjustpvouch wimAdjustpvouch) throws Exception {
		baseHibernateService.deleteByEntity(wimAdjustpvouch);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(WimAdjustpvouch.class, ids);
	}

	/** 索引对象列表 */
	public List<WimAdjustpvouch> findWimAdjustpvouchList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(WimAdjustpvouch.class, params);
	}

}
