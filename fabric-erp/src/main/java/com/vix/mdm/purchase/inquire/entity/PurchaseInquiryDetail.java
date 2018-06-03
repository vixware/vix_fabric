package com.vix.mdm.purchase.inquire.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.NumberUtil;

/**
 * 采购询价单明细
 * 
 * @author Ivan 2013-05-24
 */
public class PurchaseInquiryDetail extends BaseEntity {

	private static final long serialVersionUID = -2333776780828093197L;

	private String barCode;
	private String skuCode;
	private String groupCode;

	/** 行类型 */
	private String rowType;
	/** 采购询价单编号 */
	private String reqFormCode;
	/** 物料id */
	private String itemId;
	/** 物料编码 */
	private String itemCode;
	/** 物料名称 */
	private String itemName;
	/** 规格型号 */
	private String specification;
	/** 物料类型 */
	private String itemType;
	/** 需求描述 */
	private String requirementDetails;
	/** 数量 */
	// private Double amount;
	/** 单位 */
	private String unit;
	/** 税率 */
	private Double taxRate;
	/** 单价 */
	private Double price;
	/** 价税合计金额 */
	private Double total;
	/** 建议供应商 */
	private String supplier;
	/** 需求日期 */
	private Date requireTime;
	/** 项目 */
	private String project;
	/** 仓库 */
	private String warehouse;
	/** 收货仓库 */
	private String receivingWarehouse;
	/** 收货地址 */
	private String receivingAddress;
	/** 采购询价 */
	private PurchaseInquire purchaseInquire;
	private Double amount;

	public Double genTatalFee() {
		Double inTax = this.taxRate;
		if (inTax == null)
			inTax = 0d;
		else
			inTax = (inTax / 100);

		inTax += 1d;
		if (price != null && amount != null) {
			total = price * amount * inTax;
			total = NumberUtil.round(total, 2, BigDecimal.ROUND_HALF_UP);
		}
		return total;
	}

	public String getRowType() {
		return rowType;
	}

	public void setRowType(String rowType) {
		this.rowType = rowType;
	}

	public String getReqFormCode() {
		return reqFormCode;
	}

	public void setReqFormCode(String reqFormCode) {
		this.reqFormCode = reqFormCode;
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

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getRequirementDetails() {
		return requirementDetails;
	}

	public void setRequirementDetails(String requirementDetails) {
		this.requirementDetails = requirementDetails;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public Date getRequireTime() {
		return requireTime;
	}

	public void setRequireTime(Date requireTime) {
		this.requireTime = requireTime;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public String getReceivingWarehouse() {
		return receivingWarehouse;
	}

	public void setReceivingWarehouse(String receivingWarehouse) {
		this.receivingWarehouse = receivingWarehouse;
	}

	public String getReceivingAddress() {
		return receivingAddress;
	}

	public void setReceivingAddress(String receivingAddress) {
		this.receivingAddress = receivingAddress;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PurchaseInquire getPurchaseInquire() {
		return purchaseInquire;
	}

	public void setPurchaseInquire(PurchaseInquire purchaseInquire) {
		this.purchaseInquire = purchaseInquire;
	}

	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * @param itemId
	 *            the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

}
