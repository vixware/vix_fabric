package com.vix.hr.trainning.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @Description:培训需求-培训设施
 * @author bobchen
 * @date 2015-9-2 上午10:46:36
 */
public class TrainingFacilities extends BaseEntity {

	private static final long serialVersionUID = -7462065058013048129L;
	/** 设施编码 */
	private String facilitiesCode;
	/** 设施类别 */
	private String facilitiesType;
	/** 设施名称 */
	private String facilitiesName;
	/** 培训教室 */
	private String classroomTraining;
	/** 数量 */
	private String facilitiesNumber;
	/** 备注 */
	private String remarsks;
	/** 培训需求申请 */
	private DemandApply demandApply;
	/** 培训资源 */
	private TrainingResources trainingResources;

	public String getFacilitiesCode() {
		return facilitiesCode;
	}

	public void setFacilitiesCode(String facilitiesCode) {
		this.facilitiesCode = facilitiesCode;
	}

	public String getFacilitiesType() {
		return facilitiesType;
	}

	public void setFacilitiesType(String facilitiesType) {
		this.facilitiesType = facilitiesType;
	}

	public String getFacilitiesName() {
		return facilitiesName;
	}

	public void setFacilitiesName(String facilitiesName) {
		this.facilitiesName = facilitiesName;
	}

	public String getClassroomTraining() {
		return classroomTraining;
	}

	public void setClassroomTraining(String classroomTraining) {
		this.classroomTraining = classroomTraining;
	}

	public String getFacilitiesNumber() {
		return facilitiesNumber;
	}

	public void setFacilitiesNumber(String facilitiesNumber) {
		this.facilitiesNumber = facilitiesNumber;
	}

	public TrainingResources getTrainingResources() {
		return trainingResources;
	}

	public void setTrainingResources(TrainingResources trainingResources) {
		this.trainingResources = trainingResources;
	}

	public String getRemarsks() {
		return remarsks;
	}

	public void setRemarsks(String remarsks) {
		this.remarsks = remarsks;
	}

	public DemandApply getDemandApply() {
		return demandApply;
	}

	public void setDemandApply(DemandApply demandApply) {
		this.demandApply = demandApply;
	}
}
