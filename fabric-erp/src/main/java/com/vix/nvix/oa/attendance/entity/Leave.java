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
 *         请假
 */
public class Leave extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 请假人 */
	private Employee approve;
	/** 请假原因 */
	private String leaveReason;
	/** 请假开始时间 */
	private Date leaveStartTime;
	/** 请假结束时间 */
	private Date leaveEndTime;
	/** 天 */
	private Double days;
	/** 小时 */
	private Double hours;
	/** 请假类型 */
	private Integer leaveType;
	/** 使用年假休 */
	private Integer isUsedAnnualLeave;

	public Employee getApprove() {
		return approve;
	}

	public void setApprove(Employee approve) {
		this.approve = approve;
	}

	public String getLeaveReason() {
		return leaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	public Date getLeaveStartTime() {
		return leaveStartTime;
	}

	public void setLeaveStartTime(Date leaveStartTime) {
		this.leaveStartTime = leaveStartTime;
	}

	public Date getLeaveEndTime() {
		return leaveEndTime;
	}

	public void setLeaveEndTime(Date leaveEndTime) {
		this.leaveEndTime = leaveEndTime;
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

	public Integer getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(Integer leaveType) {
		this.leaveType = leaveType;
	}

	public Integer getIsUsedAnnualLeave() {
		return isUsedAnnualLeave;
	}

	public void setIsUsedAnnualLeave(Integer isUsedAnnualLeave) {
		this.isUsedAnnualLeave = isUsedAnnualLeave;
	}

}
