package com.vix.crm.business.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.crm.entity.CustomerAccount;

/**
 * 客户分组列表 com.vix.crm.business.entity.CustomerAccountGroupListView
 *
 * @author zhanghaibing
 *
 * @date 2015年1月9日
 */
public class CustomerAccountGroupListView extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 会员
	 */
	private CustomerAccount customerAccount;
	/**
	 * 会员ID
	 */
	private String customerAccountId;
	/**
	 * 会员名称
	 */
	private String customerName;
	/**
	 * 移动电话
	 */
	private String mobilePhone;
	/**
	 * 固定电话
	 */
	private String telephone;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 电子邮件
	 */
	private String email;
	/**
	 * 会员细分
	 */
	private MembershipSubdivision membershipSubdivision;
	/**
	 * 会员细分ID
	 */
	private String membershipSubdivisionId;

	private Long channelDistributorId;
	/**
	 * 唯一标识
	 */
	private String customerIdAndMembershipSubdivisionId;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public void setCustomerAccountId(String customerAccountId) {
		this.customerAccountId = customerAccountId;
	}

	public String getCustomerAccountId() {
		return customerAccountId;
	}

	public String getMembershipSubdivisionId() {
		return membershipSubdivisionId;
	}

	public void setMembershipSubdivisionId(String membershipSubdivisionId) {
		this.membershipSubdivisionId = membershipSubdivisionId;
	}

	public MembershipSubdivision getMembershipSubdivision() {
		return membershipSubdivision;
	}

	public void setMembershipSubdivision(MembershipSubdivision membershipSubdivision) {
		this.membershipSubdivision = membershipSubdivision;
	}

	public Long getChannelDistributorId() {
		return channelDistributorId;
	}

	public void setChannelDistributorId(Long channelDistributorId) {
		this.channelDistributorId = channelDistributorId;
	}

	public String getCustomerIdAndMembershipSubdivisionId() {
		return customerIdAndMembershipSubdivisionId;
	}

	public void setCustomerIdAndMembershipSubdivisionId(String customerIdAndMembershipSubdivisionId) {
		this.customerIdAndMembershipSubdivisionId = customerIdAndMembershipSubdivisionId;
	}


}