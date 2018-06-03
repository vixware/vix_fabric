package com.vix.sales.quotes.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.common.share.entity.MeasureUnitGroup;
import com.vix.mdm.item.entity.Item;

/** 项目式报价模板明细 树形结构  */
public class ProjectQuotationTemplateItem extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	/** 销售或者服务内容项 */
	private String contentItem;
	/** 项类型 */
	private String itemType;
	/** 物料 */
	private Item item;
	/** 数量 */
	private Double amount;
	/** 辅计算量 */
	private String assitAmount;
	/** 计量单位 */
	private String unit;
	/** 辅助计量单位 */
	private String assitUnit;
	/** 主辅计量单位换算率 */
	private Double unitExchange;
	/** 税率 */
	private Double tax;
	/** 税额 */
	private Double taxAmount;
	/** 单价 */
	private Double price;
	/** 折扣 */
	private Double discount;
	/** 单位组 */
	private MeasureUnitGroup measureUnitGroup;
	/** 主计量单位 */
	private MeasureUnit measureUnit;
	/** 辅助计量单位 */
	private MeasureUnit assitMeasureUnit;
	/** 项目式报价模板*/
	private ProjectQuotationTemplate projectQuotationTemplate;
	/** 上级项目式报价明细 */
	private ProjectQuotationTemplateItem parentProjectQuotationTemplateItem;
	/** 下级项目式报价明细 */
	private Set<ProjectQuotationTemplateItem> subProjectQuotationTemplateItems = new HashSet<ProjectQuotationTemplateItem>();
	
	public ProjectQuotationTemplateItem(){}

	public String getContentItem() {
		return contentItem;
	}

	public void setContentItem(String contentItem) {
		this.contentItem = contentItem;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getAssitAmount() {
		return assitAmount;
	}

	public void setAssitAmount(String assitAmount) {
		this.assitAmount = assitAmount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getAssitUnit() {
		return assitUnit;
	}

	public void setAssitUnit(String assitUnit) {
		this.assitUnit = assitUnit;
	}

	public Double getUnitExchange() {
		return unitExchange;
	}

	public void setUnitExchange(Double unitExchange) {
		this.unitExchange = unitExchange;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public ProjectQuotationTemplate getProjectQuotationTemplate() {
		return projectQuotationTemplate;
	}

	public void setProjectQuotationTemplate(ProjectQuotationTemplate projectQuotationTemplate) {
		this.projectQuotationTemplate = projectQuotationTemplate;
	}

	public ProjectQuotationTemplateItem getParentProjectQuotationTemplateItem() {
		return parentProjectQuotationTemplateItem;
	}

	public void setParentProjectQuotationTemplateItem(
			ProjectQuotationTemplateItem parentProjectQuotationTemplateItem) {
		this.parentProjectQuotationTemplateItem = parentProjectQuotationTemplateItem;
	}

	public Set<ProjectQuotationTemplateItem> getSubProjectQuotationTemplateItems() {
		return subProjectQuotationTemplateItems;
	}

	public void setSubProjectQuotationTemplateItems(
			Set<ProjectQuotationTemplateItem> subProjectQuotationTemplateItems) {
		this.subProjectQuotationTemplateItems = subProjectQuotationTemplateItems;
	}

	public MeasureUnitGroup getMeasureUnitGroup() {
		return measureUnitGroup;
	}

	public void setMeasureUnitGroup(MeasureUnitGroup measureUnitGroup) {
		this.measureUnitGroup = measureUnitGroup;
	}

	public MeasureUnit getMeasureUnit() {
		return measureUnit;
	}

	public void setMeasureUnit(MeasureUnit measureUnit) {
		this.measureUnit = measureUnit;
	}

	public MeasureUnit getAssitMeasureUnit() {
		return assitMeasureUnit;
	}

	public void setAssitMeasureUnit(MeasureUnit assitMeasureUnit) {
		this.assitMeasureUnit = assitMeasureUnit;
	}
	
}