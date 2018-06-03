package com.vix.drp.advertisingCampaign.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 广告活动信息
 * 
 * @author zhanghaibing
 * 
 * @date 2013-5-21
 */
public class AdvertisingCampaign extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 447989117809257525L;
	/**
	 * 媒体参与类别
	 */
	private String type;
	/**
	 * 媒体参与数量
	 */
	private Integer amount;
	/**
	 * 媒体名称
	 */
	private String mediaName;
	/**
	 * 栏目名称
	 */
	private String columnName;
	/**
	 * 每周投放次数
	 */
	private Integer deliveryTimes;
	/**
	 * 版面时间
	 */
	private Date spaceTime;
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Integer getDeliveryTimes() {
		return deliveryTimes;
	}

	public void setDeliveryTimes(Integer deliveryTimes) {
		this.deliveryTimes = deliveryTimes;
	}

	public Date getSpaceTime() {
		return spaceTime;
	}

	public void setSpaceTime(Date spaceTime) {
		this.spaceTime = spaceTime;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

}
