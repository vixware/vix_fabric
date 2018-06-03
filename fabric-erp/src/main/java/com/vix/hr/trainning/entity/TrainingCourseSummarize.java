package com.vix.hr.trainning.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * 
 * @Description:培训课程总结
 * @author luchuan
 * @date 2018-4-10 上午11:08:33
 */
public class TrainingCourseSummarize extends BaseEntity {

	private static final long serialVersionUID = -7634079115193989315L;
	
	/** 课程编码 */
	private String courseCode;
	/** 课程名称 */
	private String courseName;
	/** 所属培训活动*/
	private String activity;
	/** 开始日期 */
	private Date effectiveStartDate;
	/** 结束日期 */
	private Date effectiveEndDate;
	/** 课时 */
	private String needs;
	/** 费用 */
	private String courseFees;
	/** 培训渠道*/
	private String channel;
	/** 培训讲师*/
	private String teacher;
	/** 参加人数*/
	private String personNumber;
	/** 出席率*/
	private String probability;
	/** 评价成绩*/
	private String score;
	/** 通过率*/
	private String throuth;
	/** 规划名称*/
	private String projectName;
	/** 所属部门*/
	private String org;
	/** 拟培训时间*/
	private Date projectDate;
	/** 备注 */
	private String remarkss;
	
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

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getNeeds() {
		return needs;
	}

	public void setNeeds(String needs) {
		this.needs = needs;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(String personNumber) {
		this.personNumber = personNumber;
	}

	public String getProbability() {
		return probability;
	}

	public void setProbability(String probability) {
		this.probability = probability;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getThrouth() {
		return throuth;
	}

	public void setThrouth(String throuth) {
		this.throuth = throuth;
	}
	
	
}
