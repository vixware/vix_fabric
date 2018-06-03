package com.vix.mdm.srm.share.entity;

import com.vix.common.share.entity.BaseEntity;
/**
 * 供应商等级
 * @author jackie
 *
 */
public class SupplierLevel extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 是否默认   0,是   否 1
	 */
	private String isDefault;
	/**
	 * 是否启用 0是  1否
	 */
	private String isUse;
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
}
