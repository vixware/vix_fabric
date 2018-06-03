package com.vix.crm.business.util;

public class CustomerCombinedModel {
	/**
	 * 外部标识
	 */
	private String outerId;
	/**
	 * 会员姓名
	 */
	private String customerName;
	/**
	 * 手机
	 */
	private String mobile;
	/**
	 * 固定电话
	 */
	private String tel;
	/**
	 * 常用邮箱
	 */
	private String email;
	/**
	 * 联系地址
	 */
	private String addr;
	/**
	 * 网店ID
	 */
	private String channelDistributorId;
	/**
	 * 会员ID
	 */
	private String businessCustomerId;

	public String getOuterId() {
		return outerId;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getChannelDistributorId() {
		return channelDistributorId;
	}

	public void setChannelDistributorId(String channelDistributorId) {
		this.channelDistributorId = channelDistributorId;
	}

	public String getBusinessCustomerId() {
		return businessCustomerId;
	}

	public void setBusinessCustomerId(String businessCustomerId) {
		this.businessCustomerId = businessCustomerId;
	}


}
