package com.vix.ebusiness.order.status;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * com.vix.ebusiness.order.status.DealState
 *
 * @author zhanghaibing
 *
 * @date 2014年10月11日
 */
public class DealState {

	/**
	 * 未分拣0
	 */
	public static final Integer UNTREATED = 0;

	/**
	 * 正在分拣8
	 */
	public static final Integer CREATE_INVOICEING = 8;

	/**
	 * 已分拣 1
	 */
	public static final Integer CREATE_INVOICE = 1;

	/**
	 * 分拣打包2
	 */
	public static final Integer STORE_EXCEEDING = 2;

	/**
	 * 出库3
	 */
	public static final Integer STORE_EXCEED = 3;

	/**
	 * 正在发货9
	 */
	public static final Integer HAS_SHIPPING = 9;

	/**
	 * 发货4
	 */
	public static final Integer ALREADY_SHIPPING = 4;

	/**
	 * 已发货5
	 */
	public static final Integer STATE_SUCCESS = 5;

	/**
	 * 处理异常7
	 */
	public static final Integer STATE_EXCEPTION = 7;

	/**
	 * 退货10
	 */
	public static final Integer RETURN_ITEM = 10;

	/**
	 * 目前用到的是0（未分拣）、1（已分拣）、2（已发货）、8（分拣中）
	 * 
	 * @return
	 */
	public static final List<DealStateUtil> allDealState() {
		List<DealStateUtil> str = new ArrayList<DealStateUtil>();

		str.add(new DealStateUtil("未分拣", 0));
		str.add(new DealStateUtil("已分拣", 1));
		// str.add(new DealStateUtil("已打印",1));
		str.add(new DealStateUtil("出库", 3));
		str.add(new DealStateUtil("已发货", 5));
		// str.add(new DealStateUtil("处理完毕",5)) ;
		return str;
	}
}
