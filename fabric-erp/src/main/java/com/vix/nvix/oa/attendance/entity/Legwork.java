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
 *         外勤（外出）
 */
public class Legwork extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 负责人 */
	private Employee approver;
	/** 外出原因 */
	private String outReason;
	/** 申请时间 */
	private Date applicationTime;
	/** 外出开始时间 */
	private Date outStartDate;
	/** 外出结束时间 */
	private Date outEndDate;
	/** 天 */
	private Double days;
	/** 小时 */
	private Double hours;
	/** 是否用车 */
	private Integer isUsedCar;

	public Employee getApprover() {
		return approver;
	}

	public void setApprover(Employee approver) {
		this.approver = approver;
	}

	public String getOutReason() {
		return outReason;
	}

	public void setOutReason(String outReason) {
		this.outReason = outReason;
	}

	public Date getApplicationTime() {
		return applicationTime;
	}

	public void setApplicationTime(Date applicationTime) {
		this.applicationTime = applicationTime;
	}

	public Date getOutStartDate() {
		return outStartDate;
	}

	public void setOutStartDate(Date outStartDate) {
		this.outStartDate = outStartDate;
	}

	public Date getOutEndDate() {
		return outEndDate;
	}

	public void setOutEndDate(Date outEndDate) {
		this.outEndDate = outEndDate;
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

	public Integer getIsUsedCar() {
		return isUsedCar;
	}

	public void setIsUsedCar(Integer isUsedCar) {
		this.isUsedCar = isUsedCar;
	}

}
