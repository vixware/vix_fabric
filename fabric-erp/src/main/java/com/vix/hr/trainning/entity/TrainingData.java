package com.vix.hr.trainning.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @Description:培训需求-培训资料
 * @author bobchen
 * @date 2015-9-2 上午10:42:29
 */
public class TrainingData extends BaseEntity {

	private static final long serialVersionUID = 5662509664944179134L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/** 资料编码 */
	private String datumCode;
	/** 资料类别 */
	private String datumType;
	/** 资料名称 */
	private String datumName;
	/** 资料简介 */
	private String profile;
	/** 资料作者 */
	private String datumauthor;
	/** 资料费用 */
	private String datumCost;
	/** 出版单位 */
	private String publishingUnit;
	/** 存放位置 */
	private String storageLocation;
	/** 备注 */
	private String remarksss;
	/** 培训需求申请 */
	private DemandApply demandApply;
	/** 培训资源 */
	private TrainingResources trainingResources;
	/** 培训课程管理 */
	private TrainingCM trainingCM;
	/** 培训费用 */
	private TrainingCost trainingCost;

	public String getDatumCode() {
		return datumCode;
	}

	public void setDatumCode(String datumCode) {
		this.datumCode = datumCode;
	}

	public String getDatumType() {
		return datumType;
	}

	public void setDatumType(String datumType) {
		this.datumType = datumType;
	}

	public String getDatumName() {
		return datumName;
	}

	public void setDatumName(String datumName) {
		this.datumName = datumName;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getDatumauthor() {
		return datumauthor;
	}

	public void setDatumauthor(String datumauthor) {
		this.datumauthor = datumauthor;
	}

	public String getDatumCost() {
		return datumCost;
	}

	public void setDatumCost(String datumCost) {
		this.datumCost = datumCost;
	}

	public String getPublishingUnit() {
		return publishingUnit;
	}

	public void setPublishingUnit(String publishingUnit) {
		this.publishingUnit = publishingUnit;
	}

	public String getStorageLocation() {
		return storageLocation;
	}

	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}

	public String getRemarksss() {
		return remarksss;
	}

	public void setRemarksss(String remarksss) {
		this.remarksss = remarksss;
	}

	public TrainingResources getTrainingResources() {
		return trainingResources;
	}

	public void setTrainingResources(TrainingResources trainingResources) {
		this.trainingResources = trainingResources;
	}

	public DemandApply getDemandApply() {
		return demandApply;
	}

	public void setDemandApply(DemandApply demandApply) {
		this.demandApply = demandApply;
	}

	public TrainingCM getTrainingCM() {
		return trainingCM;
	}

	public void setTrainingCM(TrainingCM trainingCM) {
		this.trainingCM = trainingCM;
	}

	public TrainingCost getTrainingCost() {
		return trainingCost;
	}

	public void setTrainingCost(TrainingCost trainingCost) {
		this.trainingCost = trainingCost;
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
