/**
 * 
 */
package com.vix.drp.playgroundmanagementstatistics.vo;

/**
 * 集团会员分布
 * 
 * com.vix.drp.playgroundmanagementstatistics.vo.GroupMembers
 *
 * @author bjitzhang
 *
 * @date 2014年8月1日
 */
public class GroupMembers {
	/**
	 * 区域
	 */
	private String area;
	/**
	 * 比例
	 */
	private Double proportion;

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Double getProportion() {
		return proportion;
	}

	public void setProportion(Double proportion) {
		this.proportion = proportion;
	}

}
