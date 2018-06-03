package com.vix.ebusiness.order.printdeliverylist.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.ebusiness.entity.Order;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("printDeliveryListDomain")
@Transactional
public class PrintDeliveryListDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findOrderPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Order.class, params);
		return p;
	}

	/** 索引对象列表 */
	public List<Order> findOrder(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(Order.class, params);
	}

	//Domain
	public void deleteOrderByEntity(Order order) throws Exception {
		baseHibernateService.deleteByEntity(order);
	}

	public Order findOrderById(String id) throws Exception {
		return baseHibernateService.findEntityById(Order.class, id);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(Order.class, ids);
	}
}
