package com.vix.dtbcenter.pickupds.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 提货调度派车
 * 
 * @author zhanghaibing
 * 
 * @date 2013-8-19
 */
public class DeliveryReceipt extends BaseEntity {
	private static final long serialVersionUID = 6009871962378099489L;
	/** 调度单号 */
	private String scheduleCode;
	/** 调度时间 */
	private Date scheduleTime;
	/** 调度类型 */
	private String scheduleType;
	/** 车辆属性 */
	private String vehicleProperty;
	/** 车辆类型 */
	private String vehicleType;
	/** 车牌号 */
	private String vehicleNO;
	/** 车次 */
	private String vehicleNumber;
	/** 驾驶员 */
	private String driver;
	/** 驾驶员电话 */
	private String driverMobile;
	/** 实际发车时间 */
	private Date dispatchTime;
	/** 出发里程数 */
	private Integer mileage;
	/** 载重 */
	private Double carryingCapacity;
	/** 承载体积 */
	private Double carryingCubage;
	/** 计划发车时间 */
	private Date sendInPlan;
	/** 调度员 */
	private String scheduler;
	/** 起运城区 */
	private String source;
	/** 目的城区 */
	private String target;
	/** 承运方 */
	private String carrier;

	public String getScheduleCode() {
		return scheduleCode;
	}

	public void setScheduleCode(String scheduleCode) {
		this.scheduleCode = scheduleCode;
	}

	public Date getScheduleTime() {
		return scheduleTime;
	}

	public void setScheduleTime(Date scheduleTime) {
		this.scheduleTime = scheduleTime;
	}

	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	public String getVehicleProperty() {
		return vehicleProperty;
	}

	public void setVehicleProperty(String vehicleProperty) {
		this.vehicleProperty = vehicleProperty;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehicleNO() {
		return vehicleNO;
	}

	public void setVehicleNO(String vehicleNO) {
		this.vehicleNO = vehicleNO;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDriverMobile() {
		return driverMobile;
	}

	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}

	public Date getDispatchTime() {
		return dispatchTime;
	}

	public void setDispatchTime(Date dispatchTime) {
		this.dispatchTime = dispatchTime;
	}

	public Integer getMileage() {
		return mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	public Double getCarryingCapacity() {
		return carryingCapacity;
	}

	public void setCarryingCapacity(Double carryingCapacity) {
		this.carryingCapacity = carryingCapacity;
	}

	public Double getCarryingCubage() {
		return carryingCubage;
	}

	public void setCarryingCubage(Double carryingCubage) {
		this.carryingCubage = carryingCubage;
	}

	public Date getSendInPlan() {
		return sendInPlan;
	}

	public void setSendInPlan(Date sendInPlan) {
		this.sendInPlan = sendInPlan;
	}

	public String getScheduler() {
		return scheduler;
	}

	public void setScheduler(String scheduler) {
		this.scheduler = scheduler;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

}
