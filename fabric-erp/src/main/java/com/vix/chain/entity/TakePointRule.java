/**
 * 
 */
package com.vix.chain.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 积分规则
 * 
 * @author zhanghaibing
 * 
 * @date 2013-5-21
 */
public class TakePointRule extends BaseEntity {

	private static final long serialVersionUID = -8071516445127551159L;
	/**
	 * 经销商/门店编码
	 */
	private String distributorCode;
	/**
	 * 经销商/门店名称
	 */
	private String distributorName;
	/**
	 * 经销商/门店类型
	 */
	private String distributorType;
	/**
	 * 客户类型
	 */
	private String customerType;
	/**
	 * 物料编码
	 */
	private String itemCode;
	/**
	 * 物料名称
	 */
	private String itemName;
	/**
	 * 价格
	 */
	private Double price;
	/**
	 * 折扣
	 */
	private Double discount;
	/**
	 * 积分
	 */
	private Double points;
	/**
	 * 地域
	 */
	private String field;
	/**
	 * 分类
	 */
	private String catalog;
	/**
	 * 价格区间-开始
	 */
	private Double startPrice;
	/**
	 * 价格区间-截止
	 */
	private Double endPrice;
	/**
	 * 价格区间类型
	 */
	private String pricePeroidType;
	/**
	 * 积分优先级
	 */
	private Integer priority;

	public String getDistributorCode() {
		return distributorCode;
	}

	public void setDistributorCode(String distributorCode) {
		this.distributorCode = distributorCode;
	}

	public String getDistributorName() {
		return distributorName;
	}

	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}

	public String getDistributorType() {
		return distributorType;
	}

	public void setDistributorType(String distributorType) {
		this.distributorType = distributorType;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getPoints() {
		return points;
	}

	public void setPoints(Double points) {
		this.points = points;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public Double getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(Double startPrice) {
		this.startPrice = startPrice;
	}

	public Double getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(Double endPrice) {
		this.endPrice = endPrice;
	}

	public String getPricePeroidType() {
		return pricePeroidType;
	}

	public void setPricePeroidType(String pricePeroidType) {
		this.pricePeroidType = pricePeroidType;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
}
