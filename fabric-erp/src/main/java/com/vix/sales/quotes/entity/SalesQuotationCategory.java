package com.vix.sales.quotes.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 报价单分类
 * @author Administrator
 *
 */
public class SalesQuotationCategory extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 名称 */
	private String name;
	/** 上级分类 */
	private SalesQuotationCategory parentSalesQuotationCategory;
	/** 下级分类 */
	private Set<SalesQuotationCategory> subSalesQuotationCategorys = new HashSet<SalesQuotationCategory>();
	/** 分类下报价单模板 */
	private Set<ProjectQuotationTemplate> projectQuotationTemplates = new HashSet<ProjectQuotationTemplate>();
	
	public SalesQuotationCategory(){}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public SalesQuotationCategory getParentSalesQuotationCategory() {
		return parentSalesQuotationCategory;
	}

	public void setParentSalesQuotationCategory(
			SalesQuotationCategory parentSalesQuotationCategory) {
		this.parentSalesQuotationCategory = parentSalesQuotationCategory;
	}

	public Set<SalesQuotationCategory> getSubSalesQuotationCategorys() {
		return subSalesQuotationCategorys;
	}

	public void setSubSalesQuotationCategorys(
			Set<SalesQuotationCategory> subSalesQuotationCategorys) {
		this.subSalesQuotationCategorys = subSalesQuotationCategorys;
	}

	public Set<ProjectQuotationTemplate> getProjectQuotationTemplates() {
		return projectQuotationTemplates;
	}

	public void setProjectQuotationTemplates(
			Set<ProjectQuotationTemplate> projectQuotationTemplates) {
		this.projectQuotationTemplates = projectQuotationTemplates;
	}
}
