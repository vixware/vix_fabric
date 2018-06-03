package com.vix.ebusiness.order.sendOut.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.entity.Order;
import com.vix.ebusiness.entity.OrderBatch;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("sendOutDomain")
@Transactional
public class SendOutDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findOrderPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Order.class, params);
		return p;
	}

	/** 索引对象列表 */
	public List<Order> findOrder(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(Order.class, params);
	}

	public Order findOrderById(String id) throws Exception {
		return baseHibernateService.findEntityById(Order.class, id);
	}

	public OrderBatch findOrderBatchById(String id) throws Exception {
		return baseHibernateService.findEntityById(OrderBatch.class, id);
	}

	public ChannelDistributor findChannelDistributorById(String id) throws Exception {
		return baseHibernateService.findEntityById(ChannelDistributor.class, id);
	}

	public Order saveOrUpdateOrder(Order order) throws Exception {
		return baseHibernateService.merge(order);
	}
	public OrderBatch saveOrUpdateOrderBatch(OrderBatch orderBatch) throws Exception {
		return baseHibernateService.merge(orderBatch);
	}

}
