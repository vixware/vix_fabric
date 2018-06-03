package com.vix.sales.order.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 销售订单明细发运计划
 * @author Administrator
 *
 */
public class SalesDeliveryPlanDetail extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** 发运计划 */
	private SalesDeliveryPlan salesDeliveryPlan;
	private SaleOrderItem saleOrderItem;
	private Long deliveryPlanItemCount;

	public SalesDeliveryPlanDetail(){}

	public SalesDeliveryPlan getSalesDeliveryPlan() {
		return salesDeliveryPlan;
	}

	public void setSalesDeliveryPlan(SalesDeliveryPlan salesDeliveryPlan) {
		this.salesDeliveryPlan = salesDeliveryPlan;
	}

	public SaleOrderItem getSaleOrderItem() {
		return saleOrderItem;
	}

	public void setSaleOrderItem(SaleOrderItem saleOrderItem) {
		this.saleOrderItem = saleOrderItem;
	}

	public Long getDeliveryPlanItemCount() {
		return deliveryPlanItemCount;
	}

	public void setDeliveryPlanItemCount(Long deliveryPlanItemCount) {
		this.deliveryPlanItemCount = deliveryPlanItemCount;
	}
}
