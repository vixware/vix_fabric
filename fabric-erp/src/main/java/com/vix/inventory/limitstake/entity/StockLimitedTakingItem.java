package com.vix.inventory.limitstake.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 限额领料单子表
 * 
 * @author zhanghaibing
 * 
 * @date 2013-5-21
 */
public class StockLimitedTakingItem extends BaseEntity {
	private static final long serialVersionUID = 5677365819588737091L;
	/**
	 * 材料编码
	 */
	private String itemcode;
	/**
	 * 材料名称
	 */
	private String itemname;
	/**
	 * 规格型号
	 */
	private String specification;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 领用限额
	 */
	private Long limitCount;
	/**
	 * 单价
	 */
	private Double price;
	/**
	 * 请领数量
	 */
	private Long requisitionCount;
	/**
	 * 实发数量
	 */
	private Long actualQuantity;

	private StockLimitedTaking stockLimitedTaking;

	public String getItemcode() {
		return itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Long getLimitCount() {
		return limitCount;
	}

	public void setLimitCount(Long limitCount) {
		this.limitCount = limitCount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getRequisitionCount() {
		return requisitionCount;
	}

	public void setRequisitionCount(Long requisitionCount) {
		this.requisitionCount = requisitionCount;
	}

	public Long getActualQuantity() {
		return actualQuantity;
	}

	public void setActualQuantity(Long actualQuantity) {
		this.actualQuantity = actualQuantity;
	}

	public StockLimitedTaking getStockLimitedTaking() {
		return stockLimitedTaking;
	}

	public void setStockLimitedTaking(StockLimitedTaking stockLimitedTaking) {
		this.stockLimitedTaking = stockLimitedTaking;
	}
}
