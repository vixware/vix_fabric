package com.vix.hr.organization.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 联系方式
 * 
 * @author Administrator
 * 
 */
public class ContactType extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String linkMan;

	private String contactTel;

	private String contactAd;

	private String zipCode;

	private String email;

	private Employee employee;

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getContactAd() {
		return contactAd;
	}

	public void setContactAd(String contactAd) {
		this.contactAd = contactAd;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
