package com.vix.mdm.srm.share.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 供应商财务信息
 * 
 * @author Ivan 2013-05-22
 */
public class SupplierAccountingInfo extends BaseEntity {

	private static final long serialVersionUID = 8738226671100700632L;
	/** 总帐分类 */
	private String generalAccountCatalog;
	/** 付款条件 */
	private String payCondition;
	/** 付款类型 */
	private String payType;
	/** 应付帐款科目代码 */
	private String payAccountCode;
	/** 预付帐款科目代码 */
	private String prePayAccountCode;
	/** 信用信息 */
	private String creditInfo;
	/** 付款期限 */
	private Double limited;
	/** 付款方式 */
	private String payStyle;
	/** 付款对象 */
	private String payTarget;
	/** 发票抬头 */
	private String invoiceHeader;
	/** 增值税税务登记号 */
	private String vaTaxNumber;
	/** 增值税开户银行 */
	private String vaBank;
	/** 审核人 */
	private String checked;
	/** 缺省币种 */
	private String currency;
	/** 锁定付款(确定是否可以输入该供应商及付款单据) */
	private Integer isLock;
	/** 供应商编码 */
	private String supplierCode;
	/** 是否同时为客户 */
	private String icCustomer;
	/** 供应商 */
	private Supplier supplier;

	public String getGeneralAccountCatalog() {
		return generalAccountCatalog;
	}

	public void setGeneralAccountCatalog(String generalAccountCatalog) {
		this.generalAccountCatalog = generalAccountCatalog;
	}

	public String getPayCondition() {
		return payCondition;
	}

	public void setPayCondition(String payCondition) {
		this.payCondition = payCondition;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayAccountCode() {
		return payAccountCode;
	}

	public void setPayAccountCode(String payAccountCode) {
		this.payAccountCode = payAccountCode;
	}

	public String getPrePayAccountCode() {
		return prePayAccountCode;
	}

	public void setPrePayAccountCode(String prePayAccountCode) {
		this.prePayAccountCode = prePayAccountCode;
	}

	public String getCreditInfo() {
		return creditInfo;
	}

	public void setCreditInfo(String creditInfo) {
		this.creditInfo = creditInfo;
	}

	public Double getLimited() {
		return limited;
	}

	public void setLimited(Double limited) {
		this.limited = limited;
	}

	public String getPayStyle() {
		return payStyle;
	}

	public void setPayStyle(String payStyle) {
		this.payStyle = payStyle;
	}

	public String getPayTarget() {
		return payTarget;
	}

	public void setPayTarget(String payTarget) {
		this.payTarget = payTarget;
	}

	public String getInvoiceHeader() {
		return invoiceHeader;
	}

	public void setInvoiceHeader(String invoiceHeader) {
		this.invoiceHeader = invoiceHeader;
	}

	public String getVaTaxNumber() {
		return vaTaxNumber;
	}

	public void setVaTaxNumber(String vaTaxNumber) {
		this.vaTaxNumber = vaTaxNumber;
	}

	public String getVaBank() {
		return vaBank;
	}

	public void setVaBank(String vaBank) {
		this.vaBank = vaBank;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getIsLock() {
		return isLock;
	}

	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getIcCustomer() {
		return icCustomer;
	}

	public void setIcCustomer(String icCustomer) {
		this.icCustomer = icCustomer;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}
