package com.vix.hr.job.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * @Description: 招聘活动
 * @author 李大鹏
 * @date 2013-08-07
 */
public class HrRecruitactivitity extends BaseEntity {

	private static final long serialVersionUID = 3885299522860884209L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/** 活动名称 */
	private String activityName;
	/** 活动地点 */
	private String activityAddress;
	/** 招聘对象 */
	private String recruitingObject;
	/** 注意事项 */
	private String mattersNeedAttention;
	/** 招聘单位 */
	private String recruitmentOfUnits;
	/** 招聘职位 */
	private String job;
	/** 发布类型 */
	private String publicationType;
	/** 有效期限 */
	private String lifeSpan;
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
	/** 发布状态 */
	private String publicationStruts;
	/** 活动开始时间 */
	private Date activitystartDate;
	/** 活动结束时间 */
	private Date activityendDate;

	private Set<HrRecruitactivitityDetails> hrRecruitactivitityDetails = new HashSet<HrRecruitactivitityDetails>();

	public String getPublicationStruts() {
		return publicationStruts;
	}

	public void setPublicationStruts(String publicationStruts) {
		this.publicationStruts = publicationStruts;
	}

	public Date getActivitystartDate() {
		return activitystartDate;
	}

	public void setActivitystartDate(Date activitystartDate) {
		this.activitystartDate = activitystartDate;
	}
	
	public String getActivitystartDateStr() {
		if (null != activitystartDate) {
			return DateUtil.format(activitystartDate);
		}
		return "";
	}
	/** default constructor */

	public HrRecruitactivitity() {
	}

	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityAddress() {
		return this.activityAddress;
	}

	public void setActivityAddress(String activityAddress) {
		this.activityAddress = activityAddress;
	}

	public String getRecruitingObject() {
		return this.recruitingObject;
	}

	public void setRecruitingObject(String recruitingObject) {
		this.recruitingObject = recruitingObject;
	}

	public String getMattersNeedAttention() {
		return this.mattersNeedAttention;
	}

	public void setMattersNeedAttention(String mattersNeedAttention) {
		this.mattersNeedAttention = mattersNeedAttention;
	}

	public String getRecruitmentOfUnits() {
		return this.recruitmentOfUnits;
	}

	public void setRecruitmentOfUnits(String recruitmentOfUnits) {
		this.recruitmentOfUnits = recruitmentOfUnits;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getPublicationType() {
		return this.publicationType;
	}

	public void setPublicationType(String publicationType) {
		this.publicationType = publicationType;
	}

	public String getLifeSpan() {
		return this.lifeSpan;
	}

	public void setLifeSpan(String lifeSpan) {
		this.lifeSpan = lifeSpan;
	}

	public String getWorkingPlace() {
		return this.workingPlace;
	}

	public void setWorkingPlace(String workingPlace) {
		this.workingPlace = workingPlace;
	}

	public String getRecruitmentInTheSeriesNumber() {
		return this.recruitmentInTheSeriesNumber;
	}

	public void setRecruitmentInTheSeriesNumber(String recruitmentInTheSeriesNumber) {
		this.recruitmentInTheSeriesNumber = recruitmentInTheSeriesNumber;
	}

	public String getRecruitmentOfTheUnofficialNumber() {
		return this.recruitmentOfTheUnofficialNumber;
	}

	public void setRecruitmentOfTheUnofficialNumber(String recruitmentOfTheUnofficialNumber) {
		this.recruitmentOfTheUnofficialNumber = recruitmentOfTheUnofficialNumber;
	}

	public String getRecruitmentRequirements() {
		return this.recruitmentRequirements;
	}

	public void setRecruitmentRequirements(String recruitmentRequirements) {
		this.recruitmentRequirements = recruitmentRequirements;
	}

	public String getOperatingDuty() {
		return this.operatingDuty;
	}

	public void setOperatingDuty(String operatingDuty) {
		this.operatingDuty = operatingDuty;
	}

	public Date getActivityendDate() {
		return activityendDate;
	}
    
	public String getActivityendDateStr() {
		if (null != activityendDate) {
			return DateUtil.format(activityendDate);
		}
		return "";
	}
	
	public void setActivityendDate(Date activityendDate) {
		this.activityendDate = activityendDate;
	}

	public Set<HrRecruitactivitityDetails> getHrRecruitactivitityDetails() {
		return hrRecruitactivitityDetails;
	}

	public void setHrRecruitactivitityDetails(Set<HrRecruitactivitityDetails> hrRecruitactivitityDetails) {
		this.hrRecruitactivitityDetails = hrRecruitactivitityDetails;
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

}