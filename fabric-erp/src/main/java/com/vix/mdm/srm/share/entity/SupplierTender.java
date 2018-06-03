package com.vix.mdm.srm.share.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.purchase.tender.entity.PurchaseTender;

/**
 * @Description: 供应商投标
 * @author ivan 
 * @date 2013-09-25
 */
public class SupplierTender extends BaseEntity {

	private static final long serialVersionUID = -1601105743618873349L;
	/** 投标项目编码 */
	private String tenderProjectCode;
	/** 投标公司编码 */
	private String tenderCode;
	/** 投标公司名称 */
	private String tenderName;
	/** 投标内容 */
	private String content;
	/** 投标类型 */
	private String type;
	/** 投标报价 */
	private Double quote;
	/** 币种 */
	private String currency;
	/** 明细 */
	private Set<SupplierTenderItems> supplierTenderItems = new HashSet<SupplierTenderItems>();
	/** 供应商 */
	private Supplier supplier;
	/** 招标 */
	private PurchaseTender purchaseTender;

	public String getTenderProjectCode() {
		return tenderProjectCode;
	}

	public void setTenderProjectCode(String tenderProjectCode) {
		this.tenderProjectCode = tenderProjectCode;
	}

	public String getTenderCode() {
		return tenderCode;
	}

	public void setTenderCode(String tenderCode) {
		this.tenderCode = tenderCode;
	}

	public String getTenderName() {
		return tenderName;
	}

	public void setTenderName(String tenderName) {
		this.tenderName = tenderName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<SupplierTenderItems> getSupplierTenderItems() {
		return supplierTenderItems;
	}

	public void setSupplierTenderItems(Set<SupplierTenderItems> supplierTenderItems) {
		this.supplierTenderItems = supplierTenderItems;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public PurchaseTender getPurchaseTender() {
		return purchaseTender;
	}

	public void setPurchaseTender(PurchaseTender purchaseTender) {
		this.purchaseTender = purchaseTender;
	}

	public Double getQuote() {
		return quote;
	}

	public void setQuote(Double quote) {
		this.quote = quote;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
