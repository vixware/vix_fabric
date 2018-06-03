package com.vix.dtbcenter.deliveryplan.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 配送计划销售订单关联表
 * 
 * @author zhanghaibing
 * 
 * @date 2013-8-19
 */
public class DeliveryPlanToSalesOrder extends BaseEntity {

	private static final long serialVersionUID = 5878569276978490180L;
	private String deliveryPlanId;
	private String salesOrderId;

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
	 * @return the salesOrderId
	 */
	public String getSalesOrderId() {
		return salesOrderId;
	}

	/**
	 * @param salesOrderId
	 *            the salesOrderId to set
	 */
	public void setSalesOrderId(String salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

}
