package com.vix.crm.market.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/** 礼品 */
public class CrmGift extends BaseEntity {
 
	private static final long serialVersionUID = 1L;

	/** 名称 */
	private String name;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 购买数量 */
	private Long buyCount;
	/** 单价 */
	private Double unitPrice;
	/** 库存数量 */
	private Long stockNumber;
	/** 备注 */
	private String memo;
	/** 礼品厂商 */
	private String giftCompany;
	/** 接洽人 */
	private String contactPerson;
	/** 联系方式 */
	private String contactStyle;
	/** 礼品领用 */
	private Set<CrmGiftRequisition> crmGiftRequisitions = new HashSet<CrmGiftRequisition>();

	public CrmGift(){}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public Long getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Long buyCount) {
		this.buyCount = buyCount;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Long getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(Long stockNumber) {
		this.stockNumber = stockNumber;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getGiftCompany() {
		return giftCompany;
	}

	public void setGiftCompany(String giftCompany) {
		this.giftCompany = giftCompany;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactStyle() {
		return contactStyle;
	}

	public void setContactStyle(String contactStyle) {
		this.contactStyle = contactStyle;
	}

	public Set<CrmGiftRequisition> getCrmGiftRequisitions() {
		return crmGiftRequisitions;
	}

	public void setCrmGiftRequisitions(Set<CrmGiftRequisition> crmGiftRequisitions) {
		this.crmGiftRequisitions = crmGiftRequisitions;
	}
}
