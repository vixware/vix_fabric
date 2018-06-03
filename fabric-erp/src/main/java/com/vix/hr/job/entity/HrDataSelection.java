package com.vix.hr.job.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * @Description: 资料刷选
 * @author luchuan
 * @date 2018-04-04
 */
public class HrDataSelection extends BaseEntity {

	private static final long serialVersionUID = 5204270473040701094L;
	/** 应聘人姓名 */
	private String candidateName;
	/** 英文名*/
	private String enlishName;
	/** 应聘人编号*/
	private String nameNumber;
	/** 指定应聘类型*/
	private String employeeType;
	/** 应聘渠道*/
	private String employeeChannel;
	/** 推荐人*/
	private String recommendMan;
	/** 性别 */
	private String sex;
	/** 年龄 */
	private String age;
	/** 录用意见 */
	private String employmentViews;
	/** 面试成绩 */
	private String resultsOfInterview;
	/** 笔试成绩 */
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
	/** 附件 */
	private Set<HrAttachments> attachments = new HashSet<HrAttachments>();

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

	public String getEnlishName() {
		return enlishName;
	}

	public void setEnlishName(String enlishName) {
		this.enlishName = enlishName;
	}

	public String getNameNumber() {
		return nameNumber;
	}

	public void setNameNumber(String nameNumber) {
		this.nameNumber = nameNumber;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getEmployeeChannel() {
		return employeeChannel;
	}

	public void setEmployeeChannel(String employeeChannel) {
		this.employeeChannel = employeeChannel;
	}

	public String getRecommendMan() {
		return recommendMan;
	}

	public void setRecommendMan(String recommendMan) {
		this.recommendMan = recommendMan;
	}

	public Set<HrAttachments> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<HrAttachments> attachments) {
		this.attachments = attachments;
	}

	
}
