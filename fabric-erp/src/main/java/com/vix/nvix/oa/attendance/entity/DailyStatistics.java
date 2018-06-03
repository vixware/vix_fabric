/**
 * 
 */
package com.vix.nvix.oa.attendance.entity;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * @author Bluesnow 2016年7月8日
 * 
 *         日考勤统计
 * 
 */
/**
 * @类全名 com.vix.nvix.oa.attendance.entity.DailyStatistics
 *
 * @author zhanghaibing
 *
 * @date 2016年7月15日
 */
public class DailyStatistics extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 考勤唯一标示
	private String empdateCode;
	/** 考勤日期 */
	private String dayAndMonth;
	/** 员工编号 */
	private String recordNum;
	/** 折合天数 */
	private Double discountDay;
	/** 累计工作时间 */
	private Double accumulativeWorkHours;
	/** 累计请假时间 */
	private Integer accumulativeLeaveHours;
	/** 累计加班时间 */
	private Long accumulativeOverTimeHours;
	/** 累计出差时间 */
	private Integer accumulativeEvectionHours;
	/** 迟到时间 */
	private Long lateTime;
	/** 早退时间 */
	private Long earlyTime;
	/** 职员 */
	private Employee emp;
	/**
	 * 职员姓名
	 */
	private String employeeName;
	/**
	 * 上班时间
	 */
	private String startWorkTime;
	/**
	 * 下班时间
	 */
	private String endWorkTime;
	/**
	 * 上班打卡时间
	 */
	private String startcard;
	/**
	 * 下班打卡时间
	 */
	private String endcard;
	/**
	 * 上班状态 0 正常 1 迟到 2 未刷 3 刷卡超时 4 加班
	 */
	private String startWorkStatus;
	/**
	 * 下班状态 0 正常 1 早退 2 未刷 3 刷卡超时 4 加班
	 */
	private String endWorkStatus;
	/**
	 * 出勤状态 0 正常,1 缺勤,2 请假,3 其他
	 */
	private String attendanceStatus;
	/**
	 * 假期类型
	 */
	private String vacationType;

	public String getDayAndMonth() {
		return dayAndMonth;
	}

	public void setDayAndMonth(String dayAndMonth) {
		this.dayAndMonth = dayAndMonth;
	}

	public String getRecordNum() {
		return recordNum;
	}

	public String getStartCardStr() {
		if (StringUtils.isNotEmpty(startcard)) {
			return startcard.substring(11);
		}
		return "";
	}

	public Double getDiscountDay() {
		return discountDay;
	}

	public void setDiscountDay(Double discountDay) {
		this.discountDay = discountDay;
	}

	public String getVacationType() {
		return vacationType;
	}

	public void setVacationType(String vacationType) {
		this.vacationType = vacationType;
	}

	public String getEndCardStr() {
		if (StringUtils.isNotEmpty(endcard)) {
			return endcard.substring(11);
		}
		return "";
	}

	public void setRecordNum(String recordNum) {
		this.recordNum = recordNum;
	}

	public Double getAccumulativeWorkHours() {
		return accumulativeWorkHours;
	}

	public void setAccumulativeWorkHours(Double accumulativeWorkHours) {
		this.accumulativeWorkHours = accumulativeWorkHours;
	}

	public Integer getAccumulativeLeaveHours() {
		return accumulativeLeaveHours;
	}

	public void setAccumulativeLeaveHours(Integer accumulativeLeaveHours) {
		this.accumulativeLeaveHours = accumulativeLeaveHours;
	}

	public Long getAccumulativeOverTimeHours() {
		return accumulativeOverTimeHours;
	}

	public void setAccumulativeOverTimeHours(Long accumulativeOverTimeHours) {
		this.accumulativeOverTimeHours = accumulativeOverTimeHours;
	}

	public Integer getAccumulativeEvectionHours() {
		return accumulativeEvectionHours;
	}

	public void setAccumulativeEvectionHours(Integer accumulativeEvectionHours) {
		this.accumulativeEvectionHours = accumulativeEvectionHours;
	}

	public Long getLateTime() {
		return lateTime;
	}

	public void setLateTime(Long lateTime) {
		this.lateTime = lateTime;
	}

	public Long getEarlyTime() {
		return earlyTime;
	}

	public void setEarlyTime(Long earlyTime) {
		this.earlyTime = earlyTime;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public String getStartWorkTime() {
		return startWorkTime;
	}

	public void setStartWorkTime(String startWorkTime) {
		this.startWorkTime = startWorkTime;
	}

	public String getEndWorkTime() {
		return endWorkTime;
	}

	public void setEndWorkTime(String endWorkTime) {
		this.endWorkTime = endWorkTime;
	}

	public String getStartcard() {
		return startcard;
	}

	public void setStartcard(String startcard) {
		this.startcard = startcard;
	}

	public String getEndcard() {
		return endcard;
	}

	public void setEndcard(String endcard) {
		this.endcard = endcard;
	}

	public String getStartWorkStatus() {
		return startWorkStatus;
	}

	public void setStartWorkStatus(String startWorkStatus) {
		this.startWorkStatus = startWorkStatus;
	}

	public String getEndWorkStatus() {
		return endWorkStatus;
	}

	public void setEndWorkStatus(String endWorkStatus) {
		this.endWorkStatus = endWorkStatus;
	}

	public String getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	public String getEmpdateCode() {
		return empdateCode;
	}

	public void setEmpdateCode(String empdateCode) {
		this.empdateCode = empdateCode;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

}
