package com.vix.hr.attendance.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * @Description: 考勤的月结果数据
 * @author 李大鹏
 */
public class ShouldAttendance extends BaseEntity {

	private static final long serialVersionUID = -8119061404195525967L;
	/** 人员姓名 */
	private String employeeName;
	/** 审核状态 */
	private String auditState;
	/** 年度 */
	private String years;
	/** 考勤开始时间 */
	private Date attendanceStartDate;
	/** 考勤结束时间 */
	private Date attendanceEndDate;
	/** 应出勤(天) */
	private Float attendanceDays;
	/** 实际出勤(天) */
	private Float actualAttendance;
	/** 迟到次数(天) */
	private Float lateDays;
	/** 迟到时间(小时) */
	private Float lagsTimes;
	/** 早退时间(小时) */
	private Float earlyTime;
	/** 旷工时间(小时) */
	private Float workTimes;
	/** 旷工次数 */
	private Float truancy;
	/** 职员 */
	private Employee employee;

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public Date getAttendanceStartDate() {
		return attendanceStartDate;
	}

	public void setAttendanceStartDate(Date attendanceStartDate) {
		this.attendanceStartDate = attendanceStartDate;
	}

	public Date getAttendanceEndDate() {
		return attendanceEndDate;
	}

	public void setAttendanceEndDate(Date attendanceEndDate) {
		this.attendanceEndDate = attendanceEndDate;
	}

	public Float getAttendanceDays() {
		return attendanceDays;
	}

	public void setAttendanceDays(Float attendanceDays) {
		this.attendanceDays = attendanceDays;
	}

	public Float getActualAttendance() {
		return actualAttendance;
	}

	public void setActualAttendance(Float actualAttendance) {
		this.actualAttendance = actualAttendance;
	}

	public Float getLateDays() {
		return lateDays;
	}

	public void setLateDays(Float lateDays) {
		this.lateDays = lateDays;
	}

	public Float getLagsTimes() {
		return lagsTimes;
	}

	public void setLagsTimes(Float lagsTimes) {
		this.lagsTimes = lagsTimes;
	}

	public Float getEarlyTime() {
		return earlyTime;
	}

	public void setEarlyTime(Float earlyTime) {
		this.earlyTime = earlyTime;
	}

	public Float getWorkTimes() {
		return workTimes;
	}

	public void setWorkTimes(Float workTimes) {
		this.workTimes = workTimes;
	}

	public Float getTruancy() {
		return truancy;
	}

	public void setTruancy(Float truancy) {
		this.truancy = truancy;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
