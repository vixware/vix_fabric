package com.vix.hr.trainning.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * @Description: 培训资源
 * @author 李大鹏
 */
public class TrainingResources extends BaseEntity {

	private static final long serialVersionUID = 3195360454105508696L;
	/** 资源编码 */
	private String resourcesCode;
	/** 资源名称 */
	private String resourcesName;
	/** 是否有效 */
	private String whetherEffective;
	/** 是否通用 */
	private String whetherTheGeneral;
	/** 适用岗位 */
	private String applicationPost;
	/** 备注 */
	private String remarks;
	/** 培训课程 */
	private Set<TrainingCourse> trainingCourses = new HashSet<TrainingCourse>();
	/** 培训资料 */
	private Set<TrainingData> trainingDatas = new HashSet<TrainingData>();
	/** 培训设施 */
	private Set<TrainingFacilities> trainingFacilities = new HashSet<TrainingFacilities>();
	/** 培训教师 */
	private Set<TrainingLecturer> trainingLecturers = new HashSet<TrainingLecturer>();

	public String getResourcesCode() {
		return resourcesCode;
	}

	public void setResourcesCode(String resourcesCode) {
		this.resourcesCode = resourcesCode;
	}

	public String getResourcesName() {
		return resourcesName;
	}

	public void setResourcesName(String resourcesName) {
		this.resourcesName = resourcesName;
	}

	public String getWhetherEffective() {
		return whetherEffective;
	}

	public void setWhetherEffective(String whetherEffective) {
		this.whetherEffective = whetherEffective;
	}

	public String getWhetherTheGeneral() {
		return whetherTheGeneral;
	}

	public void setWhetherTheGeneral(String whetherTheGeneral) {
		this.whetherTheGeneral = whetherTheGeneral;
	}

	public String getApplicationPost() {
		return applicationPost;
	}

	public void setApplicationPost(String applicationPost) {
		this.applicationPost = applicationPost;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Set<TrainingCourse> getTrainingCourses() {
		return trainingCourses;
	}

	public void setTrainingCourses(Set<TrainingCourse> trainingCourses) {
		this.trainingCourses = trainingCourses;
	}

	public Set<TrainingData> getTrainingDatas() {
		return trainingDatas;
	}

	public void setTrainingDatas(Set<TrainingData> trainingDatas) {
		this.trainingDatas = trainingDatas;
	}

	public Set<TrainingFacilities> getTrainingFacilities() {
		return trainingFacilities;
	}

	public void setTrainingFacilities(Set<TrainingFacilities> trainingFacilities) {
		this.trainingFacilities = trainingFacilities;
	}

	public Set<TrainingLecturer> getTrainingLecturers() {
		return trainingLecturers;
	}

	public void setTrainingLecturers(Set<TrainingLecturer> trainingLecturers) {
		this.trainingLecturers = trainingLecturers;
	}

}
