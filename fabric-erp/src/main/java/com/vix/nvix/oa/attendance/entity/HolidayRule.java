/**
 * 
 */
package com.vix.nvix.oa.attendance.entity;

import java.util.Date;

import com.vix.common.properties.Utils;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * @author Bluesnow 2016年7月19日
 * 
 *         假日管理
 */
public class HolidayRule extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 假日日期 */
	private Date holidayTime;
	/** 是否启用 */
	private Integer isEnable;
	/** 假日开始时间 */
	private Date holidayStartTime;
	/** 假日结束时间 */
	private Date holidayEndTime;

	public Date getHolidayStartTime() {
		return holidayStartTime;
	}
	
	public String getHolidayStartTimeStr(){
		if(Utils.isNotEmpty(holidayStartTime)){
			return DateUtil.formatTime(holidayStartTime);
		}
		return "";
	}
	public String getHolidayEndTimeStr(){
		if(Utils.isNotEmpty(holidayEndTime)){
			return DateUtil.formatTime(holidayEndTime);
		}
		return "";
	}

	public void setHolidayStartTime(Date holidayStartTime) {
		this.holidayStartTime = holidayStartTime;
	}

	public Date getHolidayEndTime() {
		return holidayEndTime;
	}

	public void setHolidayEndTime(Date holidayEndTime) {
		this.holidayEndTime = holidayEndTime;
	}

	public Date getHolidayTime() {
		return holidayTime;
	}

	public void setHolidayTime(Date holidayTime) {
		this.holidayTime = holidayTime;
	}

	public String getHolidayTimeStr() {
		if (Utils.isNotEmpty(holidayTime)) {
			return DateUtil.format(holidayTime);
		}
		return "";
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

}
