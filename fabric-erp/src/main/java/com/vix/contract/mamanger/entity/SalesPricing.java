package com.vix.contract.mamanger.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.sales.quotes.entity.SalesQuotationItem;
/**
 * @ClassName: SalesPricing
 * @Description: 合同销售定价条件
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-12-30
 */
public class SalesPricing extends BaseEntity {

	private static final long serialVersionUID = 1482456870778679154L;
	
	/** 物料Id */
	private String itemId;	
	
	/** 采购组织 */
	private String purchasingOrganization;
	
	/** 描述 */
	private String describe1;
	
	/** 采购数量起 */
	private Double minCount;
	
	/** 采购数量止 */
	private Double maxCount;
	
	/** 定价类型 */
	private String pricingType;
	
	/** 定价等级*/
	private String priceLevel;
	
	/** 计算类型*/
	private String calculationType;
	
	/** 定价类别*/
	private String pricingCategory;
	
	/** 价格 */
	private Double price;
	
	/** 折扣 */
	private Double discount;
	
	/** 有效时间 */
	private Date validDate;
	
	/** 开始时间 */
	private Date startDate;
	
	/** 结束时间*/
	private Date endDate;
	
	/** 合同 */
	private Contract contract;
	
	/** 客户 */
	private CustomerAccount customerAccount;
	
	/** 报价单明细 */
	private Set<SalesQuotationItem> salesQuotationItems = new HashSet<SalesQuotationItem>();

	
	public SalesPricing() {
		super();
	}

	public SalesPricing(String id) {
		super();
		setId(id);
	}
	
	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}


	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getPurchasingOrganization() {
		return purchasingOrganization;
	}

	public void setPurchasingOrganization(String purchasingOrganization) {
		this.purchasingOrganization = purchasingOrganization;
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

	public String getDescribe1() {
		return describe1;
	}

	public void setDescribe1(String describe1) {
		this.describe1 = describe1;
	}

	public String getPricingType() {
		return pricingType;
	}

	public void setPricingType(String pricingType) {
		this.pricingType = pricingType;
	}

	public String getPriceLevel() {
		return priceLevel;
	}

	public void setPriceLevel(String priceLevel) {
		this.priceLevel = priceLevel;
	}

	public String getCalculationType() {
		return calculationType;
	}

	public void setCalculationType(String calculationType) {
		this.calculationType = calculationType;
	}

	public String getPricingCategory() {
		return pricingCategory;
	}

	public void setPricingCategory(String pricingCategory) {
		this.pricingCategory = pricingCategory;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public Double getMinCount() {
		return minCount;
	}

	public void setMinCount(Double minCount) {
		this.minCount = minCount;
	}

	public Double getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Double maxCount) {
		this.maxCount = maxCount;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public Set<SalesQuotationItem> getSalesQuotationItems() {
		return salesQuotationItems;
	}

	public void setSalesQuotationItems(Set<SalesQuotationItem> salesQuotationItems) {
		this.salesQuotationItems = salesQuotationItems;
	}

}
