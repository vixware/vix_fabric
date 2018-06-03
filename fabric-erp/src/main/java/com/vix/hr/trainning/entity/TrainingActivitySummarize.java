package com.vix.hr.trainning.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * 
 * @Description:培训活动总结
 * @author luchuan
 * @date 2018-4-10 上午11:08:33
 */
public class TrainingActivitySummarize extends BaseEntity {

	private static final long serialVersionUID = -7634079115193989315L;
	
	/** 活动名称*/
	private String activityName;
	/** 活动状态*/
	private String activitystatus;
	/** 计划开始时间*/
	private Date planStartDate;
	/** 计划结束时间*/
	private Date planEndDate;
	/** 实际开始日期 */
	private Date effectiveStartDate;
	/** 实际结束日期 */
	private Date effectiveEndDate;
	/** 计划费用 */
	private String courseFees;
	/** 实际费用*/
	private String cost;
	/** 总结*/
	private String remarkss;
	/** 总结时间*/
	private Date summarizeDate;
	
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

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivitystatus() {
		return activitystatus;
	}

	public void setActivitystatus(String activitystatus) {
		this.activitystatus = activitystatus;
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

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public Date getSummarizeDate() {
		return summarizeDate;
	}
    
	public String getSummarizeDateStr() {
		if (null != summarizeDate) {
			return DateUtil.format(summarizeDate);
		}
		return "";
	}
	
	public void setSummarizeDate(Date summarizeDate) {
		this.summarizeDate = summarizeDate;
	}

}
