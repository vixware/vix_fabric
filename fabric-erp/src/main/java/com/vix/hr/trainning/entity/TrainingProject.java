package com.vix.hr.trainning.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * 
 * @Description:培训需求-培训规划
 * @author luchuan
 * @date 2018-4-9 上午11:08:33
 */
public class TrainingProject extends BaseEntity {

	private static final long serialVersionUID = -7634079115193989315L;
	
	/** 规划名称*/
	private String projectName;
	/** 所属部门*/
	private String org;
	/** 拟培训时间*/
	private Date projectDate;
	/** 有效开始日期 */
	private Date effectiveStartDate;
	/** 有效结束日期 */
	private Date effectiveEndDate;
	/** 课程成本权重*/
	private String costWeight;
	/** 规划总费用 */
	private String courseFees;
	/** 所有课程总费用*/
	private String cost;
	/** 备注 */
	private String remarkss;
	/** 发布人id */
	private String uploadPersonId;
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;
	/** 培训课程*/
    private String courses;

	public String getRemarkss() {
		return remarkss;
	}

	public void setRemarkss(String remarkss) {
		this.remarkss = remarkss;
	}

	public String getCourseFees() {
		return courseFees;
	}

	public void setCourseFees(String courseFees) {
		this.courseFees = courseFees;
	}

	public Date getEffectiveStartDate() {
		return effectiveStartDate;
	}
    
	public String getEffectiveStartDateStr() {
		if (null != effectiveStartDate) {
			return DateUtil.format(effectiveStartDate);
		}
		return "";
	}
	
	public void setEffectiveStartDate(Date effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	public Date getEffectiveEndDate() {
		return effectiveEndDate;
	}
    
	public String getEffectiveEndDateStr() {
		if (null != effectiveEndDate) {
			return DateUtil.format(effectiveEndDate);
		}
		return "";
	}
		
	public void setEffectiveEndDate(Date effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public Date getProjectDate() {
		return projectDate;
	}
    
	public String getProjectDateStr() {
		if (null != projectDate) {
			return DateUtil.format(projectDate);
		}
		return "";
	}
	
	public void setProjectDate(Date projectDate) {
		this.projectDate = projectDate;
	}

	public String getCostWeight() {
		return costWeight;
	}

	public void setCostWeight(String costWeight) {
		this.costWeight = costWeight;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getCourses() {
		return courses;
	}

	public void setCourses(String courses) {
		this.courses = courses;
	}
	
}
