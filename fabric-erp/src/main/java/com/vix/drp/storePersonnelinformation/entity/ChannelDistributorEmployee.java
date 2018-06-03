package com.vix.drp.storePersonnelinformation.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

public class ChannelDistributorEmployee extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 生日
	 */
	private Date birthday;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 电话
	 */
	private String telephone;
	/**
	 * 登陆账号
	 */
	private String userAccount;
	/**
	 * 登录密码
	 */
	private String password;
	/**
	 * 入职时间
	 */
	private Date entrytime;

	/**
	 * 职位
	 */
	private String position;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getEntrytime() {
		return entrytime;
	}

	public void setEntrytime(Date entrytime) {
		this.entrytime = entrytime;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
