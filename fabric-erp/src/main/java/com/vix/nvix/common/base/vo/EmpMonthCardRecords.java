/**
 * 
 */
package com.vix.nvix.common.base.vo;

/**
 * 
 * @类全名 com.vix.nvix.common.base.vo.EmpMonthCardRecords
 *
 * @author zhanghaibing
 *
 * @date 2016年7月18日
 */
public class EmpMonthCardRecords {
	/**
	 * 员工编码
	 */
	private String userCode;
	/**
	 * 当月时间
	 */
	private String datetemp;
	/**
	 * 打卡次数
	 */
	private Double cardNum;
	/** 累计工作时间 */
	private Double accumulativeWorkHours;
	/** 累计请假时间 */
	private Double accumulativeLeaveHours;
	/** 累计加班时间 */
	private Double accumulativeOverTimeHours;
	/** 累计出差时间 */
	private Double accumulativeEvectionHours;
	/** 迟到时间 */
	private Double lateTime;
	/** 早退时间 */
	private Double earlyTime;
	/**
	 * 旷工次数
	 */
	private Double absenteeismNum;
	/**
	 * 事假
	 */
	private Double affairAbsence;
	/**
	 * 病假
	 */
	private Double sickLeave;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getDatetemp() {
		return datetemp;
	}

	public void setDatetemp(String datetemp) {
		this.datetemp = datetemp;
	}

	public Double getCardNum() {
		return cardNum;
	}

	public void setCardNum(Double cardNum) {
		this.cardNum = cardNum;
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

	public Double getLateTime() {
		return lateTime;
	}

	public void setLateTime(Double lateTime) {
		this.lateTime = lateTime;
	}

	public Double getEarlyTime() {
		return earlyTime;
	}

	public void setEarlyTime(Double earlyTime) {
		this.earlyTime = earlyTime;
	}

	public Double getAbsenteeismNum() {
		return absenteeismNum;
	}

	public void setAbsenteeismNum(Double absenteeismNum) {
		this.absenteeismNum = absenteeismNum;
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

}
