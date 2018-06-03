/**
 * 
 */
package com.vix.nvix.oa.attendance.entity;

import java.util.Date;

import com.vix.common.properties.Utils;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * @author Bluesnow 2016年7月8日
 * 
 *         打卡（考勤记录）
 */
public class PunchCardRecord extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 当前系统日期 */
	private Date recordDate;
	/** 员工编号 */
	private String userCode;
	/** 打卡日期和时间 */
	private String punchCardDate;
	/** 签到坐标 经度 */
	private String longitude;
	/** 签到坐标 纬度 */
	private String latitude;
	/** IP地址 */
	private String ipAddress;
	/**
	 * 签到来源 0:PC 1:微信  2:刷卡(导入数据)
	 * */
	private String source;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPunchCardDate() {
		return punchCardDate;
	}

	public void setPunchCardDate(String punchCardDate) {
		this.punchCardDate = punchCardDate;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public String getRecordDateStr() {
		if (Utils.isNotEmpty(recordDate)) {
			return DateUtil.format(recordDate);
		}
		return "";
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
