package com.vix.mdm.item.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;

/** 销售属性 */
public class ItemSaleProperties extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 物料编码 */
	private String itemCode;
	/** 物料类型 */
	private String itemType;
	/** 销售组织 */
	private String saleOrg;
	/** 销售组 */
	private String saleGroup;
	/** 销售员 */
	private Employee salePerson;
	/** 交货工厂 */
	private String deliveryFactory;
	/** 渠道 */
	private String channel;
	/** 缺省利润中心 */
	private String defaultMarginCenter;
	/** 缺省利润中心代码 */
	private String defaultMarginCenterCode;
	/** 销售税率 */
	private Double saleTax;
	/** 最小订购量 */
	private Double minRequireAmount;
	/** 最小发货量 */
	private Double minDeliveryAmount;
	/** 销售退回是否需要检验 */
	private String isReturnCheck;
	/** 销售基本计量单位 */
	private String saleBaseUnit;
	/** 销售默认单位 */
	private String saleDefaultUnit;
	/** 销售单位 */
	private String saleUnit;
	/** 销售辅助单位 */
	private String saleAssitUnit;
	/** 产品组 */
	private String productGroup;
	/** 是否提供现金折扣 */
	private String isCashDiscount;
	/** 物料定价组 */
	private String itemPriceGroup;
	/** 成交额回扣组 */
	private String dealAmountCommissionGroup;
	/** 定价参考物料 */
	private String setPriceRefItemCode;
	/** 佣金组 */
	private String commissionGroup;
	/** 科目设置组 */
	private String accountTitleGroup;
	/** 销售科目 */
	private String saleAccountTitle;
	/** 物料 */
	private Item item;
	
	public ItemSaleProperties(){}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getSaleOrg() {
		return saleOrg;
	}

	public void setSaleOrg(String saleOrg) {
		this.saleOrg = saleOrg;
	}

	public String getSaleGroup() {
		return saleGroup;
	}

	public void setSaleGroup(String saleGroup) {
		this.saleGroup = saleGroup;
	}

	public String getSaleDefaultUnit() {
		return saleDefaultUnit;
	}

	public void setSaleDefaultUnit(String saleDefaultUnit) {
		this.saleDefaultUnit = saleDefaultUnit;
	}

	public Employee getSalePerson() {
		return salePerson;
	}

	public void setSalePerson(Employee salePerson) {
		this.salePerson = salePerson;
	}

	public String getDeliveryFactory() {
		return deliveryFactory;
	}

	public void setDeliveryFactory(String deliveryFactory) {
		this.deliveryFactory = deliveryFactory;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
 
	public String getDefaultMarginCenter() {
		return defaultMarginCenter;
	}

	public void setDefaultMarginCenter(String defaultMarginCenter) {
		this.defaultMarginCenter = defaultMarginCenter;
	}
 
	public String getDefaultMarginCenterCode() {
		return defaultMarginCenterCode;
	}

	public void setDefaultMarginCenterCode(String defaultMarginCenterCode) {
		this.defaultMarginCenterCode = defaultMarginCenterCode;
	}

	public Double getSaleTax() {
		return saleTax;
	}

	public void setSaleTax(Double saleTax) {
		this.saleTax = saleTax;
	}

	public Double getMinRequireAmount() {
		return minRequireAmount;
	}

	public void setMinRequireAmount(Double minRequireAmount) {
		this.minRequireAmount = minRequireAmount;
	}

	public Double getMinDeliveryAmount() {
		return minDeliveryAmount;
	}

	public void setMinDeliveryAmount(Double minDeliveryAmount) {
		this.minDeliveryAmount = minDeliveryAmount;
	}

	public String getIsReturnCheck() {
		return isReturnCheck;
	}

	public void setIsReturnCheck(String isReturnCheck) {
		this.isReturnCheck = isReturnCheck;
	}

	public String getSaleBaseUnit() {
		return saleBaseUnit;
	}

	public void setSaleBaseUnit(String saleBaseUnit) {
		this.saleBaseUnit = saleBaseUnit;
	}

	public String getSaleUnit() {
		return saleUnit;
	}

	public void setSaleUnit(String saleUnit) {
		this.saleUnit = saleUnit;
	}

	public String getSaleAssitUnit() {
		return saleAssitUnit;
	}

	public void setSaleAssitUnit(String saleAssitUnit) {
		this.saleAssitUnit = saleAssitUnit;
	}

	public String getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}

	public String getIsCashDiscount() {
		return isCashDiscount;
	}

	public void setIsCashDiscount(String isCashDiscount) {
		this.isCashDiscount = isCashDiscount;
	}

	public String getItemPriceGroup() {
		return itemPriceGroup;
	}

	public void setItemPriceGroup(String itemPriceGroup) {
		this.itemPriceGroup = itemPriceGroup;
	}

	public String getDealAmountCommissionGroup() {
		return dealAmountCommissionGroup;
	}

	public void setDealAmountCommissionGroup(String dealAmountCommissionGroup) {
		this.dealAmountCommissionGroup = dealAmountCommissionGroup;
	}

	public String getSetPriceRefItemCode() {
		return setPriceRefItemCode;
	}

	public void setSetPriceRefItemCode(String setPriceRefItemCode) {
		this.setPriceRefItemCode = setPriceRefItemCode;
	}

	public String getCommissionGroup() {
		return commissionGroup;
	}

	public void setCommissionGroup(String commissionGroup) {
		this.commissionGroup = commissionGroup;
	}

	public String getAccountTitleGroup() {
		return accountTitleGroup;
	}

	public void setAccountTitleGroup(String accountTitleGroup) {
		this.accountTitleGroup = accountTitleGroup;
	}

	public String getSaleAccountTitle() {
		return saleAccountTitle;
	}

	public void setSaleAccountTitle(String saleAccountTitle) {
		this.saleAccountTitle = saleAccountTitle;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
