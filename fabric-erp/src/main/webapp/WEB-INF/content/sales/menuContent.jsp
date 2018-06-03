<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<li><a href="#" onclick="loadContent('${vix}/sales/quotes/salesQuotationAction!goSaveOrUpdate.action');"><img src="${vix}/common/img/sale/saleQuotes.png" alt="" />新增报价</a></li>
<li><a href="#" onclick="loadContent('${vix}/sales/quotes/projectQuotationSchemeAction!goSaveOrUpdate.action');"><img src="${vix}/common/img/sale/saleQuotes.png" alt="" />新增项目式报价</a></li>
<li><a href="#" onclick="loadContent('${vix}/sales/order/salesOrderAction!goSaveOrUpdate.action');"><img src="${vix}/common/img/sale/saleOrder.png" alt="" />新增订单</a></li>
<li><a href="#"><img src="${vix}/common/img/sale/sale.png" alt="" />新增销售合同</a></li>
<li><a href="#" onclick="loadContent('${vix}/sales/delivery/deliveryDocumentAction!goSaveOrUpdate.action');"><img src="${vix}/common/img/sale/saleDelivery.png" alt="" />新增发货单</a></li>
<li><a href="#" onclick="loadContent('${vix}/sales/invoice/salesInvoiceAction!goSaveOrUpdate.action?invoiceType=1');"><img src="${vix}/common/img/sale/saleInvoice.png" alt="" />新增发票</a></li>
<li><a href="#" onclick="loadContent('${vix}/sales/delivery/saleReturnFormAction!goSaveOrUpdate.action');"><img src="${vix}/common/img/sale/saleReturn.png" alt="" />新增退货单</a></li>
<li><a href="#" onclick="loadContent('/vix/sales/payment/disbursementCostAction!goSaveOrUpdate.action');"><img src="${vix}/common/img/sale/sale.png" alt="" />新增代垫费用</a></li>
<li><a href="#" onclick="loadContent('/vix/sales/payment/expensesAction!goSaveOrUpdate.action');"><img src="${vix}/common/img/sale/sale.png" alt="" />新增费用支出</a></li>