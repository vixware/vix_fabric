package com.vix.crm.analyse.wraper;

import java.util.Date;

import com.vix.mdm.crm.entity.CustomerAccount;

/**
 * RFM 数据传输类 
 *
 */
public class RfmDetailWraper {

	/** 客户 */
	private CustomerAccount customerAccount;
	/** 最后一次访问时间 */
	private Date recencyDate;
	/** 消费频率 */
	private Long frequency;
	/** 消费金额 */
	private Long monetary;
	
	public RfmDetailWraper(){}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public Date getRecencyDate() {
		return recencyDate;
	}

	public void setRecencyDate(Date recencyDate) {
		this.recencyDate = recencyDate;
	}

	public Long getFrequency() {
		return frequency;
	}

	public void setFrequency(Long frequency) {
		this.frequency = frequency;
	}

	public Long getMonetary() {
		return monetary;
	}

	public void setMonetary(Long monetary) {
		this.monetary = monetary;
	}
}
