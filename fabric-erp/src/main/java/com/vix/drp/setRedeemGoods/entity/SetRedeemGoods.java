package com.vix.drp.setRedeemGoods.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;

/**
 * 设置商品积分兑换
 * 
 * @author zhanghaibing
 * 
 * @date 2013-11-3
 */
public class SetRedeemGoods extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 兑换积分
	 */
	private Double redeemPoints;
	/**
	 * 商品库存
	 */
	private InventoryCurrentStock inventoryCurrentStock;

	public Double getRedeemPoints() {
		return redeemPoints;
	}

	public void setRedeemPoints(Double redeemPoints) {
		this.redeemPoints = redeemPoints;
	}

	public InventoryCurrentStock getInventoryCurrentStock() {
		return inventoryCurrentStock;
	}

	public void setInventoryCurrentStock(InventoryCurrentStock inventoryCurrentStock) {
		this.inventoryCurrentStock = inventoryCurrentStock;
	}

}
