package com.vix.drp.storeInformation.entity;

import com.vix.common.share.entity.BaseEntity;

public class Address extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 国家
	 */
	private String contry;
	/**
	 * 省
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 详细地址
	 */
	private String district;
	/**
	 * 详细地址
	 */
	private String detail;
	/**
	 * 邮政编码
	 */
	private String postCode;

	public String getContry() {
		return contry;
	}

	public void setContry(String contry) {
		this.contry = contry;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

}
