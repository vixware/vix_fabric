package com.vix.dtbcenter.transpotmgr.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 派车单 销售订单关联表
 * 
 * @author zhanghaibing
 * 
 * @date 2013-8-19
 */
public class DeliveryReceiptToSalesOrder extends BaseEntity {

	private static final long serialVersionUID = -5606259243468066100L;
	/**
	 * 派车单主键
	 */
	private String deliveryReceiptId;
	/**
	 * 销售订单主键
	 */
	private String salesOrderId;

	/**
	 * @return the deliveryReceiptId
	 */
	public String getDeliveryReceiptId() {
		return deliveryReceiptId;
	}

	/**
	 * @param deliveryReceiptId
	 *            the deliveryReceiptId to set
	 */
	public void setDeliveryReceiptId(String deliveryReceiptId) {
		this.deliveryReceiptId = deliveryReceiptId;
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
