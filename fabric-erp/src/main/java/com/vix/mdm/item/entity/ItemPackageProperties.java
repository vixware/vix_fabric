package com.vix.mdm.item.entity;

import com.vix.common.share.entity.BaseEntity;

/** 物料(产品)运输与包装属性 */
public class ItemPackageProperties extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 物料编码 */
	private String itemCode;
	/** 包装物料编码 */
	private String packageItemCode;
	/** 包装物料 */
	private String packageItem;
	/** 包装物规则 */
	private String packageRule;
	/** 是否可回收 */
	private Integer isRecycle;
	/** 运输分类 */
	private String transferCatalog;
	/** 容器 */
	private String container;
	/** 容器类型 */
	private String containerType;
	/** 运输工具 */
	private String transferTools;
	/** 内部尺寸 */
	private String innerDimensions;
	/** 最大运输重量 */
	private Double maxTransferWeight;
	/** 最小填充百分比 */
	private String minFillOffPercent;
	/** 物料 */
	private Item item;
	
	public ItemPackageProperties(){}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getPackageItemCode() {
		return packageItemCode;
	}

	public void setPackageItemCode(String packageItemCode) {
		this.packageItemCode = packageItemCode;
	}

	public String getPackageItem() {
		return packageItem;
	}

	public void setPackageItem(String packageItem) {
		this.packageItem = packageItem;
	}

	public String getPackageRule() {
		return packageRule;
	}

	public void setPackageRule(String packageRule) {
		this.packageRule = packageRule;
	}

	public Integer getIsRecycle() {
		return isRecycle;
	}

	public void setIsRecycle(Integer isRecycle) {
		this.isRecycle = isRecycle;
	}

	public String getTransferCatalog() {
		return transferCatalog;
	}

	public void setTransferCatalog(String transferCatalog) {
		this.transferCatalog = transferCatalog;
	}

	public String getContainer() {
		return container;
	}

	public void setContainer(String container) {
		this.container = container;
	}

	public String getContainerType() {
		return containerType;
	}

	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}

	public String getTransferTools() {
		return transferTools;
	}

	public void setTransferTools(String transferTools) {
		this.transferTools = transferTools;
	}

	public String getInnerDimensions() {
		return innerDimensions;
	}

	public void setInnerDimensions(String innerDimensions) {
		this.innerDimensions = innerDimensions;
	}

	public Double getMaxTransferWeight() {
		return maxTransferWeight;
	}

	public void setMaxTransferWeight(Double maxTransferWeight) {
		this.maxTransferWeight = maxTransferWeight;
	}

	public String getMinFillOffPercent() {
		return minFillOffPercent;
	}

	public void setMinFillOffPercent(String minFillOffPercent) {
		this.minFillOffPercent = minFillOffPercent;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
