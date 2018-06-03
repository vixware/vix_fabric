/**
 * 
 */
package com.vix.ebusiness.order.orderProcess.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.entity.DistributionCenter;
import com.vix.ebusiness.entity.Order;
import com.vix.ebusiness.entity.OrderBatch;
import com.vix.ebusiness.entity.OrderDetail;
import com.vix.ebusiness.option.entity.Logistics;
import com.vix.ebusiness.order.orderProcess.hql.OrderProcessHqlProvider;
import com.vix.ebusiness.order.orderProcess.service.IOrderProcessService;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.warehouse.entity.InvWarehouse;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("orderProcessDomain")
@Transactional
public class OrderProcessDomain extends BaseDomain{

	@Autowired
	private IOrderProcessService orderProcessService;
	@Autowired
	private OrderProcessHqlProvider orderProcessHqlProvider;

	/** 获取列表数据 */
	public Pager findOrderPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = orderProcessService.findPagerByHqlConditions(pager, Order.class, params);
		return p;
	}

	public Pager findOrderDetailPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = orderProcessService.findPagerByHqlConditions(pager, OrderDetail.class, params);
		return p;
	}

	/** 获取列表数据 */
	public Pager findOrderPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = orderProcessService.findPagerByOrHqlConditions(pager, Order.class, params);
		return p;
	}

	public Order findOrderById(String id) throws Exception {
		return orderProcessService.findEntityById(Order.class, id);
	}

	public BaseEntity findBeforeAndAfterEntity(String createTime, Map<String, Object> params, BaseEntity baseEntity, String condition) throws Exception {
		StringBuilder hql = orderProcessHqlProvider.findBeforeAndAfterEntity(params, baseEntity, condition);
		return orderProcessService.findBeforeAndAfterEntityByHql(hql.toString(), params);
	}

	// public Order findAfterOrderById(String id) throws Exception {
	// Map<String, Object> params = new HashMap<String, Object>();
	// params.put("id", id);
	// Order order = new Order();
	// StringBuilder hql = orderProcessHqlProvider.findOrder(params,
	// order,"after");
	// return orderProcessService.findOrderByHql(hql.toString(), params);
	// }

	public DistributionCenter findDistributionCenterById(String id) throws Exception {
		return orderProcessService.findEntityById(DistributionCenter.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return orderProcessService.findEntityById(Employee.class, id);
	}

	public InvWarehouse findInvWarehouseById(String id) throws Exception {
		return orderProcessService.findEntityById(InvWarehouse.class, id);
	}

	public Logistics findLogisticsById(String id) throws Exception {
		return orderProcessService.findEntityById(Logistics.class, id);
	}

	/**
	 * 
	 * @param orderDetail
	 * @return
	 * @throws Exception
	 */
	public OrderDetail saveOrUpdateOrderDetail(OrderDetail orderDetail) throws Exception {

		return orderProcessService.mergeOriginal(orderDetail);
	}

	public Order saveOrUpdateOrder(Order order) throws Exception {
		return orderProcessService.mergeOriginal(order);
	}

	public OrderBatch saveOrUpdateOrderBatch(OrderBatch orderBatch) throws Exception {
		return orderProcessService.mergeOriginal(orderBatch);
	}

	/** 索引对象列表 */
	public List<Order> findOrderIndex() throws Exception {
		return orderProcessService.findAllByConditions(Order.class, null);
	}

	public List<ChannelDistributor> findChannelDistributorList(Map<String, Object> params) throws Exception {
		return orderProcessService.findAllByConditions(ChannelDistributor.class, params);
	}

	public List<Order> findOrderList(Map<String, Object> params) throws Exception {
		return orderProcessService.findAllByConditions(Order.class, params);
	}

	public List<DistributionCenter> findDistributionCenterList() throws Exception {
		return orderProcessService.findAllByEntityClass(DistributionCenter.class);
	}

	public List<Employee> findEmployeeList() throws Exception {
		return orderProcessService.findAllByEntityClass(Employee.class);
	}

	public List<Logistics> findLogisticsList() throws Exception {
		return orderProcessService.findAllByEntityClass(Logistics.class);
	}

	public List<InvWarehouse> findInvWarehouseList() throws Exception {
		return orderProcessService.findAllByEntityClass(InvWarehouse.class);
	}
}
