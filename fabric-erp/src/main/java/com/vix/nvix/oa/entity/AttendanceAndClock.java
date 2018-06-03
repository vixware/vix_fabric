/**
 * 
 */
package com.vix.nvix.oa.entity;

import java.util.Date;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.hr.organization.entity.Employee;

/**
 * @author Bluesnow
 * 2016年5月28日
 * 
 * 考勤规则类型
 */
public class AttendanceAndClock extends BaseEntity {
	private static final long serialVersionUID = 1L;

	
	/** 人员*/
	private Employee emp;
	/** 签到类型*/
	private AttendanceRule attRule;
	/** 签到时间*/
	private Date attendanceTime;
	/** 签到规则*/
	private AttendanceAndRecord attRecord;
	/** 微信号*/
	private String wxCode;
	/** 考勤明细*/
	private Set<AttendanceRecordDetail> recordDetailSet;
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
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
	public AttendanceAndRecord getAttRecord() {
		return attRecord;
	}
	public void setAttRecord(AttendanceAndRecord attRecord) {
		this.attRecord = attRecord;
	}
	public String getWxCode() {
		return wxCode;
	}
	public void setWxCode(String wxCode) {
		this.wxCode = wxCode;
	}
	public Set<AttendanceRecordDetail> getRecordDetailSet() {
		return recordDetailSet;
	}
	public void setRecordDetailSet(Set<AttendanceRecordDetail> recordDetailSet) {
		this.recordDetailSet = recordDetailSet;
	}
	
}
