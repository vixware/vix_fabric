package com.vix.dtbcenter.deliveryplan.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 配送计划车辆关联表
 * 
 * @author zhanghaibing
 * 
 * @date 2013-8-19
 */
public class DeliveryPlanToDeliveryPerson extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4898625711334420211L;
	private String deliveryPlanId;
	private String deliveryPersonId;

	/**
	 * @return the deliveryPlanId
	 */
	public String getDeliveryPlanId() {
		return deliveryPlanId;
	}

	/**
	 * @param deliveryPlanId
	 *            the deliveryPlanId to set
	 */
	public void setDeliveryPlanId(String deliveryPlanId) {
		this.deliveryPlanId = deliveryPlanId;
	}

	/**
	 * @return the deliveryPersonId
	 */
	public String getDeliveryPersonId() {
		return deliveryPersonId;
	}

	/**
	 * @param deliveryPersonId
	 *            the deliveryPersonId to set
	 */
	public void setDeliveryPersonId(String deliveryPersonId) {
		this.deliveryPersonId = deliveryPersonId;
	}

}
