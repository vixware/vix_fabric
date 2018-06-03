package com.vix.hr.job.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * @Description: 录用管理
 * @author 李大鹏
 * @date 2013-09-16
 */
public class HrHirManage extends BaseEntity {

	private static final long serialVersionUID = 5204270473040701094L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/** 应聘人姓名 */
	private String candidateName;
	/** 性别 */
	private String sex;
	/** 年龄 */
	private String age;
	/** 录用意见 */
	private String employmentViews;
	/** 面试结果 */
	private String resultsOfInterview;
	/** 笔试结果 */
	private String writtenTestResults;
	/** 应聘职位 */
	private String employmentObjective;
	/** 应聘公司部门 */
	private String applicantsDepartment;
	/** 预计到岗日期 */
	private Date expectedArrivalDate;
	/** 转入类别 */
	// 为后备人才，高级人才，无
	private String intoTheCategory;
	/** 备注 */
	private String remarks;

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getEmploymentViews() {
		return employmentViews;
	}

	public void setEmploymentViews(String employmentViews) {
		this.employmentViews = employmentViews;
	}

	public String getResultsOfInterview() {
		return resultsOfInterview;
	}

	public void setResultsOfInterview(String resultsOfInterview) {
		this.resultsOfInterview = resultsOfInterview;
	}

	public String getWrittenTestResults() {
		return writtenTestResults;
	}

	public void setWrittenTestResults(String writtenTestResults) {
		this.writtenTestResults = writtenTestResults;
	}

	public String getEmploymentObjective() {
		return employmentObjective;
	}

	public void setEmploymentObjective(String employmentObjective) {
		this.employmentObjective = employmentObjective;
	}

	public String getApplicantsDepartment() {
		return applicantsDepartment;
	}

	public void setApplicantsDepartment(String applicantsDepartment) {
		this.applicantsDepartment = applicantsDepartment;
	}

	public Date getExpectedArrivalDate() {
		return expectedArrivalDate;
	}
    
	public String getExpectedArrivalDateStr() {
		if (null != expectedArrivalDate) {
			return DateUtil.format(expectedArrivalDate);
		}
		return "";
	}
	
	public void setExpectedArrivalDate(Date expectedArrivalDate) {
		this.expectedArrivalDate = expectedArrivalDate;
	}

	public String getIntoTheCategory() {
		return intoTheCategory;
	}

	public void setIntoTheCategory(String intoTheCategory) {
		this.intoTheCategory = intoTheCategory;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
