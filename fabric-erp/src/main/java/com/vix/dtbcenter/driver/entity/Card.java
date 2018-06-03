/**
 * 
 */
package com.vix.dtbcenter.driver.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 证件
 * 
 * @author zhanghaibing
 * 
 * @date 2013-9-2
 */
public class Card extends BaseEntity {
	private static final long serialVersionUID = 6955663389884846169L;
	/** 档案编号 */
	private String docCode;
	/** 档案类型 */
	private String docType;
	/** 证件所有人 */
	private String belongs;
	/** 存放地点 */
	private String location;
	/** 登记时间 */
	private Date registerTime;
	/** 有效期至 */
	private Date validUntil;
	/**
	 * 司机
	 */
	private Driver driver;

	public String getDocCode() {
		return docCode;
	}

	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getBelongs() {
		return belongs;
	}

	public void setBelongs(String belongs) {
		this.belongs = belongs;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Date getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

}
