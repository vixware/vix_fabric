package com.vix.ebusiness.expressFeeRules.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.ebusiness.option.entity.Logistics;

/**
 * 快递费用规则
 */

public class ExpressFeeRules extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 首重
	 */
	private Double firstWeight;
	/**
	 * 费用
	 */
	private Double firstCost;
	/**
	 * 续重单位量
	 */
	private Double perWeight;
	/**
	 * 续重费用
	 */
	private Double perCost;
	/**
	 * 快递公司
	 */
	private Logistics logistics;
	/**
	 * 区域
	 */
	private DeliveryArea deliveryArea;

	public Logistics getLogistics() {
		return logistics;
	}

	public void setLogistics(Logistics logistics) {
		this.logistics = logistics;
	}

	public Double getFirstWeight() {
		return firstWeight;
	}

	public void setFirstWeight(Double firstWeight) {
		this.firstWeight = firstWeight;
	}

	public Double getFirstCost() {
		return firstCost;
	}

	public void setFirstCost(Double firstCost) {
		this.firstCost = firstCost;
	}

	public Double getPerWeight() {
		return perWeight;
	}

	public void setPerWeight(Double perWeight) {
		this.perWeight = perWeight;
	}

	public Double getPerCost() {
		return perCost;
	}

	public void setPerCost(Double perCost) {
		this.perCost = perCost;
	}

	public DeliveryArea getDeliveryArea() {
		return deliveryArea;
	}

	public void setDeliveryArea(DeliveryArea deliveryArea) {
		this.deliveryArea = deliveryArea;
	}

}