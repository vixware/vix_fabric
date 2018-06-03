package com.vix.ebusiness.order.printpickinglist.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.ebusiness.entity.Order;
import com.vix.ebusiness.entity.PickingList;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("printPickingListDomain")
@Transactional
public class PrintPickingListDomain extends BaseDomain{


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

	public List<PickingList> findPickingListList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(PickingList.class, params);
	}

	public PickingList findPickingList(String id) throws Exception {
		return baseHibernateService.findEntityByAttribute(PickingList.class, "orderBatch.id", id);
	}
}
