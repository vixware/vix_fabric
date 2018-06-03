package com.vix.hr.trainning.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * 
 * @Description:培训需求-培训课程
 * @author bobchen
 * @date 2015-8-25 上午11:08:33
 */
public class TrainingCourse extends BaseEntity {

	private static final long serialVersionUID = -7634079115193989315L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 课程编码 */
	private String courseCode;
	/** 课程名称 */
	private String courseName;
	/** 课程类别 */
	private String courseType;
	/** 课程内容 */
	private String courseeducation;
	/** 需要课时 */
	private String needs;
	/** 课程费用 */
	private String courseFees;
	/** 课程性质 */
	private String natureCourse;
	/** 有效开始日期 */
	private Date effectiveStartDate;
	/** 有效结束日期 */
	private Date effectiveEndDate;
	/** 备注 */
	private String remarkss;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/** 培训资源 */
	private TrainingResources trainingResources;
	/** 培训需求申请 */
	private DemandApply demandApply;

	
	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseeducation() {
		return courseeducation;
	}

	public void setCourseeducation(String courseeducation) {
		this.courseeducation = courseeducation;
	}

	public String getRemarkss() {
		return remarkss;
	}

	public void setRemarkss(String remarkss) {
		this.remarkss = remarkss;
	}

	public TrainingResources getTrainingResources() {
		return trainingResources;
	}

	public void setTrainingResources(TrainingResources trainingResources) {
		this.trainingResources = trainingResources;
	}

	public String getCourseFees() {
		return courseFees;
	}

	public void setCourseFees(String courseFees) {
		this.courseFees = courseFees;
	}

	public String getNatureCourse() {
		return natureCourse;
	}

	public void setNatureCourse(String natureCourse) {
		this.natureCourse = natureCourse;
	}

	public Date getEffectiveStartDate() {
		return effectiveStartDate;
	}
    
	public String getEffectiveStartDateStr() {
		if (null != effectiveStartDate) {
			return DateUtil.format(effectiveStartDate);
		}
		return "";
	}
	
	public void setEffectiveStartDate(Date effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	public Date getEffectiveEndDate() {
		return effectiveEndDate;
	}
    
	public String getEffectiveEndDateStr() {
		if (null != effectiveEndDate) {
			return DateUtil.format(effectiveEndDate);
		}
		return "";
	}
		
	public void setEffectiveEndDate(Date effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	public String getNeeds() {
		return needs;
	}

	public void setNeeds(String needs) {
		this.needs = needs;
	}

	public DemandApply getDemandApply() {
		return demandApply;
	}

	public void setDemandApply(DemandApply demandApply) {
		this.demandApply = demandApply;
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
