package com.vix.hr.trainning.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * 
 * @Description:培训活动
 * @author bobchen
 * @date 2015-9-16 下午4:20:52
 */
public class TrainingActivity extends BaseEntity {

	private static final long serialVersionUID = 2085894080323735142L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 活动编码 */
	private String activityCode;
	/** 活动名称 */
	private String activityName;
	/** 活动部门 */
	private String activityDepartmentOrLeadings;
	/**
	 * 培训活动部门与人员类型 "O\":\"部门\",\"E\":\"人员\"
	 */
	private String pubType;
	/** 发布对象的id集合 */
	private String pubIds;
	/** 审批人 */
	private String examinationApproval;
	/** 拟培训时间 */
	private Date trainingTime;
	/** 预算费用 */
	private String budgetExpense;
	/** 活动开始日期 */
	private Date activityStartDate;
	/** 活动结束日期 */
	private Date activityEndDate;
	/** 培训内容 */
	private String trainingContent;
	/** 培训方式 */
	public Integer trainingMethod;
	/** 考核方式 */
	public Integer examinationMethod;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;

	/** 培训课程 */
	private Set<TrainingPlanningDetail> trainingPlanningDetail = new HashSet<TrainingPlanningDetail>();

	public Set<TrainingPlanningDetail> getTrainingPlanningDetail() {
		return trainingPlanningDetail;
	}

	public void setTrainingPlanningDetail(Set<TrainingPlanningDetail> trainingPlanningDetail) {
		this.trainingPlanningDetail = trainingPlanningDetail;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public String getActivityDepartmentOrLeadings() {
		return activityDepartmentOrLeadings;
	}

	public void setActivityDepartmentOrLeadings(String activityDepartmentOrLeadings) {
		this.activityDepartmentOrLeadings = activityDepartmentOrLeadings;
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

	public String getExaminationApproval() {
		return examinationApproval;
	}

	public void setExaminationApproval(String examinationApproval) {
		this.examinationApproval = examinationApproval;
	}

	public Date getTrainingTime() {
		return trainingTime;
	}
    
	public String getTrainingTimeStr() {
		if (null != trainingTime) {
			return DateUtil.format(trainingTime);
		}
		return "";
	}
	
	public void setTrainingTime(Date trainingTime) {
		this.trainingTime = trainingTime;
	}

	public String getBudgetExpense() {
		return budgetExpense;
	}

	public void setBudgetExpense(String budgetExpense) {
		this.budgetExpense = budgetExpense;
	}

	public Date getActivityStartDate() {
		return activityStartDate;
	}
    
	public String getActivityStartDateStr() {
		if (null != activityStartDate) {
			return DateUtil.format(activityStartDate);
		}
		return "";
	}
	
	public void setActivityStartDate(Date activityStartDate) {
		this.activityStartDate = activityStartDate;
	}

	public Date getActivityEndDate() {
		return activityEndDate;
	}
    
	public String getActivityEndDateStr() {
		if (null != activityEndDate) {
			return DateUtil.format(activityEndDate);
		}
		return "";
	}
	
	public void setActivityEndDate(Date activityEndDate) {
		this.activityEndDate = activityEndDate;
	}

	public String getTrainingContent() {
		return trainingContent;
	}

	public void setTrainingContent(String trainingContent) {
		this.trainingContent = trainingContent;
	}

	public Integer getTrainingMethod() {
		return trainingMethod;
	}

	public void setTrainingMethod(Integer trainingMethod) {
		this.trainingMethod = trainingMethod;
	}

	public Integer getExaminationMethod() {
		return examinationMethod;
	}

	public void setExaminationMethod(Integer examinationMethod) {
		this.examinationMethod = examinationMethod;
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

}
