package com.vix.mdm.purchase;

import com.vix.common.share.entity.BaseBOEntity;

public class PurchaseEntity extends BaseBOEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String pinyin;

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	
	
}
