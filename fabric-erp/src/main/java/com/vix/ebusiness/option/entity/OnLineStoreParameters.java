package com.vix.ebusiness.option.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 网店参数
 * 
 * @author zhanghaibing
 * 
 * @date 2014-3-7
 */

public class OnLineStoreParameters extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 是否开启自动分仓1,开启
	 */
	private String isOpenAutomaticWarehouse;
	/**
	 * 寄件公司
	 */
	private String fromCompany;
	/**
	 * 联络人
	 */
	private String contactPerson;
	/**
	 * 地址
	 */
	private String fromAddress;
	/**
	 * 联系电话
	 */
	private String fromPhone;
	/**
	 * 贺语
	 */
	private String greetings;

	public String getFromCompany() {
		return fromCompany;
	}

	public void setFromCompany(String fromCompany) {
		this.fromCompany = fromCompany;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getFromPhone() {
		return fromPhone;
	}

	public void setFromPhone(String fromPhone) {
		this.fromPhone = fromPhone;
	}

	public String getGreetings() {
		return greetings;
	}

	public void setGreetings(String greetings) {
		this.greetings = greetings;
	}

	public String getIsOpenAutomaticWarehouse() {
		return isOpenAutomaticWarehouse;
	}

	public void setIsOpenAutomaticWarehouse(String isOpenAutomaticWarehouse) {
		this.isOpenAutomaticWarehouse = isOpenAutomaticWarehouse;
	}

}