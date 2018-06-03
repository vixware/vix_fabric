/**
 * 
 */
package com.vix.drp.playgroundmanagementstatistics.vo;

/**
 * 会员性别比例 com.vix.drp.playgroundmanagementstatistics.vo.SexProportion
 *
 * @author zhanghaibing
 *
 * @date 2015年1月13日
 */
public class SexProportion {
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 比例
	 */
	private Long proportion;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Long getProportion() {
		return proportion;
	}

	public void setProportion(Long proportion) {
		this.proportion = proportion;
	}

}
