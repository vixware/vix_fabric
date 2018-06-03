package com.vix.sales.refund.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.utils.DateUtil;
import com.vix.mdm.crm.entity.CustomerAccount;

/** 退款单 */
public class SaleReturnBill extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 返款金额 */
	private Double returnAmount;
	/** 币种 */
	private CurrencyType currencyType;
	/** 返款日期 */
	private Date rbDate;
	/** 返款单明细 */
	private Set<SaleReturnBillDetail> saleReturnBillDetails = new HashSet<SaleReturnBillDetail>();

	public SaleReturnBill(){}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}
	public String getCustomerAccountName() {
		if(customerAccount != null) {
			return customerAccount.getName();
		}
		return "";
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public Double getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(Double returnAmount) {
		this.returnAmount = returnAmount;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public Date getRbDate() {
		return rbDate;
	}
	
	public String getRbDateStr() {
		if(rbDate != null){
			return DateUtil.format(rbDate);
		}
		return "";
	}
	public String getRbDateTimeStr() {
		if(rbDate != null){
			return DateUtil.formatTime(rbDate);
		}
		return "";
	}

	public void setRbDate(Date rbDate) {
		this.rbDate = rbDate;
	}

	public Set<SaleReturnBillDetail> getSaleReturnBillDetails() {
		return saleReturnBillDetails;
	}

	public void setSaleReturnBillDetails(
			Set<SaleReturnBillDetail> saleReturnBillDetails) {
		this.saleReturnBillDetails = saleReturnBillDetails;
	}
}
