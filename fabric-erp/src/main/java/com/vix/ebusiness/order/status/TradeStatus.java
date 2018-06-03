package com.vix.ebusiness.order.status;

/**
 * 订单状态 com.vix.ebusiness.order.status.TradeStatus
 *
 * @author zhanghaibing
 *
 * @date 2014年10月11日
 */
public class TradeStatus {

	/**
	 * 等待买家付款
	 */
	public static final Integer WAIT_BUYER_PAY = 0;
	/**
	 * 待发货
	 */
	public static final Integer WAIT_SELLER_SEND_GOODS = 1;
	/**
	 * 已发货
	 */
	public static final Integer WAIT_BUYER_CONFIRM_GOODS = 2;
	/**
	 * 买家已签收
	 */
	public static final Integer TRADE_BUYER_SIGNED = 3;
	/**
	 * 交易成功
	 */
	public static final Integer TRADE_FINISHED = 4;
	/**
	 * 等待出库
	 */
	public static final Integer TRADE_CLOSED_BY_USER = 5;
	/**
	 * 退款自动关闭
	 */
	public static final Integer REFUND_CLOSED = 6;
	/**
	 * 交易关闭7
	 */
	public static final Integer TRADE_CLOSED = 7;
	/**
	 * 订单锁定8
	 */
	public static final Integer TRADE_LOCKED = 8;

	/**
	 * 退货9
	 */
	public static final Integer REFUND_ITEM = 9;
}
