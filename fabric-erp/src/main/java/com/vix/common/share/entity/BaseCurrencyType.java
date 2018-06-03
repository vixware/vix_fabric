package com.vix.common.share.entity;

/**
 * 货币类型
 * @author Administrator
 *
 */
public class BaseCurrencyType extends BaseEntity {

	private static final long serialVersionUID = 1L;
 
	/** 英文缩写 */
	private String englishAbbreviation;
	/** 货币符号 */
	private String currencyCode;
	/** 备注 */
	private String remark;
	
	public BaseCurrencyType(){}

	public String getEnglishAbbreviation() {
		return englishAbbreviation;
	}

	public void setEnglishAbbreviation(String englishAbbreviation) {
		this.englishAbbreviation = englishAbbreviation;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
