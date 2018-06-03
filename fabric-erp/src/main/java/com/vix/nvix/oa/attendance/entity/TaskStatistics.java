/**
 * 
 */
package com.vix.nvix.oa.attendance.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 任务统计
 * 
 * @类全名 com.vix.nvix.oa.attendance.entity.TaskStatistics
 *
 * @author zhanghaibing
 *
 * @date 2016年8月3日
 */
public class TaskStatistics extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 月份人员编码组合
	 */
	private String empcodeAndDate;
	/**
	 * 月份
	 */
	private String datetemp;

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
	/**
	 * 超时任务
	 */
	private Double overtimeTaskNum;
	/**
	 * 类型
	 */
	private String syncTag;

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

	public String getSyncTag() {
		return syncTag;
	}

	public void setSyncTag(String syncTag) {
		this.syncTag = syncTag;
	}
}