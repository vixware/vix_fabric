/**
 * 
 */
package com.vix.ebusiness.order.outboundprocess.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.ebusiness.entity.InvoiceList;
import com.vix.ebusiness.entity.Order;
import com.vix.ebusiness.entity.OrderBatch;
import com.vix.ebusiness.entity.OrderDetail;
import com.vix.ebusiness.expressFeeRules.entity.ExpressFeeRules;
import com.vix.hr.organization.entity.Employee;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("outBoundProcessDomain")
@Transactional
public class OutBoundProcessDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findOrderBatchPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, OrderBatch.class, params);
		return p;
	}

	public Pager findOrderPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Order.class, params);
		return p;
	}

	public Order saveOrUpdateOrder(Order order) throws Exception {
		return baseHibernateService.mergeOriginal(order);
	}

	public OrderBatch saveOrUpdateOrderBatch(OrderBatch orderBatch) throws Exception {
		return baseHibernateService.mergeOriginal(orderBatch);
	}

	public OrderDetail saveOrUpdateOrderDetail(OrderDetail orderDetail) throws Exception {

		return baseHibernateService.mergeOriginal(orderDetail);
	}

	public OrderBatch findOrderBatchById(String id) throws Exception {
		return baseHibernateService.findEntityById(OrderBatch.class, id);
	}

	public Employee findEmployeeyId(String id) throws Exception {
		return baseHibernateService.findEntityById(Employee.class, id);
	}

	public InvoiceList findInvoiceListId(String id) throws Exception {
		return baseHibernateService.findEntityById(InvoiceList.class, id);
	}

	public Order findOrderById(String id) throws Exception {
		return baseHibernateService.findEntityById(Order.class, id);
	}

	/** 索引对象列表 */
	public List<OrderBatch> findOrderBatch() throws Exception {
		return baseHibernateService.findAllByConditions(OrderBatch.class, null);
	}

	public List<InvoiceList> findInvoiceListList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(InvoiceList.class, params);
	}

	public List<Order> findOrderList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(Order.class, params);
	}

	public List<ExpressFeeRules> findExpressFeeRulesList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(ExpressFeeRules.class, params);
	}

}
