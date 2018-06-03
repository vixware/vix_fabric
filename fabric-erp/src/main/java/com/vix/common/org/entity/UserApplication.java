package com.vix.common.org.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 用户申请信息
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.common.org.entity.UserApplication
 *
 * @date 2018年1月11日
 */
public class UserApplication extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 名称 */
	private String orgName;
	/** 地址 */
	private String address;
	/** 所属行业 */
	private String industry;
	/** 联系人 */
	private String companyOrgContact;
	/** 联系电话 */
	private String telephone;
	/** email */
	private String email;

	public UserApplication() {
	}

	public UserApplication(String id) {
		setId(id);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName
	 *            the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * @return the industry
	 */
	public String getIndustry() {
		return industry;
	}

	/**
	 * @param industry
	 *            the industry to set
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	/**
	 * @return the companyOrgContact
	 */
	public String getCompanyOrgContact() {
		return companyOrgContact;
	}

	/**
	 * @param companyOrgContact
	 *            the companyOrgContact to set
	 */
	public void setCompanyOrgContact(String companyOrgContact) {
		this.companyOrgContact = companyOrgContact;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
