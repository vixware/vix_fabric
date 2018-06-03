package com.vix.sales.order.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.order.entity.PurchaseOrderLineItem;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;
import com.vix.sales.order.service.ISalesOrderService;

@Service("salesOrderService")
public class SalesOrderServiceImpl extends BaseHibernateServiceImpl implements ISalesOrderService {

	private static final long serialVersionUID = 1L;

	@Override/** fixme PurchaseOrder Interface */
	public boolean transPurchaseOrderToSaleOrder(PurchaseOrder purchaseOrder) throws Exception {
		boolean tag = false;
		if(null != purchaseOrder){
			SalesOrder so = new SalesOrder();
			so.setCreateTime(new Date());
			so.setGroupCode(purchaseOrder.getGroupCode());
			so = this.merge(so);
			for(PurchaseOrderLineItem poi : purchaseOrder.getPurchaseOrderLineItems()){
				SaleOrderItem soi = new SaleOrderItem();
				Item i = this.findEntityByAttribute(Item.class, "code", poi.getItemCode());
				soi.setItem(i);
				soi.setAmount(poi.getAmount());
				soi.setSalesOrder(so);
				this.merge(soi);
			}
			tag = true;
		}
		return tag;
	}

	@Override
	public boolean convertSalesQuotationToSaleOrder(Long saleOrderId, String sqIds) throws Exception{
		
		return false;
	}
	 
}
