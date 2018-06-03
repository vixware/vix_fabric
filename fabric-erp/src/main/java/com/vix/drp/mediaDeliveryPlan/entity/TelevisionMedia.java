package com.vix.drp.mediaDeliveryPlan.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 电视
 * 
 * @author zhanghaibing
 * 
 * @date 2014-2-12
 */

public class TelevisionMedia extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1400385456588601366L;
	/**
	 * 频道名称
	 */
	private String channelName;
	/**
	 * 媒体栏目
	 */
	private String mediaColumn;
	/**
	 * 栏目覆盖率
	 */
	private Double columnCoverageRate;
	/**
	 * 栏目开播时间
	 */
	private Date columnStartTime;
	/**
	 * 播放时长
	 */
	private Integer playTimes;
	/**
	 * 广告时长
	 */
	private Integer advertisementTimes;
	/**
	 * 广告价格
	 */
	private Double advertisementPrice;
	/**
	 * 投放时间
	 */
	private Date cameTime;
	/**
	 * 受众人数
	 */
	private Integer audience;
	/**
	 * 广告文件名称
	 */
	private String advertisementFileName;

	private MediaPlan mediaPlan;

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getMediaColumn() {
		return mediaColumn;
	}

	public void setMediaColumn(String mediaColumn) {
		this.mediaColumn = mediaColumn;
	}

	public Double getColumnCoverageRate() {
		return columnCoverageRate;
	}

	public void setColumnCoverageRate(Double columnCoverageRate) {
		this.columnCoverageRate = columnCoverageRate;
	}

	public Date getColumnStartTime() {
		return columnStartTime;
	}

	public void setColumnStartTime(Date columnStartTime) {
		this.columnStartTime = columnStartTime;
	}

	public Integer getPlayTimes() {
		return playTimes;
	}

	public void setPlayTimes(Integer playTimes) {
		this.playTimes = playTimes;
	}

	public Integer getAdvertisementTimes() {
		return advertisementTimes;
	}

	public void setAdvertisementTimes(Integer advertisementTimes) {
		this.advertisementTimes = advertisementTimes;
	}

	public Double getAdvertisementPrice() {
		return advertisementPrice;
	}

	public void setAdvertisementPrice(Double advertisementPrice) {
		this.advertisementPrice = advertisementPrice;
	}

	public Date getCameTime() {
		return cameTime;
	}

	public void setCameTime(Date cameTime) {
		this.cameTime = cameTime;
	}

	public Integer getAudience() {
		return audience;
	}

	public void setAudience(Integer audience) {
		this.audience = audience;
	}

	public String getAdvertisementFileName() {
		return advertisementFileName;
	}

	public void setAdvertisementFileName(String advertisementFileName) {
		this.advertisementFileName = advertisementFileName;
	}

	public MediaPlan getMediaPlan() {
		return mediaPlan;
	}

	public void setMediaPlan(MediaPlan mediaPlan) {
		this.mediaPlan = mediaPlan;
	}

}
