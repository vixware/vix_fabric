/**
 * 
 */
package com.vix.drp.policyfeedback.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 政策反馈
 * 
 * @author zhanghaibing
 * 
 * @date 2014-5-6
 */
public class PolicyInformation extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5621786329016505251L;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 优先级
	 */
	private String priority;
	/**
	 * 政策内容
	 */
	private String policyContent;
	/**
	 * 反馈内容
	 */
	private String policyFeedbackContent;
	/**
	 * 门店
	 */
	private ChannelDistributor channelDistributor;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getPolicyContent() {
		return policyContent;
	}

	public void setPolicyContent(String policyContent) {
		this.policyContent = policyContent;
	}

	public String getPolicyFeedbackContent() {
		return policyFeedbackContent;
	}

	public void setPolicyFeedbackContent(String policyFeedbackContent) {
		this.policyFeedbackContent = policyFeedbackContent;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

}
