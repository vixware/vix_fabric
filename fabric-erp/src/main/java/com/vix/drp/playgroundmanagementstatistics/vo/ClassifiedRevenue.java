/**
 * 
 */
package com.vix.drp.playgroundmanagementstatistics.vo;

/**
 * 设备投币数排行 com.vix.drp.playgroundmanagementstatistics.vo.ClassifiedRevenue
 *
 * @author bjitzhang
 *
 * @date 2014年8月5日
 */
public class ClassifiedRevenue {
	/**
	 * 名称
	 */
	private String macName;
	/**
	 * 投币数
	 */
	private Double coinNumber;

	public String getMacName() {
		return macName;
	}

	public void setMacName(String macName) {
		this.macName = macName;
	}

	public Double getCoinNumber() {
		return coinNumber;
	}

	public void setCoinNumber(Double coinNumber) {
		this.coinNumber = coinNumber;
	}

}
