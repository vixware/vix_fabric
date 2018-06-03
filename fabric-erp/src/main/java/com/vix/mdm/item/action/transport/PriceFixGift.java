package com.vix.mdm.item.action.transport;

import java.io.Serializable;

public class PriceFixGift implements Serializable{

	private static final long serialVersionUID = 1L;

	/** 物料id*/
	private String id;
	/** 物料名称 */
	private String name;
	/** 物料规格 */
	private String specification;
	/** 价格 */
	private Double price;
	/** 数量 */
	private Double count;
	
	public PriceFixGift(){}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}
}
