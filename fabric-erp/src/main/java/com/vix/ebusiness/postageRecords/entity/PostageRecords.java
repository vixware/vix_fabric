package com.vix.ebusiness.postageRecords.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.ebusiness.entity.Order;
import com.vix.ebusiness.option.entity.Logistics;

/**
 * 费用记录
 */

public class PostageRecords extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//邮费
	private Double expressFee;
	//重量
	private Double weight;
	/**
	 * 快递公司
	 */
	private Logistics logistics;
	/**
	 * 订单信息
	 */
	private Order order;

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Double getExpressFee() {
		return expressFee;
	}

	public void setExpressFee(Double expressFee) {
		this.expressFee = expressFee;
	}

	public Logistics getLogistics() {
		return logistics;
	}

	public void setLogistics(Logistics logistics) {
		this.logistics = logistics;
	}

}