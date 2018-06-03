/**
 * 
 */
package com.vix.drp.playgroundmanagementstatistics.vo;

/**
 * 设备销售占比
 * 
 * com.vix.drp.playgroundmanagementstatistics.vo.GroupMembers
 *
 * @author bjitzhang
 *
 * @date 2014年8月1日
 */
public class EquipmentSales {
	/**
	 * 机台大类
	 */
	private String type;
	/**
	 * 项目
	 */
	private String project;
	/**
	 * 投币数
	 */
	private Double coinNumber;

	/**
	 * 总币数
	 */
	private Long totalCoin;
	/**
	 * 项目间比例
	 */
	private Long ratioBetweenProjects;
	/**
	 * 总比例
	 */
	private Long totalProportion;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Double getCoinNumber() {
		return coinNumber;
	}

	public void setCoinNumber(Double coinNumber) {
		this.coinNumber = coinNumber;
	}

	public Long getTotalCoin() {
		return totalCoin;
	}

	public void setTotalCoin(Long totalCoin) {
		this.totalCoin = totalCoin;
	}

	public Long getRatioBetweenProjects() {
		return ratioBetweenProjects;
	}

	public void setRatioBetweenProjects(Long ratioBetweenProjects) {
		this.ratioBetweenProjects = ratioBetweenProjects;
	}

	public Long getTotalProportion() {
		return totalProportion;
	}

	public void setTotalProportion(Long totalProportion) {
		this.totalProportion = totalProportion;
	}

}
