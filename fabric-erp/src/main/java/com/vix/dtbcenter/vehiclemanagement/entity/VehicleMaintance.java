/**
 * 
 */
package com.vix.dtbcenter.vehiclemanagement.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 维修保养
 * 
 * @author zhanghaibing
 * 
 * @date 2013-9-2
 */
public class VehicleMaintance extends BaseEntity {
	private static final long serialVersionUID = 2966878680160451369L;
	/** 维单编号 */
	private String vmCode;
	/** 维修类型 */
	private String vmType;
	/** 维修项目 */
	private String vmItem;
	/** 维保情况说明 */
	private String detail;
	/** 车牌号 */
	private String vehicleNumber;
	/** 车辆类型 */
	private String vehicleType;
	/** 车辆状态 */
	private String vehicleStatus;
	/** 维修厂 */
	private String maintanceFactory;
	/** 维修人 */
	private String maintancer;
	/** 预计花费 */
	private String forcastSpend;
	/** 维保开始时间 */
	private Date vmStartTime;
	/** 维保结束时间 */
	private Date vmEndTime;
	/** 下次保养时间 */
	private Date netxMaintance;
	/** 预通知天数 */
	private String forcastNoticeDay;
	/** 审核人 */
	private String checker;
	/** 审核时间 */
	private Date checkTime;
	/** 办理人 */
	private String transactor;
	/** 当前里程 */
	private String currentMileage;
	/** 下次保养里程 */
	private String nextMaintanceMileage;
	/** 验修人 */
	private String repaireMan;
	/** 车辆 */
	private Vehicle vehicle;

	public String getVmCode() {
		return vmCode;
	}

	public void setVmCode(String vmCode) {
		this.vmCode = vmCode;
	}

	public String getVmType() {
		return vmType;
	}

	public void setVmType(String vmType) {
		this.vmType = vmType;
	}

	public String getVmItem() {
		return vmItem;
	}

	public void setVmItem(String vmItem) {
		this.vmItem = vmItem;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(String vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	public String getMaintanceFactory() {
		return maintanceFactory;
	}

	public void setMaintanceFactory(String maintanceFactory) {
		this.maintanceFactory = maintanceFactory;
	}

	public String getMaintancer() {
		return maintancer;
	}

	public void setMaintancer(String maintancer) {
		this.maintancer = maintancer;
	}

	public String getForcastSpend() {
		return forcastSpend;
	}

	public void setForcastSpend(String forcastSpend) {
		this.forcastSpend = forcastSpend;
	}

	public Date getVmStartTime() {
		return vmStartTime;
	}

	public void setVmStartTime(Date vmStartTime) {
		this.vmStartTime = vmStartTime;
	}

	public Date getVmEndTime() {
		return vmEndTime;
	}

	public void setVmEndTime(Date vmEndTime) {
		this.vmEndTime = vmEndTime;
	}

	public Date getNetxMaintance() {
		return netxMaintance;
	}

	public void setNetxMaintance(Date netxMaintance) {
		this.netxMaintance = netxMaintance;
	}

	public String getForcastNoticeDay() {
		return forcastNoticeDay;
	}

	public void setForcastNoticeDay(String forcastNoticeDay) {
		this.forcastNoticeDay = forcastNoticeDay;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getTransactor() {
		return transactor;
	}

	public void setTransactor(String transactor) {
		this.transactor = transactor;
	}

	public String getCurrentMileage() {
		return currentMileage;
	}

	public void setCurrentMileage(String currentMileage) {
		this.currentMileage = currentMileage;
	}

	public String getNextMaintanceMileage() {
		return nextMaintanceMileage;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public void setNextMaintanceMileage(String nextMaintanceMileage) {
		this.nextMaintanceMileage = nextMaintanceMileage;
	}

	public String getRepaireMan() {
		return repaireMan;
	}

	public void setRepaireMan(String repaireMan) {
		this.repaireMan = repaireMan;
	}

}
