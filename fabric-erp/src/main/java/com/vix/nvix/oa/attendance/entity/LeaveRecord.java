/**
 * 
 */
package com.vix.nvix.oa.attendance.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/**
 * 
 * @类全名 com.vix.nvix.oa.attendance.entity.LeaveRecord
 *
 * @author zhanghaibing
 *
 * @date 2016年7月19日
 */
public class LeaveRecord extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 外出类型
	private String leaveType;

	private Employee employee;

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
