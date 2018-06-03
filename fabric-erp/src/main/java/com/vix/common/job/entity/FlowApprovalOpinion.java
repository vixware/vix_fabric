package com.vix.common.job.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * 审批意见
 * 
 * @author Administrator
 *
 */
public class FlowApprovalOpinion extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 任务id */
	private String taskId;
	/** 审批人 */
	private Employee approvalPerson;
	/** 审批意见 */
	private String opinion;
	/** 来源实体的primarykey */
	private String sourceClassPk;
	public FlowApprovalOpinion() {
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Employee getApprovalPerson() {
		return approvalPerson;
	}

	public void setApprovalPerson(Employee approvalPerson) {
		this.approvalPerson = approvalPerson;
	}
	public String getApprovalPersonName() {
		if (approvalPerson != null) {
			return approvalPerson.getName();
		}
		return "";
	}
	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	/**
	 * @return the sourceClassPk
	 */
	public String getSourceClassPk() {
		return sourceClassPk;
	}

	/**
	 * @param sourceClassPk
	 *            the sourceClassPk to set
	 */
	public void setSourceClassPk(String sourceClassPk) {
		this.sourceClassPk = sourceClassPk;
	}

}
