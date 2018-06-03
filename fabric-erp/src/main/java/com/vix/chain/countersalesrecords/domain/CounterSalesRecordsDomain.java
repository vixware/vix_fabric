package com.vix.chain.countersalesrecords.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.org.entity.Organization;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;

@Component("counterSalesRecordsDomain")
@Transactional
public class CounterSalesRecordsDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, SalesOrder.class, params);
		return p;
	}

	/** 获取列表数据 */
	public Pager findInventoryCurrentStockPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, InventoryCurrentStock.class, params);
		return p;
	}

	public SalesOrder findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(SalesOrder.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return baseHibernateService.findEntityById(Employee.class, id);
	}

	public InventoryCurrentStock findInventoryCurrentStockById(String id) throws Exception {
		return baseHibernateService.findEntityById(InventoryCurrentStock.class, id);
	}

	public InventoryCurrentStock findInventoryCurrentStock(String itemcode) throws Exception {
		return baseHibernateService.findEntityByAttribute(InventoryCurrentStock.class, "itemcode", itemcode);
	}

	public SaleOrderItem findSaleOrderItemById(String id) throws Exception {
		return baseHibernateService.findEntityById(SaleOrderItem.class, id);
	}

	public Organization findOrganizationById(String id) throws Exception {
		return baseHibernateService.findEntityById(Organization.class, id);
	}

	public ChannelDistributor merge(ChannelDistributor channelDistributor) throws Exception {
		return baseHibernateService.merge(channelDistributor);
	}

	public SalesOrder saveOrUpdate(SalesOrder salesOrder) throws Exception {
		return baseHibernateService.merge(salesOrder);
	}

	public void saveOrUpdateSaleOrderItem(SaleOrderItem saleOrderItem) throws Exception {
		baseHibernateService.saveOrUpdate(saleOrderItem);
	}

	public void deleteByEntity(SalesOrder salesOrder) throws Exception {
		baseHibernateService.deleteByEntity(salesOrder);
	}

	public void deleteSaleOrderItemByEntity(SaleOrderItem saleOrderItem) throws Exception {
		baseHibernateService.deleteByEntity(saleOrderItem);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(ChannelDistributor.class, ids);
	}

	/** 索引对象列表 */
	public List<SalesOrder> findSalesOrderIndex() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizType," + SearchCondition.ANYLIKE, "S");
		return baseHibernateService.findAllByConditions(SalesOrder.class, params);
	}
}
