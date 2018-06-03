package com.vix.common.orginialMeta.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 币种管理
 *
 */
public class OrginialCurrencyType extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 是否本位币 */
	private String isBaseCurrency;
	
	public OrginialCurrencyType(){}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getIsBaseCurrency() {
		return isBaseCurrency;
	}

	public void setIsBaseCurrency(String isBaseCurrency) {
		this.isBaseCurrency = isBaseCurrency;
	}

}
