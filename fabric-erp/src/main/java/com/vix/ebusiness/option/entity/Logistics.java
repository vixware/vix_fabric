package com.vix.ebusiness.option.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 物流公司 com.vix.E_business.option.entity.Logistics
 * 
 * @author zhanghaibing
 * 
 * @date 2014-6-18
 */

public class Logistics extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String isDefault;

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

}