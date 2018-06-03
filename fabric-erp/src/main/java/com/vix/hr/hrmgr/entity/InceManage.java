package com.vix.hr.hrmgr.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * @Description:奖惩管理
 * @author 李大鹏
 */
public class InceManage extends BaseEntity {

	private static final long serialVersionUID = 1285403279457814632L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/** 员工编号 */
	private String inceEmployeeCode;
	/** 员工名称 */
	private String inceEmployeeName;
	/** 主题 */
	private String inceTheme;
	/** 当事人 */
	private String litigant;
	/** 奖惩理由 */
	private String reasons;
	/** 状态 */
	private String states;
	/** 奖惩时间 */
	private Date inceTime;
	/** 执行人 */
	private String approval;
	/** 备注 */
	private String remarks;

	public String getInceEmployeeCode() {
		return inceEmployeeCode;
	}

	public void setInceEmployeeCode(String inceEmployeeCode) {
		this.inceEmployeeCode = inceEmployeeCode;
	}

	public String getInceEmployeeName() {
		return inceEmployeeName;
	}

	public void setInceEmployeeName(String inceEmployeeName) {
		this.inceEmployeeName = inceEmployeeName;
	}

	public String getInceTheme() {
		return inceTheme;
	}

	public void setInceTheme(String inceTheme) {
		this.inceTheme = inceTheme;
	}

	public String getLitigant() {
		return litigant;
	}

	public void setLitigant(String litigant) {
		this.litigant = litigant;
	}

	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	public Date getInceTime() {
		return inceTime;
	}

	public void setInceTime(Date inceTime) {
		this.inceTime = inceTime;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
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
