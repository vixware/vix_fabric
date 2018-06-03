/**
 * 
 */
package com.vix.dtbcenter.distributioncost.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.dtbcenter.costestimating.entity.SaleOrderCost;
import com.vix.dtbcenter.costestimating.entity.SaleOrderCostItem;
import com.vix.dtbcenter.costsset.entity.CostItem;
import com.vix.sales.order.entity.SalesOrder;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("distributionCostDomain")
@Transactional
public class DistributionCostDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findSaleOrderCostPagerByHql(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, SaleOrderCost.class, params);
		return p;
	}

	public Pager findCostItemPagerByHql(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, CostItem.class, params);
		return p;
	}

	/** 获取列表数据 */
	public Pager findSaleOrderCostPagerByOrHql(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, SaleOrderCost.class, params);
		return p;
	}

	public SaleOrderCost findSaleOrderCostById(String id) throws Exception {
		return baseHibernateService.findEntityById(SaleOrderCost.class, id);
	}

	public CostItem findCostItemById(String id) throws Exception {
		return baseHibernateService.findEntityById(CostItem.class, id);
	}

	public Pager findSalesOrderPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, SalesOrder.class, params);
		return p;
	}

	public SaleOrderCostItem mergeSaleOrderCostItem(SaleOrderCostItem saleOrderCostItem) throws Exception {
		return baseHibernateService.merge(saleOrderCostItem);
	}

	public SaleOrderCost mergeSaleOrderCost(SaleOrderCost saleOrderCost) throws Exception {
		return baseHibernateService.merge(saleOrderCost);
	}

	public List<SaleOrderCost> findSaleOrderCostList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(SaleOrderCost.class, params);
	}

}
