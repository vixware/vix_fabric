/**
 * 
 */
package com.vix.dtbcenter.other.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 货物明细
 * 
 * @author zhanghaibing
 * 
 * @date 2013-9-1
 */
public class GoodsDetail extends BaseEntity {
	private static final long serialVersionUID = -3452778771933049707L;
	/** 编码 */
	private String itemCode;
	/** 名称 */
	private String itemName;
	/** 规格 */
	private String specification;
	/** 数量 */
	private Integer amount;
	/** 包装单位 */
	private String unit;
	/** 重量 */
	private Double weight;
	/** 净重 */
	private Double netWeight;
	/** 体积 */
	private Double capacity;

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}

	public Double getCapacity() {
		return capacity;
	}

	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}

}
