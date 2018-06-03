package com.vix.hr.attendance.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * @Description: 考勤记录
 * @author ivan
 * @date 2013-09-02
 */
public class AttendanceRecord extends BaseEntity {

	private static final long serialVersionUID = -5541838917294518788L;
	/** 所属公司 */
	private String company;
	/** 职位 */
	private String position;
	/** 到岗时间(最早时间) */
	private Date startDate;
	/** 离岗时间(最晚时间) */
	private Date endDate;
	/** 班次(早班,晚班) */
	private String morningShift;
	/** 迟到时间(小时) */
	private Float lagTime;
	/** 早退时间(小时) */
	private Float earlyTime;
	/** 出勤天数 */
	private Float attendanceDays;
	/** 请假缺勤天数(考勤记录中间离岗时间) */
	private Float absenceOfTime;
	/** 旷工天数(非请假) */
	private Float workTime;
	/** 职员 */
	private Employee employee;

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getMorningShift() {
		return morningShift;
	}

	public void setMorningShift(String morningShift) {
		this.morningShift = morningShift;
	}

	public Float getLagTime() {
		return lagTime;
	}

	public void setLagTime(Float lagTime) {
		this.lagTime = lagTime;
	}

	public Float getEarlyTime() {
		return earlyTime;
	}

	public void setEarlyTime(Float earlyTime) {
		this.earlyTime = earlyTime;
	}

	public Float getAttendanceDays() {
		return attendanceDays;
	}

	public void setAttendanceDays(Float attendanceDays) {
		this.attendanceDays = attendanceDays;
	}

	public Float getAbsenceOfTime() {
		return absenceOfTime;
	}

	public void setAbsenceOfTime(Float absenceOfTime) {
		this.absenceOfTime = absenceOfTime;
	}

	public Float getWorkTime() {
		return workTime;
	}

	public void setWorkTime(Float workTime) {
		this.workTime = workTime;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
