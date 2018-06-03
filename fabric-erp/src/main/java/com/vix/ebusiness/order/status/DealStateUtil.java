package com.vix.ebusiness.order.status;

/**
 * 
 * com.vix.ebusiness.order.status.DealStateUtil
 *
 * @author zhanghaibing
 *
 * @date 2014年10月11日
 */
public class DealStateUtil {

	private String key;

	private int order;

	public DealStateUtil(String key, int order) {
		super();
		this.key = key;
		this.order = order;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}
