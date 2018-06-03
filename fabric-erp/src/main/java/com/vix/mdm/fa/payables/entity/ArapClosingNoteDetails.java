package com.vix.mdm.fa.payables.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @Description: 收付款单据明细
 * @author ivan
 * @date 2014-01-10
 */
public class ArapClosingNoteDetails extends BaseEntity {

	private static final long serialVersionUID = -5472381442017109316L;
	/** 款项类型号 */
	private String aparTyple;
	/** 预收预付标志 */
	private String isPrePay;
	/** 客户或供应商编码 */
	private String cusOrVendorCode;
	/** 本币金额 */
	private Double amount;
	/** 原币金额 */
	private Double originalAmount;
	/** 本币余额 */
	private Double remainSumAmount;
	/** 原币余额 */
	private Double origianlRemainSumAmount;
	/** 科目编码 */
	private String accountCode;
	/** 项目大类编码 */
	private String itemCatalogClass;
	/** 项目编码 */
	private String itemCode;
	/** 业务员编码 */
	private String personCode;
	/** 收付款单编码 */
	private String orderCode;
	/** 项目名称 */
	private String itemName;
	/** 合同名称 */
	private String cconname;
	/** 数量 */
	private Double iamt_s;
	/** 数量余额 */
	private Double iramt_s;
	/** 收付款单类型 */
	private String iordertype;
	/** 主表 */
	private ArapClosingNote arapClosingNote;

	public String getAparTyple() {
		return aparTyple;
	}

	public void setAparTyple(String aparTyple) {
		this.aparTyple = aparTyple;
	}

	public String getIsPrePay() {
		return isPrePay;
	}

	public void setIsPrePay(String isPrePay) {
		this.isPrePay = isPrePay;
	}

	public String getCusOrVendorCode() {
		return cusOrVendorCode;
	}

	public void setCusOrVendorCode(String cusOrVendorCode) {
		this.cusOrVendorCode = cusOrVendorCode;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getOriginalAmount() {
		return originalAmount;
	}

	public void setOriginalAmount(Double originalAmount) {
		this.originalAmount = originalAmount;
	}

	public Double getRemainSumAmount() {
		return remainSumAmount;
	}

	public void setRemainSumAmount(Double remainSumAmount) {
		this.remainSumAmount = remainSumAmount;
	}

	public Double getOrigianlRemainSumAmount() {
		return origianlRemainSumAmount;
	}

	public void setOrigianlRemainSumAmount(Double origianlRemainSumAmount) {
		this.origianlRemainSumAmount = origianlRemainSumAmount;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getItemCatalogClass() {
		return itemCatalogClass;
	}

	public void setItemCatalogClass(String itemCatalogClass) {
		this.itemCatalogClass = itemCatalogClass;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCconname() {
		return cconname;
	}

	public void setCconname(String cconname) {
		this.cconname = cconname;
	}

	public Double getIamt_s() {
		return iamt_s;
	}

	public void setIamt_s(Double iamt_s) {
		this.iamt_s = iamt_s;
	}

	public Double getIramt_s() {
		return iramt_s;
	}

	public void setIramt_s(Double iramt_s) {
		this.iramt_s = iramt_s;
	}

	public String getIordertype() {
		return iordertype;
	}

	public void setIordertype(String iordertype) {
		this.iordertype = iordertype;
	}

	public ArapClosingNote getArapClosingNote() {
		return arapClosingNote;
	}

	public void setArapClosingNote(ArapClosingNote arapClosingNote) {
		this.arapClosingNote = arapClosingNote;
	}

}
