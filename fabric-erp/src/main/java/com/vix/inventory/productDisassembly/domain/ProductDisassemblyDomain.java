/**
 * 
 */
package com.vix.inventory.productDisassembly.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("productDisassemblyDomain")
@Transactional
public class ProductDisassemblyDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findInventoryCurrentStockPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, InventoryCurrentStock.class, params);
		return p;
	}

	public InventoryCurrentStock findInventoryCurrentStockById(String id) throws Exception {
		return baseHibernateService.findEntityById(InventoryCurrentStock.class, id);
	}

	public List<InventoryCurrentStock> findInventoryCurrentStockList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(InventoryCurrentStock.class, params);
	}

	public InventoryCurrentStock mergeInventoryCurrentStock(InventoryCurrentStock inventoryCurrentStock) throws Exception {
		return baseHibernateService.merge(inventoryCurrentStock);
	}

	public void deleteWimAdjustpvouchByEntity(InventoryCurrentStock inventoryCurrentStock) throws Exception {
		baseHibernateService.deleteByEntity(inventoryCurrentStock);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(InventoryCurrentStock.class, ids);
	}
}
