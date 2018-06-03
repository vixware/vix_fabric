package com.vix.mdm.fa.payables.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @Description: 应收应付单明细
 * @author ivan
 * @date 2014-01-09
 */
public class ArapVoucherDetails extends BaseEntity {

	private static final long serialVersionUID = 3968378401794108767L;
	/** 业务员编码 */
	private String personCode;
	/** 项目大类编码 */
	private String itemCatalogClass;
	/** 项目编码 */
	private String itemCode;
	/** 摘要 */
	private String digest;
	/** 对应科目编码 */
	private String accountCode;
	/** 币种名称 */
	private String currencyName;
	/** 汇率 */
	private Double exchangeRate;
	/** 借贷方向 */
	private String debitOrCredit;
	/** 本币金额 */
	private Double amount;
	/** 原币金额 */
	private Double orginalAmount;
	/** 项目名称 */
	private String cItemName;
	/** 主表 */
	private ArapVoucher arapVoucher;

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
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

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getDebitOrCredit() {
		return debitOrCredit;
	}

	public void setDebitOrCredit(String debitOrCredit) {
		this.debitOrCredit = debitOrCredit;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getOrginalAmount() {
		return orginalAmount;
	}

	public void setOrginalAmount(Double orginalAmount) {
		this.orginalAmount = orginalAmount;
	}

	public String getcItemName() {
		return cItemName;
	}

	public void setcItemName(String cItemName) {
		this.cItemName = cItemName;
	}

	public ArapVoucher getArapVoucher() {
		return arapVoucher;
	}

	public void setArapVoucher(ArapVoucher arapVoucher) {
		this.arapVoucher = arapVoucher;
	}

}
