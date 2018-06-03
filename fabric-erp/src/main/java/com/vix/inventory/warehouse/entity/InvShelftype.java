package com.vix.inventory.warehouse.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 库位类型(字典表)
 */

public class InvShelftype extends BaseEntity {
	private static final long serialVersionUID = 4864296877779290140L;
	/**
	 * 货架类型编码
	 */
	private String shelfTypeCode;
	/**
	 * 货架类型
	 */
	private String shelfType;

	public InvShelftype() {
	}

	public String getShelfTypeCode() {
		return this.shelfTypeCode;
	}

	public void setShelfTypeCode(String shelfTypeCode) {
		this.shelfTypeCode = shelfTypeCode;
	}

	public String getShelfType() {
		return this.shelfType;
	}

	public void setShelfType(String shelfType) {
		this.shelfType = shelfType;
	}

}