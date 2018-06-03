package com.vix.crm.business.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 催付设置 com.vix.crm.business.entity.ExpeditingSetting
 *
 * @author zhanghaibing
 *
 * @date 2014年12月30日
 */
public class ExpeditingSetting extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 类型 1 催付 2 关怀
	 */
	private String type;
	/**
	 * 催付类型
	 */
	private String isExpediting;
	/**
	 * 关怀节点
	 */
	private String careNode;
	/**
	 * 启动类型
	 */
	private String startType;
	/**
	 * 是否设置启动时间
	 */
	private String isSetTime;
	/**
	 * 启动时间
	 */
	private String startDate;
	/**
	 * 下单时间
	 */
	private Long orderTime;
	/**
	 * 超时是否催付
	 */
	private String timeOutIsExpediting;
	/**
	 * 会员等级
	 */
	private String customerLevel;
	/**
	 * 订单金额是否限制
	 */
	private String isLimit;
	/**
	 * 最小金额
	 */
	private Double minAmount;
	/**
	 * 最大值
	 */
	private Double maxAmount;
	/**
	 * 是否聚划算
	 */
	private String isJhs;

	public String getIsExpediting() {
		return isExpediting;
	}

	public void setIsExpediting(String isExpediting) {
		this.isExpediting = isExpediting;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getCareNode() {
		return careNode;
	}

	public void setCareNode(String careNode) {
		this.careNode = careNode;
	}

	public String getStartType() {
		return startType;
	}

	public void setStartType(String startType) {
		this.startType = startType;
	}

	public String getCustomerLevel() {
		return customerLevel;
	}

	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}

	public String getIsLimit() {
		return isLimit;
	}

	public void setIsLimit(String isLimit) {
		this.isLimit = isLimit;
	}

	public Double getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(Double minAmount) {
		this.minAmount = minAmount;
	}

	public Double getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(Double maxAmount) {
		this.maxAmount = maxAmount;
	}

	public String getIsJhs() {
		return isJhs;
	}

	public void setIsJhs(String isJhs) {
		this.isJhs = isJhs;
	}

	public String getIsSetTime() {
		return isSetTime;
	}

	public void setIsSetTime(String isSetTime) {
		this.isSetTime = isSetTime;
	}

	public Long getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Long orderTime) {
		this.orderTime = orderTime;
	}

	public String getTimeOutIsExpediting() {
		return timeOutIsExpediting;
	}

	public void setTimeOutIsExpediting(String timeOutIsExpediting) {
		this.timeOutIsExpediting = timeOutIsExpediting;
	}

}
