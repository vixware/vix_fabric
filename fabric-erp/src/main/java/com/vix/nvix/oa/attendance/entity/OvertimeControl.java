/**
 * 
 */
package com.vix.nvix.oa.attendance.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @author Bluesnow 2016年7月8日
 * 
 *         加班管控
 * 
 *         OT(overTime 加班)
 */
public class OvertimeControl extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 开始日期 */
	private Integer dailyStartTime;
	/** 结束日期 */
	private Integer dailyEndTime;
	/** 标准平时加班 */
	private Integer standardOT;
	/** 标准周末加班 */
	private Integer standardWeekendOT;
	/** 标准节日加班 */
	private Integer standardHolidayOT;
	/** 平时已加班 */
	private Double dailyOT;
	/** 周末已加班 */
	private Double weekendOT;
	/** 节日已加班 */
	private Double holidayOT;

	public Integer getDailyStartTime() {
		return dailyStartTime;
	}

	public void setDailyStartTime(Integer dailyStartTime) {
		this.dailyStartTime = dailyStartTime;
	}

	public Integer getDailyEndTime() {
		return dailyEndTime;
	}

	public void setDailyEndTime(Integer dailyEndTime) {
		this.dailyEndTime = dailyEndTime;
	}

	public Integer getStandardOT() {
		return standardOT;
	}

	public void setStandardOT(Integer standardOT) {
		this.standardOT = standardOT;
	}

	public Integer getStandardWeekendOT() {
		return standardWeekendOT;
	}

	public void setStandardWeekendOT(Integer standardWeekendOT) {
		this.standardWeekendOT = standardWeekendOT;
	}

	public Integer getStandardHolidayOT() {
		return standardHolidayOT;
	}

	public void setStandardHolidayOT(Integer standardHolidayOT) {
		this.standardHolidayOT = standardHolidayOT;
	}

	public Double getDailyOT() {
		return dailyOT;
	}

	public void setDailyOT(Double dailyOT) {
		this.dailyOT = dailyOT;
	}

	public Double getWeekendOT() {
		return weekendOT;
	}

	public void setWeekendOT(Double weekendOT) {
		this.weekendOT = weekendOT;
	}

	public Double getHolidayOT() {
		return holidayOT;
	}

	public void setHolidayOT(Double holidayOT) {
		this.holidayOT = holidayOT;
	}

}
