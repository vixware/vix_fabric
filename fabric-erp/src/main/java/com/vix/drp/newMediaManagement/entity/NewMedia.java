package com.vix.drp.newMediaManagement.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 新媒体
 * 
 * @author zhanghaibing
 * 
 * @date 2013-5-21
 */
public class NewMedia extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 媒体名称
	 */
	private String mediaName;
	/**
	 * 节目名称
	 */
	private String programName;
	/**
	 * 覆盖率百分比
	 */
	private Double percentageCoverage;
	/**
	 * 节目收视率百分比
	 */
	private Double programSectionPercentage;
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

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public Double getPercentageCoverage() {
		return percentageCoverage;
	}

	public void setPercentageCoverage(Double percentageCoverage) {
		this.percentageCoverage = percentageCoverage;
	}

	public Double getProgramSectionPercentage() {
		return programSectionPercentage;
	}

	public void setProgramSectionPercentage(Double programSectionPercentage) {
		this.programSectionPercentage = programSectionPercentage;
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
