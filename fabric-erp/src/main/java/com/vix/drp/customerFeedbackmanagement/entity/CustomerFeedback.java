package com.vix.drp.customerFeedbackmanagement.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 客户反馈
 * 
 * @author zhanghaibing
 * 
 * @date 2013-5-21
 */
public class CustomerFeedback extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3452354596906647676L;
	/**
	 * 客户姓名
	 */
	private String customerName;
	/**
	 * 反馈内容
	 */
	private String feedbackContent;
	/**
	 * 反馈时间
	 */
	private Date feedbackTime;
	/**
	 * 处理人
	 */
	private String processPerson;
	/**
	 * 处理结果
	 */
	private String processResult;
	/**
	 * 处理时间
	 */
	private Date processTime;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getFeedbackContent() {
		return feedbackContent;
	}

	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}

	public Date getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	public String getProcessPerson() {
		return processPerson;
	}

	public void setProcessPerson(String processPerson) {
		this.processPerson = processPerson;
	}

	public Date getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	public String getProcessResult() {
		return processResult;
	}

	public void setProcessResult(String processResult) {
		this.processResult = processResult;
	}

}
