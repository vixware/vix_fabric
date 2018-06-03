/**
 * 
 */
package com.vix.drp.dayWithSalesOrder.domain;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("dayWithSalesOrderDomain")
@Transactional
public class DayWithSalesOrderDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, SalesOrder.class, params);
		return p;
	}

	public SalesOrder findSalesOrderById(String id) throws Exception {
		return baseHibernateService.findEntityById(SalesOrder.class, id);
	}

	public SaleOrderItem findSaleOrderItemById(String id) throws Exception {
		return baseHibernateService.findEntityById(SaleOrderItem.class, id);
	}

	public SalesOrder saveOrUpdateSalesOrder(SalesOrder salesOrder) throws Exception {
		return baseHibernateService.merge(salesOrder);
	}

	public SaleOrderItem saveOrUpdateSaleOrderItem(SaleOrderItem saleOrderItem) throws Exception {
		return baseHibernateService.merge(saleOrderItem);
	}

	public void deleteByEntity(SalesOrder salesOrder) throws Exception {
		baseHibernateService.deleteByEntity(salesOrder);
	}

	public void deleteSaleOrderItemByEntity(SaleOrderItem saleOrderItem) throws Exception {
		baseHibernateService.deleteByEntity(saleOrderItem);
	}

}
