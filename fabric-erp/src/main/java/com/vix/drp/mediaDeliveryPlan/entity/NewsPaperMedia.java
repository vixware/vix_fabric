package com.vix.drp.mediaDeliveryPlan.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 报纸
 * 
 * @author zhanghaibing
 * 
 * @date 2014-2-12
 */

public class NewsPaperMedia extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1400385456588601366L;
	/**
	 * 栏目名称
	 */
	private String columnName;
	/**
	 * 栏目覆盖率
	 */
	private Double columnCoverageRate;
	/**
	 * 广告版面
	 */
	private String advertisementPage;
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

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Double getColumnCoverageRate() {
		return columnCoverageRate;
	}

	public void setColumnCoverageRate(Double columnCoverageRate) {
		this.columnCoverageRate = columnCoverageRate;
	}

	public String getAdvertisementPage() {
		return advertisementPage;
	}

	public void setAdvertisementPage(String advertisementPage) {
		this.advertisementPage = advertisementPage;
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
