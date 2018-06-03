package com.vix.common.share.entity;

import java.util.Date;

/**
 * 代办提醒
 * @author Administrator
 *
 */
public class Todo extends BaseEntity{
 
	private static final long serialVersionUID = 1L;

	/** 主题 */
	private String subject;
	/** 执行人 */
	private String executor;
	/** 内容 */
	private String content;
	/** 时间 */
	private Date time;
	/** 优先级 */
	private String priority;
	/** 类型 */
	private String type;
	/** 提醒时间 */
	private Date remindTime;
	/** 流程编码 */
	private String workflowCode;
	/** 流程名称 */
	private String workflowName;
	/** 业务对象类型 */
	private String boType;
	/** 业务对象编码 */
	private String boCode;
	/** 执行地址 */
	private String actionURL;
	
	public Todo(){}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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

	public String getWorkflowCode() {
		return workflowCode;
	}

	public void setWorkflowCode(String workflowCode) {
		this.workflowCode = workflowCode;
	}

	public String getWorkflowName() {
		return workflowName;
	}

	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}

	public String getBoType() {
		return boType;
	}

	public void setBoType(String boType) {
		this.boType = boType;
	}

	public String getBoCode() {
		return boCode;
	}

	public void setBoCode(String boCode) {
		this.boCode = boCode;
	}

	public String getActionURL() {
		return actionURL;
	}

	public void setActionURL(String actionURL) {
		this.actionURL = actionURL;
	}
}
