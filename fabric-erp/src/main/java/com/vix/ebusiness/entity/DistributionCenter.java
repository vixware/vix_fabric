package com.vix.ebusiness.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 配送中心 com.vix.E_business.entity.DistributionCenter
 *
 * @author bjitzhang
 *
 * @date 2014年8月19日
 */
public class DistributionCenter extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9178864461048165159L;
	private Long distributionCenterId;
	private String distributionCenterName;
	private String companyName;
	private String province;
	private String city;
	private String country;
	private String address;
	private String region;
	private String contactName;
	private String zip;
	private String phone;
	private String mobile;
	private String distributioncenterCode;
	private Integer distributioncenterType;
	private Integer isDefault;

	public Long getDistributionCenterId() {
		return distributionCenterId;
	}

	public void setDistributionCenterId(Long distributionCenterId) {
		this.distributionCenterId = distributionCenterId;
	}

	public String getDistributionCenterName() {
		return distributionCenterName;
	}

	public void setDistributionCenterName(String distributionCenterName) {
		this.distributionCenterName = distributionCenterName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDistributioncenterCode() {
		return distributioncenterCode;
	}

	public void setDistributioncenterCode(String distributioncenterCode) {
		this.distributioncenterCode = distributioncenterCode;
	}

	public Integer getDistributioncenterType() {
		return distributioncenterType;
	}

	public void setDistributioncenterType(Integer distributioncenterType) {
		this.distributioncenterType = distributioncenterType;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

}
