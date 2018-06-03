package com.vix.drp.printMediaManagement.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 平面媒体
 * 
 * @author zhanghaibing
 * 
 * @date 2013-5-21
 */
public class PrintMedia extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7543077433410026685L;
	/**
	 * 栏目
	 */
	private String mediaColumn;
	/**
	 * 版面
	 */
	private String section;
	/**
	 * 价格
	 */
	private Double price;
	/**
	 * 说明
	 */
	private String introductions;
	/**
	 * 联系人
	 */
	private String contactPerson;
	/**
	 * 手机
	 */
	private String mobile;
	/**
	 * 邮件
	 */
	private String email;

	public String getMediaColumn() {
		return mediaColumn;
	}

	public void setMediaColumn(String mediaColumn) {
		this.mediaColumn = mediaColumn;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getIntroductions() {
		return introductions;
	}

	public void setIntroductions(String introductions) {
		this.introductions = introductions;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
