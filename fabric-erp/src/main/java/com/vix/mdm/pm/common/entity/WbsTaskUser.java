package com.vix.mdm.pm.common.entity;

import com.vix.common.org.entity.BusinessOrg;
import com.vix.common.share.entity.BaseEntityWithEndTime;

public class WbsTaskUser extends BaseEntityWithEndTime {
	
	private Project project;	//项目
	private WbsTask task;		//任务
	
	private String taskRole;		//任务角色
	private BusinessOrg businessOrg;		//任务业务组织
	private String content;		//任务内容
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public WbsTask getTask() {
		return task;
	}
	public void setTask(WbsTask task) {
		this.task = task;
	}
	public String getTaskRole() {
		return taskRole;
	}
	public void setTaskRole(String taskRole) {
		this.taskRole = taskRole;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public BusinessOrg getBusinessOrg() {
		return businessOrg;
	}
	public void setBusinessOrg(BusinessOrg businessOrg) {
		this.businessOrg = businessOrg;
	}
	
	
}
