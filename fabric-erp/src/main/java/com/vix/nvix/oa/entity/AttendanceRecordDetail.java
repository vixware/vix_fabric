/**
 * 
 */
package com.vix.nvix.oa.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * @author Bluesnow
 * 2016年5月30日
 * 
 * 考勤记录明细
 */
public class AttendanceRecordDetail extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 是否签到 
	 * 
	 *  0 ： 未签到   1 ： 签到   2 ： 签退   3 ：迟到   4 ：早退
	 *  
	 */
	
	/** 考勤主体*/
	private AttendanceAndClock attClock;
	/** 是否已签到*/
	private String isAttendance;
	/** 签到类型*/
	private AttendanceRule attRule;
	/** 签到时间*/
	private Date attendanceTime;
	/** 签到坐标 经度*/
	private String longitude;
	/** 签到坐标 纬度*/
	private String latitude;
	/** 考勤关系*/
	private AttendanceRelation attRelation;
	/** 签到地址*/
	private String attAddress;
	/** 微信号*/
	private String wxCode;
	/** 签到或签退*/
	private String sign;
	
	public String getIsAttendance() {
		return isAttendance;
	}
	public void setIsAttendance(String isAttendance) {
		this.isAttendance = isAttendance;
	}
	public AttendanceRule getAttRule() {
		return attRule;
	}
	public void setAttRule(AttendanceRule attRule) {
		this.attRule = attRule;
	}
	public Date getAttendanceTime() {
		return attendanceTime;
	}
	
	public String getAttendanceTimeStr(){
		if(null != attendanceTime){
			return DateUtil.format(attendanceTime);
		}
		return "";
	}
	
	public void setAttendanceTime(Date attendanceTime) {
		this.attendanceTime = attendanceTime;
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
	public AttendanceRelation getAttRelation() {
		return attRelation;
	}
	public void setAttRelation(AttendanceRelation attRelation) {
		this.attRelation = attRelation;
	}
	public String getAttAddress() {
		return attAddress;
	}
	public void setAttAddress(String attAddress) {
		this.attAddress = attAddress;
	}
	public AttendanceAndClock getAttClock() {
		return attClock;
	}
	public void setAttClock(AttendanceAndClock attClock) {
		this.attClock = attClock;
	}
	public String getWxCode() {
		return wxCode;
	}
	public void setWxCode(String wxCode) {
		this.wxCode = wxCode;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
}
