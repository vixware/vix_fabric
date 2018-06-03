package com.vix.drp.rebatesManagement.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.CurrencyType;
import com.vix.drp.refundRule.entity.RefundRule;
import com.vix.mdm.crm.entity.CustomerAccount;

/**
 * 返款单
 * 
 * @author zhanghaibing
 * 
 * @date 2014-2-14
 */

public class ReturnBill extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6372110282661495527L;
	/** 返款单编码 */
	private String rbCode;
	/** 返款单主题 */
	private String rbTitle;
	/** 物料编码 */
	private String itemCode;
	/** 物料 */
	private String itemName;
	/** 返款金额 */
	private Double returnAmount;
	/** 币种 */
	private String currency;
	/** 返款日期 */
	private Date rbDate;
	/**
	 * 返款规则
	 */
	private RefundRule refundRule;
	/**
	 * 会员信息
	 */
	private CustomerAccount customerAccount;
	/**
	 * 币种
	 */
	private CurrencyType currencyType;

	public String getRbCode() {
		return rbCode;
	}

	public void setRbCode(String rbCode) {
		this.rbCode = rbCode;
	}

	public String getRbTitle() {
		return rbTitle;
	}

	public void setRbTitle(String rbTitle) {
		this.rbTitle = rbTitle;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(Double returnAmount) {
		this.returnAmount = returnAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getRbDate() {
		return rbDate;
	}

	public void setRbDate(Date rbDate) {
		this.rbDate = rbDate;
	}

	public RefundRule getRefundRule() {
		return refundRule;
	}

	public void setRefundRule(RefundRule refundRule) {
		this.refundRule = refundRule;
	}

	/**
	 * @return the currencyType
	 */
	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	/**
	 * @param currencyType
	 *            the currencyType to set
	 */
	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

}
