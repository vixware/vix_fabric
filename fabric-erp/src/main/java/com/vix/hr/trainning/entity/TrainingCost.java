package com.vix.hr.trainning.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @ClassName: TrainingCost
 * @Description: 培训费用
 * @author bobchen
 * @date 2015年10月9日 下午3:04:55
 *
 */
public class TrainingCost extends BaseEntity {

	private static final long serialVersionUID = -7634079115193989315L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 培训项目名称 */
	private String projectTraining;
	/** 培训类型 */
	private String trainingType;
	/** 费用类型 */
	private String costTypes;
	/** 费用金额 */
	private Double costAccount;
	/** 费用提出人 */
	private String costPeople;
	/** 费用审核人 */
	private String costAudit;
	/** 是否通过审批 */
	private Integer hroughApproval;
	/** 提出时间 */
	private Date costStartDate;
	/** 审核时间 */
	private Date costCheckDate;
	/** 备注 */
	private String remarks;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;

	/** 培训讲师 */
	private CourseType courseType;
	/** 培训讲师 */
	private Set<TrainingLecturer> trainingLecturer = new HashSet<TrainingLecturer>();
	/** 培训渠道 */
	private Set<TrainingChannel> trainingChannel = new HashSet<TrainingChannel>();
	/** 培训资料 */
	private Set<TrainingData> trainingData = new HashSet<TrainingData>();

	public String getProjectTraining() {
		return projectTraining;
	}

	public void setProjectTraining(String projectTraining) {
		this.projectTraining = projectTraining;
	}

	public String getTrainingType() {
		return trainingType;
	}

	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
	}

	public String getCostTypes() {
		return costTypes;
	}

	public void setCostTypes(String costTypes) {
		this.costTypes = costTypes;
	}

	public Double getCostAccount() {
		return costAccount;
	}

	public void setCostAccount(Double costAccount) {
		this.costAccount = costAccount;
	}

	public String getCostPeople() {
		return costPeople;
	}

	public void setCostPeople(String costPeople) {
		this.costPeople = costPeople;
	}

	public String getCostAudit() {
		return costAudit;
	}

	public void setCostAudit(String costAudit) {
		this.costAudit = costAudit;
	}

	public Integer getHroughApproval() {
		return hroughApproval;
	}

	public void setHroughApproval(Integer hroughApproval) {
		this.hroughApproval = hroughApproval;
	}

	public Date getCostStartDate() {
		return costStartDate;
	}

	public void setCostStartDate(Date costStartDate) {
		this.costStartDate = costStartDate;
	}

	public Date getCostCheckDate() {
		return costCheckDate;
	}

	public void setCostCheckDate(Date costCheckDate) {
		this.costCheckDate = costCheckDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public Set<TrainingLecturer> getTrainingLecturer() {
		return trainingLecturer;
	}

	public void setTrainingLecturer(Set<TrainingLecturer> trainingLecturer) {
		this.trainingLecturer = trainingLecturer;
	}

	public Set<TrainingChannel> getTrainingChannel() {
		return trainingChannel;
	}

	public void setTrainingChannel(Set<TrainingChannel> trainingChannel) {
		this.trainingChannel = trainingChannel;
	}

	public Set<TrainingData> getTrainingData() {
		return trainingData;
	}

	public void setTrainingData(Set<TrainingData> trainingData) {
		this.trainingData = trainingData;
	}

	public CourseType getCourseType() {
		return courseType;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}
}
