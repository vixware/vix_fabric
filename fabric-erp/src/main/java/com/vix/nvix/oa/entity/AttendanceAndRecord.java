/**
 * 
 */
package com.vix.nvix.oa.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * @author Bluesnow 2016年5月30日
 * 
 *         考勤规则
 */
public class AttendanceAndRecord extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 考勤时间 */
	private Date attendanceTime;
	/** 考勤坐标east */
	private String east;
	/** 考勤坐标west */
	private String west;
	/** 考勤坐标south */
	private String south;
	/** 考勤坐标north */
	private String north;

	public Date getAttendanceTime() {
		return attendanceTime;
	}

	public String getAttendanceTimeStr() {
		if (null != attendanceTime) {
			return DateUtil.format(attendanceTime);
		}
		return "";
	}

	public void setAttendanceTime(Date attendanceTime) {
		this.attendanceTime = attendanceTime;
	}

	public String getEast() {
		return east;
	}

	public void setEast(String east) {
		this.east = east;
	}

	public String getWest() {
		return west;
	}

	public void setWest(String west) {
		this.west = west;
	}

	public String getSouth() {
		return south;
	}

	public void setSouth(String south) {
		this.south = south;
	}

	public String getNorth() {
		return north;
	}

	public void setNorth(String north) {
		this.north = north;
	}

}
