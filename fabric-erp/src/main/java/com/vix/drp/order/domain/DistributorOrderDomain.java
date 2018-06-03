package com.vix.drp.order.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.order.hql.DistributorOrderHqlProvider;
import com.vix.drp.order.service.IDistributorOrderService;
import com.vix.hr.organization.entity.Employee;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;

@Component("distributorOrderDomain")
@Transactional
public class DistributorOrderDomain extends BaseDomain{

	@Autowired
	private IDistributorOrderService distributorOrderService;
	@Autowired
	private DistributorOrderHqlProvider distributorOrderHqlProvider;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = distributorOrderService.findPagerByHqlConditions(pager, SalesOrder.class, params);
		return p;
	}

	public SalesOrder findEntityById(String id) throws Exception {
		return distributorOrderService.findEntityById(SalesOrder.class, id);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		distributorOrderService.batchDelete(SalesOrder.class, ids);
	}

	public ChannelDistributor findChannelDistributorById(String id) throws Exception {
		return distributorOrderService.findEntityById(ChannelDistributor.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return distributorOrderService.findEntityById(Employee.class, id);
	}

	public SaleOrderItem findSaleOrderItemById(String id) throws Exception {
		return distributorOrderService.findEntityById(SaleOrderItem.class, id);
	}

	public ChannelDistributor merge(ChannelDistributor channelDistributor) throws Exception {
		return distributorOrderService.merge(channelDistributor);
	}

	public SalesOrder saveOrUpdate(SalesOrder salesOrder) throws Exception {
		return distributorOrderService.merge(salesOrder);
	}

	public void saveOrUpdateSaleOrderItem(SaleOrderItem saleOrderItem) throws Exception {
		distributorOrderService.saveOrUpdate(saleOrderItem);
	}

	public void deleteByEntity(SalesOrder salesOrder) throws Exception {
		distributorOrderService.deleteByEntity(salesOrder);
	}

	public void deleteSaleOrderItemByEntity(SaleOrderItem saleOrderItem) throws Exception {
		distributorOrderService.deleteByEntity(saleOrderItem);
	}

	public SalesOrder findBeforeSalesOrderById(String id) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		StringBuilder hql = distributorOrderHqlProvider.findBeforeSalesOrder(params);
		return distributorOrderService.findOrderByHql(hql.toString(), params);
	}

	public SalesOrder findAfterSalesOrderById(String id) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		StringBuilder hql = distributorOrderHqlProvider.findAfterSalesOrder(params);
		return distributorOrderService.findOrderByHql(hql.toString(), params);
	}

	/** 索引对象列表 */
	public List<SalesOrder> findSalesOrderList(Map<String, Object> params) throws Exception {
		return distributorOrderService.findAllByConditions(SalesOrder.class, params);
	}
}
