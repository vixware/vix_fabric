package com.vix.hr.hrmgr.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * @Description:政策制度管理
 * @author 李大鹏
 */
public class PolSysManage extends BaseEntity {

	private static final long serialVersionUID = -8815597653523426682L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/** 编号 */
	private String polCode;
	/** 名称 */
	private String polName;
	/** 文件名称 */
	private String fileName;
	/** 类型 */
	private String polTypes;
	/** 所属部门 */
	private String org;
	/** 状态 */
	private String states;
	/** 发布时间 */
	private Date releaseTime;
	/** 对象类型 */
	private String objectType;

	/** 行政制度分类 */
	private PolSysManageCategory polSysManageCategory;

	public String getPolCode() {
		return polCode;
	}

	public void setPolCode(String polCode) {
		this.polCode = polCode;
	}

	public String getPolName() {
		return polName;
	}

	public void setPolName(String polName) {
		this.polName = polName;
	}

	public String getPolTypes() {
		return polTypes;
	}

	public void setPolTypes(String polTypes) {
		this.polTypes = polTypes;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public PolSysManageCategory getPolSysManageCategory() {
		return polSysManageCategory;
	}

	public void setPolSysManageCategory(PolSysManageCategory polSysManageCategory) {
		this.polSysManageCategory = polSysManageCategory;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
