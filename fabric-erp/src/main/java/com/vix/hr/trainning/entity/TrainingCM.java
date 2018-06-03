package com.vix.hr.trainning.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @Description:培训课程管理
 * @author bobchen
 * @date 2015-8-26 下午4:38:43
 */
public class TrainingCM extends BaseEntity {

	private static final long serialVersionUID = -7634079115193989315L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/**
	 * 部门与人员类型 "O\":\"部门\",\"E\":\"人员\"
	 */
	private String pubType;
	/** 发布对象的id集合 */
	private String pubIds;
	/** 课程编码 */
	private String courseCode;
	/** 课程名称 */
	private String courseName;
	/** 目的 */
	private String objective;
	/** 课程内容 */
	private String courseeducation;
	/** 需要课时 */
	private String needClassHour;
	/** 课程费用 */
	private Double course;
	/** 有效开始日期 */
	private Date effectiveStartDate;
	/** 有效结束日期 */
	private Date effectiveEndDate;
	/** 部门或参与人 */
	private String xgdepartmentOrParticipants;
	/** 最小开班人数 */
	public String minimumClasses;
	/** 是否公开 */
	public Integer isOpen;
	/** 是否有证书 */
	public Integer isCertificate;
	/** 是否有合同 */
	public Integer isContract;
	/** 参与条件 */
	private String participationCondition;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;

	/** 素质指标 */
	private QualityIndex qualityIndex;
	/** 课程性质 */
	private CourseNature courseNature;
	/** 培训类别 */
	private TrainCategory trainCategory;
	/** 培训讲师 */
	private Set<TrainingLecturer> trainingLecturer = new HashSet<TrainingLecturer>();
	/** 培训渠道 */
	private Set<TrainingChannel> trainingChannel = new HashSet<TrainingChannel>();
	/** 培训资料 */
	private Set<TrainingData> trainingData = new HashSet<TrainingData>();

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseeducation() {
		return courseeducation;
	}

	public void setCourseeducation(String courseeducation) {
		this.courseeducation = courseeducation;
	}

	public Date getEffectiveStartDate() {
		return effectiveStartDate;
	}

	public void setEffectiveStartDate(Date effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	public Date getEffectiveEndDate() {
		return effectiveEndDate;
	}

	public void setEffectiveEndDate(Date effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public String getNeedClassHour() {
		return needClassHour;
	}

	public void setNeedClassHour(String needClassHour) {
		this.needClassHour = needClassHour;
	}

	public Double getCourse() {
		return course;
	}

	public void setCourse(Double course) {
		this.course = course;
	}

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	public Integer getIsCertificate() {
		return isCertificate;
	}

	public void setIsCertificate(Integer isCertificate) {
		this.isCertificate = isCertificate;
	}

	public Integer getIsContract() {
		return isContract;
	}

	public void setIsContract(Integer isContract) {
		this.isContract = isContract;
	}

	public String getParticipationCondition() {
		return participationCondition;
	}

	public void setParticipationCondition(String participationCondition) {
		this.participationCondition = participationCondition;
	}

	public String getMinimumClasses() {
		return minimumClasses;
	}

	public void setMinimumClasses(String minimumClasses) {
		this.minimumClasses = minimumClasses;
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

	public QualityIndex getQualityIndex() {
		return qualityIndex;
	}

	public void setQualityIndex(QualityIndex qualityIndex) {
		this.qualityIndex = qualityIndex;
	}

	public CourseNature getCourseNature() {
		return courseNature;
	}

	public void setCourseNature(CourseNature courseNature) {
		this.courseNature = courseNature;
	}

	public TrainCategory getTrainCategory() {
		return trainCategory;
	}

	public void setTrainCategory(TrainCategory trainCategory) {
		this.trainCategory = trainCategory;
	}

	public String getXgdepartmentOrParticipants() {
		return xgdepartmentOrParticipants;
	}

	public void setXgdepartmentOrParticipants(String xgdepartmentOrParticipants) {
		this.xgdepartmentOrParticipants = xgdepartmentOrParticipants;
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

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public String getPubType() {
		return pubType;
	}

	public void setPubType(String pubType) {
		this.pubType = pubType;
	}

	public String getPubIds() {
		return pubIds;
	}

	public void setPubIds(String pubIds) {
		this.pubIds = pubIds;
	}
}
