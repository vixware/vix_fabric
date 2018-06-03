package com.vix.crm.business.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 店铺销售信息 zhanghaibing
 *
 * com.vix.crm.business.entity.StoreSalesInformation
 *
 * 2015年2月6日
 */
public class StoreSalesInformation extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 购买日期
	 */
	private Date buyDate;
	/**
	 * 总金额
	 */
	private Double totalAmount;
	/**
	 * 首次购买金额
	 */
	private Double firstOrderAmount;
	/**
	 * 首次购买订单数
	 */
	private Long firstOrderNum;
	/**
	 * 订单均价
	 */
	private Double firstOrderPrice;
	/**
	 * 回头购买金额
	 */
	private Double buyBackOrderAmount;
	/**
	 * 回头购买订单数
	 */
	private Long buyBackOrderNum;
	/**
	 * 回头订单均价
	 */
	private Double buyBackOrderPrice;

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getFirstOrderAmount() {
		return firstOrderAmount;
	}

	public void setFirstOrderAmount(Double firstOrderAmount) {
		this.firstOrderAmount = firstOrderAmount;
	}

	public Double getFirstOrderPrice() {
		return firstOrderPrice;
	}

	public void setFirstOrderPrice(Double firstOrderPrice) {
		this.firstOrderPrice = firstOrderPrice;
	}

	public Double getBuyBackOrderAmount() {
		return buyBackOrderAmount;
	}

	public void setBuyBackOrderAmount(Double buyBackOrderAmount) {
		this.buyBackOrderAmount = buyBackOrderAmount;
	}

	public Double getBuyBackOrderPrice() {
		return buyBackOrderPrice;
	}

	public void setBuyBackOrderPrice(Double buyBackOrderPrice) {
		this.buyBackOrderPrice = buyBackOrderPrice;
	}

	public Long getFirstOrderNum() {
		return firstOrderNum;
	}

	public void setFirstOrderNum(Long firstOrderNum) {
		this.firstOrderNum = firstOrderNum;
	}

	public Long getBuyBackOrderNum() {
		return buyBackOrderNum;
	}

	public void setBuyBackOrderNum(Long buyBackOrderNum) {
		this.buyBackOrderNum = buyBackOrderNum;
	}

}
