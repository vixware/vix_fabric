package com.vix.common.share.entity;


/**
 * 共用-地址
 * @author Administrator
 *
 */
public class CMNAddress extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** 编码 */
	private String boCode;
	/** 业务对象名称 */
	private String boName;
	/** 业务对象类型 */
	private String boType;
	/** 国家 */
	private String country;
	/** 省 */
	private String province;
	/** 城市*/
	private String city;
	/** 区  */
	private String district;
	/** 详细地址 */
	private String detail;
	/** 邮政编码 */
	private String postCode;
	
	private CMNAddress(){}

	public String getBoCode() {
		return boCode;
	}

	public void setBoCode(String boCode) {
		this.boCode = boCode;
	}

	public String getBoName() {
		return boName;
	}

	public void setBoName(String boName) {
		this.boName = boName;
	}

	public String getBoType() {
		return boType;
	}

	public void setBoType(String boType) {
		this.boType = boType;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
	};
}
