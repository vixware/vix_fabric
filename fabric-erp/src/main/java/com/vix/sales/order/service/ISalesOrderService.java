package com.vix.sales.order.service;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;


public interface ISalesOrderService extends IBaseHibernateService{

	public boolean transPurchaseOrderToSaleOrder(PurchaseOrder purchaseOrder) throws Exception ;
	
	public boolean convertSalesQuotationToSaleOrder(Long saleOrderId,String sqIds) throws Exception;
}
