package com.vix.drp.tvMediaManagement.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 电视媒体
 * 
 * @author zhanghaibing
 * 
 * @date 2013-5-21
 */
public class TvMedia extends BaseEntity {

	private static final long serialVersionUID = -9090929814206311938L;
	/**
	 * 电视栏目
	 */
	private String tvProgram;
	/**
	 * 频道名称
	 */
	private String channelName;
	/**
	 * 覆盖率百分比
	 */
	private Double percentageCoverage;
	/**
	 * 栏目名称
	 */
	private String columnName;
	/**
	 * 栏目收视率百分比
	 */
	private Double ratingsSectionPercentage;
	/**
	 * 目标受众率
	 */
	private Double targetAudienceRate;
	/**
	 * 广告时间
	 */
	private Date advertisingTime;
	/**
	 * 广告价格
	 */
	private Double advertisingPrice;

	public String getTvProgram() {
		return tvProgram;
	}

	public void setTvProgram(String tvProgram) {
		this.tvProgram = tvProgram;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Double getPercentageCoverage() {
		return percentageCoverage;
	}

	public void setPercentageCoverage(Double percentageCoverage) {
		this.percentageCoverage = percentageCoverage;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Double getRatingsSectionPercentage() {
		return ratingsSectionPercentage;
	}

	public void setRatingsSectionPercentage(Double ratingsSectionPercentage) {
		this.ratingsSectionPercentage = ratingsSectionPercentage;
	}

	public Double getTargetAudienceRate() {
		return targetAudienceRate;
	}

	public void setTargetAudienceRate(Double targetAudienceRate) {
		this.targetAudienceRate = targetAudienceRate;
	}

	public Date getAdvertisingTime() {
		return advertisingTime;
	}

	public void setAdvertisingTime(Date advertisingTime) {
		this.advertisingTime = advertisingTime;
	}

	public Double getAdvertisingPrice() {
		return advertisingPrice;
	}

	public void setAdvertisingPrice(Double advertisingPrice) {
		this.advertisingPrice = advertisingPrice;
	}

}
