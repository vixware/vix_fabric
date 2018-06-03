package com.vix.mdm.purchase.apply.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.core.utils.NumberUtil;

/**
 * 请购单/采购申请明细
 * 
 * @author Ivan 2013-05-22
 */
public class PurchaseApplyDetails extends BaseEntity {

	private static final long serialVersionUID = 9047346763794464906L;

	private String barCode;
	private String skuCode;
	private String groupCode;

	/** 行类型 */
	private String rowType;
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
	/** 数量 */
	// private Double amount;
	/** 单位 */
	private String unit;
	/** 税率 */
	private Double taxRate;
	/** 金额 */
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
	/** 收获地址 */
	private String receivingAddress;
	/** 采购申请 */
	private PurchaseApply purchaseApply;
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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
	public String getRequireTimeStr() {
		if(requireTime != null){
			return DateUtil.format(requireTime);
		}
		return "";
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

	public PurchaseApply getPurchaseApply() {
		return purchaseApply;
	}

	public void setPurchaseApply(PurchaseApply purchaseApply) {
		this.purchaseApply = purchaseApply;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

}
