package com.vix.dtbcenter.transpotmgr.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 派车单 发货通知单关联表
 * 
 * @author zhanghaibing
 * 
 * @date 2013-8-19
 */
public class DeliveryReceiptToDelieryNotification extends BaseEntity {

	private static final long serialVersionUID = -5606259243468066100L;
	private String deliveryReceiptId;
	private String delieryNotificationId;

	public String getDeliveryReceiptId() {
		return deliveryReceiptId;
	}

	public void setDeliveryReceiptId(String deliveryReceiptId) {
		this.deliveryReceiptId = deliveryReceiptId;
	}

	public String getDelieryNotificationId() {
		return delieryNotificationId;
	}

	public void setDelieryNotificationId(String delieryNotificationId) {
		this.delieryNotificationId = delieryNotificationId;
	}

}
