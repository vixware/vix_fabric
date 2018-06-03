package com.vix.hr.trainning.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @Description:培训资源-培训讲师
 * @author bobchen
 * @date 2015-8-25 下午1:52:01
 */
public class TrainingLecturer extends BaseEntity {

	private static final long serialVersionUID = 8762478132950268507L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 讲师性质 */
	private String lecturerNature;
	/** 讲师编码 */
	private String lecturerCode;
	/** 讲师姓名 */
	private String lecturerName;
	/** 职位 */
	private String lecturerPosition;
	/** 所属部门 */
	private String branches;
	/** 讲师级别 */
	private String lecturerLevel;
	/** 讲师类别 */
	private String lecturerType;
	/** 讲师费用 */
	private Double lecturerCost;
	/** 讲师简介 */
	private String lecturerIntroduction;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;

	/** 培训渠道 */
	private TrainingChannel trainingChannel;
	/** 培训课程管理 */
	private TrainingCM trainingCM;
	/** 培训资源 */
	private TrainingResources trainingResources;
	/** 培训费用 */
	private TrainingCost trainingCost;

	public String getLecturerNature() {
		return lecturerNature;
	}

	public void setLecturerNature(String lecturerNature) {
		this.lecturerNature = lecturerNature;
	}

	public String getLecturerCode() {
		return lecturerCode;
	}

	public void setLecturerCode(String lecturerCode) {
		this.lecturerCode = lecturerCode;
	}

	public String getLecturerName() {
		return lecturerName;
	}

	public void setLecturerName(String lecturerName) {
		this.lecturerName = lecturerName;
	}

	public String getLecturerPosition() {
		return lecturerPosition;
	}

	public void setLecturerPosition(String lecturerPosition) {
		this.lecturerPosition = lecturerPosition;
	}

	public String getBranches() {
		return branches;
	}

	public void setBranches(String branches) {
		this.branches = branches;
	}

	public String getLecturerLevel() {
		return lecturerLevel;
	}

	public void setLecturerLevel(String lecturerLevel) {
		this.lecturerLevel = lecturerLevel;
	}

	public String getLecturerType() {
		return lecturerType;
	}

	public void setLecturerType(String lecturerType) {
		this.lecturerType = lecturerType;
	}

	public Double getLecturerCost() {
		return lecturerCost;
	}

	public void setLecturerCost(Double lecturerCost) {
		this.lecturerCost = lecturerCost;
	}

	public String getLecturerIntroduction() {
		return lecturerIntroduction;
	}

	public void setLecturerIntroduction(String lecturerIntroduction) {
		this.lecturerIntroduction = lecturerIntroduction;
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

	public TrainingResources getTrainingResources() {
		return trainingResources;
	}

	public void setTrainingResources(TrainingResources trainingResources) {
		this.trainingResources = trainingResources;
	}

	public TrainingChannel getTrainingChannel() {
		return trainingChannel;
	}

	public void setTrainingChannel(TrainingChannel trainingChannel) {
		this.trainingChannel = trainingChannel;
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

}
