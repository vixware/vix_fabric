/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import java.util.Date;
import java.util.Set;

import com.vix.common.security.entity.UserAccount;
import com.vix.common.share.entity.BaseEntityWithEndTime;


public class WbsTask extends BaseEntityWithEndTime {
	private String taskCode;		//任务编码
	private String title;		//任务名称
	private Date createTime;		//创建时间
	private UserAccount createUser;		//创建人
	private Date expectBeginTime;		//预计开始时间
	private Date expectFinishTime;		//预计结束时间
	private Date beginTime;		//实际开始时间
	private Date finishTime;		//实际结束时间
	private Integer isOvertime;		//是否超期，0未超期，1超期
	private Integer taskStatus;		//状态，0新建，1执行中,2完成，3取消
	private Double finishPercent;		//完成进度百分比
	private String content;		//任务描述
	private Integer type;		//任务类型，0普通任务，1里程碑，2虚拟任务
	private Integer priority;		//优先级，1重要、2普通
	
	//private Long projectId;		//项目id
	//private Long parentId;		//父级任务id
	private Project project;
	private WbsTask parentTask;
	private Set<WbsTask> subTasks;

	public String getTaskCode() {
		return taskCode;
	}
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public Date getCreateTime() {
		return createTime;
	}
	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getExpectBeginTime() {
		return expectBeginTime;
	}
	public void setExpectBeginTime(Date expectBeginTime) {
		this.expectBeginTime = expectBeginTime;
	}
	public Date getExpectFinishTime() {
		return expectFinishTime;
	}
	public void setExpectFinishTime(Date expectFinishTime) {
		this.expectFinishTime = expectFinishTime;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public Integer getIsOvertime() {
		return isOvertime;
	}
	public void setIsOvertime(Integer isOvertime) {
		this.isOvertime = isOvertime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Set<WbsTask> getSubTasks() {
		return subTasks;
	}
	public void setSubTasks(Set<WbsTask> subTasks) {
		this.subTasks = subTasks;
	}
	public Integer getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}
	public UserAccount getCreateUser() {
		return createUser;
	}
	public void setCreateUser(UserAccount createUser) {
		this.createUser = createUser;
	}
	public WbsTask getParentTask() {
		return parentTask;
	}
	public void setParentTask(WbsTask parentTask) {
		this.parentTask = parentTask;
	}
	public Double getFinishPercent() {
		return finishPercent;
	}
	public void setFinishPercent(Double finishPercent) {
		this.finishPercent = finishPercent;
	}


	
	
}
