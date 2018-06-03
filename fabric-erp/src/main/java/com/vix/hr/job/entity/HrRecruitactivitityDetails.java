package com.vix.hr.job.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * @Description: 招聘活动明细
 * @author 李大鹏
 * @date 2013-07-29
 */
public class HrRecruitactivitityDetails extends BaseEntity {

	private static final long serialVersionUID = 5976073884496549189L;
	/** 招聘组织部门 */
	private String orgDepartment;
	/** 招聘职位 */
	private String job;
	/** 发布类型 */
	private String publicationType;
	/** 发布状态 */
	private String publicationStruts;
	/** 有效期限 */
	private Date lifeSpan;
	/** 工作地点 */
	private String workingPlace;
	/** 招聘在编人数 */
	private String recruitmentInTheSeriesNumber;
	/** 招聘非在编人数 */
	private String recruitmentOfTheUnofficialNumber;
	/** 招聘要求 */
	private String recruitmentRequirements;
	/** 工作职责 */
	private String operatingDuty;

	/** 招聘活动 */
	private HrRecruitactivitity hrRecruitactivitity;

	public Date getLifeSpan() {
		return lifeSpan;
	}

	public void setLifeSpan(Date lifeSpan) {
		this.lifeSpan = lifeSpan;
	}
    
	public String getLifeSpaStr() {
		if (null != lifeSpan) {
			return DateUtil.format(lifeSpan);
		}
		return "";
	}
	
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getPublicationType() {
		return publicationType;
	}

	public void setPublicationType(String publicationType) {
		this.publicationType = publicationType;
	}

	public String getPublicationStruts() {
		return publicationStruts;
	}

	public void setPublicationStruts(String publicationStruts) {
		this.publicationStruts = publicationStruts;
	}

	public String getWorkingPlace() {
		return workingPlace;
	}

	public void setWorkingPlace(String workingPlace) {
		this.workingPlace = workingPlace;
	}

	public String getRecruitmentInTheSeriesNumber() {
		return recruitmentInTheSeriesNumber;
	}

	public void setRecruitmentInTheSeriesNumber(String recruitmentInTheSeriesNumber) {
		this.recruitmentInTheSeriesNumber = recruitmentInTheSeriesNumber;
	}

	public String getRecruitmentOfTheUnofficialNumber() {
		return recruitmentOfTheUnofficialNumber;
	}

	public void setRecruitmentOfTheUnofficialNumber(String recruitmentOfTheUnofficialNumber) {
		this.recruitmentOfTheUnofficialNumber = recruitmentOfTheUnofficialNumber;
	}

	public String getRecruitmentRequirements() {
		return recruitmentRequirements;
	}

	public void setRecruitmentRequirements(String recruitmentRequirements) {
		this.recruitmentRequirements = recruitmentRequirements;
	}

	public String getOperatingDuty() {
		return operatingDuty;
	}

	public void setOperatingDuty(String operatingDuty) {
		this.operatingDuty = operatingDuty;
	}

	public HrRecruitactivitity getHrRecruitactivitity() {
		return hrRecruitactivitity;
	}

	public void setHrRecruitactivitity(HrRecruitactivitity hrRecruitactivitity) {
		this.hrRecruitactivitity = hrRecruitactivitity;
	}

	public String getOrgDepartment() {
		return orgDepartment;
	}

	public void setOrgDepartment(String orgDepartment) {
		this.orgDepartment = orgDepartment;
	}

}