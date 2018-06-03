/**
 * 
 */
package com.vix.nvix.oa.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.hr.organization.entity.Employee;

/**
 * @author Bluesnow
 * 2016年5月30日
 * 
 * 考勤人员关系
 */
public class AttendanceRelation extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 考勤人员*/
	private Employee emp;
	/** 考勤类型*/
	private AttendanceRule attRule;
	/** 考勤时间*/
	private Date attendanceTime;
	
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
}
