/**
 * 
 */
package com.vix.drp.marketingCampaign.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 活动任务
 * 
 * @author zhanghaibing
 * 
 * @date 2014-5-11
 */
public class MarketingCampaignTask extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 反馈
	 */
	private String feedbackContent;
	/**
	 * 活动
	 */
	private MarketingCampaign marketingCampaign;
	/**
	 * 经销商
	 */
	private ChannelDistributor channelDistributor;

	public String getFeedbackContent() {
		return feedbackContent;
	}

	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}

	public MarketingCampaign getMarketingCampaign() {
		return marketingCampaign;
	}

	public void setMarketingCampaign(MarketingCampaign marketingCampaign) {
		this.marketingCampaign = marketingCampaign;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

}
