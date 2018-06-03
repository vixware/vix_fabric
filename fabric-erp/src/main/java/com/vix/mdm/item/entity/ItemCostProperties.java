package com.vix.mdm.item.entity;

import com.vix.common.share.entity.BaseEntity;
/** 物料(产品)成本属性 */
public class ItemCostProperties extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 物料编码 */
	private String itemCode;
	/** 物料类型 */
	private String itemType;
	/** 计价方式 */
	private String priceStyle;
	/** 费用率 */
	private Double feeRate;
	/** 计划价格 */
	private Double planPrice;
	/** 销售价格 */
	private Double salePrice;
	/** 最高进价 */
	private Double maxCost;
	/** 成本价格 */
	private Double costPrice;
	/** 参考成本 */
	private Double refereceCost;
	/** 最新成本 */
	private Double newCost;
	/** 参考售价 */
	private Double refereceSalePrice;
	/** 最低售价 */
	private Double minSalePrice;
	/** 默认仓库 */
	private String defaultWarehouse;
	/** 默认货位 */
	private String defaultShelf;
	/** 采购人员 */
	private String purchasePerson;
	/** 退税率 */
	private Double rebateRate;
	/** 销售加成率 */
	private Double saleAddRate;
	/** 零售价格 */
	private Double retailPrice;
	/** 准备人工 */
	private Double prepareManPower;
	/** 加工人工 */
	private Double processManPower;
	/** 变动制造费用 */
	private Double variableManufacturingCost;
	/** 固定制造费用 */
	private Double fixedManufacturingCost;
	/** 外协费用 */
	private Double outSoucingFee;
	/** 单位材料成本 */
	private Double perMaterialCost;
	/** 材料单价 */
	private Double materialCost;
	/** 物料 */
	private Item item;

	public ItemCostProperties(){}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getPriceStyle() {
		return priceStyle;
	}

	public void setPriceStyle(String priceStyle) {
		this.priceStyle = priceStyle;
	}

	public Double getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(Double feeRate) {
		this.feeRate = feeRate;
	}

	public Double getPlanPrice() {
		return planPrice;
	}

	public void setPlanPrice(Double planPrice) {
		this.planPrice = planPrice;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Double getMaxCost() {
		return maxCost;
	}

	public void setMaxCost(Double maxCost) {
		this.maxCost = maxCost;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Double getRefereceCost() {
		return refereceCost;
	}

	public void setRefereceCost(Double refereceCost) {
		this.refereceCost = refereceCost;
	}

	public Double getNewCost() {
		return newCost;
	}

	public void setNewCost(Double newCost) {
		this.newCost = newCost;
	}

	public Double getRefereceSalePrice() {
		return refereceSalePrice;
	}

	public void setRefereceSalePrice(Double refereceSalePrice) {
		this.refereceSalePrice = refereceSalePrice;
	}

	public Double getMinSalePrice() {
		return minSalePrice;
	}

	public void setMinSalePrice(Double minSalePrice) {
		this.minSalePrice = minSalePrice;
	}

	public String getDefaultWarehouse() {
		return defaultWarehouse;
	}

	public void setDefaultWarehouse(String defaultWarehouse) {
		this.defaultWarehouse = defaultWarehouse;
	}

	public String getDefaultShelf() {
		return defaultShelf;
	}

	public void setDefaultShelf(String defaultShelf) {
		this.defaultShelf = defaultShelf;
	}

	public String getPurchasePerson() {
		return purchasePerson;
	}

	public void setPurchasePerson(String purchasePerson) {
		this.purchasePerson = purchasePerson;
	}

	public Double getRebateRate() {
		return rebateRate;
	}

	public void setRebateRate(Double rebateRate) {
		this.rebateRate = rebateRate;
	}

	public Double getSaleAddRate() {
		return saleAddRate;
	}

	public void setSaleAddRate(Double saleAddRate) {
		this.saleAddRate = saleAddRate;
	}

	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public Double getPrepareManPower() {
		return prepareManPower;
	}

	public void setPrepareManPower(Double prepareManPower) {
		this.prepareManPower = prepareManPower;
	}

	public Double getProcessManPower() {
		return processManPower;
	}

	public void setProcessManPower(Double processManPower) {
		this.processManPower = processManPower;
	}

	public Double getVariableManufacturingCost() {
		return variableManufacturingCost;
	}

	public void setVariableManufacturingCost(Double variableManufacturingCost) {
		this.variableManufacturingCost = variableManufacturingCost;
	}

	public Double getFixedManufacturingCost() {
		return fixedManufacturingCost;
	}

	public void setFixedManufacturingCost(Double fixedManufacturingCost) {
		this.fixedManufacturingCost = fixedManufacturingCost;
	}

	public Double getOutSoucingFee() {
		return outSoucingFee;
	}

	public void setOutSoucingFee(Double outSoucingFee) {
		this.outSoucingFee = outSoucingFee;
	}

	public Double getPerMaterialCost() {
		return perMaterialCost;
	}

	public void setPerMaterialCost(Double perMaterialCost) {
		this.perMaterialCost = perMaterialCost;
	}

	public Double getMaterialCost() {
		return materialCost;
	}

	public void setMaterialCost(Double materialCost) {
		this.materialCost = materialCost;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
