package com.vix.mdm.item.entity;

import com.vix.common.share.entity.BaseEntity;

/** 物料(产品)计划属性 */
public class ItemPlanProperties extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 物料编码 */
	private String itemCode;
	/** ROP件 */
	private String isROPComponent;
	/** 再订货点方法 */
	private String reOrderMethod;
	/** ROP批量规则 */
	private String ropBatchRule;
	/** 保证供应天数 */
	private Float promiseSupplyDay;
	/** 规定供应量 */
	private Float prescriptSupplyAmount;
	/** 固定提前期 */
	private Float fixedLeadTime;
	/** 累计提前期 */
	private Float totalLeadTime;
	/** 变动提前期(加工提前期) */
	private Float varLeadTime;
	/** 检查提前期 */
	private Float checkLeadTime;
	/** 安全时间 */
	private Float safetyTime;
	/** 安全存量 */
	private Float safetyStock;
	/** 最大订购量 */
	private Float maxBookingAmount;
	/** 倍数 */
	private Float multiple;
	/** 固定订购量 */
	private Float fixedBookingAmount;
	/** 再订购点 */
	private Float reOrderPoint;
	/** 经济订购量 */
	private Float economicOrderQuantity;
	/** 结构代码 */
	private String structureCode;
	/** 物料 */
	private Item item;
	
	public ItemPlanProperties(){}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getIsROPComponent() {
		return isROPComponent;
	}

	public void setIsROPComponent(String isROPComponent) {
		this.isROPComponent = isROPComponent;
	}

	public String getReOrderMethod() {
		return reOrderMethod;
	}

	public void setReOrderMethod(String reOrderMethod) {
		this.reOrderMethod = reOrderMethod;
	}

	public String getRopBatchRule() {
		return ropBatchRule;
	}

	public void setRopBatchRule(String ropBatchRule) {
		this.ropBatchRule = ropBatchRule;
	}

	public Float getPromiseSupplyDay() {
		return promiseSupplyDay;
	}

	public void setPromiseSupplyDay(Float promiseSupplyDay) {
		this.promiseSupplyDay = promiseSupplyDay;
	}

	public Float getPrescriptSupplyAmount() {
		return prescriptSupplyAmount;
	}

	public void setPrescriptSupplyAmount(Float prescriptSupplyAmount) {
		this.prescriptSupplyAmount = prescriptSupplyAmount;
	}

	public Float getFixedLeadTime() {
		return fixedLeadTime;
	}

	public void setFixedLeadTime(Float fixedLeadTime) {
		this.fixedLeadTime = fixedLeadTime;
	}

	public Float getTotalLeadTime() {
		return totalLeadTime;
	}

	public void setTotalLeadTime(Float totalLeadTime) {
		this.totalLeadTime = totalLeadTime;
	}

	public Float getVarLeadTime() {
		return varLeadTime;
	}

	public void setVarLeadTime(Float varLeadTime) {
		this.varLeadTime = varLeadTime;
	}

	public Float getCheckLeadTime() {
		return checkLeadTime;
	}

	public void setCheckLeadTime(Float checkLeadTime) {
		this.checkLeadTime = checkLeadTime;
	}

	public Float getSafetyTime() {
		return safetyTime;
	}

	public void setSafetyTime(Float safetyTime) {
		this.safetyTime = safetyTime;
	}

	public Float getSafetyStock() {
		return safetyStock;
	}

	public void setSafetyStock(Float safetyStock) {
		this.safetyStock = safetyStock;
	}

	public Float getMaxBookingAmount() {
		return maxBookingAmount;
	}

	public void setMaxBookingAmount(Float maxBookingAmount) {
		this.maxBookingAmount = maxBookingAmount;
	}

	public Float getMultiple() {
		return multiple;
	}

	public void setMultiple(Float multiple) {
		this.multiple = multiple;
	}

	public Float getFixedBookingAmount() {
		return fixedBookingAmount;
	}

	public void setFixedBookingAmount(Float fixedBookingAmount) {
		this.fixedBookingAmount = fixedBookingAmount;
	}

	public Float getReOrderPoint() {
		return reOrderPoint;
	}

	public void setReOrderPoint(Float reOrderPoint) {
		this.reOrderPoint = reOrderPoint;
	}

	public Float getEconomicOrderQuantity() {
		return economicOrderQuantity;
	}

	public void setEconomicOrderQuantity(Float economicOrderQuantity) {
		this.economicOrderQuantity = economicOrderQuantity;
	}

	public String getStructureCode() {
		return structureCode;
	}

	public void setStructureCode(String structureCode) {
		this.structureCode = structureCode;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
