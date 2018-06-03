package com.vix.drp.playgroundmanagementstatistics.vo;

/**
 * 水吧销售统计
 * 
 * com.vix.drp.playgroundmanagementstatistics.vo.WaterSalesStatistics
 *
 * @author bjitzhang
 *
 * @date 2014年8月1日
 */
public class WaterSalesStatistics {
	/**
	 * 杂项套餐名称
	 */
	private String name;
	/**
	 * 销售数量
	 */
	private Double salesAmount;
	/**
	 * 原金额
	 */
	private Double oldAmount;
	/**
	 * 实收金额
	 */
	private Double paidAmount;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(Double salesAmount) {
		this.salesAmount = salesAmount;
	}

	public Double getOldAmount() {
		return oldAmount;
	}

	public void setOldAmount(Double oldAmount) {
		this.oldAmount = oldAmount;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
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
