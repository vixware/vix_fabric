package com.vix.pm.milestonereport.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.pm.common.entity.Project;

public class MilestoneReport extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 成员
	 */
	private String projectMembers;
	/**
	 * 优先级
	 */
	private String priority;
	/**
	 * 持续时间
	 */
	private Integer durationDate;
	/**
	 * 项目
	 */
	private Project project;

	public String getProjectMembers() {
		return projectMembers;
	}

	public void setProjectMembers(String projectMembers) {
		this.projectMembers = projectMembers;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Integer getDurationDate() {
		return durationDate;
	}

	public void setDurationDate(Integer durationDate) {
		this.durationDate = durationDate;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
