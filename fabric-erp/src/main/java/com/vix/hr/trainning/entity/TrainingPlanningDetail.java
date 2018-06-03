package com.vix.hr.trainning.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * @Description: 培训计划明细
 * @author 李大鹏
 */
public class TrainingPlanningDetail extends BaseEntity {

	private static final long serialVersionUID = -9159059653064562340L;
	/** 培训方式 */
	private String trainingWay;
	/** 培训目标 */
	private String trainingGoal;
	/** 培训教师 */
	private String goalOfTraining;
	/** 培训内容 */
	private String trainingContent;
	/** 培训课程名称 */
	private String trainingCourse;
	/** 培训地点 */
	private String trainingSite;
	/** 培训机构 */
	private String trainingInstitutions;
	/** 师资来源 */
	private String sourcesOfTeachers;
	/** 课程学时 */
	private String curriculumClassHours;
	/** 计划开始时间 */
	private Date planStartDate;
	/** 计划结束时间 */
	private Date planEndDate;
	/** 培训计划 */
	private TrainingPlanning trainingPlanning;
	/** 培训计划活动 */
	private TrainingActivity trainingActivity;

	public TrainingPlanning getTrainingPlanning() {
		return trainingPlanning;
	}

	public void setTrainingPlanning(TrainingPlanning trainingPlanning) {
		this.trainingPlanning = trainingPlanning;
	}

	public String getTrainingWay() {
		return trainingWay;
	}

	public void setTrainingWay(String trainingWay) {
		this.trainingWay = trainingWay;
	}

	public String getGoalOfTraining() {
		return goalOfTraining;
	}

	public void setGoalOfTraining(String goalOfTraining) {
		this.goalOfTraining = goalOfTraining;
	}

	public String getTrainingContent() {
		return trainingContent;
	}

	public void setTrainingContent(String trainingContent) {
		this.trainingContent = trainingContent;
	}

	public String getTrainingCourse() {
		return trainingCourse;
	}

	public void setTrainingCourse(String trainingCourse) {
		this.trainingCourse = trainingCourse;
	}

	public String getTrainingSite() {
		return trainingSite;
	}

	public void setTrainingSite(String trainingSite) {
		this.trainingSite = trainingSite;
	}

	public String getTrainingInstitutions() {
		return trainingInstitutions;
	}

	public void setTrainingInstitutions(String trainingInstitutions) {
		this.trainingInstitutions = trainingInstitutions;
	}

	public String getSourcesOfTeachers() {
		return sourcesOfTeachers;
	}

	public void setSourcesOfTeachers(String sourcesOfTeachers) {
		this.sourcesOfTeachers = sourcesOfTeachers;
	}

	public Date getPlanStartDate() {
		return planStartDate;
	}
    
	public String getPlanStartDateStr() {
		if (null != planStartDate) {
			return DateUtil.format(planStartDate);
		}
		return "";
	}
	
	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}

	public Date getPlanEndDate() {
		return planEndDate;
	}
    
	public String getPlanEndDateStr() {
		if (null != planEndDate) {
			return DateUtil.format(planEndDate);
		}
		return "";
	}
	
	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}

	public String getCurriculumClassHours() {
		return curriculumClassHours;
	}

	public void setCurriculumClassHours(String curriculumClassHours) {
		this.curriculumClassHours = curriculumClassHours;
	}

	public TrainingActivity getTrainingActivity() {
		return trainingActivity;
	}

	public void setTrainingActivity(TrainingActivity trainingActivity) {
		this.trainingActivity = trainingActivity;
	}

	public String getTrainingGoal() {
		return trainingGoal;
	}

	public void setTrainingGoal(String trainingGoal) {
		this.trainingGoal = trainingGoal;
	}
    
}
