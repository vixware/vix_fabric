package com.vix.hr.trainning.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * @Description: 培训费用
 * @author 李大鹏
 */
public class TrainingExpenses extends BaseEntity {

	private static final long serialVersionUID = -2767404336548332881L;
	/** 培训项目名称 */
	private String projectTraining;
	/** 培训类型 */
	private String trainingType;
	/** 费用类型 */
	private String costTypes;
	/** 费用金额 */
	private String costAccount;
	/** 费用提出人 */
	private String costPeople;
	/** 费用审核人 */
	private String costAudit;
	/** 是否通过审批 */
	private String hroughApproval;
	/** 提出时间 */
	private Date costStartDate;
	/** 审核时间 */
	private Date costCheckDate;
	/** 备注 */
	private String remarks;

	public String getProjectTraining() {
		return projectTraining;
	}

	public void setProjectTraining(String projectTraining) {
		this.projectTraining = projectTraining;
	}

	public String getTrainingType() {
		return trainingType;
	}

	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
	}

	public String getCostTypes() {
		return costTypes;
	}

	public void setCostTypes(String costTypes) {
		this.costTypes = costTypes;
	}

	public String getCostAccount() {
		return costAccount;
	}

	public void setCostAccount(String costAccount) {
		this.costAccount = costAccount;
	}

	public String getCostPeople() {
		return costPeople;
	}

	public void setCostPeople(String costPeople) {
		this.costPeople = costPeople;
	}

	public String getCostAudit() {
		return costAudit;
	}

	public void setCostAudit(String costAudit) {
		this.costAudit = costAudit;
	}

	public String getHroughApproval() {
		return hroughApproval;
	}

	public void setHroughApproval(String hroughApproval) {
		this.hroughApproval = hroughApproval;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCostStartDate() {
		return costStartDate;
	}

	public void setCostStartDate(Date costStartDate) {
		this.costStartDate = costStartDate;
	}

	public Date getCostCheckDate() {
		return costCheckDate;
	}

	public void setCostCheckDate(Date costCheckDate) {
		this.costCheckDate = costCheckDate;
	}

}
