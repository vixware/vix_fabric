/**
 * 
 */
package com.vix.nvix.oa.bo;

/**
 * 
 * @类全名 com.vix.nvix.oa.bo.TaskStatisticsBo
 *
 * @author zhanghaibing
 *
 * @date 2016年8月3日
 */
public class TaskStatisticsBo {

	/**
	 * 月份人员编码组合
	 */
	private String empcodeAndDate;

	/**
	 * 人员姓名
	 */
	private String empName;
	/**
	 * 任务数量
	 */
	private Double taskNum;
	/**
	 * 已完成的任务
	 */
	private Double finishTaskNum;
	/**
	 * 超时任务
	 */
	private Double overtimeTaskNum;
	/**
	 * 进行中的问题
	 */
	private Double progressTaskNum;
	/**
	 * 未开始的任务
	 */
	private Double noStartTaskNum;
	/**
	 * 员工ID
	 */
	private String empId;

	private String datetemp;

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

	public Double getTaskNum() {
		return taskNum;
	}

	public void setTaskNum(Double taskNum) {
		this.taskNum = taskNum;
	}

	public Double getFinishTaskNum() {
		return finishTaskNum;
	}

	public void setFinishTaskNum(Double finishTaskNum) {
		this.finishTaskNum = finishTaskNum;
	}

	public Double getProgressTaskNum() {
		return progressTaskNum;
	}

	public void setProgressTaskNum(Double progressTaskNum) {
		this.progressTaskNum = progressTaskNum;
	}

	public Double getNoStartTaskNum() {
		return noStartTaskNum;
	}

	public void setNoStartTaskNum(Double noStartTaskNum) {
		this.noStartTaskNum = noStartTaskNum;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public Double getOvertimeTaskNum() {
		return overtimeTaskNum;
	}

	public void setOvertimeTaskNum(Double overtimeTaskNum) {
		this.overtimeTaskNum = overtimeTaskNum;
	}

	public String getDatetemp() {
		return datetemp;
	}

	public void setDatetemp(String datetemp) {
		this.datetemp = datetemp;
	}

}