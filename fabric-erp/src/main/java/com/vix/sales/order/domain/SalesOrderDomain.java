package com.vix.sales.order.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.share.entity.CMNObjectModifyRecord;
import com.vix.core.web.Pager;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesAttachFile;
import com.vix.sales.order.entity.SalesDeliveryPlan;
import com.vix.sales.order.entity.SalesOrder;
import com.vix.sales.order.service.ISalesOrderService;

@Component("salesOrderDomain")
@Transactional
public class SalesOrderDomain extends BaseDomain{
	
	@Autowired
	private ISalesOrderService salesOrderService;
	
	/** 获取列表数据  */
	public Pager findPagerByHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = salesOrderService.findPagerByHqlConditions(pager, SalesOrder.class, params);
		return p;
	}
	
	public SalesOrder findEntityById(String id) throws Exception{
		return salesOrderService.findEntityById(SalesOrder.class, id);
	}
	
	public SalesAttachFile findSalesAttachFileEntityById(String id) throws Exception{
		return salesOrderService.findEntityById(SalesAttachFile.class, id);
	}
	
	public SalesOrder merge(SalesOrder salesOrder) throws Exception{
		return salesOrderService.merge(salesOrder);
	}
	
	public SaleOrderItem merge(SaleOrderItem soi) throws Exception{
		return salesOrderService.merge(soi);
	}
	
	public CMNObjectModifyRecord merge(CMNObjectModifyRecord cMNObjectModifyRecord) throws Exception{
		return salesOrderService.merge(cMNObjectModifyRecord);
	}

	public SalesAttachFile merge(SalesAttachFile salesAttachFile) throws Exception{
		return salesOrderService.merge(salesAttachFile);
	}
	
	public SalesDeliveryPlan merge(SalesDeliveryPlan saleOrderItemsDeliveryPlan) throws Exception{
		return salesOrderService.merge(saleOrderItemsDeliveryPlan);
	}
	
	public void deleteByEntity(SalesOrder salesOrder) throws Exception{
		salesOrderService.deleteByEntity(salesOrder);
	}
	
	public void deleteSalesAttachFileEntity(SalesAttachFile salesAttachFile) throws Exception{
		salesOrderService.deleteByEntity(salesAttachFile);
	}
	
	public void deleteByIds(List<String> ids) throws Exception{
		salesOrderService.batchDelete(SalesOrder.class,ids);
	}
	
	/** 索引对象列表 */
	public List<SalesOrder> findSalesOrderIndex() throws Exception{
		return salesOrderService.findAllByConditions(SalesOrder.class, null);
	}
}
