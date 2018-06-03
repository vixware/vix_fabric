package com.vix.sales.quotes.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.item.entity.Item;

/**
 * 销售报价明细 行类型表示当前记录行是零配件的记录还是需要进行计算的费率记录
 * 
 * @author Administrator
 *
 */
public class SalesQuotationTemplateItem extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 销售或者服务内容 */
	private String content;
	/** 物料 */
	private Item item;
	/** 是否外采 */
	private String isPurchase;
	/** 供应商编码 */
	private String supplierCode;
	/** 供应商名称 */
	private String supplierName;
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
	/** 单价 */
	private Double price;
	/** 折扣 */
	private Double discount;
	/** 仓库 */
	private String wareHouse;
	/** 收货仓库 */
	private String recieveWareHouse;
	/** 仓库组织 */
	private String wareHouseOrg;
	/** 收货地址 */
	private String recieveAddress;
	/** 是否允许替换物料 */
	private String isUseReplace;
	/** 替换物料标识ID */
	private String replaceItemCode;
	/** 替换物料名称 */
	private String replaceItemName;
	/** 报价单 */
	private SalesQuotationTemplate salesQuotationTemplate;

	public SalesQuotationTemplateItem() {
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getIsPurchase() {
		return isPurchase;
	}

	public void setIsPurchase(String isPurchase) {
		this.isPurchase = isPurchase;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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

	public String getWareHouse() {
		return wareHouse;
	}

	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}

	public String getRecieveWareHouse() {
		return recieveWareHouse;
	}

	public void setRecieveWareHouse(String recieveWareHouse) {
		this.recieveWareHouse = recieveWareHouse;
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

	public String getIsUseReplace() {
		return isUseReplace;
	}

	public void setIsUseReplace(String isUseReplace) {
		this.isUseReplace = isUseReplace;
	}

	public String getReplaceItemCode() {
		return replaceItemCode;
	}

	public void setReplaceItemCode(String replaceItemCode) {
		this.replaceItemCode = replaceItemCode;
	}

	public String getReplaceItemName() {
		return replaceItemName;
	}

	public void setReplaceItemName(String replaceItemName) {
		this.replaceItemName = replaceItemName;
	}

	public SalesQuotationTemplate getSalesQuotationTemplate() {
		return salesQuotationTemplate;
	}

	public void setSalesQuotationTemplate(SalesQuotationTemplate salesQuotationTemplate) {
		this.salesQuotationTemplate = salesQuotationTemplate;
	}
}
