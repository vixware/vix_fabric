package com.vix.nvix.oa.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 客户任务
 * 
 * @类全名 com.vix.nvix.oa.entity.ScheduleJob
 *
 * @author zhanghaibing
 *
 * @date 2016年8月12日
 */
public class ScheduleJob extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public static final String SJSTATUS_NONE = "0";
	/** Trigger已经完成，且不会在执行，或者找不到该触发器，或者Trigger已经被删除 */
	public static final String SJSTATUS_NORMAL = "1";
	/** 正常 */
	public static final String SJSTATUS_PAUSED = "2";
	/** 暂停 */
	public static final String SJSTATUS_COMPLETE = "3";
	/** 触发器完成，但是任务可能还正在执行中 */
	public static final String SJSTATUS_BLOCKED = "4";
	/** 线程阻塞状态 */
	public static final String SJSTATUS_ERROR = "5";
	/** 出现错误 */

	/** 任务实现 */
	private QuartzTask quartzTask;
	/** 编码 */
	private String code;
	/** 任务名称 */
	private String jobName;
	/** 任务别名 */
	private String aliasName;
	/** 任务分组 */
	private String jobGroup;
	/** 触发器 */
	private String jobTrigger;
	/** 任务状态 */
	private String status;
	/** 任务运行时间表达式 */
	private String cronExpression;
	/** 任务描述 */
	private String description;
	/** 顺序 */
	private Long orderBy;

	public ScheduleJob() {
	}

	public QuartzTask getQuartzTask() {
		return quartzTask;
	}

	public void setQuartzTask(QuartzTask quartzTask) {
		this.quartzTask = quartzTask;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getSingleJobName() {
		return this.jobName + "_" + this.getCompanyCode();
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getSingleJobGroup() {
		return this.jobGroup + "_" + this.getCompanyCode();
	}

	public String getJobTrigger() {
		return jobTrigger;
	}

	public void setJobTrigger(String jobTrigger) {
		this.jobTrigger = jobTrigger;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public Long getOrderBy() {
		return orderBy;
	}

	@Override
	public void setOrderBy(Long orderBy) {
		this.orderBy = orderBy;
	}
}