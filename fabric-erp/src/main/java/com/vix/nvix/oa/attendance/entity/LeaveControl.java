/**
 * 
 */
package com.vix.nvix.oa.attendance.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @author Bluesnow 2016年7月8日
 * 
 *         请假管控
 */
public class LeaveControl extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 请假 */
	private Leave leave;
	/** 工时计算比率 */
	private Integer rate;
	/** 是否禁用 */
	private Integer isDisable;
	/** 是否累计到总请假天数 */
	private Integer isAccumulativeTotal;
	/** 启用请假管控 */
	private Integer isEnabelLeaveControl;
	/** 累计上期剩余允许请假数到本周 */
	private Integer cumulativeSurplusToThisWeek;
	/** 请假不计周休时间 */
	private Integer weeksOff;
	/** 请假不计假日时间 */
	private Integer noHoliday;
	/** 按天计算 */
	private Integer calculatedByDay;
	/** 按时计算 */
	private Integer calculatedOnTime;

	public Leave getLeave() {
		return leave;
	}

	public void setLeave(Leave leave) {
		this.leave = leave;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Integer getIsDisable() {
		return isDisable;
	}

	public void setIsDisable(Integer isDisable) {
		this.isDisable = isDisable;
	}

	public Integer getIsAccumulativeTotal() {
		return isAccumulativeTotal;
	}

	public void setIsAccumulativeTotal(Integer isAccumulativeTotal) {
		this.isAccumulativeTotal = isAccumulativeTotal;
	}

	public Integer getIsEnabelLeaveControl() {
		return isEnabelLeaveControl;
	}

	public void setIsEnabelLeaveControl(Integer isEnabelLeaveControl) {
		this.isEnabelLeaveControl = isEnabelLeaveControl;
	}

	public Integer getCumulativeSurplusToThisWeek() {
		return cumulativeSurplusToThisWeek;
	}

	public void setCumulativeSurplusToThisWeek(
			Integer cumulativeSurplusToThisWeek) {
		this.cumulativeSurplusToThisWeek = cumulativeSurplusToThisWeek;
	}

	public Integer getWeeksOff() {
		return weeksOff;
	}

	public void setWeeksOff(Integer weeksOff) {
		this.weeksOff = weeksOff;
	}

	public Integer getNoHoliday() {
		return noHoliday;
	}

	public void setNoHoliday(Integer noHoliday) {
		this.noHoliday = noHoliday;
	}

	public Integer getCalculatedByDay() {
		return calculatedByDay;
	}

	public void setCalculatedByDay(Integer calculatedByDay) {
		this.calculatedByDay = calculatedByDay;
	}

	public Integer getCalculatedOnTime() {
		return calculatedOnTime;
	}

	public void setCalculatedOnTime(Integer calculatedOnTime) {
		this.calculatedOnTime = calculatedOnTime;
	}

}
