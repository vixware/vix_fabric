package com.vix.mdm.purchase.plan.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.MeasureUnit;

/**
 * 采购计划明细
 * 
 * @author Ivan 2013-07-22
 */
public class PurchasePlanItems extends BaseEntity {

	private static final long serialVersionUID = 3039208378626738616L;
	/** 行类型 */
	private String rowType;
	/** 物料编码 */
	private String itemCode;
	/** 物料名称 */
	private String itemName;
	/** 规格型号 */
	private String specification;
	/** 物料类型 */
	private String itemType;
	/** 数量 */
	private Double amount;
	/** 单价 */
	private Double unitcost;
	/** 单位 */
	private String unit;
	/** 需求日期 */
	private Date requireTime;
	/** 需求人 */
	private String requirePerson;
	/** 税率 */
	private Double taxRate;
	/** 金额 */
	private Double price;
	/** 价税合计金额 */
	private Double total;
	/** 项目 */
	private String project;
	/** 仓库 */
	private String warehouse;
	/** 收货仓库 */
	private String receivingWarehouse;
	/** 收货地址 */
	private String receivingAddress;
	/** 采购计划 */
	private PurchasePlan purchasePlan;

	/**
	 * 单位
	 */
	private MeasureUnit measureUnit;

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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getRequireTime() {
		return requireTime;
	}

	public void setRequireTime(Date requireTime) {
		this.requireTime = requireTime;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRequirePerson() {
		return requirePerson;
	}

	public void setRequirePerson(String requirePerson) {
		this.requirePerson = requirePerson;
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

	public PurchasePlan getPurchasePlan() {
		return purchasePlan;
	}

	public void setPurchasePlan(PurchasePlan purchasePlan) {
		this.purchasePlan = purchasePlan;
	}

	public Double getUnitcost() {
		return unitcost;
	}

	public void setUnitcost(Double unitcost) {
		this.unitcost = unitcost;
	}

	/**
	 * @return the measureUnit
	 */
	public MeasureUnit getMeasureUnit() {
		return measureUnit;
	}

	/**
	 * @param measureUnit
	 *            the measureUnit to set
	 */
	public void setMeasureUnit(MeasureUnit measureUnit) {
		this.measureUnit = measureUnit;
	}

}
