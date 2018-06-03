package com.vix.mdm.item.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.common.share.entity.MeasureUnitGroup;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.warehouse.entity.InvShelf;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.mdm.purchase.order.entity.OrderType;
import com.vix.mdm.srm.share.entity.Supplier;

/** 采购属性 */
public class ItemPurchaseProperties extends BaseEntity {
 
	private static final long serialVersionUID = 1L;

	/** 供应商 */
	private Supplier supplier;
	/** 供应商物料编码 */
	private String supplierItemCode;
	/** 物料类型 */
	private String itemType;
	/** 单位组 */
	private MeasureUnitGroup measureUnitGroup;
	/** 采购基本计量单位 */
	private MeasureUnit purBaseUnit;
	/** 采购辅助计量单位 */
	private MeasureUnit purAssitUnit;
	/** 采购订单单位 */
	private MeasureUnit poUnit;
	/** 采购组 */
	private String purchaseGroup;
	/** 采购员编码 */
	private String purchasePersonCode;
	/** 采购员 */
	private Employee purchasePerson;
	/** 不足交货容差 */
	private Double lessDeliveryAllowance;
	/** 超量交货容差 */
	private Double exceedDelieryAllowance;
	/** 最小交货数量百分比 */
	private Double minDelieryPercent;
	/** 最小交货数量 */
	private Double minDelieryAmount;
	/** 催付天数1 */
	private Double remindDay1;
	/** 催付天数2 */
	private Double remindDay2;
	/** 催付天数3 */
	private Double remindDay3;
	/** 催付天数4 */
	private Double remindDay4;
	/** 催付天数5 */
	private Double remindDay5;
	/** 标准交货时间偏差(D) */
	private Double standardDelieryDeviation;
	/** 收货处理时间(D) */
	private Double takeDelieryDays;
	/** 配额安排 */
	private Double quota;
	/** 是否需要批次管理 */
	private String isNeedBatch;
	/** 是否为关键组件 */
	private Integer isKeyComponent;
	/** JIT */
	private Integer isJIT;
	/** 供应商物料条形码 */
	private String supplierItemBarCode;
	/** 制造商零配件编码 */
	private String producerSharepartCode;
	/** 制造商零配件概况 */
	private String produceSharepartDesc;
	/** 采购类型 */
	private OrderType orderType;
	/** 特殊采购类 */
	private String specialPurchaseType;
	/** 允许提前天数 */
	private Double permitAheadDays;
	/** 允许滞后天数 */
	private Double permitDelayDays;
	/** 是否允许替换物品收货 */
	private Integer isPermitReplaceItem;
	/** 是否允许没有订单收货 */
	private Integer isPermitNoOrderTake;
	/** 接收仓库 */
	private InvWarehouse recieveWarehouse;
	/** 库位 */
	private InvShelf invShelf;
	/** 接收地址 */
	private String recieveLocation;
	/** 物料 */
	private Item item;
	
	public ItemPurchaseProperties(){}
 
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getSupplierItemCode() {
		return supplierItemCode;
	}

