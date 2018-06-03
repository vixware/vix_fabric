/**
 * 
 */
package com.vix.drp.playgroundmanagementstatistics.vo;

/**
 * 会员年龄段统计
 * 
 * com.vix.drp.playgroundmanagementstatistics.vo.GroupMembers
 *
 * @author bjitzhang
 *
 * @date 2014年8月1日
 */
public class AgeStatistic {
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 数量
	 */
	private Double amount;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
