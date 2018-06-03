/**
 * 
 */
package com.vix.drp.playgroundmanagementstatistics.vo;

/**
 * 商品统计信息
 * 
 * com.vix.drp.playgroundmanagementstatistics.vo.GroupMembers
 *
 * @author bjitzhang
 *
 * @date 2014年8月1日
 */
public class GoodsStatistics {
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 销售额
	 */
	private Double salesAmount;

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

}
