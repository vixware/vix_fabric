package com.vix.common.share.entity;

import java.util.Date;

import com.vix.hr.organization.entity.Employee;
import com.vix.system.warningSource.entity.WarningType;

/**
 * 任务信息
 */
public class AlertTask extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 主题 */
	private String subject;
	/** 内容 */
	private String content;
	/** 优先级 */
	private String priority;
	/**
	 * 类型 执行一次O 重复执行 R
	 */
	private String type;
	/** 提醒时间 */
	private Date remindTime;
	/**
	 * 员工 提醒人
	 */
	private Employee employee;
	/**
	 * 任务源
	 */
	private WarningType warningType;

	public WarningType getWarningType() {
		return warningType;
	}

	public void setWarningType(WarningType warningType) {
		this.warningType = warningType;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getRemindTime() {
		return remindTime;
	}

	public void setRemindTime(Date remindTime) {
		this.remindTime = remindTime;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
