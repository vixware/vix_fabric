/**
 * 
 */
package com.vix.WebService.vo;

/**
 * @author zhanghaibing
 * 
 * @date 2014-4-30
 */
public class CustomerInformation {
	/**
	 * 会员卡号
	 */
	private String vipcardCode;
	/**
	 * 积分
	 */
	private Long integral;
	/**
	 * 余额
	 */
	private Double residual;

	public String getVipcardCode() {
		return vipcardCode;
	}

	public void setVipcardCode(String vipcardCode) {
		this.vipcardCode = vipcardCode;
	}

	public Long getIntegral() {
		return integral;
	}

	public void setIntegral(Long integral) {
		this.integral = integral;
	}

	public Double getResidual() {
		return residual;
	}

	public void setResidual(Double residual) {
		this.residual = residual;
	}

}
