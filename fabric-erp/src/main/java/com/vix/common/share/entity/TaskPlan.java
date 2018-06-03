package com.vix.common.share.entity;

import java.util.Date;

/**
 * 计划
 */
public class TaskPlan extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -946450580540009473L;
	/**
	 * 执行频率
	 */
	private Long executionFrequency;
	/**
	 * 执行频率单位 时分秒
	 */
	private String executionFrequencyUtil;
	/**
	 * 下次执行时间
	 */
	private Date nextTime;
	/**
	 * 上次执行时间
	 */
	private Date lastTime;
	/**
	 * 定时任务
	 */
	private AlertTask timedTask;

	public Long getExecutionFrequency() {
		return executionFrequency;
	}

	public void setExecutionFrequency(Long executionFrequency) {
		this.executionFrequency = executionFrequency;
	}

	public String getExecutionFrequencyUtil() {
		return executionFrequencyUtil;
	}

	public void setExecutionFrequencyUtil(String executionFrequencyUtil) {
		this.executionFrequencyUtil = executionFrequencyUtil;
	}

	public Date getNextTime() {
		return nextTime;
	}

	public void setNextTime(Date nextTime) {
		this.nextTime = nextTime;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public AlertTask getTimedTask() {
		return timedTask;
	}

	public void setTimedTask(AlertTask timedTask) {
		this.timedTask = timedTask;
	}

}
