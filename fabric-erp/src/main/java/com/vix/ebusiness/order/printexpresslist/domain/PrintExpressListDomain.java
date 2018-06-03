package com.vix.ebusiness.order.printexpresslist.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.ebusiness.entity.Order;
import com.vix.ebusiness.entity.OrderBatch;
import com.vix.ebusiness.entity.Shipping;
import com.vix.ebusiness.option.entity.Logistics;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("printExpressListDomain")
@Transactional
public class PrintExpressListDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findShippingPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Shipping.class, params);
		return p;
	}

	public List<Order> findOrderList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(Order.class, params);
	}

	public Order saveOrUpdateOrder(Order order) throws Exception {
		return baseHibernateService.merge(order);
	}

	public Shipping saveOrUpdateShipping(Shipping shipping) throws Exception {
		return baseHibernateService.merge(shipping);
	}

	public OrderBatch saveOrUpdateOrderBatch(OrderBatch orderBatch) throws Exception {
		return baseHibernateService.merge(orderBatch);
	}

	public OrderBatch findOrderBatchById(String id) throws Exception {
		return baseHibernateService.findEntityById(OrderBatch.class, id);
	}

	public Logistics findLogisticsById(String id) throws Exception {
		return baseHibernateService.findEntityById(Logistics.class, id);
	}

	public Shipping findShippingById(String orderId) throws Exception {
		return baseHibernateService.findEntityByAttribute(Shipping.class, "order.id", orderId);
	}

	public Order findOrderById(String id) throws Exception {
		return baseHibernateService.findEntityById(Order.class, id);
	}

	public List<Order> findOrder(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(Order.class, params);
	}

	public List<Logistics> findLogisticsList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(Logistics.class, params);
	}

	public List<Shipping> findShipping(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(Shipping.class, params);
	}

}
