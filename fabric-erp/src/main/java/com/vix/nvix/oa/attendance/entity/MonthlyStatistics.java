/**
 * 
 */
package com.vix.nvix.oa.attendance.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * @author Bluesnow 2016年7月8日
 * 
 *         月考勤统计
 */
public class MonthlyStatistics extends BaseEntity {

	private static final long serialVersionUID = 1L;
	// 考勤唯一标示
	private String empdateCode;
	/**
	 * 打卡次数
	 */
	private Double cardNum;
	/**
	 * 应到次数
	 */
	private Double workDays;
	/** 年月 */
	private String dayAndMonth;
	/** 记录编号 */
	private String recordNum;
	/** 累计工作时间 */
	private Double accumulativeWorkHours;
	/** 累计请假时间 */
	private Double accumulativeLeaveHours;
	/** 累计加班时间 */
	private Double accumulativeOverTimeHours;
	/** 累计出差时间 */
	private Double accumulativeEvectionHours;
	/** 迟到次数 */
	private Double lateTimes;
	/** 早退次数 */
	private Double earlyTimes;
	/**
	 * 旷工次数
	 */
	private Double absenteeismNum;
	/** 职员 */
	private Employee emp;
	/**
	 * 员工姓名
	 */
	private String empName;
	/**
	 * 事假
	 */
	private Double affairAbsence;
	/**
	 * 病假
	 */
	private Double sickLeave;
	/**
	 * 市内出差4
	 */
	public Double cityTravel;
	/**
	 * 外地出差5
	 */
	public Double townonBusiness;
	/**
	 * 换休6
	 */
	public Double changeTheDayOff;
	/**
	 * 婚假9
	 */
	public Double marriageleave;
	/**
	 * 丧假10
	 */
	public Double funeralLeave;
	/**
	 * 产假11
	 */
	public Double maternityLeave;
	/**
	 * 哺乳假12
	 */
	public Double breastfeedingLeave;
	/**
	 * 产后长假13
	 */
	public Double postPartum;
	/**
	 * 计划生育假14
	 */
	public Double familyPlanning;
	/**
	 * 护理假15
	 */
	public Double nursingLeave;
	/**
	 * 探亲假16
	 */
	public Double homeLeave;
	/**
	 * 年休假21
	 */
	public Double annualLeave;
	/**
	 * 补休假22
	 */
	public Double takedeferredholiDays;
	/**
	 * 产前假23
	 */
	public Double antenatal;
	/**
	 * 加班 47
	 */
	public Double overtime;

	public String getRecordNum() {
		return recordNum;
	}

	public void setRecordNum(String recordNum) {
		this.recordNum = recordNum;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Double getCardNum() {
		return cardNum;
	}

	public void setCardNum(Double cardNum) {
		this.cardNum = cardNum;
	}

	public String getEmpdateCode() {
		return empdateCode;
	}

	public void setEmpdateCode(String empdateCode) {
		this.empdateCode = empdateCode;
	}

	public Double getAbsenteeismNum() {
		return absenteeismNum;
	}

	public void setAbsenteeismNum(Double absenteeismNum) {
		this.absenteeismNum = absenteeismNum;
	}

	public String getDayAndMonth() {
		return dayAndMonth;
	}

	public void setDayAndMonth(String dayAndMonth) {
		this.dayAndMonth = dayAndMonth;
	}

	public Double getAccumulativeWorkHours() {
		return accumulativeWorkHours;
	}

	public void setAccumulativeWorkHours(Double accumulativeWorkHours) {
		this.accumulativeWorkHours = accumulativeWorkHours;
	}

	public Double getAccumulativeLeaveHours() {
		return accumulativeLeaveHours;
	}

	public void setAccumulativeLeaveHours(Double accumulativeLeaveHours) {
		this.accumulativeLeaveHours = accumulativeLeaveHours;
	}

	public Double getAccumulativeOverTimeHours() {
		return accumulativeOverTimeHours;
	}

	public void setAccumulativeOverTimeHours(Double accumulativeOverTimeHours) {
		this.accumulativeOverTimeHours = accumulativeOverTimeHours;
	}

	public Double getAccumulativeEvectionHours() {
		return accumulativeEvectionHours;
	}

	public void setAccumulativeEvectionHours(Double accumulativeEvectionHours) {
		this.accumulativeEvectionHours = accumulativeEvectionHours;
	}

	public Double getLateTimes() {
		return lateTimes;
	}

	public void setLateTimes(Double lateTimes) {
		this.lateTimes = lateTimes;
	}

	public Double getEarlyTimes() {
		return earlyTimes;
	}

	public void setEarlyTimes(Double earlyTimes) {
		this.earlyTimes = earlyTimes;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public Double getAffairAbsence() {
		return affairAbsence;
	}

	public void setAffairAbsence(Double affairAbsence) {
		this.affairAbsence = affairAbsence;
	}

	public Double getSickLeave() {
		return sickLeave;
	}

	public void setSickLeave(Double sickLeave) {
		this.sickLeave = sickLeave;
	}

	public Double getWorkDays() {
		return workDays;
	}

	public void setWorkDays(Double workDays) {
		this.workDays = workDays;
	}

}
