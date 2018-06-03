/**
 * 
 */
package com.vix.nvix.oa.bo;

/**
 * 
 * @类全名 com.vix.nvix.oa.bo.WorkLogStatisticsBo
 *
 * @author zhanghaibing
 *
 * @date 2017年2月3日
 */
public class WorkLogStatisticsBo {
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
}