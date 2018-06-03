package com.vix.inventory.reorderPoint.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.item.entity.Item;

/**
 * 再订货点 com.vix.inventory.reorderPoint.entity.ReorderPoint
 *
 * @author bjitzhang
 *
 * @date 2015年5月6日
 */
public class ReorderPoint extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 正常到货天数
	 */
	private Long normalDeliveryDays;
	/**
	 * 每日销量
	 */
	private Double daySales;
	/**
	 * 安全库存
	 */
	private Double safetyStock;
	/**
	 * 再订货点
	 */
	private Double reorderPoint;
	/**
	 * 商品
	 */
	private Item item;

	public Long getNormalDeliveryDays() {
		return normalDeliveryDays;
	}

	public void setNormalDeliveryDays(Long normalDeliveryDays) {
		this.normalDeliveryDays = normalDeliveryDays;
	}

	public Double getDaySales() {
		return daySales;
	}

	public void setDaySales(Double daySales) {
		this.daySales = daySales;
	}

	public Double getSafetyStock() {
		return safetyStock;
	}

	public void setSafetyStock(Double safetyStock) {
		this.safetyStock = safetyStock;
	}

	public Double getReorderPoint() {
		return reorderPoint;
	}

	public void setReorderPoint(Double reorderPoint) {
		this.reorderPoint = reorderPoint;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}