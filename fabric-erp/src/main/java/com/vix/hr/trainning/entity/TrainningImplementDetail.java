package com.vix.hr.trainning.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * @Description: 培训实施明细
 * @author 李大鹏
 */
public class TrainningImplementDetail extends BaseEntity {

	private static final long serialVersionUID = -6846965792845629388L;
	/** 培训明细编码 */
	private String ppCode;
	/** 培训课程名称 */
	private String trainingCourse;
	/** 授课教师 */
	private String goalOfTraining;
	/** 授课地点 */
	private String trainingSite;
	/** 课程内容 */
	private String trainingContent;
	/** 课程学时 */
	private String curriculumClassHours;
	/** 授课开始日期 */
	private Date planStartDate;
	/** 授课结束日期 */
	private Date planEndDate;

	public String getTrainingCourse() {
		return trainingCourse;
	}

	public void setTrainingCourse(String trainingCourse) {
		this.trainingCourse = trainingCourse;
	}

	public String getGoalOfTraining() {
		return goalOfTraining;
	}

	public void setGoalOfTraining(String goalOfTraining) {
		this.goalOfTraining = goalOfTraining;
	}

	public String getTrainingSite() {
		return trainingSite;
	}

	public void setTrainingSite(String trainingSite) {
		this.trainingSite = trainingSite;
	}

	public String getTrainingContent() {
		return trainingContent;
	}

	public void setTrainingContent(String trainingContent) {
		this.trainingContent = trainingContent;
	}

	public String getCurriculumClassHours() {
		return curriculumClassHours;
	}

	public void setCurriculumClassHours(String curriculumClassHours) {
		this.curriculumClassHours = curriculumClassHours;
	}

	public Date getPlanStartDate() {
		return planStartDate;
	}

	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}

	public Date getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}

	public String getPpCode() {
		return ppCode;
	}

	public void setPpCode(String ppCode) {
		this.ppCode = ppCode;
	}

}
