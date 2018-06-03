package com.vix.mdm.purchase.order.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.mdm.purchase.order.entity.PendingOrder
 *
 * @author yhl
 *
 * @date 2017年10月19日
 */
public class PendingOrder extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private RequireGoodsOrder requireGoodsOrder;

	public RequireGoodsOrder getRequireGoodsOrder() {
		return requireGoodsOrder;
	}

	public void setRequireGoodsOrder(RequireGoodsOrder requireGoodsOrder) {
		this.requireGoodsOrder = requireGoodsOrder;
	}

}
