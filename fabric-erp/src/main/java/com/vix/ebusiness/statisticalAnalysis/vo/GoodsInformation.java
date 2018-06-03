/**
 * 
 */
package com.vix.ebusiness.statisticalAnalysis.vo;

/**
 * 商品销售统计 com.vix.ebusiness.statisticalAnalysis.vo.GoodsInformation
 *
 * @author bjitzhang
 *
 * @date 2015年3月6日
 */
public class GoodsInformation {
	/**
	 * 名称
	 */
	private String goodsName;
	/**
	 * 销售额
	 */
	private Double salesAmount;

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Double getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(Double salesAmount) {
		this.salesAmount = salesAmount;
	}

}
