package com.vix.drp.activityplan.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 活动计划明细
 */

public class ActivityPlanDetail extends BaseEntity {

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
	 * 计划内容
	 */
	private String planContact;
	/**
	 * 负责人
	 */
	private String principal;
	/**
	 * 预算
	 */
	private Double budget;
	/**
	 * 活动计划
	 */
	private ActivityPlan activityPlan;

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

	public ActivityPlan getActivityPlan() {
		return activityPlan;
	}

	public void setActivityPlan(ActivityPlan activityPlan) {
		this.activityPlan = activityPlan;
	}
}