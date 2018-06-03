package com.vix.sales.quotes.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseBOEntity;
import com.vix.common.share.entity.CurrencyType;

/** 销售报价单  列表显示数据：主题，客户，单据类型，价格，单据日期*/
public class SalesQuotationTemplate extends BaseBOEntity {

	private static final long serialVersionUID = 1L;

	/** 报价主题 */
	private String quoteSubject;
	/** 业务类型 */
	private String bizType;
	/** 单据类型 */
	private String formType;
	/** 报价单类型 */
	private String type;
	/** 币种 */
	private CurrencyType currencyType;
	/** 税率 */
	private Double tax;
	/** 版本 */
	private String version;
	/** 报价单明细 */
	private Set<SalesQuotationTemplateItem> salesQuotationTemplateItems = new HashSet<SalesQuotationTemplateItem>();
	/** 是否为临时对象 临时为：1 实际使用为空 */
	private String isTemp;
	/** 金额 */
	private Double amount;
	
	public SalesQuotationTemplate(){}

	public String getQuoteSubject() {
		return quoteSubject;
	}

	public void setQuoteSubject(String quoteSubject) {
		this.quoteSubject = quoteSubject;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public void setVersion(String version) {
		this.version = version;
	}

	public Set<SalesQuotationTemplateItem> getSalesQuotationTemplateItems() {
		return salesQuotationTemplateItems;
	}

	public void setSalesQuotationTemplateItems(
			Set<SalesQuotationTemplateItem> salesQuotationTemplateItems) {
		this.salesQuotationTemplateItems = salesQuotationTemplateItems;
	}

	@Override
	public String getIsTemp() {
		return isTemp;
	}

	@Override
	public void setIsTemp(String isTemp) {
		this.isTemp = isTemp;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
