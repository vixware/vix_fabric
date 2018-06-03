/**
 * 
 */
package com.vix.inventory.shelfLife.domain;

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
@Component("shelfLifeDomain")
@Transactional
public class ShelfLifeDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findInventoryCurrentStockPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, InventoryCurrentStock.class, params);
		return p;
	}

	/** 索引对象列表 */
	public List<InventoryCurrentStock> findInventoryCurrentStockList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(InventoryCurrentStock.class, params);
	}
}
