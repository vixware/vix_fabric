package com.vix.mdm.item.entity;

import com.vix.common.share.entity.BaseEntity;

/** 物料(产品)质量属性 */
public class ItemQualityProperties extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 物料编码 */
	private String itemCode;
	/** 物料类型 */
	private String itemType;
	/** 质量要求 */
	private String qualityRequirement;
	/** 质量检验方法 */
	private String qualityMethod;
	/** 质检周期 */
	private String qualityCycle;
	/** 质检周期天数 */
	private Double qualityCycleDays;
	/** 检验说明 */
	private String qualityDescription;
	/** 到货入库质检 */
	private String isInStockQuality;
	/** 出库质检 */
	private String isOutStockQuality;
	/** 退货质检 */
	private String isReturnQuality;
	/** 抽检方案 */
	private String spotCheck;
	/** 抽检率 */
	private Double spotCheckRate;
	/** 抽检量 */
	private Double spotCheckAmount;
	/** 良品率%	 */
	private Double yieldRate;
	/** 检验等级 */
	private String checkClass;
	/** 是否批次取样 */
	private String isBackCheck;
	/** 取样标准 */
	private String checkStandart;
	/** 允许水平AQL */
	private Double averageAQL;
	/** 物料 */
	private Item item;
	
	public ItemQualityProperties(){}

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

	public String getQualityRequirement() {
		return qualityRequirement;
	}

	public void setQualityRequirement(String qualityRequirement) {
		this.qualityRequirement = qualityRequirement;
	}

	public String getQualityMethod() {
		return qualityMethod;
	}

	public void setQualityMethod(String qualityMethod) {
		this.qualityMethod = qualityMethod;
	}
 
	public String getQualityDescription() {
		return qualityDescription;
	}

	public void setQualityDescription(String qualityDescription) {
		this.qualityDescription = qualityDescription;
	}

	public String getQualityCycle() {
		return qualityCycle;
	}

	public void setQualityCycle(String qualityCycle) {
		this.qualityCycle = qualityCycle;
	}

	public Double getQualityCycleDays() {
		return qualityCycleDays;
	}

	public void setQualityCycleDays(Double qualityCycleDays) {
		this.qualityCycleDays = qualityCycleDays;
	}

	public String getIsInStockQuality() {
		return isInStockQuality;
	}

	public void setIsInStockQuality(String isInStockQuality) {
		this.isInStockQuality = isInStockQuality;
	}

	public String getIsOutStockQuality() {
		return isOutStockQuality;
	}

	public void setIsOutStockQuality(String isOutStockQuality) {
		this.isOutStockQuality = isOutStockQuality;
	}

	public String getIsReturnQuality() {
		return isReturnQuality;
	}

	public void setIsReturnQuality(String isReturnQuality) {
		this.isReturnQuality = isReturnQuality;
	}

	public String getSpotCheck() {
		return spotCheck;
	}

	public void setSpotCheck(String spotCheck) {
		this.spotCheck = spotCheck;
	}

	public Double getSpotCheckRate() {
		return spotCheckRate;
	}

	public void setSpotCheckRate(Double spotCheckRate) {
		this.spotCheckRate = spotCheckRate;
	}

	public Double getSpotCheckAmount() {
		return spotCheckAmount;
	}

	public void setSpotCheckAmount(Double spotCheckAmount) {
		this.spotCheckAmount = spotCheckAmount;
	}

	public Double getYieldRate() {
		return yieldRate;
	}

	public void setYieldRate(Double yieldRate) {
		this.yieldRate = yieldRate;
	}

	public String getCheckClass() {
		return checkClass;
	}

	public void setCheckClass(String checkClass) {
		this.checkClass = checkClass;
	}

	public String getIsBackCheck() {
		return isBackCheck;
	}

	public void setIsBackCheck(String isBackCheck) {
		this.isBackCheck = isBackCheck;
	}

	public String getCheckStandart() {
		return checkStandart;
	}

	public void setCheckStandart(String checkStandart) {
		this.checkStandart = checkStandart;
	}

	public Double getAverageAQL() {
		return averageAQL;
	}

	public void setAverageAQL(Double averageAQL) {
		this.averageAQL = averageAQL;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
