package com.vix.sales.quotes.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.item.entity.Item;

/**
 * 销售报价明细 行类型表示当前记录行是零配件的记录还是需要进行计算的费率记录
 * 
 * @author Administrator
 *
 */
public class SalesQuotationItem extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 行编码(ID) */
	private String rowID;
	/** 行类型 */
	private String rowType;
	/** 销售订单编号 */
	private String poCode;
	/** 销售或者服务内容 */
	private String content;
	/** 负责人 */
	private String charger;
	/** 负责部门 */
	private String chargeDepartment;
	/** 父项编码 */
	private String parentCode;
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
	/** 规格 */
	private String specification;
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
	/** 是否是赠品 */
	private String isGift;
	/** 是否有赠品 */
	private String isHasGift;
	/** 对应的订单子项id */
	private String salesQuotationItemId;
	/** 报价单 */
	private SalesQuotation salesQuotation;

	public SalesQuotationItem() {
	}

	public String getRowID() {
		return rowID;
	}

	public void setRowID(String rowID) {
		this.rowID = rowID;
	}

	public String getRowType() {
		return rowType;
	}

	public void setRowType(String rowType) {
		this.rowType = rowType;
	}

	public String getPoCode() {
		return poCode;
	}

	public void setPoCode(String poCode) {
		this.poCode = poCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCharger() {
		return charger;
	}

	public void setCharger(String charger) {
		this.charger = charger;
	}

	public String getChargeDepartment() {
		return chargeDepartment;
	}

	public void setChargeDepartment(String chargeDepartment) {
		this.chargeDepartment = chargeDepartment;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
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
		if (null != item && null != item.getMeasureUnit()) {
			return item.getMeasureUnit().getName();
		}
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
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

	public String getIsGift() {
		return isGift;
	}

	public void setIsGift(String isGift) {
		this.isGift = isGift;
	}

	public String getIsHasGift() {
		return isHasGift;
	}

	public void setIsHasGift(String isHasGift) {
		this.isHasGift = isHasGift;
	}

	public String getSalesQuotationItemId() {
		return salesQuotationItemId;
	}

	public void setSalesQuotationItemId(String salesQuotationItemId) {
		this.salesQuotationItemId = salesQuotationItemId;
	}

	public SalesQuotation getSalesQuotation() {
		return salesQuotation;
	}

	public void setSalesQuotation(SalesQuotation salesQuotation) {
		this.salesQuotation = salesQuotation;
	}

	public String getItemCode() {
		if (null != item) {
			return item.getCode();
		}
		return "";
	}

	public String getItemName() {
		if (null != item) {
			return item.getName();
		}
		return "";
	}
}
