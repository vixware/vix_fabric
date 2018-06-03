package com.vix.common.share.entity;

import java.util.Date;

import com.vix.core.utils.DateUtil;
import com.vix.system.warningSource.entity.WarningType;

/**
 * 提醒
 * 
 * @类全名 com.vix.common.share.entity.AlertNotice
 *
 * @author zhanghaibing
 *
 * @date 2017年3月3日
 */
public class AlertNotice extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 主题 */
	private String subject;
	/** 发布人 */
	private String publisher;
	/** 内容 */
	private String content;
	/** 时间 */
	private Date time;
	/** 优先级 */
	private String priority;
	/** 类型 */
	private String type;// 库存提醒 inventory 审批提醒 approval 合同有效期提醒 contract
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
	/**
	 * 任务源
	 */
	private WarningType warningType;
	/** 开始的日期 天 */
	private Date startDay;
	/** 结束的日期 天 */
	private Date endDay;
	/**
	 * 整天
	 */
	private Boolean allDay;
	/**
	 * 图标
	 */
	private String icon;

	public AlertNotice() {
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
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
	public String getTimeStr() {
		if (time != null) {
			return DateUtil.format(time);
		}
		return "";
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
	public String getRemindTimeStr() {
		if (remindTime != null) {
			return DateUtil.format(remindTime);
		}
		return "";
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

	public WarningType getWarningType() {
		return warningType;
	}

	public void setWarningType(WarningType warningType) {
		this.warningType = warningType;
	}

	/**
	 * @return the startDay
	 */
	public Date getStartDay() {
		return startDay;
	}

	/**
	 * @param startDay
	 *            the startDay to set
	 */
	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	/**
	 * @return the endDay
	 */
	public Date getEndDay() {
		return endDay;
	}

	/**
	 * @param endDay
	 *            the endDay to set
	 */
	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}

	/**
	 * @return the allDay
	 */
	public Boolean getAllDay() {
		return allDay;
	}

	/**
	 * @param allDay
	 *            the allDay to set
	 */
	public void setAllDay(Boolean allDay) {
		this.allDay = allDay;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon
	 *            the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

}
