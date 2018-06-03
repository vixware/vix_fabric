package com.vix.drp.putInEffect.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 投放效果
 * 
 * @author zhanghaibing
 * 
 * @date 2013-5-21
 */
public class PutInEffect extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8091120221133228550L;

	/**
	 * 栏目名称
	 */
	private String columnName;
	/**
	 * 收视人数
	 */
	private Integer viewership;
	/**
	 * 栏目收视率百分比
	 */
	private Double sectionPercentage;
	/**
	 * 受众人数
	 */
	private Integer audience;
	/**
	 * 目标受众率百分比
	 */
	private Double targetAudienceRate;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Integer getViewership() {
		return viewership;
	}

	public void setViewership(Integer viewership) {
		this.viewership = viewership;
	}

	public Double getSectionPercentage() {
		return sectionPercentage;
	}

	public void setSectionPercentage(Double sectionPercentage) {
		this.sectionPercentage = sectionPercentage;
	}

	public Integer getAudience() {
		return audience;
	}

	public void setAudience(Integer audience) {
		this.audience = audience;
	}

	public Double getTargetAudienceRate() {
		return targetAudienceRate;
	}

	public void setTargetAudienceRate(Double targetAudienceRate) {
		this.targetAudienceRate = targetAudienceRate;
	}

}
