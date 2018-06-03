package com.vix.dtbcenter.dispatchingreceipt.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 配送回执单
 * 
 * @author zhanghaibing
 * 
 * @date 2013-8-19
 */
public class DispatchingReceipt extends BaseEntity {
	private static final long serialVersionUID = 7442010832601332702L;
	/** 填写日期 */
	private Date fillTime;
	/** 用车人员 */
	private String person;
	/** 用车事由 */
	private String reason;
	/** 计划用车情况 */
	private String usageOfPlan;
	/** 部门经理 */
	private String deparmentManager;
	/** 行政部门经理 */
	private String officeManager;
	/** 派车 */
	private String send;
	/** 实际出车情况 */
	private String actualUsageInfo;
	/** 车辆 */
	private String vehicle;
	/** 司机 */
	private String driver;

	public Date getFillTime() {
		return fillTime;
	}

	public void setFillTime(Date fillTime) {
		this.fillTime = fillTime;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getUsageOfPlan() {
		return usageOfPlan;
	}

	public void setUsageOfPlan(String usageOfPlan) {
		this.usageOfPlan = usageOfPlan;
	}

	public String getDeparmentManager() {
		return deparmentManager;
	}

	public void setDeparmentManager(String deparmentManager) {
		this.deparmentManager = deparmentManager;
	}

	public String getOfficeManager() {
		return officeManager;
	}

	public void setOfficeManager(String officeManager) {
		this.officeManager = officeManager;
	}

	public String getSend() {
		return send;
	}

	public void setSend(String send) {
		this.send = send;
	}

	public String getActualUsageInfo() {
		return actualUsageInfo;
	}

	public void setActualUsageInfo(String actualUsageInfo) {
		this.actualUsageInfo = actualUsageInfo;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

}
