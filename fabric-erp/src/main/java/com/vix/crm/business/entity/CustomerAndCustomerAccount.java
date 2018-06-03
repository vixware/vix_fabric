package com.vix.crm.business.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.ebusiness.entity.BusinessCustomer;
import com.vix.mdm.crm.entity.CustomerAccount;

/***
 * 网店会员 系统会员关联表 com.vix.crm.business.entity.CustomerAndCustomerAccount
 *
 * @author zhanghaibing
 *
 * @date 2014年12月30日
 */
public class CustomerAndCustomerAccount extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 门店key
	 */
	private String channelDistributorId;
	/**
	 * 网店会员
	 */
	private BusinessCustomer customer;
	/**
	 * 系统会员
	 */
	private CustomerAccount customerAccount;

	public BusinessCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(BusinessCustomer customer) {
		this.customer = customer;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public String getChannelDistributorId() {
		return channelDistributorId;
	}

	public void setChannelDistributorId(String channelDistributorId) {
		this.channelDistributorId = channelDistributorId;
	}


}
