/**
 * 
 */
package com.vix.nvix.oa.attendance.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @author Bluesnow 2016年7月8日
 * 
 *         计算规则
 */
public class CalculationRule extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 工时计算取整 */
	private Integer workingHoursTrunc;
	/** 工时计算类型 */
	private Integer workHoursCalculationType;
	/** 加班计算取整 */
	private Integer overTimeTrunc;
	/** 加班计算类型 */
	private Integer overTimeCalculationType;
	/** 请假计算取整 */
	private Integer leaveTrunc;
	/** 请假计算类型 */
	private Integer leaveCalculationType;
	/** 迟到早退取整 */
	private Integer lateTrunc;
	/** 迟到早退计算精确到秒 */
	private Integer precision;
	/** 计算保留小数 */
	private Integer keepDecimals;
	/** 上班开始时间1 */
	private Integer workingStartTimeOne;
	/** 上班开始时间2 */
	private Integer workingStartTimeTwo;
	/** 工时以排班或当天较大的为准 */
	private Integer maxNum;
	/** 时段工时1 */
	private Integer man_hour_One;
	/** 时段工时2 */
	private Integer man_hour_Two;
	/** 按每个班段手工设定的值计算天数 */
	private Integer manually;

	public Integer getWorkingHoursTrunc() {
		return workingHoursTrunc;
	}

	public void setWorkingHoursTrunc(Integer workingHoursTrunc) {
		this.workingHoursTrunc = workingHoursTrunc;
	}

	public Integer getWorkHoursCalculationType() {
		return workHoursCalculationType;
	}

	public void setWorkHoursCalculationType(Integer workHoursCalculationType) {
		this.workHoursCalculationType = workHoursCalculationType;
	}

	public Integer getOverTimeTrunc() {
		return overTimeTrunc;
	}

	public void setOverTimeTrunc(Integer overTimeTrunc) {
		this.overTimeTrunc = overTimeTrunc;
	}

	public Integer getOverTimeCalculationType() {
		return overTimeCalculationType;
	}

	public void setOverTimeCalculationType(Integer overTimeCalculationType) {
		this.overTimeCalculationType = overTimeCalculationType;
	}

	public Integer getLeaveTrunc() {
		return leaveTrunc;
	}

	public void setLeaveTrunc(Integer leaveTrunc) {
		this.leaveTrunc = leaveTrunc;
	}

	public Integer getLeaveCalculationType() {
		return leaveCalculationType;
	}

	public void setLeaveCalculationType(Integer leaveCalculationType) {
		this.leaveCalculationType = leaveCalculationType;
	}

	public Integer getLateTrunc() {
		return lateTrunc;
	}

	public void setLateTrunc(Integer lateTrunc) {
		this.lateTrunc = lateTrunc;
	}

	public Integer getPrecision() {
		return precision;
	}

	public void setPrecision(Integer precision) {
		this.precision = precision;
	}

	public Integer getKeepDecimals() {
		return keepDecimals;
	}

	public void setKeepDecimals(Integer keepDecimals) {
		this.keepDecimals = keepDecimals;
	}

	public Integer getWorkingStartTimeOne() {
		return workingStartTimeOne;
	}

	public void setWorkingStartTimeOne(Integer workingStartTimeOne) {
		this.workingStartTimeOne = workingStartTimeOne;
	}

	public Integer getWorkingStartTimeTwo() {
		return workingStartTimeTwo;
	}

	public void setWorkingStartTimeTwo(Integer workingStartTimeTwo) {
		this.workingStartTimeTwo = workingStartTimeTwo;
	}

	public Integer getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}

	public Integer getMan_hour_One() {
		return man_hour_One;
	}

	public void setMan_hour_One(Integer man_hour_One) {
		this.man_hour_One = man_hour_One;
	}

	public Integer getMan_hour_Two() {
		return man_hour_Two;
	}

	public void setMan_hour_Two(Integer man_hour_Two) {
		this.man_hour_Two = man_hour_Two;
	}

	public Integer getManually() {
		return manually;
	}

	public void setManually(Integer manually) {
		this.manually = manually;
	}

}
