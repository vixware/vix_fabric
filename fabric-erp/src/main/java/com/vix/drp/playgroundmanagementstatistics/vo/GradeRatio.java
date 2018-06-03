/**
 * 
 */
package com.vix.drp.playgroundmanagementstatistics.vo;

/**
 * 会员级别比例
 * 
 * com.vix.drp.playgroundmanagementstatistics.vo.GroupMembers
 *
 * @author bjitzhang
 *
 * @date 2014年8月1日
 */
public class GradeRatio {
	/**
	 * 级别
	 */
	private String grade;
	/**
	 * 比例
	 */
	private Double proportion;

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Double getProportion() {
		return proportion;
	}

	public void setProportion(Double proportion) {
		this.proportion = proportion;
	}

}
