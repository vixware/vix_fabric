package com.vix.pm.problemreport.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.pm.common.entity.Project;

public class ProblemReport extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 指定人
	 */
	private String designees;
	/**
	 * 优先级
	 */
	private String priority;
	/**
	 * 报错日期
	 */
	private Date errorDate;
	/**
	 * 项目
	 */
	private Project project;

	public String getDesignees() {
		return designees;
	}

	public void setDesignees(String designees) {
		this.designees = designees;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Date getErrorDate() {
		return errorDate;
	}

	public void setErrorDate(Date errorDate) {
		this.errorDate = errorDate;
	}

}
