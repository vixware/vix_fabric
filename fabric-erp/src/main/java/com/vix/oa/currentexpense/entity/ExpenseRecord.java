package com.vix.oa.currentexpense.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.mdm.crm.entity.CustomerAccount;
/**
 * 消费记录
 * @author jackie
 *
 */
public class ExpenseRecord extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 消费金额*/
	private Double payAmount;
	/** 会员*/
	private CustomerAccount customerAccount;
	/** 消费日期*/
	private Date payDate;
	/** 消费类型*/
	private String payType;
	
	public Double getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}
	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}
	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public String getPayDateStr(){
		if(payDate != null){
			return DateUtil.formatTime(payDate);
		}
		return "";
	}
}
