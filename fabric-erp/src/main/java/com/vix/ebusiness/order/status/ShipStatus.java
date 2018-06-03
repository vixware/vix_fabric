package com.vix.ebusiness.order.status;

/**
 * 
 * com.vix.ebusiness.order.status.ShipStatus
 *
 * @author zhanghaibing
 *
 * @date 2014年10月11日
 */
public class ShipStatus {

	/**
	 * 未处理
	 */
	public static final Integer UNTREATED = 0;

	/**
	 * 已发货
	 */
	public static final Integer ALREADY_SHIPPING = 1;

	/**
	 * 发货单已生成
	 */
	public static final Integer CREATE_INVOICE = 2;
}
