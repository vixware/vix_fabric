package com.vix.hr.job.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * @Description: 面试管理
 * @author 李大鹏
 * @date 2013-08-23
 */
public class HrInterviewManagement extends BaseEntity {

	private static final long serialVersionUID = 5505126445997828754L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/** 应聘人姓名 */
	private String candidateName;
	/** 应聘部门 */
	private String applicantsDepartment;
	/** 应聘职位 */
	private String employmentObjective;
	/** 笔试成绩 */
	private String writtenExaminationAchievement;
	/** 面试成绩 */
	private String interviewResult;
	/** 应聘人联系方式 */
	private String contact;
	/** 人员类型 */
	// 为候选面试人员，选中待面试人员
	private String typesOfOersonnel;
	/** 面试评价 */
	private String interviewAssessment;
	/** 综合评语 */
	private String comprehensiveReviews;
	/** 评价等级 */
	private String orderOfEvaluation;
	/** 录用情况 */
	private String employmentSituation;
	/** 面试人 */
	private String interviewer;
	/** 评价人 */
	private String appraiser;
	/** 预计到岗日期 */
	private Date expectedArrivalDate;
	/** 转入类别 */
	// 为后备人才，高级人才，无
	private String intoTheCategory;

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getApplicantsDepartment() {
		return applicantsDepartment;
	}

	public void setApplicantsDepartment(String applicantsDepartment) {
		this.applicantsDepartment = applicantsDepartment;
	}

	public String getEmploymentObjective() {
		return employmentObjective;
	}

	public void setEmploymentObjective(String employmentObjective) {
		this.employmentObjective = employmentObjective;
	}

	public String getWrittenExaminationAchievement() {
		return writtenExaminationAchievement;
	}

	public void setWrittenExaminationAchievement(String writtenExaminationAchievement) {
		this.writtenExaminationAchievement = writtenExaminationAchievement;
	}

	public String getInterviewResult() {
		return interviewResult;
	}

	public void setInterviewResult(String interviewResult) {
		this.interviewResult = interviewResult;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTypesOfOersonnel() {
		return typesOfOersonnel;
	}

	public void setTypesOfOersonnel(String typesOfOersonnel) {
		this.typesOfOersonnel = typesOfOersonnel;
	}

	public String getInterviewAssessment() {
		return interviewAssessment;
	}

	public void setInterviewAssessment(String interviewAssessment) {
		this.interviewAssessment = interviewAssessment;
	}

	public String getComprehensiveReviews() {
		return comprehensiveReviews;
	}

	public void setComprehensiveReviews(String comprehensiveReviews) {
		this.comprehensiveReviews = comprehensiveReviews;
	}

	public String getOrderOfEvaluation() {
		return orderOfEvaluation;
	}

	public void setOrderOfEvaluation(String orderOfEvaluation) {
		this.orderOfEvaluation = orderOfEvaluation;
	}

	public String getEmploymentSituation() {
		return employmentSituation;
	}

	public void setEmploymentSituation(String employmentSituation) {
		this.employmentSituation = employmentSituation;
	}

	public String getInterviewer() {
		return interviewer;
	}

	public void setInterviewer(String interviewer) {
		this.interviewer = interviewer;
	}

	public String getAppraiser() {
		return appraiser;
	}

	public void setAppraiser(String appraiser) {
		this.appraiser = appraiser;
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
