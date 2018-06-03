package com.vix.system.entity;

import com.vix.common.share.entity.BaseEntity;

public class UserFeedback extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 内容
	 */
	private String center;
	/**
	 * 联系人
	 */
	private String person;
	/**
	 * 手机号
	 */
	private String mobilePhone;
	/**
	 * 邮箱
	 */
	private String email;
	public String getCenter() {
		return center;
	}
	public void setCenter(String center) {
		this.center = center;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
