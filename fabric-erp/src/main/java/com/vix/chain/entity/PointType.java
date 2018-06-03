package com.vix.chain.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 积分类型
 * 
 * @author zhanghaibing
 * 
 * @date 2013-5-21
 */
public class PointType extends BaseEntity {

	private static final long serialVersionUID = 7792368439455650007L;
	/**
	 * 积分主题
	 */
	private String subject;
	/**
	 * 积分类型
	 */
	private String priceType;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	/**
	 * @pdRoleInfo migr=no name=TakePointRule assc=association35
	 *             coll=java.util.Collection impl=java.util.HashSet mult=0..*
	 */
	/* public java.util.Collection<TakePointRule> takePointRule; */

	/** @pdGenerated default getter */
	/*
	 * public java.util.Collection<TakePointRule> getTakePointRule() { if
	 * (takePointRule == null) takePointRule = new
	 * java.util.HashSet<TakePointRule>(); return takePointRule; }
	 */

	/** @pdGenerated default iterator getter */
	/*
	 * public java.util.Iterator getIteratorTakePointRule() { if (takePointRule
	 * == null) takePointRule = new java.util.HashSet<TakePointRule>(); return
	 * takePointRule.iterator(); }
	 */

	/**
	 * @pdGenerated default setter
	 * @param newTakePointRule
	 */
	/*
	 * public void setTakePointRule(java.util.Collection<TakePointRule>
	 * newTakePointRule) { removeAllTakePointRule(); for (java.util.Iterator
	 * iter = newTakePointRule.iterator(); iter.hasNext();)
	 * addTakePointRule((TakePointRule) iter.next()); }
	 */

	/**
	 * @pdGenerated default add
	 * @param newTakePointRule
	 */
	/*
	 * public void addTakePointRule(TakePointRule newTakePointRule) { if
	 * (newTakePointRule == null) return; if (this.takePointRule == null)
	 * this.takePointRule = new java.util.HashSet<TakePointRule>(); if
	 * (!this.takePointRule.contains(newTakePointRule))
	 * this.takePointRule.add(newTakePointRule); }
	 */

	/**
	 * @pdGenerated default remove
	 * @param oldTakePointRule
	 */
	/*
	 * public void removeTakePointRule(TakePointRule oldTakePointRule) { if
	 * (oldTakePointRule == null) return; if (this.takePointRule != null) if
	 * (this.takePointRule.contains(oldTakePointRule))
	 * this.takePointRule.remove(oldTakePointRule); }
	 */

	/** @pdGenerated default removeAll */
	/*
	 * public void removeAllTakePointRule() { if (takePointRule != null)
	 * takePointRule.clear(); }
	 */
}
