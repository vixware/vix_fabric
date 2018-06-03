/**
 * 
 */
package com.vix.dtbcenter.vehiclemanagement.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 运输事故
 * 
 * @author zhanghaibing
 * 
 * @date 2013-9-2
 */
public class TransportationAccident extends BaseEntity {
	private static final long serialVersionUID = -4048550499238613610L;
	/** 事故编号 */
	private String accidentCode;
	/** 车牌号 */
	private String vechileNumber;
	/** 调度单编号 */
	private String dispatchFormCode;
	/** 驾驶员 */
	private String drvier;
	/** 事故类型 */
	private String accidentType;
	/** 事故等级 */
	private String accidentLevel;
	/** 事故责任 */
	private String accidentDuty;
	/** 事故时间 */
	private Date accidentTime;
	/** 事故地点 */
	private String accidentPoint;
	/** 车辆载重状态 */
	private String vechileLoadStatus;
	/** 事故描述 */
	private String accidentDescription;
	/** 事故管理员 */
	private String accidentManger;
	/** 车辆 */
	private Vehicle vehicle;

	public String getAccidentCode() {
		return accidentCode;
	}

	public void setAccidentCode(String accidentCode) {
		this.accidentCode = accidentCode;
	}

	public String getVechileNumber() {
		return vechileNumber;
	}

	public void setVechileNumber(String vechileNumber) {
		this.vechileNumber = vechileNumber;
	}

	public String getDispatchFormCode() {
		return dispatchFormCode;
	}

	public void setDispatchFormCode(String dispatchFormCode) {
		this.dispatchFormCode = dispatchFormCode;
	}

	public String getDrvier() {
		return drvier;
	}

	public void setDrvier(String drvier) {
		this.drvier = drvier;
	}

	public String getAccidentType() {
		return accidentType;
	}

	public void setAccidentType(String accidentType) {
		this.accidentType = accidentType;
	}

	public String getAccidentLevel() {
		return accidentLevel;
	}

	public void setAccidentLevel(String accidentLevel) {
		this.accidentLevel = accidentLevel;
	}

	public String getAccidentDuty() {
		return accidentDuty;
	}

	public void setAccidentDuty(String accidentDuty) {
		this.accidentDuty = accidentDuty;
	}

	public Date getAccidentTime() {
		return accidentTime;
	}

	public void setAccidentTime(Date accidentTime) {
		this.accidentTime = accidentTime;
	}

	public String getAccidentPoint() {
		return accidentPoint;
	}

	public void setAccidentPoint(String accidentPoint) {
		this.accidentPoint = accidentPoint;
	}

	public String getVechileLoadStatus() {
		return vechileLoadStatus;
	}

	public void setVechileLoadStatus(String vechileLoadStatus) {
		this.vechileLoadStatus = vechileLoadStatus;
	}

	public String getAccidentDescription() {
		return accidentDescription;
	}

	public void setAccidentDescription(String accidentDescription) {
		this.accidentDescription = accidentDescription;
	}

	public String getAccidentManger() {
		return accidentManger;
	}

	public void setAccidentManger(String accidentManger) {
		this.accidentManger = accidentManger;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
}
