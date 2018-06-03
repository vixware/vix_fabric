package com.vix.crm.base.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 证件类型
 * 用户的证件类型，如：身份证，驾驶证等
 * @author Administrator
 *
 */
public class CredentialType extends BaseEntity {

	private static final long serialVersionUID = 1L;
	

	/** 是否启用 */
	private String enable;
	/** 是否缺省 */
	private String isDefault;
	/** 证件类型 */
	private String name;
	/** 备注 */
	private String memo;
	
	public CredentialType(){}
 
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

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
