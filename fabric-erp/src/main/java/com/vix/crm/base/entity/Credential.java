package com.vix.crm.base.entity;

import com.vix.common.share.entity.BaseEntity;

/** 证件 */
public class Credential extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 证件号 */
	private String credentialCode;
	/** 证件类型 */
	private CredentialType credentialType;
	/** 备注 */
	private String memo;

	public Credential(){}

	public String getCredentialCode() {
		return credentialCode;
	}

	public void setCredentialCode(String credentialCode) {
		this.credentialCode = credentialCode;
	}
 
	public CredentialType getCredentialType() {
		return credentialType;
	}

	public void setCredentialType(CredentialType credentialType) {
		this.credentialType = credentialType;
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
