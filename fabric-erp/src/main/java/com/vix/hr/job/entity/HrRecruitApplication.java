package com.vix.hr.job.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * @Description: 用人申请
 * @author 李大鹏
 * @date 2013-08-16
 */
public class HrRecruitApplication extends BaseEntity {

	private static final long serialVersionUID = 131934666340770915L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/** 申请名称 */
	private String applicationName;
	/** 申请职位 */
	private String jobApplication;
	/** 申请编号 */
	private String applicationNumber;
	/** 申请部门 */
	private String applicationDepartment;
	/** 申请人姓名 */
	private String nameOfTheApplicant;
	/** 申请日期 */
	private Date applicationDate;
	/** 用工到位时间 */
	private Date laborTime;
	/** 申请理由 */
	private String reasonOfTheApplication;
	/** 备注 */
	private String remarks;
	/** 申请内容 */
	private String noticeContent;
	/** 审批状态 */
	private String approvalStatus;

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getJobApplication() {
		return jobApplication;
	}

	public void setJobApplication(String jobApplication) {
		this.jobApplication = jobApplication;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public String getApplicationDepartment() {
		return applicationDepartment;
	}

	public void setApplicationDepartment(String applicationDepartment) {
		this.applicationDepartment = applicationDepartment;
	}

	public String getNameOfTheApplicant() {
		return nameOfTheApplicant;
	}

	public void setNameOfTheApplicant(String nameOfTheApplicant) {
		this.nameOfTheApplicant = nameOfTheApplicant;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}
    
	public String getApplicationDateStr() {
		if (null != applicationDate) {
			return DateUtil.format(applicationDate);
		}
		return "";
	}
	
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public Date getLaborTime() {
		return laborTime;
	}
    
	public String getLaborTimeStr() {
		if (null != laborTime) {
			return DateUtil.format(laborTime);
		}
		return "";
	}
	
	public void setLaborTime(Date laborTime) {
		this.laborTime = laborTime;
	}

	public String getReasonOfTheApplication() {
		return reasonOfTheApplication;
	}

	public void setReasonOfTheApplication(String reasonOfTheApplication) {
		this.reasonOfTheApplication = reasonOfTheApplication;
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
