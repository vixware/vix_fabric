package com.vix.hr.trainning.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @Description:培训资源-培训渠道
 * @author bobchen
 * @date 2015-8-25 下午5:41:13
 */
public class TrainingChannel extends BaseEntity {

	private static final long serialVersionUID = 8762478132950268507L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 渠道名称 */
	private String channelName;
	/** 渠道费用 */
	private Double channelCost;
	/** 所在省份 */
	private String province;
	/** 所在城市 */
	private String city;
	/** 联系方式 */
	private String contactInformation;
	/** 渠道类型 */
	private String channelType;
	/** 渠道描述 */
	private String channelDescription;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/** 培训课程管理 */
	private TrainingCM trainingCM;
	/** 培训费用 */
	private TrainingCost trainingCost;
	private Set<TrainingLecturer> trainingLecturers = new HashSet<TrainingLecturer>();

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Double getChannelCost() {
		return channelCost;
	}

	public void setChannelCost(Double channelCost) {
		this.channelCost = channelCost;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getChannelDescription() {
		return channelDescription;
	}

	public void setChannelDescription(String channelDescription) {
		this.channelDescription = channelDescription;
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

	public Set<TrainingLecturer> getTrainingLecturers() {
		return trainingLecturers;
	}

	public void setTrainingLecturers(Set<TrainingLecturer> trainingLecturers) {
		this.trainingLecturers = trainingLecturers;
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
