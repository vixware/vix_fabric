package com.vix.sales.invoice.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.CurrencyType;

public class SalesInvoiceItem extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 行类型 */
	private String rowType;
	/** 销售订单编码 */
	private String saleOrderCode;
	/** 成组编码 */
	private String groupCode;
	/** 销售发票编号 */
	private String invoiceCode;
	/** 销售或者服务内容 */
	private String content;
	/** 物料编码  */
	private String itemCode;
	/** 物料名称 */
	private String itemName;
	/** 物料类型 */
	private String itemType;
	/** 销售数量 */
	private Double amount;
	/** 辅计算量 */
	private String assitAmount;
	/** 计量单位 */
	private String unit;
	/** 辅助计量单位 */
	private String assitUnit;
	/** 主辅计量单位换算率 */
	private Double unitExchange; 
	/** 税率 */
	private Double tax; 
	/** 税额 */
	private Double taxAmount; 
	/** 单价 */
	private Double price; 
	/** 折扣 */
	private Double discount; 
	/** 净单价 */
	private Double netPrice; 
	/** 含税单价 */
	private Double taxPrice; 
	/** 金额(净单价*数量) */
	private Double netTotal; 
	/** 价税合计金额 */
	private Double taxTotal;
	/** 累计出库数量 */
	private Double sumInventoryAmount; 
	/** 需求日期 */
	private Date requireDate; 
	/** 项目 */
	private String project; 
	/** 仓库 */
//	private WimWarehouse wimWareHouse; 
	/** 收货仓库 */
//	private WimWarehouse recieveWimWareHouse; 
	/** 仓库组织 */
	private String wareHouseOrg; 
	/** 收获地址 */
	private String recieveAddress;
	/** 币种 */
	private CurrencyType currencyType;
	/** 销售发票 */
	private SalesInvoice salesInvoice; 

	public SalesInvoiceItem(){}

	public String getRowType() {
		return rowType;
	}

	public void setRowType(String rowType) {
		this.rowType = rowType;
	}

	public String getSaleOrderCode() {
		return saleOrderCode;
	}

	public void setSaleOrderCode(String saleOrderCode) {
		this.saleOrderCode = saleOrderCode;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getAssitAmount() {
		return assitAmount;
	}

	public void setAssitAmount(String assitAmount) {
		this.assitAmount = assitAmount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getAssitUnit() {
		return assitUnit;
	}

	public void setAssitUnit(String assitUnit) {
		this.assitUnit = assitUnit;
	}

	public Double getUnitExchange() {
		return unitExchange;
	}

	public void setUnitExchange(Double unitExchange) {
		this.unitExchange = unitExchange;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(Double netPrice) {
		this.netPrice = netPrice;
	}

	public Double getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(Double taxPrice) {
		this.taxPrice = taxPrice;
	}

	public Double getNetTotal() {
		return netTotal;
	}

	public void setNetTotal(Double netTotal) {
		this.netTotal = netTotal;
	}

	public Double getTaxTotal() {
		return taxTotal;
	}

	public void setTaxTotal(Double taxTotal) {
		this.taxTotal = taxTotal;
	}

	public Double getSumInventoryAmount() {
		return sumInventoryAmount;
	}

	public void setSumInventoryAmount(Double sumInventoryAmount) {
		this.sumInventoryAmount = sumInventoryAmount;
	}

	public Date getRequireDate() {
		return requireDate;
	}

	public void setRequireDate(Date requireDate) {
		this.requireDate = requireDate;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getWareHouseOrg() {
		return wareHouseOrg;
	}

	public void setWareHouseOrg(String wareHouseOrg) {
		this.wareHouseOrg = wareHouseOrg;
	}

	public String getRecieveAddress() {
		return recieveAddress;
	}

	public void setRecieveAddress(String recieveAddress) {
		this.recieveAddress = recieveAddress;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public SalesInvoice getSalesInvoice() {
		return salesInvoice;
	}

	public void setSalesInvoice(SalesInvoice salesInvoice) {
		this.salesInvoice = salesInvoice;
	}
}
