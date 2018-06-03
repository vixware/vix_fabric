package com.vix.mdm.srm.share.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 供应商银行信息
 * 
 * @author Ivan 2013-05-22
 */
public class SupplierBankInfo extends BaseEntity {

	private static final long serialVersionUID = 1888927822238082627L;
	/** 供应商编码 */
	private String supplierCode;
	/** 开户银行 */
	private String bankName;
	/** 银行帐号 */
	private String bankAccount;
	/** 开户银行代码 */
	private String bankCode;
	/** 供应商 */
	private Supplier supplier;

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}
