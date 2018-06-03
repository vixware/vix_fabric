/**
 * 
 */
package com.vix.nvix.oa.attendance.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.oa.attendance.entity.AllWorkLogStatistics
 *
 * @author zhanghaibing
 *
 * @date 2017年2月9日
 */
public class AllWorkLogStatistics extends BaseEntity {

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
	 * 员工ID
	 */
	private String empId;

	private Double num;

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

	public String getDatetemp() {
		return datetemp;
	}

	public void setDatetemp(String datetemp) {
		this.datetemp = datetemp;
	}

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

}