package com.vix.ebusiness.order.orderProcess.vo;

import java.io.Serializable;

public class WaitDelivery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1539996378367154864L;
	String tradeId;
	String orderId;
	String logisticsId;
	String outsid;
	String channelDistributorId;

	/**
	 * @return the tradeId
	 */
	public String getTradeId() {
		return tradeId;
	}

	/**
	 * @param tradeId
	 *            the tradeId to set
	 */
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(String logisticsId) {
		this.logisticsId = logisticsId;
	}

	public String getOutsid() {
		return outsid;
	}

	public void setOutsid(String outsid) {
		this.outsid = outsid;
	}

	/**
	 * @return the channelDistributorId
	 */
	public String getChannelDistributorId() {
		return channelDistributorId;
	}

	/**
	 * @param channelDistributorId
	 *            the channelDistributorId to set
	 */
	public void setChannelDistributorId(String channelDistributorId) {
		this.channelDistributorId = channelDistributorId;
	}

}
