package com.vix.hr.job.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * @Description: 高级人才招聘
 * @author 李大鹏
 * @date 2013-08-19
 */
public class HrSenior extends BaseEntity {
	private static final long serialVersionUID = -2338080986944566382L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/** 应聘人姓名 */
	private String applicantsName;
	/** 当前应聘职位名称 */
	private String employmentObjective;
	/** 所在公司部门 */
	private String applicantsDepartment;
	/** 最高学历 */
	private String highestDegree;
	/** 所属招聘阶段 */
	private String recruitmentStage;
	/** 推荐职位名称 */
	private String recommendedPositionName;
	/** 推荐部门 */
	private String recommendedDepartment;
	/** 评价人 */
	private String appraiser;
	/** 评价人职位 */
	private String appraiserPosition;
	/** 评价日期 */
	private Date evaluationDate;
	/** 评语 */
	private String comment;
	/** 结论 */
	private String conclusion;

	public String getApplicantsName() {
		return applicantsName;
	}

	public void setApplicantsName(String applicantsName) {
		this.applicantsName = applicantsName;
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

	public String getHighestDegree() {
		return highestDegree;
	}

	public void setHighestDegree(String highestDegree) {
		this.highestDegree = highestDegree;
	}

	public String getRecruitmentStage() {
		return recruitmentStage;
	}

	public void setRecruitmentStage(String recruitmentStage) {
		this.recruitmentStage = recruitmentStage;
	}

	public String getRecommendedPositionName() {
		return recommendedPositionName;
	}

	public void setRecommendedPositionName(String recommendedPositionName) {
		this.recommendedPositionName = recommendedPositionName;
	}

	public String getRecommendedDepartment() {
		return recommendedDepartment;
	}

	public void setRecommendedDepartment(String recommendedDepartment) {
		this.recommendedDepartment = recommendedDepartment;
	}

	public String getAppraiser() {
		return appraiser;
	}

	public void setAppraiser(String appraiser) {
		this.appraiser = appraiser;
	}

	public String getAppraiserPosition() {
		return appraiserPosition;
	}

	public void setAppraiserPosition(String appraiserPosition) {
		this.appraiserPosition = appraiserPosition;
	}

	public Date getEvaluationDate() {
		return evaluationDate;
	}
    
	public String getEvaluationDateStr() {
		if (null != evaluationDate) {
			return DateUtil.format(evaluationDate);
		}
		return "";
	}
	
	public void setEvaluationDate(Date evaluationDate) {
		this.evaluationDate = evaluationDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
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
