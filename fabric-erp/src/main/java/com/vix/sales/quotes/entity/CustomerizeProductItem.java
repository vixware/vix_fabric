package com.vix.sales.quotes.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.item.entity.Item;

/**
 * 客户定制产品需求明细
 * 定制化产品需求
 * 用户个性化需求在设计部门转换为定制BOM，每个零件的定制需求，按照业务与设计，分解到设计部门及外协的工作进行分别报价
 * @author Administrator
 *
 */
public class CustomerizeProductItem extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 单据成组编码 */
	private String groupCode;
	/** 物料编码 */
	private Item item;
	/** 销售数量 */
	private Double amount;
	/** 辅计算量 */
	private String assitAmount;
	/** 计量单位 */
	private String unit;
	/** 辅助计量单位 */
	private String assitUnit;
	/** 主辅计量单位换算率 */
	private Double unitExchange;
	/** 需求项内容 */
	private String requirementContent;
	/** 负责人 */
	private Employee charger;
	/** 负责部门 */
	private String chargeDepartment;
	/** 客户需求 */
	private CustomerizeProduct customerizeProduct;
	
	public CustomerizeProductItem(){}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void setCharger(Employee charger) {
		this.charger = charger;
	}

	public Employee getCharger() {
		return charger;
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

	public String getRequirementContent() {
		return requirementContent;
	}

	public void setRequirementContent(String requirementContent) {
		this.requirementContent = requirementContent;
	}

	public String getChargeDepartment() {
		return chargeDepartment;
	}

	public void setChargeDepartment(String chargeDepartment) {
		this.chargeDepartment = chargeDepartment;
	}

	public CustomerizeProduct getCustomerizeProduct() {
		return customerizeProduct;
	}

	public void setCustomerizeProduct(CustomerizeProduct customerizeProduct) {
		this.customerizeProduct = customerizeProduct;
	}
}
