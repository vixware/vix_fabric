package com.vix.hr.job.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * @Description: 招聘计划
 * @author 李大鹏
 * @date 2013-08-07
 */
public class HrRecruitplan extends BaseEntity {

	private static final long serialVersionUID = -6938376131496792843L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/** 计划名称 */
	private String programName;
	/** 所属组织单元 */
	private String org;
	/** 覆盖地区 */
	private String coverageArea;
	/** 费用预算 */
	private String expenseBudget;
	/** 负责组织单元 */
	private String responsibleForOrgUnit;
	/** 计划状态 */
	private String planStatus;
	/** 负责人 */
	private String leadingOfficial;
	/** 备注 */
	private String remarks;
	/** 职位名称 */
	private String positionName;
	/** 职位说明 */
	private String jobDescription;
	/** 生效时间 */
	private Date effectTime;
	/** 扩大(减少)在编人数 */
	private Integer increaseSize;
	/** 扩大(减少)非在编人数 */
	private Integer increaseUnofficialNumber;
	/** 规划性质 */
	private String planningNature;
	/** 计划内容 */
	private String projectContent;
	/** 计划人 */
	private String schemer;
	/** 提出计划时间 */
	private Date proposedTime;

	/** 招聘计划汇总 */
	private HrRecruitmentPlansummary hrRecruitmentPlansummary;

	/** 附件 */
	private Set<HrAttachments> attachments = new HashSet<HrAttachments>();

	/** 招聘计划明细 */
	private Set<HrRecruitmentPlanDetails> hrRecruitmentPlanDetails = new HashSet<HrRecruitmentPlanDetails>();

	// Constructors

	/** default constructor */
	public HrRecruitplan() {
	}

	// Property accessors

	public String getProgramName() {
		return this.programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getOrg() {
		return this.org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getCoverageArea() {
		return this.coverageArea;
	}

	public void setCoverageArea(String coverageArea) {
		this.coverageArea = coverageArea;
	}

	public String getExpenseBudget() {
		return this.expenseBudget;
	}

	public void setExpenseBudget(String expenseBudget) {
		this.expenseBudget = expenseBudget;
	}

	public String getResponsibleForOrgUnit() {
		return this.responsibleForOrgUnit;
	}

	public void setResponsibleForOrgUnit(String responsibleForOrgUnit) {
		this.responsibleForOrgUnit = responsibleForOrgUnit;
	}

	public String getPlanStatus() {
		return this.planStatus;
	}

	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

	public String getLeadingOfficial() {
		return this.leadingOfficial;
	}

	public void setLeadingOfficial(String leadingOfficial) {
		this.leadingOfficial = leadingOfficial;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public Date getEffectTime() {
		return effectTime;
	}
    
	public String getEffectTimeStr() {
		if (null != effectTime) {
			return DateUtil.format(effectTime);
		}
		return "";
	}
	
	public void setEffectTime(Date effectTime) {
		this.effectTime = effectTime;
	}

	public Integer getIncreaseSize() {
		return increaseSize;
	}

	public void setIncreaseSize(Integer increaseSize) {
		this.increaseSize = increaseSize;
	}

	public String getPlanningNature() {
		return planningNature;
	}

	public void setPlanningNature(String planningNature) {
		this.planningNature = planningNature;
	}

	public Integer getIncreaseUnofficialNumber() {
		return increaseUnofficialNumber;
	}

	public void setIncreaseUnofficialNumber(Integer increaseUnofficialNumber) {
		this.increaseUnofficialNumber = increaseUnofficialNumber;
	}

	public Set<HrRecruitmentPlanDetails> getHrRecruitmentPlanDetails() {
		return hrRecruitmentPlanDetails;
	}

	public void setHrRecruitmentPlanDetails(Set<HrRecruitmentPlanDetails> hrRecruitmentPlanDetails) {
		this.hrRecruitmentPlanDetails = hrRecruitmentPlanDetails;
	}

	public String getProjectContent() {
		return projectContent;
	}

	public void setProjectContent(String projectContent) {
		this.projectContent = projectContent;
	}

	public String getSchemer() {
		return schemer;
	}

	public void setSchemer(String schemer) {
		this.schemer = schemer;
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

	public Set<HrAttachments> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<HrAttachments> attachments) {
		this.attachments = attachments;
	}

	public HrRecruitmentPlansummary getHrRecruitmentPlansummary() {
		return hrRecruitmentPlansummary;
	}

	public void setHrRecruitmentPlansummary(HrRecruitmentPlansummary hrRecruitmentPlansummary) {
		this.hrRecruitmentPlansummary = hrRecruitmentPlansummary;
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