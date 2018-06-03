package com.vix.dtbcenter.transpotmgr.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @author zhanghaibing
 * 
 * @date 2013-8-19
 */
public class TruckingOrderToSalesOrder extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5606259243468066100L;
	private String truckingOrderId;
	private String salesOrderId;

	public String getTruckingOrderId() {
		return truckingOrderId;
	}

	public void setTruckingOrderId(String truckingOrderId) {
		this.truckingOrderId = truckingOrderId;
	}

	public String getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(String salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

}
