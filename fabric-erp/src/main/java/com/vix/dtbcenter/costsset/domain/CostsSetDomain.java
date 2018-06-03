/**
 * 
 */
package com.vix.dtbcenter.costsset.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.dtbcenter.costsset.entity.CostItem;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("costsSetDomain")
@Transactional
public class CostsSetDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findCostItemPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, CostItem.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findCostItemPagerByHql(String hql, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerJustByHql(pager, hql);
		return p;
	}

	public CostItem findCostItemById(String id) throws Exception {
		return baseHibernateService.findEntityById(CostItem.class, id);
	}

	public CostItem mergeCostItem(CostItem costItem) throws Exception {
		return baseHibernateService.merge(costItem);
	}

	/** 索引对象列表 */
	public List<CostItem> findCostItemIndex(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(CostItem.class, params);
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
