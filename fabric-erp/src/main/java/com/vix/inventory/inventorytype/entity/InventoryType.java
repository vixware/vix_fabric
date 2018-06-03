package com.vix.inventory.inventorytype.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 入库类别
 * 
 * @author zhanghaibing
 * 
 * @date 2014-3-4
 */
public class InventoryType extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 是否启用 */
	private String enable;
	/** 是否缺省 */
	private String isDefault;

	public InventoryType() {
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

}
