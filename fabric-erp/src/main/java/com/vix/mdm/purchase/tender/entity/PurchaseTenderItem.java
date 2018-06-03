package com.vix.mdm.purchase.tender.entity;

import com.vix.common.share.entity.BaseEntity;

public class PurchaseTenderItem extends BaseEntity {

	private static final long serialVersionUID = -5363495551594995832L;
	/** 招标项目编号 */
	private String tenderCode;
	/** 招标项目主题 */
	private String tenderItemName;
	/** 招标项目内容 */
	private String tenderItemContent;
	/** 招标 */
	private PurchaseTender purchaseTender;
	
	public String getTenderCode() {
		return tenderCode;
	}
	public void setTenderCode(String tenderCode) {
		this.tenderCode = tenderCode;
	}
	public String getTenderItemName() {
		return tenderItemName;
	}
	public void setTenderItemName(String tenderItemName) {
		this.tenderItemName = tenderItemName;
	}
	public String getTenderItemContent() {
		return tenderItemContent;
	}
	public void setTenderItemContent(String tenderItemContent) {
		this.tenderItemContent = tenderItemContent;
	}
	public PurchaseTender getPurchaseTender() {
		return purchaseTender;
	}
	public void setPurchaseTender(PurchaseTender purchaseTender) {
		this.purchaseTender = purchaseTender;
	}
	
}
