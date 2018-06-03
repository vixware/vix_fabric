package com.vix.nvix.system.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.inventory.inbound.entity.StockRecords;
import com.vix.mdm.purchase.apply.entity.PurchaseApply;
import com.vix.mdm.purchase.arrivalmgr.entity.PurchaseArrival;
import com.vix.mdm.purchase.inquire.entity.PurchaseInquire;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.plan.entity.PurchasePlan;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.sales.order.entity.SalesOrder;
import com.vix.sales.plan.entity.SalePlan;
import com.vix.sales.quotes.entity.SalesQuotation;

@Controller
@Scope("prototype")
public class VixntSourceAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private StockRecords stockRecords;
	private PurchasePlan purchasePlan;
	private PurchaseOrder purchaseOrder;
	private PurchaseApply purchaseApply;
	private PurchaseArrival purchaseArrival;
	private SalesOrder salesOrder;
	private SalesQuotation salesQuotation;
	private PurchaseInquire purchaseInquire;
	private SalePlan salePlan;
	private String className;
	private String code;
	public String goShowBill() {
		if (StringUtils.isNotEmpty(className)) {
			try {
				if ("com.vix.inventory.inbound.entity.StockRecords".equalsIgnoreCase(className)) {
					stockRecords = (StockRecords) vixntBaseService.findEntityByAttribute(Class.forName(className), "code", code);
					return "goShowStockRecords";
				}
				if("com.vix.mdm.purchase.plan.entity.PurchasePlan".equalsIgnoreCase(className)){
					purchasePlan = (PurchasePlan) vixntBaseService.findEntityByAttribute(Class.forName(className), "code", code);
					return "goShowPurchasePlan";
				}
				if("com.vix.mdm.purchase.order.entity.PurchaseOrder".equalsIgnoreCase(className)){
					purchaseOrder = (PurchaseOrder) vixntBaseService.findEntityByAttribute(Class.forName(className), "code", code);
					return "goShowPurchaseOrder";
				}
				if("com.vix.mdm.purchase.apply.entity.PurchaseApply".equalsIgnoreCase(className)){
					purchaseApply = (PurchaseApply) vixntBaseService.findEntityByAttribute(Class.forName(className), "code", code);
					return "goShowPurchaseApply";
				}
				if("com.vix.mdm.purchase.arrivalmgr.entity.PurchaseArrival".equalsIgnoreCase(className)){
					purchaseArrival = (PurchaseArrival) vixntBaseService.findEntityByAttribute(Class.forName(className), "code", code);
					return "goShowPurchaseArrival";
				}
				if("com.vix.sales.order.entity.SalesOrder".equalsIgnoreCase(className)){
					salesOrder = (SalesOrder) vixntBaseService.findEntityByAttribute(Class.forName(className), "code", code);
					return "goShowSalesOrder";
				}
				if("com.vix.sales.quotes.entity.SalesQuotation".equalsIgnoreCase(className)){
					salesQuotation = (SalesQuotation) vixntBaseService.findEntityByAttribute(Class.forName(className), "code", code);
					return "goShowSalesQuotation";
				}
				if("com.vix.sales.plan.entity.SalePlan".equalsIgnoreCase(className)){
					salePlan = (SalePlan) vixntBaseService.findEntityByAttribute(Class.forName(className), "code", code);
					return "goShowSalePlan";
				}
				if("com.vix.mdm.purchase.inquire.entity.PurchaseInquire".equalsIgnoreCase(className)){
					purchaseInquire = (PurchaseInquire) vixntBaseService.findEntityByAttribute(Class.forName(className), "code", code);
					return "goShowPurchaseInquire";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return UPDATE;
	}

	/**
	 * @return the stockRecords
	 */
	public StockRecords getStockRecords() {
		return stockRecords;
	}

	/**
	 * @param stockRecords
	 *            the stockRecords to set
	 */
	public void setStockRecords(StockRecords stockRecords) {
		this.stockRecords = stockRecords;
	}

	public PurchasePlan getPurchasePlan() {
		return purchasePlan;
	}

	public void setPurchasePlan(PurchasePlan purchasePlan) {
		this.purchasePlan = purchasePlan;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public PurchaseApply getPurchaseApply() {
		return purchaseApply;
	}

	public void setPurchaseApply(PurchaseApply purchaseApply) {
		this.purchaseApply = purchaseApply;
	}

	public PurchaseArrival getPurchaseArrival() {
		return purchaseArrival;
	}

	public void setPurchaseArrival(PurchaseArrival purchaseArrival) {
		this.purchaseArrival = purchaseArrival;
	}

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	public SalesQuotation getSalesQuotation() {
		return salesQuotation;
	}

	public void setSalesQuotation(SalesQuotation salesQuotation) {
		this.salesQuotation = salesQuotation;
	}

	public SalePlan getSalePlan() {
		return salePlan;
	}

	public void setSalePlan(SalePlan salePlan) {
		this.salePlan = salePlan;
	}

	public PurchaseInquire getPurchaseInquire() {
		return purchaseInquire;
	}

	public void setPurchaseInquire(PurchaseInquire purchaseInquire) {
		this.purchaseInquire = purchaseInquire;
	}
}
