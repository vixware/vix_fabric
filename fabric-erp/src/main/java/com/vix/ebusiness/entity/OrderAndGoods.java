package com.vix.ebusiness.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 订单商品关联表
 * 
 * @author zhanghaibing
 * 
 * @date 2014-3-20
 */
public class OrderAndGoods extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2872723549715028541L;
	// 订单ID
	private String orderId;
	// 订单明细ID
	private String orderDetailId;
	// 商品ID
	private String goodId;
	// 锁定数量
	private Double num;
	// 商品编码
	private String itemcode;
	// SKU编码
	private String skuCode;

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the orderDetailId
	 */
	public String getOrderDetailId() {
		return orderDetailId;
	}

	/**
	 * @param orderDetailId
	 *            the orderDetailId to set
	 */
	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	/**
	 * @return the goodId
	 */
	public String getGoodId() {
		return goodId;
	}

	/**
	 * @param goodId
	 *            the goodId to set
	 */
	public void setGoodId(String goodId) {
		this.goodId = goodId;
	}

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	public String getItemcode() {
		return itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

}
