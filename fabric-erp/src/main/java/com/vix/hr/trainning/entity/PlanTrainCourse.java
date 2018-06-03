package com.vix.hr.trainning.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * 
 * @Description:计划培训课程
 * @author bobchen
 * @date 2015-9-16 下午4:20:52
 */
public class PlanTrainCourse extends BaseEntity {

	private static final long serialVersionUID = 2085894080323735142L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 计划学时 */
	private String planHours;
	/** 每次课程费用 */
	private String courseFees;
	/** 举办次数 */
	private String holdNumber;
	/** 负责人 */
	private String leadings;
	/** 计划开始日期 */
	private Date planStartDate;
	/** 计划结束日期 */
	private Date planEndDate;
	/** 备注 */
	private String remarks;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;

	/** 培训课程 */
	private Set<Plan> plan = new HashSet<Plan>();

	public Set<Plan> getPlan() {
		return plan;
	}

	public void setPlan(Set<Plan> plan) {
		this.plan = plan;
	}

	public String getPlanHours() {
		return planHours;
	}

	public void setPlanHours(String planHours) {
		this.planHours = planHours;
	}

	public String getLeadings() {
		return leadings;
	}

	public void setLeadings(String leadings) {
		this.leadings = leadings;
	}

	public String getCourseFees() {
		return courseFees;
	}

	public void setCourseFees(String courseFees) {
		this.courseFees = courseFees;
	}

	public String getHoldNumber() {
		return holdNumber;
	}

	public void setHoldNumber(String holdNumber) {
		this.holdNumber = holdNumber;
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

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

}
