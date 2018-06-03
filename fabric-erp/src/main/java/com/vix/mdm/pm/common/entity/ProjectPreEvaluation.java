/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 项目预评估
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class ProjectPreEvaluation extends BaseEntity {
	/** 项目编号 */
	private String projectCode;
	/** 策划编号 */
	private String peCode;
	/** 审批状态 */
	private String approvalStatus;
	/** 意见 */
	private String opinion;
	/** 结论 */
	private String conclusion;

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getPeCode() {
		return peCode;
	}

	public void setPeCode(String peCode) {
		this.peCode = peCode;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

}
