/**
 * 
 */
package com.vix.nvix.oa.attendance.entity;

import java.util.Date;

import com.vix.common.properties.Utils;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.hr.organization.entity.Employee;

/**
 * @author Bluesnow 2016年7月8日
 * 
 *         加班
 */
public class OverTimeWork extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 负责人 */
	private Employee approver;
	/** 申请日期 */
	private Date applicationovertimeDate;
	/** 加班开始时间 */
	private Date overTimeStartTime;
	/** 加班结束时间 */
	private Date overTimeEndTime;
	/** 天 */
	private Double days;
	/** 小时 */
	private Double hours;
	/** 加班内容 */
	private String overTimeContent;

	public Employee getApprover() {
		return approver;
	}

	public void setApprover(Employee approver) {
		this.approver = approver;
	}

	public Date getApplicationovertimeDate() {
		return applicationovertimeDate;
	}

	public String getApplicationovertimeDateStr() {
		if (Utils.isEmpty(applicationovertimeDate)) {
			return DateUtil.format(applicationovertimeDate);
		}
		return "";
	}

	public void setApplicationovertimeDate(Date applicationovertimeDate) {
		this.applicationovertimeDate = applicationovertimeDate;
	}

	public Date getOverTimeStartTime() {
		return overTimeStartTime;
	}

	public String getOverTimeStartTimeStr() {
		if (Utils.isEmpty(overTimeStartTime)) {
			return DateUtil.format(overTimeStartTime);
		}
		return "";
	}

	public void setOverTimeStartTime(Date overTimeStartTime) {
		this.overTimeStartTime = overTimeStartTime;
	}

	public Date getOverTimeEndTime() {
		return overTimeEndTime;
	}

	public String getOverTimeEndTimeStr() {
		if (Utils.isEmpty(overTimeEndTime)) {
			return DateUtil.format(overTimeEndTime);
		}
		return "";
	}

	public void setOverTimeEndTime(Date overTimeEndTime) {
		this.overTimeEndTime = overTimeEndTime;
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

	public String getOverTimeContent() {
		return overTimeContent;
	}

	public void setOverTimeContent(String overTimeContent) {
		this.overTimeContent = overTimeContent;
	}
}
