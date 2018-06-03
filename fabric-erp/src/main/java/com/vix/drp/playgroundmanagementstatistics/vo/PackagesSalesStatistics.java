/**
 * 
 */
package com.vix.drp.playgroundmanagementstatistics.vo;

/**
 * 套餐销售统计
 * 
 * com.vix.drp.playgroundmanagementstatistics.vo.WaterSalesStatistics
 *
 * @author bjitzhang
 *
 * @date 2014年8月1日
 */
public class PackagesSalesStatistics {
	/**
	 * 套餐名称
	 */
	private String name;
	/**
	 * 销售数量
	 */
	private Double salesAmount;
	/**
	 * 总金额
	 */
	private Double totalAmount;
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

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
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
