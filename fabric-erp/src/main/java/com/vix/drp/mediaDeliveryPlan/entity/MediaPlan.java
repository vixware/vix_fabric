package com.vix.drp.mediaDeliveryPlan.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 媒体投放计划
 * 
 * @author zhanghaibing
 * 
 * @date 2014-2-12
 */

public class MediaPlan extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1400385456588601366L;
	/**
	 * 计划投放费用
	 */
	private Double cost;
	/**
	 * 电视
	 */
	private Set<TelevisionMedia> televisionMedias = new HashSet<TelevisionMedia>();
	/**
	 * 电台
	 */
	private Set<RadioMedia> radioMedias = new HashSet<RadioMedia>();
	/**
	 * 报纸
	 */
	private Set<NewsPaperMedia> newsPaperMedias = new HashSet<NewsPaperMedia>();

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Set<TelevisionMedia> getTelevisionMedias() {
		return televisionMedias;
	}

	public void setTelevisionMedias(Set<TelevisionMedia> televisionMedias) {
		this.televisionMedias = televisionMedias;
	}

	public Set<RadioMedia> getRadioMedias() {
		return radioMedias;
	}

	public void setRadioMedias(Set<RadioMedia> radioMedias) {
		this.radioMedias = radioMedias;
	}

	public Set<NewsPaperMedia> getNewsPaperMedias() {
		return newsPaperMedias;
	}

	public void setNewsPaperMedias(Set<NewsPaperMedia> newsPaperMedias) {
		this.newsPaperMedias = newsPaperMedias;
	}

}
