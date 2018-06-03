package com.vix.hr.trainning.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.hr.job.entity.HrAttachments;

/**
 * @Description: 培训计划
 * @author 李大鹏
 */
public class TrainingPlanning extends BaseEntity {

	private static final long serialVersionUID = 131934666340770915L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 计划主题 */
	private String applicationName;
	/** 计划提出部门与负责人 */
	private String orgUnitAndLeadingOfficial;
    /** 部门名称*/
	private String org;
	/** 发布对象的id集合 */
	private String pubIds;
	/** 计划状态 */
	private String planStatus;
	/** 计划类型 */
	private String planType;
	/**
	 * 提出计划部门与人员类型 "O\":\"部门\",\"E\":\"人员\"
	 */
	private String pubTypes;
	/** 计划级别 */
	private String planLevel;
	/** 计划总费用 */
	private String planningATotalCost;
	/** 培训计划性质 */
	private String planningNature;
	/** 计划人 */
	private String courseDescription;
	/** 计划提出时间 */
	private Date proposedTime;
	/** 备注 */
	private String remarks;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;

	/** 培训实施 */
	private TrainningImplement trainningImplement;
	/** 附件 */
	private Set<HrAttachments> attachments = new HashSet<HrAttachments>();
	/** 培训计划明细 */
	private Set<TrainingPlanningDetail> trainingPlanningDetails = new HashSet<TrainingPlanningDetail>();
	/** 部门 */
	private OrganizationUnit organizationUnit;

	public Set<HrAttachments> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<HrAttachments> attachments) {
		this.attachments = attachments;
	}

	public Set<TrainingPlanningDetail> getTrainingPlanningDetails() {
		return trainingPlanningDetails;
	}

	public void setTrainingPlanningDetails(Set<TrainingPlanningDetail> trainingPlanningDetails) {
		this.trainingPlanningDetails = trainingPlanningDetails;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public String getPlanLevel() {
		return planLevel;
	}

	public void setPlanLevel(String planLevel) {
		this.planLevel = planLevel;
	}

	public String getOrgUnitAndLeadingOfficial() {
		return orgUnitAndLeadingOfficial;
	}

	public void setOrgUnitAndLeadingOfficial(String orgUnitAndLeadingOfficial) {
		this.orgUnitAndLeadingOfficial = orgUnitAndLeadingOfficial;
	}

	public String getPubIds() {
		return pubIds;
	}

	public void setPubIds(String pubIds) {
		this.pubIds = pubIds;
	}

	public String getPubTypes() {
		return pubTypes;
	}

	public void setPubTypes(String pubTypes) {
		this.pubTypes = pubTypes;
	}

	public String getPlanningATotalCost() {
		return planningATotalCost;
	}

	public void setPlanningATotalCost(String planningATotalCost) {
		this.planningATotalCost = planningATotalCost;
	}

	public String getPlanningNature() {
		return planningNature;
	}

	public void setPlanningNature(String planningNature) {
		this.planningNature = planningNature;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public Date getProposedTime() {
		return proposedTime;
	}
    
	public String getProposedTimeStr() {
		if (null != proposedTime) {
			return DateUtil.format(proposedTime);
		}
		return "";
	}
	
	public void setProposedTime(Date proposedTime) {
		this.proposedTime = proposedTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public OrganizationUnit getOrganizationUnit() {
		return organizationUnit;
	}

	public void setOrganizationUnit(OrganizationUnit organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	public TrainningImplement getTrainningImplement() {
		return trainningImplement;
	}

	public void setTrainningImplement(TrainningImplement trainningImplement) {
		this.trainningImplement = trainningImplement;
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

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}
    
}
