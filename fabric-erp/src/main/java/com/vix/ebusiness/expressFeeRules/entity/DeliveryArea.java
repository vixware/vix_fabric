package com.vix.ebusiness.expressFeeRules.entity;

import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 区域 com.vix.ebusiness.expressFeeRules.entity.DeliveryArea
 *
 * @author zhanghaibing
 *
 * @date 2014年12月18日
 */
public class DeliveryArea extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9178864461048165159L;
	/**
	 * 包含省份
	 */
	private Set<Provinces> subProvincess;

	public Set<Provinces> getSubProvincess() {
		return subProvincess;
	}

	public void setSubProvincess(Set<Provinces> subProvincess) {
		this.subProvincess = subProvincess;
	}

}
