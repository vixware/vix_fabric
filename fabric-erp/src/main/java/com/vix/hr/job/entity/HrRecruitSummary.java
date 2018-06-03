package com.vix.hr.job.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * @Description: 招聘总结
 * @author 李大鹏
 * @date 2013-08-21
 */
public class HrRecruitSummary extends BaseEntity {

	private static final long serialVersionUID = 6484837105976663486L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/** 招聘计划名称 */
	private String recruitmentPlanName;
	/** 招聘活动名称 */
	private String recruitmentActivityName;
	/** 已招聘职位 */
	private String hasTheJob;
	/** 成功率 */
	private String successRateOfRecruitment;
	/** 计划招聘人数 */
	private String recruitmentPlanning;
	/** 实际招聘人数 */
	private String actualBigintOfRecruitment;
	/** 实际费用 */
	private String expensePocket;
	/** 实际开始时间 */
	private Date actualStartTime;
	/** 实际结束时间 */
	private Date actualEndTime;
	/** 审批状态 */
	private String approvalStatus;
	/** 招聘负责部门 */
	private String responsibleForRecruitmentDepartment;
	/** 招聘负责人 */
	private String recruiter;
	/** 招聘结果评价 */
	private String recruitmentResultEvaluation;
	/** 备注 */
	private String remark;
	/** 招聘结果评价 */
	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getRecruitmentPlanName() {
		return recruitmentPlanName;
	}

	public void setRecruitmentPlanName(String recruitmentPlanName) {
		this.recruitmentPlanName = recruitmentPlanName;
	}

	public String getHasTheJob() {
		return hasTheJob;
	}

	public void setHasTheJob(String hasTheJob) {
		this.hasTheJob = hasTheJob;
	}

	public String getRecruitmentPlanning() {
		return recruitmentPlanning;
	}

	public void setRecruitmentPlanning(String recruitmentPlanning) {
		this.recruitmentPlanning = recruitmentPlanning;
	}

	public String getActualBigintOfRecruitment() {
		return actualBigintOfRecruitment;
	}

	public void setActualBigintOfRecruitment(String actualBigintOfRecruitment) {
		this.actualBigintOfRecruitment = actualBigintOfRecruitment;
	}

	public String getExpensePocket() {
		return expensePocket;
	}

	public void setExpensePocket(String expensePocket) {
		this.expensePocket = expensePocket;
	}

	public Date getActualStartTime() {
		return actualStartTime;
	}
    
	public String getActualStartTimeStr() {
		if (null != actualStartTime) {
			return DateUtil.format(actualStartTime);
		}
		return "";
	}
	
	public void setActualStartTime(Date actualStartTime) {
		this.actualStartTime = actualStartTime;
	}

	public Date getActualEndTime() {
		return actualEndTime;
	}
    
	public String getActualEndTimeStr() {
		if (null != actualEndTime) {
			return DateUtil.format(actualEndTime);
		}
		return "";
	}
	
	public void setActualEndTime(Date actualEndTime) {
		this.actualEndTime = actualEndTime;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getResponsibleForRecruitmentDepartment() {
		return responsibleForRecruitmentDepartment;
	}

	public void setResponsibleForRecruitmentDepartment(String responsibleForRecruitmentDepartment) {
		this.responsibleForRecruitmentDepartment = responsibleForRecruitmentDepartment;
	}

	public String getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(String recruiter) {
		this.recruiter = recruiter;
	}

	public String getRecruitmentResultEvaluation() {
		return recruitmentResultEvaluation;
	}

	public void setRecruitmentResultEvaluation(String recruitmentResultEvaluation) {
		this.recruitmentResultEvaluation = recruitmentResultEvaluation;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRecruitmentActivityName() {
		return recruitmentActivityName;
	}

	public void setRecruitmentActivityName(String recruitmentActivityName) {
		this.recruitmentActivityName = recruitmentActivityName;
	}

	public String getSuccessRateOfRecruitment() {
		return successRateOfRecruitment;
	}

	public void setSuccessRateOfRecruitment(String successRateOfRecruitment) {
		this.successRateOfRecruitment = successRateOfRecruitment;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public String getUploadPersonId() {
		return uploadPersonId;
	}

	public void setUploadPersonId(String uploadPersonId) {
		this.uploadPersonId = uploadPersonId;
	}

	public String getUploadPerson() {
		return uploadPerson;
	}

	public void setUploadPerson(String uploadPerson) {
		this.uploadPerson = uploadPerson;
	}

	public String getUploadPersonName() {
		return uploadPersonName;
	}

	public void setUploadPersonName(String uploadPersonName) {
		this.uploadPersonName = uploadPersonName;
	}
}
