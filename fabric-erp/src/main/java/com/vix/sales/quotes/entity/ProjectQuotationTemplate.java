package com.vix.sales.quotes.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseBOEntity;

/** 项目式报价模板  */
public class ProjectQuotationTemplate extends BaseBOEntity {
 
	private static final long serialVersionUID = 1L;
	/** 单据名称 */
	private String name;
	/** 业务类型 */
	private String bizType;
	/** 单据类型 */
	private String formType;
	/** 需求内容 */
	private String requirementContent;
	/** 币种 */
	private String currency;
	/** 税率 */
	private Double tax;
	/** 金额 */
	private Double amount;
	/** 项目式报价明细 */
	private Set<ProjectQuotationTemplateItem> projectQuotationTemplateItems = new HashSet<ProjectQuotationTemplateItem>();
	
	public ProjectQuotationTemplate(){}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
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

	public String getRequirementContent() {
		return requirementContent;
	}

	public void setRequirementContent(String requirementContent) {
		this.requirementContent = requirementContent;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Set<ProjectQuotationTemplateItem> getProjectQuotationTemplateItems() {
		return projectQuotationTemplateItems;
	}

	public void setProjectQuotationTemplateItems(
			Set<ProjectQuotationTemplateItem> projectQuotationTemplateItems) {
		this.projectQuotationTemplateItems = projectQuotationTemplateItems;
	}
}
