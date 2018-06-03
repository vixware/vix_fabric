package com.vix.dtbcenter.dispatchingreceipt.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 配送回执单 发货通知单关联表
 * 
 * @author zhanghaibing
 * 
 * @date 2013-8-19
 */
public class DispatchingReceiptToSalesOrder extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 422003986125458323L;
	/**
	 * 配送回执单ID
	 */
	private String dispatchingReceiptId;
	/**
	 * 发货通知单ID
	 */
	private String salesOrderId;

	/**
	 * @return the dispatchingReceiptId
	 */
	public String getDispatchingReceiptId() {
		return dispatchingReceiptId;
	}

	/**
	 * @param dispatchingReceiptId
	 *            the dispatchingReceiptId to set
	 */
	public void setDispatchingReceiptId(String dispatchingReceiptId) {
		this.dispatchingReceiptId = dispatchingReceiptId;
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
