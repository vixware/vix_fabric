package com.vix.common.share.entity;

public class BaseLanguage extends BaseEntity {
 
	private static final long serialVersionUID = 1L;
	/** 英文缩写 */
	private String englishAbbreviation;
	
	public BaseLanguage(){}

	public String getEnglishAbbreviation() {
		return englishAbbreviation;
	}

	public void setEnglishAbbreviation(String englishAbbreviation) {
		this.englishAbbreviation = englishAbbreviation;
	}
}
