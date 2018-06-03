package com.vix.ebusiness.order.pickingOut.domain;

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
@Component("pickingOutDomain")
@Transactional
public class PickingOutDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findOrderPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Order.class, params);
		return p;
	}

	/** 索引对象列表 */
	public List<Order> findOrderList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(Order.class, params);
	}

	public Order findOrderById(String id) throws Exception {
		return baseHibernateService.findEntityById(Order.class, id);
	}

}
