package com.vix.hr.job.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * @Description: 招聘计划明细
 * @author 李大鹏
 * @date 2013-07-29
 */
public class HrRecruitmentPlanDetails extends BaseEntity {

	private static final long serialVersionUID = 3664492765352777305L;
	/** 招聘对象*/
	private String recruitingObject;
	/** 招聘职位*/
	private String theJob;
	/** 补充连续数字*/
	private String recruitmentSeriesNumber;
	/** 补充非官方数字*/
	private String recruitmentUnofficialNumber;
	/** 工作职责*/
	private String operatingDuty;
	/** 计划名称 */
	private String programName;
	/** 计划内容 */
	private String projectContent;
	/** 职位说明 */
	private String jobDescription;
	/** 负责人 */
	private String leadingOfficial;
	/**工作区域*/
	private String jobAddress;
	/** 招聘要求*/
	private String recruitmentRequirements;
	/** 覆盖地区 */
	private String coverageArea;
	/** 费用预算 */
	private String expenseBudget;
	/** 开始时间*/
	private Date plannedStartTime;
	/** 结束时间*/
	private Date plannedEndTime;
	/** 招聘计划*/
	private HrRecruitplan hrRecruitplan;

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getProjectContent() {
		return projectContent;
	}

	public void setProjectContent(String projectContent) {
		this.projectContent = projectContent;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getLeadingOfficial() {
		return leadingOfficial;
	}

	public void setLeadingOfficial(String leadingOfficial) {
		this.leadingOfficial = leadingOfficial;
	}

	public String getJobAddress() {
		return jobAddress;
	}

	public void setJobAddress(String jobAddress) {
		this.jobAddress = jobAddress;
	}

	public String getRecruitmentRequirements() {
		return recruitmentRequirements;
	}

	public void setRecruitmentRequirements(String recruitmentRequirements) {
		this.recruitmentRequirements = recruitmentRequirements;
	}

	public String getCoverageArea() {
		return coverageArea;
	}

	public void setCoverageArea(String coverageArea) {
		this.coverageArea = coverageArea;
	}

	public String getExpenseBudget() {
		return expenseBudget;
	}

	public void setExpenseBudget(String expenseBudget) {
		this.expenseBudget = expenseBudget;
	}

	public String getRecruitingObject() {
		return recruitingObject;
	}

	public void setRecruitingObject(String recruitingObject) {
		this.recruitingObject = recruitingObject;
	}

	public String getTheJob() {
		return theJob;
	}

	public void setTheJob(String theJob) {
		this.theJob = theJob;
	}

	public String getRecruitmentSeriesNumber() {
		return recruitmentSeriesNumber;
	}

	public void setRecruitmentSeriesNumber(String recruitmentSeriesNumber) {
		this.recruitmentSeriesNumber = recruitmentSeriesNumber;
	}

	public String getRecruitmentUnofficialNumber() {
		return recruitmentUnofficialNumber;
	}

	public void setRecruitmentUnofficialNumber(String recruitmentUnofficialNumber) {
		this.recruitmentUnofficialNumber = recruitmentUnofficialNumber;
	}

	public String getOperatingDuty() {
		return operatingDuty;
	}

	public void setOperatingDuty(String operatingDuty) {
		this.operatingDuty = operatingDuty;
	}

	public HrRecruitplan getHrRecruitplan() {
		return hrRecruitplan;
	}

	public void setHrRecruitplan(HrRecruitplan hrRecruitplan) {
		this.hrRecruitplan = hrRecruitplan;
	}

	public Date getPlannedStartTime() {
		return plannedStartTime;
	}
    
	public String getPlannedStartTimeStr() {
		if (null != plannedStartTime) {
			return DateUtil.format(plannedStartTime);
		}
		return "";
	}
	
	public void setPlannedStartTime(Date plannedStartTime) {
		this.plannedStartTime = plannedStartTime;
	}

	public Date getPlannedEndTime() {
		return plannedEndTime;
	}
    
	public String getPlannedEndTimeStr() {
		if (null != plannedEndTime) {
			return DateUtil.format(plannedEndTime);
		}
		return "";
	}
	
	public void setPlannedEndTime(Date plannedEndTime) {
		this.plannedEndTime = plannedEndTime;
	}

}
