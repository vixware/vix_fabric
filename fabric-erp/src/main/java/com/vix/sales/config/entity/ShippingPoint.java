package com.vix.sales.config.entity;

import com.vix.common.share.entity.BaseEntity;

/** 发运点 */
public class ShippingPoint extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 起运点编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 工作时间 */
	private String workTime;
	/** 地址信息 */
	private String address;
	/** 电话 */
	private String phone;
	/** 手机 */
	private String mobie;
	/** 邮件 */
	private String email;
	/** 备注 */
	private String memo;
	
	public ShippingPoint(){}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobie() {
		return mobie;
	}

	public void setMobie(String mobie) {
		this.mobie = mobie;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
