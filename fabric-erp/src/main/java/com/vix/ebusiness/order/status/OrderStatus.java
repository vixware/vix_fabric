package com.vix.ebusiness.order.status;

/**
 * 订单状态 com.vix.ebusiness.order.status.Order
 *
 * @author zhanghaibing
 *
 * @date 2014年10月11日
 */
public class OrderStatus {

	/**
	 * 确认状态 -- 未确认
	 */
	public static final int CONFIRM_NOT_AFFIRM = 0;
	/**
	 * 确认状态 -- 已确认
	 */
	public static final int CONFIRM_IS_AFFIRM = 1;
	/**
	 * 确认状态 -- 确认不通过
	 */
	public static final int CONFIRM_NOT_PASS = 2;

	/**
	 * 订单状态 -- 等待买家付款
	 */
	public static final int ORDER_STATUS_WAIT_PAY = 0;

	// 活动订单
	public static final int STATUS_ACTIVATED = 0;
	// 已完成订单
	public static final int STATUS_FINISHED = 1;
	// 已作废订单
	public static final int STATUS_INVALID = 2;

	// 订单审核未处理
	public static final int CONFIRM_NOT_YET = 0;
	// 订单已确认
	public static final int CONFIRM_YES = 1;
	// 订单审核未通过
	public static final int CONFIRM_NO = 2;

	// 订单未支付
	public static final int PAY_STATUS_UNPAYED = 0;
	// 订单已支付
	public static final int PAY_STATUS_PAYED = 1;
	// 订单已支付
	public static final int PAY_STATUS_DEALING = 2;
	// 订单部分付款
	public static final int PAY_STATUS_PART_PAYED = 3;
	// 订单全额退款
	public static final int PAY_STATUS_REFUND = 4;
	// 订单部分退款
	public static final int PAY_STATUS_PART_REFUND = 5;

	// 订单未发货
	public static final int SHIP_STATUS_UN_DELIVED = 0;
	// 订单已发货
	public static final int SHIP_STATUS_DELIVED = 1;
	// 订单部分发货
	public static final int SHIP_STATUS_PART_DELIVED = 2;
	// 订单部分退货
	public static final int SHIP_STATUS_PART_RETURNED = 3;
	// 订单已退货
	public static final int SHIP_STATUS_RETURNED = 4;

	// 订单未配送
	public static final int DELIVERY_STATUS_NO = 0;
	// 订单已配送
	public static final int DELIVERY_STATUS_YES = 1;

	// 无反馈
	public static final int USER_STATUS_NOTHING = 0;
	// 已支付
	public static final int USER_STATUS_PAYED = 1;
	// 已收货
	public static final int USER_STATUS_TAKE_DELIVERY = 2;

	// 物流不保价
	public static final int SHIP_IS_PROTECT_NO = 0;
	// 物流保价
	public static final int SHIP_IS_PROTECT_YES = 1;

	// 要发票
	public static final int IS_TAX_NO = 0;
	// 不要发票
	public static final int IS_TAX_YES = 1;

}
