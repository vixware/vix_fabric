package com.vix.sales.credit.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;

/** 信用执行结果 */
public class CreditExcuteResult extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 信用控制对象，订单与报价单 */
	private String creditControlObject;
	/** 信用执行结果 */
	private String result;
	/** 执行时间 */
	private Date excuteDate;
	/** 执行人 */
	private Employee excuter;
	
	public CreditExcuteResult(){}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public String getCreditControlObject() {
		return creditControlObject;
	}

	public void setCreditControlObject(String creditControlObject) {
		this.creditControlObject = creditControlObject;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getExcuteDate() {
		return excuteDate;
	}

	public void setExcuteDate(Date excuteDate) {
		this.excuteDate = excuteDate;
	}

	public Employee getExcuter() {
		return excuter;
	}

	public void setExcuter(Employee excuter) {
		this.excuter = excuter;
	}
}
