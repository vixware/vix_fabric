package com.vix.common.job.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * @ClassName: JobTodo
 * @Description: 代办任务
 * @author wangmingchen
 * @date 2012-7-20 上午9:33:07
 * 
 */
public class JobTodo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 778065135247637193L;
	/** 待办任务名称 */
	public String jobName;
	/** 待办任务内容 */
	private String jobContent;

	/** 流程名称 */
	private String flowName;
	/** 流程id */
	private String flowInstanceId;

	/** 开始和结束时间参见父类 */

	/** 完成情况 */
	private String resultState;
	/** 完成标准 */
	private String finishStandard;
	/* 优先级 */
	private String priority;

	/** 来源实体 */
	private String sourceClass;
	/** 来源实体的primarykey */
	private String sourceClassPk;
	/** 来源实体类型 */
	private String sourceType;

	/** 发布者 */
	// private User sender;

	/**
	 * 任务的分配者 基于中间表来设定 双向多对多
	 */
	// private Set<User> jobExecutor;

	/**
	 * 任务状态 0 新建未生效 1 发布 生效 2 任务完成 3 任务终止 4 任务过期
	 */
	private String status;
	/**
	 * 
	 */
	private String url;

	/**
	 * 跟人员进行关联
	 * 
	 * add by zhanghaibing 2015/6/2
	 */
	private Employee employee;
	/**
	 * 是否查看 N :新数据,O:已查看数据
	 */
	public String isNew;

	public String getJobName() {
		return jobName;
	}

	public JobTodo() {
		super();
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobContent() {
		return jobContent;
	}

	public void setJobContent(String jobContent) {
		this.jobContent = jobContent;
	}

	/**
	 * @return the isNew
	 */
	public String getIsNew() {
		return isNew;
	}

	/**
	 * @param isNew
	 *            the isNew to set
	 */
	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getFlowInstanceId() {
		return flowInstanceId;
	}

	public void setFlowInstanceId(String flowInstanceId) {
		this.flowInstanceId = flowInstanceId;
	}

	public String getResultState() {
		return resultState;
	}

	public void setResultState(String resultState) {
		this.resultState = resultState;
	}

	public String getFinishStandard() {
		return finishStandard;
	}

	public void setFinishStandard(String finishStandard) {
		this.finishStandard = finishStandard;
	}

	public String getSourceClass() {
		return sourceClass;
	}

	public void setSourceClass(String sourceClass) {
		this.sourceClass = sourceClass;
	}

	public String getSourceClassPk() {
		return sourceClassPk;
	}

	public void setSourceClassPk(String sourceClassPk) {
		this.sourceClassPk = sourceClassPk;
	}

	/*
	 * public User getSender() { return sender; }
	 * 
	 * public void setSender(User sender) { this.sender = sender; }
	 * 
	 * public Set<User> getJobExecutor() { return jobExecutor; }
	 * 
	 * public void setJobExecutor(Set<User> jobExecutor) { this.jobExecutor =
	 * jobExecutor; }
	 */
	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

}
