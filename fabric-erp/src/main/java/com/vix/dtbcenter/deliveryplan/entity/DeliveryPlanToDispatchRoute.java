package com.vix.dtbcenter.deliveryplan.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 配送计划车辆关联表
 * 
 * @author zhanghaibing
 * 
 * @date 2013-8-19
 */
public class DeliveryPlanToDispatchRoute extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5606259243468066100L;
	private String deliveryPlanId;
	private String dispatchRouteId;

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
	 * @return the dispatchRouteId
	 */
	public String getDispatchRouteId() {
		return dispatchRouteId;
	}

	/**
	 * @param dispatchRouteId
	 *            the dispatchRouteId to set
	 */
	public void setDispatchRouteId(String dispatchRouteId) {
		this.dispatchRouteId = dispatchRouteId;
	}

}
