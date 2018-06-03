package com.vix.inventory.warehouse.entity;

import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 货区
 */

public class InvWarehousezone extends BaseEntity {
	private static final long serialVersionUID = -5760214008774871164L;
	/**
	 * 父类
	 */
	private InvWarehousezone parentInvWarehousezone;
	/**
	 * 子集合
	 */
	private Set<InvWarehousezone> subInvWarehousezone;

	/**
	 * 仓库
	 */
	private InvWarehouse invWarehouse;

	/**
	 * 货架(位)
	 */
	private Set<InvShelf> subInvShelf;

	public InvWarehousezone() {
	}

	public InvWarehousezone getParentInvWarehousezone() {
		return parentInvWarehousezone;
	}

	public void setParentInvWarehousezone(InvWarehousezone parentInvWarehousezone) {
		this.parentInvWarehousezone = parentInvWarehousezone;
	}

	public Set<InvWarehousezone> getSubInvWarehousezone() {
		return subInvWarehousezone;
	}

	public void setSubInvWarehousezone(Set<InvWarehousezone> subInvWarehousezone) {
		this.subInvWarehousezone = subInvWarehousezone;
	}

	public InvWarehouse getInvWarehouse() {
		return invWarehouse;
	}

	public void setInvWarehouse(InvWarehouse invWarehouse) {
		this.invWarehouse = invWarehouse;
	}

	public Set<InvShelf> getSubInvShelf() {
		return subInvShelf;
	}

	public void setSubInvShelf(Set<InvShelf> subInvShelf) {
		this.subInvShelf = subInvShelf;
	}

}