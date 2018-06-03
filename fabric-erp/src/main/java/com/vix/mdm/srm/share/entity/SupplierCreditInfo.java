package com.vix.mdm.srm.share.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * 供应商信用
 * 
 * @author Ivan 2013-05-22
 */
public class SupplierCreditInfo extends BaseEntity {

	private static final long serialVersionUID = 8625856086974109661L;
	/** 供应商编码 */
	private String supplierCode;
	/** 供应商名称 */
	private String supplierName;
	/** 单价是否含税 */
	private String isIncludeTax;
	/** 信用等级 */
	private String creditLevel;
	/** 信用额度 */
	private Double creditAmount;
	/** 信用证联系人 */
	private String creditContactPerson;
	/** 应付余额 */
	private Double payableBalance;
	/** 折扣率 */
	private Double discount;
	/** 付款条件 */
	private String payCondition;
	/** 采购/进口/委外付款协议 */
	private String payableAgreement;
	/** 合同默认收付款协议 */
	private String defaultPayAgreement;
	/** 最后交易日期 */
	private Date lastDealTime;
	/** 最后交易金额 */
	private Double lastDealAmount;
	/** 供应商 */
	private Supplier supplier;

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getIsIncludeTax() {
		return isIncludeTax;
	}

	public void setIsIncludeTax(String isIncludeTax) {
		this.isIncludeTax = isIncludeTax;
	}

	public String getCreditLevel() {
		return creditLevel;
	}

	public void setCreditLevel(String creditLevel) {
		this.creditLevel = creditLevel;
	}

	public Double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(Double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public String getCreditContactPerson() {
		return creditContactPerson;
	}

	public void setCreditContactPerson(String creditContactPerson) {
		this.creditContactPerson = creditContactPerson;
	}

	public Double getPayableBalance() {
		return payableBalance;
	}

	public void setPayableBalance(Double payableBalance) {
		this.payableBalance = payableBalance;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getPayCondition() {
		return payCondition;
	}

	public void setPayCondition(String payCondition) {
		this.payCondition = payCondition;
	}

	public String getPayableAgreement() {
		return payableAgreement;
	}

	public void setPayableAgreement(String payableAgreement) {
		this.payableAgreement = payableAgreement;
	}

	public String getDefaultPayAgreement() {
		return defaultPayAgreement;
	}

	public void setDefaultPayAgreement(String defaultPayAgreement) {
		this.defaultPayAgreement = defaultPayAgreement;
	}

	public Date getLastDealTime() {
		return lastDealTime;
	}
	
	public String getLastDealTimeStr(){
		if(lastDealTime != null){
			return DateUtil.format(lastDealTime);
		}
		return "";
	}
	public void setLastDealTime(Date lastDealTime) {
		this.lastDealTime = lastDealTime;
	}

	public Double getLastDealAmount() {
		return lastDealAmount;
	}

	public void setLastDealAmount(Double lastDealAmount) {
		this.lastDealAmount = lastDealAmount;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}
