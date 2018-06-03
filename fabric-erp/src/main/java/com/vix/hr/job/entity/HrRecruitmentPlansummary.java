package com.vix.hr.job.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * @Description: 招聘汇总
 * @author bob
 * @date 2015-08-20
 */
public class HrRecruitmentPlansummary extends BaseEntity {

	private static final long serialVersionUID = -6938376131496792843L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/** 招聘职务 */
	private String recruitment;
	/** 发布时间 */
	private Date releaseTime;
	/** 招聘部门 */
	private String recruitmentDepartment;
	/** 审核人 */
	private String auditPerson;
	/** 发布状态 */
	private String releaseState;
	/** 发布方式 */
	private String releaseMode;
	/** 备注 */
	private String remarks;

	/** 招聘计划 */
	private Set<HrRecruitplan> hrRecruitplan = new HashSet<HrRecruitplan>();

	public HrRecruitmentPlansummary() {
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRecruitment() {
		return recruitment;
	}

	public void setRecruitment(String recruitment) {
		this.recruitment = recruitment;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}
    
	public String getReleaseTimeStr() {
		if (null != releaseTime) {
			return DateUtil.format(releaseTime);
		}
		return "";
	}
	
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getRecruitmentDepartment() {
		return recruitmentDepartment;
	}

	public void setRecruitmentDepartment(String recruitmentDepartment) {
		this.recruitmentDepartment = recruitmentDepartment;
	}

	public String getReleaseState() {
		return releaseState;
	}

	public void setReleaseState(String releaseState) {
		this.releaseState = releaseState;
	}

	public String getReleaseMode() {
		return releaseMode;
	}

	public void setReleaseMode(String releaseMode) {
		this.releaseMode = releaseMode;
	}

	public String getAuditPerson() {
		return auditPerson;
	}

	public void setAuditPerson(String auditPerson) {
		this.auditPerson = auditPerson;
	}

	public Set<HrRecruitplan> getHrRecruitplan() {
		return hrRecruitplan;
	}

	public void setHrRecruitplan(Set<HrRecruitplan> hrRecruitplan) {
		this.hrRecruitplan = hrRecruitplan;
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