package com.vix.hr.trainning.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * 
 * @Description:培训需求汇总
 * @author bobchen
 * @date 2015-9-7 上午9:20:35
 */
public class DemandSummary extends BaseEntity {

	private static final long serialVersionUID = -8485291618096444564L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 培训讲师安排 */
	private String trainingInstructor;
	/** 汇总名称 */
	private String summaryName;
	/** 培训开始时间安排 */
	private Date summaryStartDate;
	/** 培训结束时间安排 */
	private Date summaryEndDate;
	/** 汇总日期 */
	private Date summaryDate;
	/** 汇总部门或负责人 */
	private String summaryDepartmentORPeople;
	/** 汇总描述 */
	private String summaryDescription;
	/** 汇总数量 */
	private String summaryNumber;
	/**
	 * 提出汇总部门与人员类型 "O\":\"部门\",\"E\":\"人员\"
	 */
	private String pubType;
	/** 发布对象的id集合 */
	private String pubIds;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;

	/** 部门 */
	private OrganizationUnit organizationUnit;
	/** 培训申请 */
	private Set<DemandApply> demandApply = new HashSet<DemandApply>();

	public String getTrainingInstructor() {
		return trainingInstructor;
	}

	public void setTrainingInstructor(String trainingInstructor) {
		this.trainingInstructor = trainingInstructor;
	}

	public String getSummaryName() {
		return summaryName;
	}

	public void setSummaryName(String summaryName) {
		this.summaryName = summaryName;
	}

	public Date getSummaryStartDate() {
		return summaryStartDate;
	}
    
	public String getSummaryStartDateStr() {
		if (null != summaryStartDate) {
			return DateUtil.format(summaryStartDate);
		}
		return "";
	}
	
	public void setSummaryStartDate(Date summaryStartDate) {
		this.summaryStartDate = summaryStartDate;
	}

	public Date getSummaryEndDate() {
		return summaryEndDate;
	}
    
	public String getSummaryEndDateStr() {
		if (null != summaryEndDate) {
			return DateUtil.format(summaryEndDate);
		}
		return "";
	}
	
	public void setSummaryEndDate(Date summaryEndDate) {
		this.summaryEndDate = summaryEndDate;
	}

	public Date getSummaryDate() {
		return summaryDate;
	}
    
	public String getSummaryDateStr() {
		if (null != summaryDate) {
			return DateUtil.format(summaryDate);
		}
		return "";
	}
	
	public void setSummaryDate(Date summaryDate) {
		this.summaryDate = summaryDate;
	}

	public String getSummaryDepartmentORPeople() {
		return summaryDepartmentORPeople;
	}

	public void setSummaryDepartmentORPeople(String summaryDepartmentORPeople) {
		this.summaryDepartmentORPeople = summaryDepartmentORPeople;
	}

	public String getSummaryNumber() {
		return summaryNumber;
	}

	public void setSummaryNumber(String summaryNumber) {
		this.summaryNumber = summaryNumber;
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

	public OrganizationUnit getOrganizationUnit() {
		return organizationUnit;
	}

	public void setOrganizationUnit(OrganizationUnit organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	public Set<DemandApply> getDemandApply() {
		return demandApply;
	}

	public void setDemandApply(Set<DemandApply> demandApply) {
		this.demandApply = demandApply;
	}

	public String getPubType() {
		return pubType;
	}

	public void setPubType(String pubType) {
		this.pubType = pubType;
	}

	public String getPubIds() {
		return pubIds;
	}

	public void setPubIds(String pubIds) {
		this.pubIds = pubIds;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public String getSummaryDescription() {
		return summaryDescription;
	}

	public void setSummaryDescription(String summaryDescription) {
		this.summaryDescription = summaryDescription;
	}

}
