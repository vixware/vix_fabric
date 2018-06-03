/**
 * 
 */
package com.vix.drp.playgroundmanagementstatistics.vo;

/**
 * 消费人次报表 com.vix.drp.playgroundmanagementstatistics.vo.ConsumerPeopleReport
 *
 * @author bjitzhang
 *
 * @date 2014年8月1日
 */
public class ConsumerPeopleReport {
	/**
	 * 机台名称
	 */
	private String macName;
	/**
	 * 总投币数
	 */
	private Double tatal;
	/**
	 * 日均投币数
	 */
	private Double averageNumberCoins;
	/**
	 * 人均投币数
	 */
	private Double perCapitaNumberCoins;

	public String getMacName() {
		return macName;
	}

	public void setMacName(String macName) {
		this.macName = macName;
	}

	public Double getTatal() {
		return tatal;
	}

	public void setTatal(Double tatal) {
		this.tatal = tatal;
	}

	public Double getAverageNumberCoins() {
		return averageNumberCoins;
	}

	public void setAverageNumberCoins(Double averageNumberCoins) {
		this.averageNumberCoins = averageNumberCoins;
	}

	public Double getPerCapitaNumberCoins() {
		return perCapitaNumberCoins;
	}

	public void setPerCapitaNumberCoins(Double perCapitaNumberCoins) {
		this.perCapitaNumberCoins = perCapitaNumberCoins;
	}

}
