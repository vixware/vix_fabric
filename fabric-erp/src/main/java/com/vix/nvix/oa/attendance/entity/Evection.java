/**
 * 
 */
package com.vix.nvix.oa.attendance.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * @author Bluesnow 2016年7月8日
 * 
 *         出差
 */
public class Evection extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 出差人 */
	private Employee approver;
	/** 出差地点 */
	private String businessLocation;
	/** 外出开始时间 */
	private Date businessStartDate;
	/** 外出结束时间 */
	private Date businessEndDate;
	/** 天 */
	private Double days;
	/** 小时 */
	private Double hours;
	/** 事由 */
	private String reason;
	/** 是否用车 */
	private Integer isUsedCar;

	public Employee getApprover() {
		return approver;
	}

	public void setApprover(Employee approver) {
		this.approver = approver;
	}

	public String getBusinessLocation() {
		return businessLocation;
	}

	public void setBusinessLocation(String businessLocation) {
		this.businessLocation = businessLocation;
	}

	public Date getBusinessStartDate() {
		return businessStartDate;
	}

	public void setBusinessStartDate(Date businessStartDate) {
		this.businessStartDate = businessStartDate;
	}

	public Date getBusinessEndDate() {
		return businessEndDate;
	}

	public void setBusinessEndDate(Date businessEndDate) {
		this.businessEndDate = businessEndDate;
	}

	public Double getDays() {
		return days;
	}

	public void setDays(Double days) {
		this.days = days;
	}

	public Double getHours() {
		return hours;
	}

	public void setHours(Double hours) {
		this.hours = hours;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getIsUsedCar() {
		return isUsedCar;
	}

	public void setIsUsedCar(Integer isUsedCar) {
		this.isUsedCar = isUsedCar;
	}

}
