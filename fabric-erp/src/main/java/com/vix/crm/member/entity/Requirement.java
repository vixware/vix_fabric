package com.vix.crm.member.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 
 * @类全名 com.vix.crm.member.entity.Requirement
 *
 * @author zhanghaibing
 *
 * @date 2016年11月21日
 */
public class Requirement extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 门店
	 */
	private ChannelDistributor channelDistributor;
	/**
	 * 新品需求内容
	 */
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public String getChannelDistributorName() {
		if (channelDistributor != null) {
			return channelDistributor.getName();
		}
		return "";
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}
}