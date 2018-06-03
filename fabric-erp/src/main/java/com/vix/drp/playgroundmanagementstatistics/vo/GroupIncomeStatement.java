/**
 * 
 */
package com.vix.drp.playgroundmanagementstatistics.vo;

import java.util.Date;

/**
 * 集团收入报表 com.vix.drp.playgroundmanagementstatistics.vo.GroupIncomeStatement
 *
 * @author bjitzhang
 *
 * @date 2014年8月1日
 */
public class GroupIncomeStatement {
	/**
	 * 店铺名称
	 */
	private String storename;
	/**
	 * 日期
	 */
	private Date salesDate;
	/**
	 * 售币金额
	 */
	private Double saleCoinAmount;
	/**
	 * 餐饮销售金额
	 */
	private Double cateringSalesAmount;
	/**
	 * 商品销售金额
	 */
	private Double goodsSalesAmount;
	/**
	 * 总计
	 */
	private Double total;

	/**
	 * 现金
	 */
	private Double cash;
	/**
	 * POS机
	 */
	private Double pos;
	/**
	 * 团购
	 */
	private Double groupBuying;

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public Date getSalesDate() {
		return salesDate;
	}

	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}

	public Double getSaleCoinAmount() {
		return saleCoinAmount;
	}

	public void setSaleCoinAmount(Double saleCoinAmount) {
		this.saleCoinAmount = saleCoinAmount;
	}

	public Double getCateringSalesAmount() {
		return cateringSalesAmount;
	}

	public void setCateringSalesAmount(Double cateringSalesAmount) {
		this.cateringSalesAmount = cateringSalesAmount;
	}

	public Double getGoodsSalesAmount() {
		return goodsSalesAmount;
	}

	public void setGoodsSalesAmount(Double goodsSalesAmount) {
		this.goodsSalesAmount = goodsSalesAmount;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getCash() {
		return cash;
	}

	public void setCash(Double cash) {
		this.cash = cash;
	}

	public Double getPos() {
		return pos;
	}

	public void setPos(Double pos) {
		this.pos = pos;
	}

	public Double getGroupBuying() {
		return groupBuying;
	}

	public void setGroupBuying(Double groupBuying) {
		this.groupBuying = groupBuying;
	}

}
