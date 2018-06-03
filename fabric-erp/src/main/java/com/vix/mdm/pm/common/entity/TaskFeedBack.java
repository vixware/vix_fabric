/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * WBS任务执行反馈
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class TaskFeedBack extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 任务编码 */
	private String taskCode;
	/** 执行人 */
	private String exectuore;
	/** 反馈内容 */
	private Date content;
	/** 任务执行进度 */
	private Float taskProcess;

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getExectuore() {
		return exectuore;
	}

	public void setExectuore(String exectuore) {
		this.exectuore = exectuore;
	}

	public Date getContent() {
		return content;
	}

	public void setContent(Date content) {
		this.content = content;
	}

	public Float getTaskProcess() {
		return taskProcess;
	}

	public void setTaskProcess(Float taskProcess) {
		this.taskProcess = taskProcess;
	}
}
