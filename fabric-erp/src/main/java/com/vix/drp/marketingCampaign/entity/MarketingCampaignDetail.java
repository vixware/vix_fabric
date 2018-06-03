package com.vix.drp.marketingCampaign.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 活动明细
 */

public class MarketingCampaignDetail extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6979309143910389710L;
	/**
	 * 商品编码
	 */
	private String itemcode;
	/**
	 * 商品名称
	 */
	private String itemname;
	/**
	 * 活动内容
	 */
	private String planContact;
	/**
	 * 负责人
	 */
	private String principal;
	/**
	 * 费用
	 */
	private Double budget;
	/**
	 * 活动
	 */
	private MarketingCampaign marketingCampaign;

	public String getItemcode() {
		return itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getPlanContact() {
		return planContact;
	}

	public void setPlanContact(String planContact) {
		this.planContact = planContact;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public MarketingCampaign getMarketingCampaign() {
		return marketingCampaign;
	}

	public void setMarketingCampaign(MarketingCampaign marketingCampaign) {
		this.marketingCampaign = marketingCampaign;
	}

}