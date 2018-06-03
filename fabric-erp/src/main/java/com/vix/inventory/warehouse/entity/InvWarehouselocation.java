package com.vix.inventory.warehouse.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 仓库地址
 */

public class InvWarehouselocation extends BaseEntity {

	private static final long serialVersionUID = 4797076521399599092L;
	/**
	 * 国家
	 */
	private String country;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 省份/州
	 */
	private String province;
	/**
	 * 街道
	 */
	private String street;
	/**
	 * 邮政编码
	 */
	private String postCode;
	/**
	 * 邮政信箱
	 */
	private String mailBox;

	/** 仓库信息 */
	private InvWarehouse invWarehouse;

	public InvWarehouselocation() {
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getMailBox() {
		return mailBox;
	}

	public void setMailBox(String mailBox) {
		this.mailBox = mailBox;
	}

	public InvWarehouse getInvWarehouse() {
		return invWarehouse;
	}

	public void setInvWarehouse(InvWarehouse invWarehouse) {
		this.invWarehouse = invWarehouse;
	}

}