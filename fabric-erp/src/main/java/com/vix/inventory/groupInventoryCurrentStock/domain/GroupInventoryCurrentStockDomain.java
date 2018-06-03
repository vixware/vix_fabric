/**
 * 
 */
package com.vix.inventory.groupInventoryCurrentStock.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.inventory.standingbook.entity.GroupInventoryCurrentStock;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.mdm.item.entity.Item;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("groupInventoryCurrentStockDomain")
@Transactional
public class GroupInventoryCurrentStockDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findGroupInventoryCurrentStockPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, GroupInventoryCurrentStock.class, params);
		return p;
	}

	/** 获取列表数据 */
	public Pager findInventoryCurrentStockPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, InventoryCurrentStock.class, params);
		return p;
	}

	public GroupInventoryCurrentStock findGroupInventoryCurrentStockById(String id) throws Exception {
		return baseHibernateService.findEntityById(GroupInventoryCurrentStock.class, id);
	}

	public InventoryCurrentStock findInventoryCurrentStockById(String id) throws Exception {
		return baseHibernateService.findEntityById(InventoryCurrentStock.class, id);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(GroupInventoryCurrentStock.class, ids);
	}

	public void deleteGroupInventoryCurrentStockByEntity(GroupInventoryCurrentStock groupInventoryCurrentStock) throws Exception {
		baseHibernateService.deleteByEntity(groupInventoryCurrentStock);
	}

	public List<InventoryCurrentStock> findInventoryCurrentStock() throws Exception {
		return baseHibernateService.findAllByEntityClass(InventoryCurrentStock.class);
	}

	public GroupInventoryCurrentStock saveOrUpdateGroupInventoryCurrentStock(GroupInventoryCurrentStock groupInventoryCurrentStock) throws Exception {
		return baseHibernateService.merge(groupInventoryCurrentStock);
	}

	public InventoryCurrentStock saveOrUpdateInventoryCurrentStock(InventoryCurrentStock inventoryCurrentStock) throws Exception {
		return baseHibernateService.merge(inventoryCurrentStock);
	}

	public Item saveItem(Item item) throws Exception {
		return baseHibernateService.merge(item);
	}

	/** 索引对象列表 */
	public List<GroupInventoryCurrentStock> findGroupInventoryCurrentStockList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(GroupInventoryCurrentStock.class, params);
	}

}