	public void setSupplierItemCode(String supplierItemCode) {
		this.supplierItemCode = supplierItemCode;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public MeasureUnitGroup getMeasureUnitGroup() {
		return measureUnitGroup;
	}

	public void setMeasureUnitGroup(MeasureUnitGroup measureUnitGroup) {
		this.measureUnitGroup = measureUnitGroup;
	}

	public MeasureUnit getPurBaseUnit() {
		return purBaseUnit;
	}

	public void setPurBaseUnit(MeasureUnit purBaseUnit) {
		this.purBaseUnit = purBaseUnit;
	}

	public MeasureUnit getPurAssitUnit() {
		return purAssitUnit;
	}

	public void setPurAssitUnit(MeasureUnit purAssitUnit) {
		this.purAssitUnit = purAssitUnit;
	}

	public MeasureUnit getPoUnit() {
		return poUnit;
	}

	public void setPoUnit(MeasureUnit poUnit) {
		this.poUnit = poUnit;
	}

	public String getPurchaseGroup() {
		return purchaseGroup;
	}

	public void setPurchaseGroup(String purchaseGroup) {
		this.purchaseGroup = purchaseGroup;
	}

	public String getPurchasePersonCode() {
		return purchasePersonCode;
	}

	public void setPurchasePersonCode(String purchasePersonCode) {
		this.purchasePersonCode = purchasePersonCode;
	}

	public Employee getPurchasePerson() {
		return purchasePerson;
	}

	public void setPurchasePerson(Employee purchasePerson) {
		this.purchasePerson = purchasePerson;
	}

	public Double getLessDeliveryAllowance() {
		return lessDeliveryAllowance;
	}

	public void setLessDeliveryAllowance(Double lessDeliveryAllowance) {
		this.lessDeliveryAllowance = lessDeliveryAllowance;
	}

	public Double getExceedDelieryAllowance() {
		return exceedDelieryAllowance;
	}

	public void setExceedDelieryAllowance(Double exceedDelieryAllowance) {
		this.exceedDelieryAllowance = exceedDelieryAllowance;
	}

	public Double getMinDelieryPercent() {
		return minDelieryPercent;
	}

	public void setMinDelieryPercent(Double minDelieryPercent) {
		this.minDelieryPercent = minDelieryPercent;
	}

	public Double getMinDelieryAmount() {
		return minDelieryAmount;
	}

	public void setMinDelieryAmount(Double minDelieryAmount) {
		this.minDelieryAmount = minDelieryAmount;
	}

	public Double getRemindDay1() {
		return remindDay1;
	}

	public void setRemindDay1(Double remindDay1) {
		this.remindDay1 = remindDay1;
	}

	public Double getRemindDay2() {
		return remindDay2;
	}

	public void setRemindDay2(Double remindDay2) {
		this.remindDay2 = remindDay2;
	}

	public Double getRemindDay3() {
		return remindDay3;
	}

	public void setRemindDay3(Double remindDay3) {
		this.remindDay3 = remindDay3;
	}

	public Double getRemindDay4() {
		return remindDay4;
	}

	public void setRemindDay4(Double remindDay4) {
		this.remindDay4 = remindDay4;
	}

	public Double getRemindDay5() {
		return remindDay5;
	}

	public void setRemindDay5(Double remindDay5) {
		this.remindDay5 = remindDay5;
	}

	public Double getStandardDelieryDeviation() {
		return standardDelieryDeviation;
	}

	public void setStandardDelieryDeviation(Double standardDelieryDeviation) {
		this.standardDelieryDeviation = standardDelieryDeviation;
	}

	public Double getTakeDelieryDays() {
		return takeDelieryDays;
	}

	public void setTakeDelieryDays(Double takeDelieryDays) {
		this.takeDelieryDays = takeDelieryDays;
	}

	public Double getQuota() {
		return quota;
	}

	public void setQuota(Double quota) {
		this.quota = quota;
	}

	public String getIsNeedBatch() {
		return isNeedBatch;
	}

	public void setIsNeedBatch(String isNeedBatch) {
		this.isNeedBatch = isNeedBatch;
	}

	public Integer getIsKeyComponent() {
		return isKeyComponent;
	}

	public void setIsKeyComponent(Integer isKeyComponent) {
		this.isKeyComponent = isKeyComponent;
	}

	public Integer getIsJIT() {
		return isJIT;
	}

	public void setIsJIT(Integer isJIT) {
		this.isJIT = isJIT;
	}

	public String getSupplierItemBarCode() {
		return supplierItemBarCode;
	}

	public void setSupplierItemBarCode(String supplierItemBarCode) {
		this.supplierItemBarCode = supplierItemBarCode;
	}

	public String getProducerSharepartCode() {
		return producerSharepartCode;
	}

	public void setProducerSharepartCode(String producerSharepartCode) {
		this.producerSharepartCode = producerSharepartCode;
	}

	public String getProduceSharepartDesc() {
		return produceSharepartDesc;
	}

	public void setProduceSharepartDesc(String produceSharepartDesc) {
		this.produceSharepartDesc = produceSharepartDesc;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public String getSpecialPurchaseType() {
		return specialPurchaseType;
	}

	public void setSpecialPurchaseType(String specialPurchaseType) {
		this.specialPurchaseType = specialPurchaseType;
	}

	public Double getPermitAheadDays() {
		return permitAheadDays;
	}

	public void setPermitAheadDays(Double permitAheadDays) {
		this.permitAheadDays = permitAheadDays;
	}

	public Double getPermitDelayDays() {
		return permitDelayDays;
	}

	public void setPermitDelayDays(Double permitDelayDays) {
		this.permitDelayDays = permitDelayDays;
	}

	public Integer getIsPermitReplaceItem() {
		return isPermitReplaceItem;
	}

	public void setIsPermitReplaceItem(Integer isPermitReplaceItem) {
		this.isPermitReplaceItem = isPermitReplaceItem;
	}

	public Integer getIsPermitNoOrderTake() {
		return isPermitNoOrderTake;
	}

	public void setIsPermitNoOrderTake(Integer isPermitNoOrderTake) {
		this.isPermitNoOrderTake = isPermitNoOrderTake;
	}

	public InvWarehouse getRecieveWarehouse() {
		return recieveWarehouse;
	}

	public void setRecieveWarehouse(InvWarehouse recieveWarehouse) {
		this.recieveWarehouse = recieveWarehouse;
	}

	public InvShelf getInvShelf() {
		return invShelf;
	}

	public void setInvShelf(InvShelf invShelf) {
		this.invShelf = invShelf;
	}

	public String getRecieveLocation() {
		return recieveLocation;
	}

	public void setRecieveLocation(String recieveLocation) {
		this.recieveLocation = recieveLocation;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
