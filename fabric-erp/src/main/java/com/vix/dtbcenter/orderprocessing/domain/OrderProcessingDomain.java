/**
 * 
 */
package com.vix.dtbcenter.orderprocessing.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.core.web.Pager;
import com.vix.mdm.item.entity.Item;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("orderProcessingDomain")
@Transactional
public class OrderProcessingDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findSalesOrderPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, SalesOrder.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findTakeDeliveryPagerByOrHqlConditions(String hql, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerJustByHql(pager, hql);
		return p;
	}

	public SalesOrder findSalesOrderById(String id) throws Exception {
		return baseHibernateService.findEntityById(SalesOrder.class, id);
	}

	public SaleOrderItem findSaleOrderItemById(String id) throws Exception {
		return baseHibernateService.findEntityById(SaleOrderItem.class, id);
	}

	public Item findItemById(String id) throws Exception {
		return baseHibernateService.findEntityById(Item.class, id);
	}

	public Pager findItem(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Item.class, params);
		return p;
	}

	public SalesOrder mergeSalesOrder(SalesOrder salesOrder) throws Exception {
		return baseHibernateService.merge(salesOrder);
	}

	public SaleOrderItem mergeSaleOrderItem(SaleOrderItem saleOrderItem) throws Exception {
		return baseHibernateService.merge(saleOrderItem);
	}

	public void deleteSalesOrderByEntity(SalesOrder salesOrder) throws Exception {
		baseHibernateService.deleteByEntity(salesOrder);
	}

	public void deleteSaleOrderItemByEntity(SaleOrderItem saleOrderItem) throws Exception {
		baseHibernateService.deleteByEntity(saleOrderItem);
	}

	/** 索引对象列表 */
	public List<SalesOrder> findSalesOrderList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(SalesOrder.class, params);
	}

	/** 索引对象列表 */
	public List<MeasureUnit> findMeasureUnitList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(MeasureUnit.class, params);
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
