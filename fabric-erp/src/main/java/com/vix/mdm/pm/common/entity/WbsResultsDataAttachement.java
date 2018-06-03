/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * WBS结果资料附件
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class WbsResultsDataAttachement extends BaseEntity {
	/** 任务编码 */
	private String taskCode;
	/** 工作流附件 */
	private String workFlowAttachments;
	/** 资料 */
	private String meterials;

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getWorkFlowAttachments() {
		return workFlowAttachments;
	}

	public void setWorkFlowAttachments(String workFlowAttachments) {
		this.workFlowAttachments = workFlowAttachments;
	}

	public String getMeterials() {
		return meterials;
	}

	public void setMeterials(String meterials) {
		this.meterials = meterials;
	}

}
