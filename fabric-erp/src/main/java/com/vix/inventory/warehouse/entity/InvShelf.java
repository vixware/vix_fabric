package com.vix.inventory.warehouse.entity;

import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 货架(位)
 */

public class InvShelf extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 高
	 */
	private Double shelfHeight;
	/**
	 * 宽
	 */
	private Double shelfWidth;
	/**
	 * 长
	 */
	private Double shelfLength;
	/**
	 * 成本中心编码
	 */
	private String costCenterCode;
	/**
	 * 成本中心
	 */
	private String costCenter;
	/**
	 * 发料标识
	 */
	private String isDeliverItem;
	/**
	 * 成本标识
	 */
	private String isCostAsset;
	/**
	 * 是否允许负数标识
	 */
	private String isNagitive;

	/**
	 * 出货仓库标识
	 */
	private String isDeliverWarehouse;
	/**
	 * 优先级
	 */
	private Integer priority;
	/**
	 * 类型1 货架 2货位
	 */
	private Integer type;
	/**
	 * 最大存货数量
	 */
	private Integer maximum;

	/**
	 * 仓库
	 */
	private InvWarehouse invWarehouse;

	/**
	 * 货区
	 */
	private InvWarehousezone invWarehousezone;
	/**
	 * 父类
	 */
	private InvShelf parentInvShelf;
	/**
	 * 子集合
	 */
	private Set<InvShelf> subInvShelfs;
	/**
	 * 是否默认0,是;1,否
	 */
	private String isDefault;

	public InvShelf() {
	}

	public Double getShelfHeight() {
		return shelfHeight;
	}

	public void setShelfHeight(Double shelfHeight) {
		this.shelfHeight = shelfHeight;
	}

	public Double getShelfWidth() {
		return shelfWidth;
	}

	public String getWarehouseName() {
		if (invWarehouse != null) {
			return invWarehouse.getName();
		}
		return "";
	}

	public void setShelfWidth(Double shelfWidth) {
		this.shelfWidth = shelfWidth;
	}

	public Double getShelfLength() {
		return shelfLength;
	}

	public void setShelfLength(Double shelfLength) {
		this.shelfLength = shelfLength;
	}

	public String getCostCenterCode() {
		return this.costCenterCode;
	}

	public void setCostCenterCode(String costCenterCode) {
		this.costCenterCode = costCenterCode;
	}

	public String getCostCenter() {
		return this.costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getIsDeliverItem() {
		return this.isDeliverItem;
	}

	public void setIsDeliverItem(String isDeliverItem) {
		this.isDeliverItem = isDeliverItem;
	}

	public String getIsCostAsset() {
		return this.isCostAsset;
	}

	public void setIsCostAsset(String isCostAsset) {
		this.isCostAsset = isCostAsset;
	}

	public String getIsNagitive() {
		return this.isNagitive;
	}

	public void setIsNagitive(String isNagitive) {
		this.isNagitive = isNagitive;
	}

	public String getIsDeliverWarehouse() {
		return this.isDeliverWarehouse;
	}

	public void setIsDeliverWarehouse(String isDeliverWarehouse) {
		this.isDeliverWarehouse = isDeliverWarehouse;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getMaximum() {
		return this.maximum;
	}

	public void setMaximum(Integer maximum) {
		this.maximum = maximum;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public InvWarehouse getInvWarehouse() {
		return invWarehouse;
	}

	public void setInvWarehouse(InvWarehouse invWarehouse) {
		this.invWarehouse = invWarehouse;
	}

	public InvWarehousezone getInvWarehousezone() {
		return invWarehousezone;
	}

	public void setInvWarehousezone(InvWarehousezone invWarehousezone) {
		this.invWarehousezone = invWarehousezone;
	}

	public InvShelf getParentInvShelf() {
		return parentInvShelf;
	}

	public void setParentInvShelf(InvShelf parentInvShelf) {
		this.parentInvShelf = parentInvShelf;
	}

	public Set<InvShelf> getSubInvShelfs() {
		return subInvShelfs;
	}

	public void setSubInvShelfs(Set<InvShelf> subInvShelfs) {
		this.subInvShelfs = subInvShelfs;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

}