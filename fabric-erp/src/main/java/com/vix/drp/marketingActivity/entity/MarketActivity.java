package com.vix.drp.marketingActivity.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 市场活动
 * 
 * @author zhanghaibing
 * 
 * @date 2013-5-21
 */
public class MarketActivity extends BaseEntity {
	private static final long serialVersionUID = -900059729115306783L;
	/**
	 * 活动类型
	 */
	private String type;
	/**
	 * 促销产品名称
	 */
	private String promotionalProductName;
	/**
	 * 新产品名称
	 */
	private String newProductName;
	/**
	 * 渠道
	 */
	private ChannelDistributor channelDistributor;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPromotionalProductName() {
		return promotionalProductName;
	}

	public void setPromotionalProductName(String promotionalProductName) {
		this.promotionalProductName = promotionalProductName;
	}

	public String getNewProductName() {
		return newProductName;
	}

	public void setNewProductName(String newProductName) {
		this.newProductName = newProductName;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

}
