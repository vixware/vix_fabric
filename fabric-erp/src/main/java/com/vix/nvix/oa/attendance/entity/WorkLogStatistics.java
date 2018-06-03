/**
 * 
 */
package com.vix.nvix.oa.attendance.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 日报统计
 * 
 * @类全名 com.vix.nvix.oa.attendance.entity.WorkLogStatistics
 *
 * @author zhanghaibing
 *
 * @date 2017年2月3日
 */
/**
 * @类全名 com.vix.nvix.oa.attendance.entity.WorkLogStatistics
 *
 * @author zhanghaibing
 *
 * @date 2017年2月4日
 */
public class WorkLogStatistics extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 日期人员编码组合
	 */
	private String empcodeAndDate;
	/**
	 * 日期
	 */
	private String datetemp;
	/**
	 * 人员姓名
	 */
	private String empName;
	/**
	 * 是否编写
	 */
	private String isCreate;
	/**
	 * 员工ID
	 */
	private String empId;
	/**
	 * 星期
	 */
	private String weekDate;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpcodeAndDate() {
		return empcodeAndDate;
	}

	public void setEmpcodeAndDate(String empcodeAndDate) {
		this.empcodeAndDate = empcodeAndDate;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getIsCreate() {
		return isCreate;
	}

	public void setIsCreate(String isCreate) {
		this.isCreate = isCreate;
	}

	public String getDatetemp() {
		return datetemp;
	}

	public void setDatetemp(String datetemp) {
		this.datetemp = datetemp;
	}

	public String getWeekDate() {
		return weekDate;
	}

	public void setWeekDate(String weekDate) {
		this.weekDate = weekDate;
	}

}