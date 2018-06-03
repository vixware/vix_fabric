package com.vix.dtbcenter.vehiclemanagement.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 车辆跟踪
 * 
 * @author zhanghaibing
 * 
 * @date 2013-9-2
 */
public class ViechleTrace extends BaseEntity {
	private static final long serialVersionUID = 7026155592916249837L;
	/** 车辆编码 */
	private String viechleNumber;
	/** 经度 */
	private String longitude;
	/** 纬度 */
	private String latitude;
	/** 时间点 */
	private Date timePoint;
	/** 调度单编码 */
	private String dispatchCode;
	/** 车辆 */
	private Vehicle vehicle;

	public String getViechleNumber() {
		return viechleNumber;
	}

	public void setViechleNumber(String viechleNumber) {
		this.viechleNumber = viechleNumber;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Date getTimePoint() {
		return timePoint;
	}

	public void setTimePoint(Date timePoint) {
		this.timePoint = timePoint;
	}

	public String getDispatchCode() {
		return dispatchCode;
	}

	public void setDispatchCode(String dispatchCode) {
		this.dispatchCode = dispatchCode;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}
