package com.vix.hr.trainning.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * @Description: 培训实施
 * @author 李大鹏
 */
public class TrainningImplement extends BaseEntity {

	private static final long serialVersionUID = 2085894080323735142L;
	/** 培训项目编号 */
	private String trainingProjectNumber;
	/** 培训名称 */
	private String trainingName;
	/** 培训级别 */
	// 公司,部门
	private String trainingLevel;
	/** 培训部门 */
	private String orgUnit;
	/** 培训类别 */
	private String planType;
	/** 培训方式 */
	private String trainingWay;
	/** 培训内容 */
	private String trainingContent;
	/** 培训开始日期 */
	private Date trainingStartDate;
	/** 培训结束日期 */
	private Date trainingEndDate;
	/** 注意事项 */
	private String needing;
	/** 培训对象 */
	private String trainingObject;
	/** 培训人数 */
	private String trainingPeopleNumber;
	/** 培训费用预算 */
	private String expensesBudget;
	/** 负责人 */
	private String leadings;
	/** 联系方式 */
	private String contactInformation;
	/** 主办单位 */
	private String org;
	/** 培训机构 */
	private String trainingInstitutions;
	/** 培训地点 */
	private String trainingSite;
	/** 培训学时 */
	private String trainingHours;
	/** 培训实施明细 */
	private Set<TrainingPlanning> trainingPlanning = new HashSet<TrainingPlanning>();

	public Set<TrainingPlanning> getTrainingPlanning() {
		return trainingPlanning;
	}

	public void setTrainingPlanning(Set<TrainingPlanning> trainingPlanning) {
		this.trainingPlanning = trainingPlanning;
	}

	public String getTrainingProjectNumber() {
		return trainingProjectNumber;
	}

	public void setTrainingProjectNumber(String trainingProjectNumber) {
		this.trainingProjectNumber = trainingProjectNumber;
	}

	public String getTrainingName() {
		return trainingName;
	}

	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}

	public String getTrainingLevel() {
		return trainingLevel;
	}

	public void setTrainingLevel(String trainingLevel) {
		this.trainingLevel = trainingLevel;
	}

	public String getOrgUnit() {
		return orgUnit;
	}

	public void setOrgUnit(String orgUnit) {
		this.orgUnit = orgUnit;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public String getTrainingWay() {
		return trainingWay;
	}

	public void setTrainingWay(String trainingWay) {
		this.trainingWay = trainingWay;
	}

	public String getTrainingContent() {
		return trainingContent;
	}

	public void setTrainingContent(String trainingContent) {
		this.trainingContent = trainingContent;
	}

	public Date getTrainingStartDate() {
		return trainingStartDate;
	}

	public void setTrainingStartDate(Date trainingStartDate) {
		this.trainingStartDate = trainingStartDate;
	}

	public Date getTrainingEndDate() {
		return trainingEndDate;
	}

	public void setTrainingEndDate(Date trainingEndDate) {
		this.trainingEndDate = trainingEndDate;
	}

	public String getNeeding() {
		return needing;
	}

	public void setNeeding(String needing) {
		this.needing = needing;
	}

	public String getTrainingObject() {
		return trainingObject;
	}

	public void setTrainingObject(String trainingObject) {
		this.trainingObject = trainingObject;
	}

	public String getTrainingPeopleNumber() {
		return trainingPeopleNumber;
	}

	public void setTrainingPeopleNumber(String trainingPeopleNumber) {
		this.trainingPeopleNumber = trainingPeopleNumber;
	}

	public String getExpensesBudget() {
		return expensesBudget;
	}

	public void setExpensesBudget(String expensesBudget) {
		this.expensesBudget = expensesBudget;
	}

	public String getLeadings() {
		return leadings;
	}

	public void setLeadings(String leadings) {
		this.leadings = leadings;
	}

	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getTrainingInstitutions() {
		return trainingInstitutions;
	}

	public void setTrainingInstitutions(String trainingInstitutions) {
		this.trainingInstitutions = trainingInstitutions;
	}

	public String getTrainingSite() {
		return trainingSite;
	}

	public void setTrainingSite(String trainingSite) {
		this.trainingSite = trainingSite;
	}

	public String getTrainingHours() {
		return trainingHours;
	}

	public void setTrainingHours(String trainingHours) {
		this.trainingHours = trainingHours;
	}

}
