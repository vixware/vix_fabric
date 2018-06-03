package com.vix.mdm.srm.share.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 供应商地址
 * 
 * @author Ivan 2013-05-22
 */
public class SupplierAddress extends BaseEntity {

	private static final long serialVersionUID = -4532667229385913680L;
	/** 供应商编码 */
	private String supplierCode;
	/** 国家 */
	private String country;
	/** 城市 */
	private String city;
	/** 省份/州 */
	private String province;
	/** 街道 */
	private String street;
	/** 电话 */
	private String telephone;
	/** 传真 */
	private String fax;
	/** 邮政编码 */
	private String postCode;
	/** 邮政信箱 */
	private String postMail;
	/** 网址 */
	private String website;
	/** 供应商 */
	private Supplier supplier;

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPostMail() {
		return postMail;
	}

	public void setPostMail(String postMail) {
		this.postMail = postMail;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}
