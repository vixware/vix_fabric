package com.vix.crm.base.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.nvix.common.base.entity.AddressInfo;

/** 客户地址
 * 客户地址信息，每个客户会有一个或多个地址。
 * @author Administrator
 *
 */
public class CustomerAddress extends BaseEntity {
 
	private static final long serialVersionUID = 1L;
	/** 国家 */
	private String country;
	/** 地区 */
	//private String region;
	private AddressInfo district;
	/** 省 */
	//private String province;
	private AddressInfo province;
	/** 城市 */
	//private String city;
	private AddressInfo city;
	/** 街道 */
	private String street;
	/** 地址 */
	private String address;
	/** 邮政编码 */
	private String postCode;
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 是否默认  */
	private String isDefault;
	
	public CustomerAddress(){}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public AddressInfo getDistrict() {
		return district;
	}

	public void setDistrict(AddressInfo district) {
		this.district = district;
	}

	public AddressInfo getProvince() {
		return province;
	}

	public void setProvince(AddressInfo province) {
		this.province = province;
	}

	public AddressInfo getCity() {
		return city;
	}

	public void setCity(AddressInfo city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}
	
	public String getCustomerAccountName() {
		if(customerAccount != null){
			return customerAccount.getName();
		}
		return "";
		
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
}
