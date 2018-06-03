/**
 * 
 */
package com.vix.nvix.oa.attendance.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 关联人员班次
 * 
 * @类全名 com.vix.nvix.oa.attendance.entity.EmpAndClass
 *
 * @author zhanghaibing
 *
 * @date 2016年7月13日
 */
public class EmpAndClass extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 员工Id
	 */
	private String empId;
	/**
	 * 班次Id
	 */
	private String classId;

	/**
	 * 是否启用
	 * 
	 * @return
	 */
	public boolean isEnable() {
		long timeMill = System.currentTimeMillis();
		if (timeMill >= this.getStartTime().getTime() && timeMill <= this.getEndTime().getTime()) {
			return true;
		}
		return false;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

}
