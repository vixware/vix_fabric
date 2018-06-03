package com.vix.nvix.oa.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 定时任务实现，系统内置
 * 
 * @类全名 com.vix.nvix.oa.entity.QuartzTask
 *
 * @author zhanghaibing
 *
 * @date 2016年8月12日
 */
public class QuartzTask extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 是否启用 0：否 1： 是 */
	private String enable;
	/** 定时任务实现类 */
	private String taskClass;
	/** 顺序 */
	private Long orderBy;
	/** 备注 */
	private String memo;
	/** 任务计划 */
	private Set<ScheduleJob> scheduleJobs = new HashSet<ScheduleJob>();

	public QuartzTask() {
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getTaskClass() {
		return taskClass;
	}

	public void setTaskClass(String taskClass) {
		this.taskClass = taskClass;
	}

	@Override
	public Long getOrderBy() {
		return orderBy;
	}

	@Override
	public void setOrderBy(Long orderBy) {
		this.orderBy = orderBy;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Set<ScheduleJob> getScheduleJobs() {
		return scheduleJobs;
	}

	public void setScheduleJobs(Set<ScheduleJob> scheduleJobs) {
		this.scheduleJobs = scheduleJobs;
	}
}