package com.vix.hr.trainning.entity;
import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * @Description: 创建填报计划
 * @author 陈正文
 */
public class Plan extends BaseEntity {

	private static final long serialVersionUID = -2767404336548332881L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 计划名称 */
	private String planName;
	/** 部门 */
	private String planDepartment;
	/** 培训开始日期 */
	private Date trainingStartDate;
	/** 培训结束日期 */
	private Date trainingEndDate;
	/** 拟培训时间 */
	private Date quasiTrainingTime;
	/** 计划总费用 */
	private Double totalCost;
	/** 课程成本权重 */
	private String courseCostWeight;
	/** 所有总费用 */
	private Double courseTotalCost;
	/**
	 * 提出计划部门与人员类型 "O\":\"部门\",\"E\":\"人员\"
	 */
	private String pubType;
	/** 发布对象的id集合 */
	private String pubIds;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/** 计划汇总 */
	private PlanTrainCourse planTrainCourse;
	/**
	 * 是否查看 N :新数据,O:已查看数据
	 */
	public String isNew;
	public String getPlanName() {
		return planName;
	}
	
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	
	public String getPlanDepartment() {
		return planDepartment;
	}
	
	public void setPlanDepartment(String planDepartment) {
		this.planDepartment = planDepartment;
	}
	
	public Date getTrainingStartDate() {
		return trainingStartDate;
	}
	
	public String getTrainingStartDateStr() {
		if (null != trainingStartDate) {
			return DateUtil.format(trainingStartDate);
		}
		return "";
	}
	
	public void setTrainingStartDate(Date trainingStartDate) {
		this.trainingStartDate = trainingStartDate;
	}
	
	public Date getTrainingEndDate() {
		return trainingEndDate;
	}
	
	public String getTrainingEndDateStr() {
		if (null != trainingEndDate) {
			return DateUtil.format(trainingEndDate);
		}
		return "";
	}
	
	public void setTrainingEndDate(Date trainingEndDate) {
		this.trainingEndDate = trainingEndDate;
	}
	/**
	 * @return the isNew
	 */
	public String getIsNew() {
		return isNew;
	}
	/**
	 * @param isNew
	 *            the isNew to set
	 */
	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	
	public Date getQuasiTrainingTime() {
		return quasiTrainingTime;
	}
	
	public String getQuasiTrainingTimeStr() {
		if (null != quasiTrainingTime) {
			return DateUtil.format(quasiTrainingTime);
		}
		return "";
	}
	
	public void setQuasiTrainingTime(Date quasiTrainingTime) {
		this.quasiTrainingTime = quasiTrainingTime;
	}
	
	public String getCourseCostWeight() {
		return courseCostWeight;
	}
	
	public void setCourseCostWeight(String courseCostWeight) {
		this.courseCostWeight = courseCostWeight;
	}
	
	public Double getTotalCost() {
		return totalCost;
	}
	
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	
	public Double getCourseTotalCost() {
		return courseTotalCost;
	}
	
	public void setCourseTotalCost(Double courseTotalCost) {
		this.courseTotalCost = courseTotalCost;
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
	
	public PlanTrainCourse getPlanTrainCourse() {
		return planTrainCourse;
	}
	
	public void setPlanTrainCourse(PlanTrainCourse planTrainCourse) {
		this.planTrainCourse = planTrainCourse;
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
