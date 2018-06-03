package com.vix.drp.storesalesrecord.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.org.entity.Organization;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.storesalesrecord.hql.StoreSalesRecordHqlProvider;
import com.vix.drp.storesalesrecord.service.IStoreSalesRecordService;
import com.vix.ebusiness.entity.Order;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.mdm.item.entity.Item;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;

@Component("storeSalesRecordDomain")
@Transactional
public class StoreSalesRecordDomain extends BaseDomain{

	@Autowired
	private IStoreSalesRecordService storeSalesRecordService;
	@Autowired
	private StoreSalesRecordHqlProvider storeSalesRecordHqlProvider;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = storeSalesRecordService.findPagerByHqlConditions(pager, SalesOrder.class, params);
		return p;
	}

	/** 获取列表数据 */
	public Pager findInventoryCurrentStockPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = storeSalesRecordService.findPagerByHqlConditions(pager, Item.class, params);
		return p;
	}

	public SalesOrder findEntityById(String id) throws Exception {
		return storeSalesRecordService.findEntityById(SalesOrder.class, id);
	}

	public ChannelDistributor findChannelDistributorById(String id) throws Exception {
		return storeSalesRecordService.findEntityById(ChannelDistributor.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return storeSalesRecordService.findEntityById(Employee.class, id);
	}

	public Item findInventoryCurrentStockById(String id) throws Exception {
		return storeSalesRecordService.findEntityById(Item.class, id);
	}

	public InventoryCurrentStock findInventoryCurrentStock(String itemcode) throws Exception {
		return storeSalesRecordService.findEntityByAttribute(InventoryCurrentStock.class, "itemcode", itemcode);
	}

	public SaleOrderItem findSaleOrderItemById(String id) throws Exception {
		return storeSalesRecordService.findEntityById(SaleOrderItem.class, id);
	}

	public Organization findOrganizationById(String id) throws Exception {
		return storeSalesRecordService.findEntityById(Organization.class, id);
	}

	public ChannelDistributor merge(ChannelDistributor channelDistributor) throws Exception {
		return storeSalesRecordService.merge(channelDistributor);
	}

	public SalesOrder saveOrUpdate(SalesOrder salesOrder) throws Exception {
		return storeSalesRecordService.merge(salesOrder);
	}

	public void saveOrUpdateSaleOrderItem(SaleOrderItem saleOrderItem) throws Exception {
		storeSalesRecordService.saveOrUpdate(saleOrderItem);
	}

	public void deleteByEntity(SalesOrder salesOrder) throws Exception {
		storeSalesRecordService.deleteByEntity(salesOrder);
	}

	public void deleteSaleOrderItemByEntity(SaleOrderItem saleOrderItem) throws Exception {
		storeSalesRecordService.deleteByEntity(saleOrderItem);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		storeSalesRecordService.batchDelete(SalesOrder.class, ids);
	}

	public Order findBeforeOrderById(String id) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		StringBuilder hql = storeSalesRecordHqlProvider.findBeforeOrder(params);
		return storeSalesRecordService.findOrderByHql(hql.toString(), params);
	}

	public Order findAfterOrderById(String id) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		StringBuilder hql = storeSalesRecordHqlProvider.findAfterOrder(params);
		return storeSalesRecordService.findOrderByHql(hql.toString(), params);
	}

	/** 索引对象列表 */
	public List<SalesOrder> findSalesOrderList(Map<String, Object> params) throws Exception {
		return storeSalesRecordService.findAllByConditions(SalesOrder.class, params);
	}
}
