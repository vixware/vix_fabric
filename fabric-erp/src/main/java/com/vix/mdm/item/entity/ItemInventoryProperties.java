package com.vix.mdm.item.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.inventory.warehouse.entity.InvShelf;
import com.vix.inventory.warehouse.entity.InvWarehouse;

/** 物料(产品)库存属性 */
public class ItemInventoryProperties extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 物料编码 */
	private String itemCode;
	/** 物料类型 */
	private String itemType;
	/** 最高库存 */
	private Double maxStockAmount;
	/** 最低库存 */
	private Double minStockAmount;
	/** 安全库存 */
	private Double safeStockAmount;
	/** 默认仓库 */
	private InvWarehouse defaultWarehouse;
	/** 库位 */
	private InvShelf defaultInvShelf;
	/** 替换物料 */
	private String replaceItem;
	/** 请购时上限 */
	private Double requireMaxAmount;
	/** 入库超额上限 */
	private Double inStockExceedAmount;
	/** 出库超额上限 */
	private Double outStockExceedAmount;
	/** 订货超额上限 */
	private Double bookingExceedAmount;
	/** 主辅计量单位转换率 */
	private Double unitExchangeRate;
	/** 合理耗损率 */
	private Double coefficientOfLosses;
	/** 经济批量 */
	private Double economicBatchAmount;
	/** ABC分类(码) */
	private String abcCatalog;
	/** 库存分类 */
	private String catalogCode;
	/** 自定义库存分类 */
	private String customerCatalog;
	/** 领料批量 */
	private Double receiveMateriaBatchAmount;
	/** 最小分割组 */
	private String minSperateGroup;
	/** 盘点周期 */
	private Double countingCycle;
	/** 盘点周期单位 */
	private String countingCycleUnit;
	/** 上次盘点时间 */
	private Date lastCountingTime;
	/** 是否单独存放 */
	private String isStockAlone;
	/** 出库跟踪入库 */
	private String isOutTraceIn;
	/** 序列号管理 */
	private String isNeedSerialNumber;
	/** 条码管理 */
	private String isNeedBarCode;
	/** 呆滞积压 */
	private Integer overStock;
	/** 质量检验 */
	private String isNeedQuality;
	/** 是否进行库存管理 */
	private String isNeedStock;
	/** 是否需要批次号 */
	private String isNeedBatchNumber;
	/** 库存寿命(保质期) */
	private Double shelfLife;
	/** 是否倒冲入账 */
	private String isBackflush;
	/** 物料 */
	private Item item;

	public ItemInventoryProperties() {
	}

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

	public Double getMaxStockAmount() {
		return maxStockAmount;
	}

	public void setMaxStockAmount(Double maxStockAmount) {
		this.maxStockAmount = maxStockAmount;
	}

	public Double getMinStockAmount() {
		return minStockAmount;
	}

	public void setMinStockAmount(Double minStockAmount) {
		this.minStockAmount = minStockAmount;
	}

	public Double getSafeStockAmount() {
		return safeStockAmount;
	}

	public void setSafeStockAmount(Double safeStockAmount) {
		this.safeStockAmount = safeStockAmount;
	}

	public InvWarehouse getDefaultWarehouse() {
		return defaultWarehouse;
	}

	public void setDefaultWarehouse(InvWarehouse defaultWarehouse) {
		this.defaultWarehouse = defaultWarehouse;
	}

	public InvShelf getDefaultInvShelf() {
		return defaultInvShelf;
	}

	public void setDefaultInvShelf(InvShelf defaultInvShelf) {
		this.defaultInvShelf = defaultInvShelf;
	}

	public String getReplaceItem() {
		return replaceItem;
	}

	public void setReplaceItem(String replaceItem) {
		this.replaceItem = replaceItem;
	}

	public Double getRequireMaxAmount() {
		return requireMaxAmount;
	}

	public void setRequireMaxAmount(Double requireMaxAmount) {
		this.requireMaxAmount = requireMaxAmount;
	}

	public Double getInStockExceedAmount() {
		return inStockExceedAmount;
	}

	public void setInStockExceedAmount(Double inStockExceedAmount) {
		this.inStockExceedAmount = inStockExceedAmount;
	}

	public Double getOutStockExceedAmount() {
		return outStockExceedAmount;
	}

	public void setOutStockExceedAmount(Double outStockExceedAmount) {
		this.outStockExceedAmount = outStockExceedAmount;
	}

	public Double getBookingExceedAmount() {
		return bookingExceedAmount;
	}

	public void setBookingExceedAmount(Double bookingExceedAmount) {
		this.bookingExceedAmount = bookingExceedAmount;
	}

	public Double getUnitExchangeRate() {
		return unitExchangeRate;
	}

	public void setUnitExchangeRate(Double unitExchangeRate) {
		this.unitExchangeRate = unitExchangeRate;
	}

	public Double getCoefficientOfLosses() {
		return coefficientOfLosses;
	}

	public void setCoefficientOfLosses(Double coefficientOfLosses) {
		this.coefficientOfLosses = coefficientOfLosses;
	}

	public Double getEconomicBatchAmount() {
		return economicBatchAmount;
	}

	public void setEconomicBatchAmount(Double economicBatchAmount) {
		this.economicBatchAmount = economicBatchAmount;
	}

	public String getAbcCatalog() {
		return abcCatalog;
	}

	public void setAbcCatalog(String abcCatalog) {
		this.abcCatalog = abcCatalog;
	}

	public String getCatalogCode() {
		return catalogCode;
	}

	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}

	public String getCustomerCatalog() {
		return customerCatalog;
	}

	public void setCustomerCatalog(String customerCatalog) {
		this.customerCatalog = customerCatalog;
	}

	public Double getReceiveMateriaBatchAmount() {
		return receiveMateriaBatchAmount;
	}

	public void setReceiveMateriaBatchAmount(Double receiveMateriaBatchAmount) {
		this.receiveMateriaBatchAmount = receiveMateriaBatchAmount;
	}

	public String getMinSperateGroup() {
		return minSperateGroup;
	}

	public void setMinSperateGroup(String minSperateGroup) {
		this.minSperateGroup = minSperateGroup;
	}

	public Double getCountingCycle() {
		return countingCycle;
	}

	public void setCountingCycle(Double countingCycle) {
		this.countingCycle = countingCycle;
	}

	public String getCountingCycleUnit() {
		return countingCycleUnit;
	}

	public void setCountingCycleUnit(String countingCycleUnit) {
		this.countingCycleUnit = countingCycleUnit;
	}

	public Date getLastCountingTime() {
		return lastCountingTime;
	}

	public void setLastCountingTime(Date lastCountingTime) {
		this.lastCountingTime = lastCountingTime;
	}

	public String getIsStockAlone() {
		return isStockAlone;
	}

	public void setIsStockAlone(String isStockAlone) {
		this.isStockAlone = isStockAlone;
	}

	public String getIsOutTraceIn() {
		return isOutTraceIn;
	}

	public void setIsOutTraceIn(String isOutTraceIn) {
		this.isOutTraceIn = isOutTraceIn;
	}

	public String getIsNeedSerialNumber() {
		return isNeedSerialNumber;
	}

	public void setIsNeedSerialNumber(String isNeedSerialNumber) {
		this.isNeedSerialNumber = isNeedSerialNumber;
	}

	public String getIsNeedBarCode() {
		return isNeedBarCode;
	}

	public void setIsNeedBarCode(String isNeedBarCode) {
		this.isNeedBarCode = isNeedBarCode;
	}

	public Integer getOverStock() {
		return overStock;
	}

	public void setOverStock(Integer overStock) {
		this.overStock = overStock;
	}

	public String getIsNeedQuality() {
		return isNeedQuality;
	}

	public void setIsNeedQuality(String isNeedQuality) {
		this.isNeedQuality = isNeedQuality;
	}

	public String getIsNeedStock() {
		return isNeedStock;
	}

	public void setIsNeedStock(String isNeedStock) {
		this.isNeedStock = isNeedStock;
	}

	public String getIsNeedBatchNumber() {
		return isNeedBatchNumber;
	}

	public void setIsNeedBatchNumber(String isNeedBatchNumber) {
		this.isNeedBatchNumber = isNeedBatchNumber;
	}

	public Double getShelfLife() {
		return shelfLife;
	}

	public void setShelfLife(Double shelfLife) {
		this.shelfLife = shelfLife;
	}

	public String getIsBackflush() {
		return isBackflush;
	}

	public void setIsBackflush(String isBackflush) {
		this.isBackflush = isBackflush;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
