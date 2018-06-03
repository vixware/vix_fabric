/**
 * 
 */
package com.vix.inventory.productAssembly.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.core.web.Pager;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.mdm.item.entity.Item;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("productAssemblyDomain")
@Transactional
public class ProductAssemblyDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findInventoryCurrentStockPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, InventoryCurrentStock.class, params);
		return p;
	}

	public InventoryCurrentStock findInventoryCurrentStockById(String id) throws Exception {
		return baseHibernateService.findEntityById(InventoryCurrentStock.class, id);
	}

	public List<InventoryCurrentStock> findInventoryCurrentStock() throws Exception {
		return baseHibernateService.findAllByEntityClass(InventoryCurrentStock.class);
	}

	public InventoryCurrentStock mergeInventoryCurrentStock(InventoryCurrentStock inventoryCurrentStock) throws Exception {
		return baseHibernateService.merge(inventoryCurrentStock);
	}

	public void deleteByEntity(InventoryCurrentStock inventoryCurrentStock) throws Exception {
		baseHibernateService.deleteByEntity(inventoryCurrentStock);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(InventoryCurrentStock.class, ids);
	}

	public Item saveItem(Item item) throws Exception {
		return baseHibernateService.merge(item);
	}

	public List<MeasureUnit> findMeasureUnitList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(MeasureUnit.class, params);
	}

	/** 索引对象列表 */
	public List<InventoryCurrentStock> findInventoryCurrentStockList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(InventoryCurrentStock.class, params);
	}

}
