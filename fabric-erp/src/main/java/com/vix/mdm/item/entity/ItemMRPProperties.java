package com.vix.mdm.item.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/** 物料(产品)MRP属性 */
public class ItemMRPProperties extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 物料编码 */
	private String itemCode;
	/** 物料类型 */
	private String itemType;
	/** MRP类型 */
	private String mrpType;
	/** 重订货点 */
	private Integer reOrderPoint;
	/** 生产部门  */
	private String produceDepartment;
	/** 计划员 */
	private String planner;
	/** 计划方法 */
	private String planMethod;
	/** 计划时界 */
	private Double planTimeFence;
	/** 计划周期 */
	private String planPeroid;
	/** 装配报废率 */
	private Double assembleScrapRate;
	/** 产出率 */
	private Double outPutRate;
	/** 需求时栅 */
	private Double requirementTimefence;
	/** 计划时栅天数 */
	private Double planTimefenceDays;
	/** 重叠天数 */
	private Double overlapDays;
	/** 最高供应量 */
	private Double maxSupplyAmount;
	/** 最低供应量 */
	private Double minSupplyAmount;
	/** 供需政策 */
	private String supplyPolicy;
	/** 供应期间 */
	private Double supplyPeriod;
	/** 供应倍数 */
	private Double supplyMultiple;
	/** 变动基数 */
	private Double changeBase;
	/** 变动提前期 */
	private Double changeLeadTime;
	/** 可用性检查方式 */
	private String aTPCheckType;
	/** 供应类型 */
	private String supplyType;
	/** 工程图号 */
	private String projectDrawNumber;
	/** 低阶码 */
	private Integer lowLevelCode;
	/** 计划品 */
	private String planItem;
	/** 转换因子 */
	private Double conversionFactor;
	/** 是否检查ATP */
	private Integer isCheckATP;
	/** ATP规则 */
	private String aTPRule;
	/** 替换日期 */
	private Date replaceDate;
	/** 安全库存方法 */
	private String safeStockMethod;
	/** 期间类型 */
	private String periodType;
	/** 期间数  */
	private Double periodAmount;
	/** 动态安全库存方法 */
	private String dynamicSafeStockMethod;
	/** 覆盖天数 */
	private Double overlayDays;
	/** 百分比 */
	private Double percent;
	/** 是否属于主生产计划 */
	private String isBelongMPS;
	/** 订购策略 */
	private String bookingPolice;
	/** 工艺路线代码 */
	private String routeCode;
	/** 是否成本相关 */
	private String isCostRelated;
	/** 是否切除尾数 */
	private String isRemovalMantissa;
	/** 是否令单合并 */
	private String isProductOrderMerge;
	/** 是否为重复计划 */
	private String isRepeatPlan;
	/** 是否MPS件 */
	private String isMPSComponent;
	/** 是否允许BOM母件 */
	private String isPermitBOMParent;
	/** 是否允许BOM子件 */
	private String isPermitBOMChild;
	/** 是否允许生产订单 */
	private String isPermitProductOrder;
	/** 是否为销售跟单 */
	private String isSaleTrace;
	/** 批量数据-最小批量大小 */
	private Double batchMinAmount;
	/** 批量数据-最大批量大小 */
	private Double batchMaxAmount;
	/** 批量数据-固定批量大小 */
	private Double batchFixedAmount;
	/** 批量数据-最大库存水平 */
	private Double batchMaxInventory2;
	/** 批量数据-订购成本 */
	private Double batchOrderCost;
	/** 批量数据-装配报废率% */
	private Double batchAssembleScrap;
	/** 批量数据-间隔时间 */
	private Double batchPeriodTime;
	/** 批量数据-舍入值 */
	private Double batchRoundedValue;
	/** 批量数据-计量单位组 */
	private String batchUnitGroup;
	/** 采购-采购类型 */
	private String purType;
	/** 采购-反冲 */
	private String purRecoil;
	/** 采购-JIT交货	 */
	private String purJITDelivery;
	/** 计划-收货处理时间 */
	private Double planTakeDeliveryTime;
	/** 计划-交货时间 */
	private Date planDeliveryTime;
	/** 计划-计划日历 */
	private String planCalendar;
	/** 物料 */
	private Item item;
	
	public ItemMRPProperties(){}

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

	public String getMrpType() {
		return mrpType;
	}

	public void setMrpType(String mrpType) {
		this.mrpType = mrpType;
	}

	public Integer getReOrderPoint() {
		return reOrderPoint;
	}

	public void setReOrderPoint(Integer reOrderPoint) {
		this.reOrderPoint = reOrderPoint;
	}

	public String getProduceDepartment() {
		return produceDepartment;
	}

	public void setProduceDepartment(String produceDepartment) {
		this.produceDepartment = produceDepartment;
	}

	public String getPlanner() {
		return planner;
	}

	public void setPlanner(String planner) {
		this.planner = planner;
	}

	public String getPlanMethod() {
		return planMethod;
	}

	public void setPlanMethod(String planMethod) {
		this.planMethod = planMethod;
	}

	public Double getPlanTimeFence() {
		return planTimeFence;
	}

	public void setPlanTimeFence(Double planTimeFence) {
		this.planTimeFence = planTimeFence;
	}

	public String getPlanPeroid() {
		return planPeroid;
	}

	public void setPlanPeroid(String planPeroid) {
		this.planPeroid = planPeroid;
	}

	public Double getAssembleScrapRate() {
		return assembleScrapRate;
	}

	public void setAssembleScrapRate(Double assembleScrapRate) {
		this.assembleScrapRate = assembleScrapRate;
	}

	public Double getOutPutRate() {
		return outPutRate;
	}

	public void setOutPutRate(Double outPutRate) {
		this.outPutRate = outPutRate;
	}

	public Double getRequirementTimefence() {
		return requirementTimefence;
	}

	public void setRequirementTimefence(Double requirementTimefence) {
		this.requirementTimefence = requirementTimefence;
	}

	public Double getPlanTimefenceDays() {
		return planTimefenceDays;
	}

	public void setPlanTimefenceDays(Double planTimefenceDays) {
		this.planTimefenceDays = planTimefenceDays;
	}

	public Double getOverlapDays() {
		return overlapDays;
	}

	public void setOverlapDays(Double overlapDays) {
		this.overlapDays = overlapDays;
	}

	public Double getMaxSupplyAmount() {
		return maxSupplyAmount;
	}

	public void setMaxSupplyAmount(Double maxSupplyAmount) {
		this.maxSupplyAmount = maxSupplyAmount;
	}

	public Double getMinSupplyAmount() {
		return minSupplyAmount;
	}

	public void setMinSupplyAmount(Double minSupplyAmount) {
		this.minSupplyAmount = minSupplyAmount;
	}

	public String getSupplyPolicy() {
		return supplyPolicy;
	}

	public void setSupplyPolicy(String supplyPolicy) {
		this.supplyPolicy = supplyPolicy;
	}

	public Double getSupplyPeriod() {
		return supplyPeriod;
	}

	public void setSupplyPeriod(Double supplyPeriod) {
		this.supplyPeriod = supplyPeriod;
	}

	public Double getSupplyMultiple() {
		return supplyMultiple;
	}

	public void setSupplyMultiple(Double supplyMultiple) {
		this.supplyMultiple = supplyMultiple;
	}

	public Double getChangeBase() {
		return changeBase;
	}

	public void setChangeBase(Double changeBase) {
		this.changeBase = changeBase;
	}

	public Double getChangeLeadTime() {
		return changeLeadTime;
	}

	public void setChangeLeadTime(Double changeLeadTime) {
		this.changeLeadTime = changeLeadTime;
	}

	public String getaTPCheckType() {
		return aTPCheckType;
	}

	public void setaTPCheckType(String aTPCheckType) {
		this.aTPCheckType = aTPCheckType;
	}

	public String getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(String supplyType) {
		this.supplyType = supplyType;
	}

	public String getProjectDrawNumber() {
		return projectDrawNumber;
	}

	public void setProjectDrawNumber(String projectDrawNumber) {
		this.projectDrawNumber = projectDrawNumber;
	}

	public Integer getLowLevelCode() {
		return lowLevelCode;
	}

	public void setLowLevelCode(Integer lowLevelCode) {
		this.lowLevelCode = lowLevelCode;
	}

	public String getPlanItem() {
		return planItem;
	}

	public void setPlanItem(String planItem) {
		this.planItem = planItem;
	}

	public Double getConversionFactor() {
		return conversionFactor;
	}

	public void setConversionFactor(Double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	public Integer getIsCheckATP() {
		return isCheckATP;
	}

	public void setIsCheckATP(Integer isCheckATP) {
		this.isCheckATP = isCheckATP;
	}

	public String getaTPRule() {
		return aTPRule;
	}

	public void setaTPRule(String aTPRule) {
		this.aTPRule = aTPRule;
	}

	public Date getReplaceDate() {
		return replaceDate;
	}

	public void setReplaceDate(Date replaceDate) {
		this.replaceDate = replaceDate;
	}

	public String getSafeStockMethod() {
		return safeStockMethod;
	}

	public void setSafeStockMethod(String safeStockMethod) {
		this.safeStockMethod = safeStockMethod;
	}

	public String getPeriodType() {
		return periodType;
	}

	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}

	public Double getPeriodAmount() {
		return periodAmount;
	}

	public void setPeriodAmount(Double periodAmount) {
		this.periodAmount = periodAmount;
	}

	public String getDynamicSafeStockMethod() {
		return dynamicSafeStockMethod;
	}

	public void setDynamicSafeStockMethod(String dynamicSafeStockMethod) {
		this.dynamicSafeStockMethod = dynamicSafeStockMethod;
	}

	public Double getOverlayDays() {
		return overlayDays;
	}

	public void setOverlayDays(Double overlayDays) {
		this.overlayDays = overlayDays;
	}

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public String getIsBelongMPS() {
		return isBelongMPS;
	}

	public void setIsBelongMPS(String isBelongMPS) {
		this.isBelongMPS = isBelongMPS;
	}

	public String getBookingPolice() {
		return bookingPolice;
	}

	public void setBookingPolice(String bookingPolice) {
		this.bookingPolice = bookingPolice;
	}

	public String getRouteCode() {
		return routeCode;
	}

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	public String getIsCostRelated() {
		return isCostRelated;
	}

	public void setIsCostRelated(String isCostRelated) {
		this.isCostRelated = isCostRelated;
	}

	public String getIsRemovalMantissa() {
		return isRemovalMantissa;
	}

	public void setIsRemovalMantissa(String isRemovalMantissa) {
		this.isRemovalMantissa = isRemovalMantissa;
	}

	public String getIsProductOrderMerge() {
		return isProductOrderMerge;
	}

	public void setIsProductOrderMerge(String isProductOrderMerge) {
		this.isProductOrderMerge = isProductOrderMerge;
	}

	public String getIsRepeatPlan() {
		return isRepeatPlan;
	}

	public void setIsRepeatPlan(String isRepeatPlan) {
		this.isRepeatPlan = isRepeatPlan;
	}

	public String getIsMPSComponent() {
		return isMPSComponent;
	}

	public void setIsMPSComponent(String isMPSComponent) {
		this.isMPSComponent = isMPSComponent;
	}

	public String getIsPermitBOMParent() {
		return isPermitBOMParent;
	}

	public void setIsPermitBOMParent(String isPermitBOMParent) {
		this.isPermitBOMParent = isPermitBOMParent;
	}

	public String getIsPermitBOMChild() {
		return isPermitBOMChild;
	}

	public void setIsPermitBOMChild(String isPermitBOMChild) {
		this.isPermitBOMChild = isPermitBOMChild;
	}

	public String getIsPermitProductOrder() {
		return isPermitProductOrder;
	}

	public void setIsPermitProductOrder(String isPermitProductOrder) {
		this.isPermitProductOrder = isPermitProductOrder;
	}

	public String getIsSaleTrace() {
		return isSaleTrace;
	}

	public void setIsSaleTrace(String isSaleTrace) {
		this.isSaleTrace = isSaleTrace;
	}

	public Double getBatchMinAmount() {
		return batchMinAmount;
	}

	public void setBatchMinAmount(Double batchMinAmount) {
		this.batchMinAmount = batchMinAmount;
	}

	public Double getBatchMaxAmount() {
		return batchMaxAmount;
	}

	public void setBatchMaxAmount(Double batchMaxAmount) {
		this.batchMaxAmount = batchMaxAmount;
	}

	public Double getBatchFixedAmount() {
		return batchFixedAmount;
	}

	public void setBatchFixedAmount(Double batchFixedAmount) {
		this.batchFixedAmount = batchFixedAmount;
	}

	public Double getBatchMaxInventory2() {
		return batchMaxInventory2;
	}

	public void setBatchMaxInventory2(Double batchMaxInventory2) {
		this.batchMaxInventory2 = batchMaxInventory2;
	}

	public Double getBatchOrderCost() {
		return batchOrderCost;
	}

	public void setBatchOrderCost(Double batchOrderCost) {
		this.batchOrderCost = batchOrderCost;
	}

	public Double getBatchAssembleScrap() {
		return batchAssembleScrap;
	}

	public void setBatchAssembleScrap(Double batchAssembleScrap) {
		this.batchAssembleScrap = batchAssembleScrap;
	}

	public Double getBatchPeriodTime() {
		return batchPeriodTime;
	}

	public void setBatchPeriodTime(Double batchPeriodTime) {
		this.batchPeriodTime = batchPeriodTime;
	}

	public Double getBatchRoundedValue() {
		return batchRoundedValue;
	}

	public void setBatchRoundedValue(Double batchRoundedValue) {
		this.batchRoundedValue = batchRoundedValue;
	}

	public String getBatchUnitGroup() {
		return batchUnitGroup;
	}

	public void setBatchUnitGroup(String batchUnitGroup) {
		this.batchUnitGroup = batchUnitGroup;
	}

	public String getPurType() {
		return purType;
	}

	public void setPurType(String purType) {
		this.purType = purType;
	}

	public String getPurRecoil() {
		return purRecoil;
	}

	public void setPurRecoil(String purRecoil) {
		this.purRecoil = purRecoil;
	}

	public String getPurJITDelivery() {
		return purJITDelivery;
	}

	public void setPurJITDelivery(String purJITDelivery) {
		this.purJITDelivery = purJITDelivery;
	}

	public Double getPlanTakeDeliveryTime() {
		return planTakeDeliveryTime;
	}

	public void setPlanTakeDeliveryTime(Double planTakeDeliveryTime) {
		this.planTakeDeliveryTime = planTakeDeliveryTime;
	}

	public Date getPlanDeliveryTime() {
		return planDeliveryTime;
	}

	public void setPlanDeliveryTime(Date planDeliveryTime) {
		this.planDeliveryTime = planDeliveryTime;
	}

	public String getPlanCalendar() {
		return planCalendar;
	}

	public void setPlanCalendar(String planCalendar) {
		this.planCalendar = planCalendar;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
