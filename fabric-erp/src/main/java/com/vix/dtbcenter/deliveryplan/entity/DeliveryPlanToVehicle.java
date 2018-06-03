package com.vix.dtbcenter.deliveryplan.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 配送计划车辆关联表
 * 
 * @author zhanghaibing
 * 
 * @date 2013-8-19
 */
public class DeliveryPlanToVehicle extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5606259243468066100L;
	private String deliveryPlanId;
	private String vehicleId;

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
	 * @return the vehicleId
	 */
	public String getVehicleId() {
		return vehicleId;
	}

	/**
	 * @param vehicleId
	 *            the vehicleId to set
	 */
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

}
