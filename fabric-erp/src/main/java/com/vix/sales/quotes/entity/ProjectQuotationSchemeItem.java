package com.vix.sales.quotes.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.item.entity.Item;

/** 项目式报价方案明细 树形结构 */
public class ProjectQuotationSchemeItem extends BaseEntity {
 
	private static final long serialVersionUID = 1L;
	/** 单据成组编码 */
	private String groupCode;
	/** 销售或者服务内容项 */
	private String contentItem;
	/** 项类型 */
	private String itemType;
	/** 报价负责人 */
	private Employee quotationCharger;
	/** 报价负责部门 */
	private String quotationDepartment;
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
	/** 单价 */
	private Double price;
	/** 折扣 */
	private Double discount;
	/** 项目式报价*/
	private ProjectQuotationScheme projectQuotationScheme;
	/** 上级项目式报价明细 */
	private ProjectQuotationSchemeItem parentProjectQuotationSchemeItem;
	/** 下级项目式报价明细 */
	private Set<ProjectQuotationSchemeItem> subProjectQuotationSchemeItems = new HashSet<ProjectQuotationSchemeItem>();
	
	public ProjectQuotationSchemeItem(){}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

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

	public Employee getQuotationCharger() {
		return quotationCharger;
	}

	public void setQuotationCharger(Employee quotationCharger) {
		this.quotationCharger = quotationCharger;
	}

	public String getQuotationDepartment() {
		return quotationDepartment;
	}

	public void setQuotationDepartment(String quotationDepartment) {
		this.quotationDepartment = quotationDepartment;
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

	public ProjectQuotationScheme getProjectQuotationScheme() {
		return projectQuotationScheme;
	}

	public void setProjectQuotationScheme(ProjectQuotationScheme projectQuotationScheme) {
		this.projectQuotationScheme = projectQuotationScheme;
	}

	public ProjectQuotationSchemeItem getParentProjectQuotationSchemeItem() {
		return parentProjectQuotationSchemeItem;
	}

	public void setParentProjectQuotationSchemeItem(
			ProjectQuotationSchemeItem parentProjectQuotationSchemeItem) {
		this.parentProjectQuotationSchemeItem = parentProjectQuotationSchemeItem;
	}

	public Set<ProjectQuotationSchemeItem> getSubProjectQuotationSchemeItems() {
		return subProjectQuotationSchemeItems;
	}

	public void setSubProjectQuotationSchemeItems(
			Set<ProjectQuotationSchemeItem> subProjectQuotationSchemeItems) {
		this.subProjectQuotationSchemeItems = subProjectQuotationSchemeItems;
	}
}
