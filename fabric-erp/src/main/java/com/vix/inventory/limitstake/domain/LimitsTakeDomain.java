/**
 * 
 */
package com.vix.inventory.limitstake.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.inventory.limitstake.entity.StockLimitedTaking;
import com.vix.inventory.limitstake.entity.StockLimitedTakingItem;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("limitsTakeDomain")
@Transactional
public class LimitsTakeDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findStockLimitedTakingPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, StockLimitedTaking.class, params);
		return p;
	}

	public StockLimitedTaking findStockLimitedTakingById(String id) throws Exception {
		return baseHibernateService.findEntityById(StockLimitedTaking.class, id);
	}

	public StockLimitedTakingItem findStockLimitedTakingItemById(String id) throws Exception {
		return baseHibernateService.findEntityById(StockLimitedTakingItem.class, id);
	}

	public void deleteWimStockrecordlinesEntity(StockLimitedTakingItem stockLimitedTakingItem) throws Exception {
		baseHibernateService.deleteByEntity(stockLimitedTakingItem);
	}

	public StockLimitedTaking mergeStockLimitedTaking(StockLimitedTaking stockLimitedTaking) throws Exception {
		return baseHibernateService.merge(stockLimitedTaking);
	}

	public StockLimitedTakingItem mergeStockLimitedTakingItem(StockLimitedTakingItem stockLimitedTakingItem) throws Exception {
		return baseHibernateService.merge(stockLimitedTakingItem);
	}

	public void deleteByEntity(StockLimitedTaking stockLimitedTaking) throws Exception {
		baseHibernateService.deleteByEntity(stockLimitedTaking);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(StockLimitedTaking.class, ids);
	}

	/** 索引对象列表 */
	public List<StockLimitedTaking> findStockLimitedTakingList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(StockLimitedTaking.class, params);
	}
}
