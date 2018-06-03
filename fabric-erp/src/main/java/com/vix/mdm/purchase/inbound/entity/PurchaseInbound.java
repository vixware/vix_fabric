package com.vix.mdm.purchase.inbound.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.mdm.purchase.PurchaseEntity;

/**
 * 采购入库单
 * 
 * @author Ivan 2013-07-25
 */
public class PurchaseInbound extends PurchaseEntity {

	private static final long serialVersionUID = -6579362645349190351L;

	private String barCode;
	private String skuCode;

	/** 单据成组编码 */
	private String groupCode;
	/** 业务类型 */
	private String businessType;
	/** 单据类型 */
	private String orderType;
	/** 供应商代码 */
	private String supplierCode;
	/** 供应商名称 */
	private String supplierName;
	/** 供应商ID */
	private String supplierId;
	/** 联系人 */
	private String contactPerson;
	/** 采购组织 */
	private String purchaseOrg;
	/** 采购组织id */
	private String purchaseOrgId;
	/** 采购人代码 */
	private String purchasePersonId;
	/** 采购人 */
	private String purchasePerson;
	/** 总额 */
	private Double totalAmount;
	/** 币种 */
	private String currency;
	/** 税率 */
	private Double taxRate;
	/** 过账日期 */
	private Date postingDate;
	/** 交货日期 */
	private Date deliveryDate;
	/** 采购入库明细 */
	private Set<PurchaseInboundItems> purchaseInboundItems = new HashSet<PurchaseInboundItems>();

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getPurchasePersonId() {
		return purchasePersonId;
	}

	public void setPurchasePersonId(String purchasePersonId) {
		this.purchasePersonId = purchasePersonId;
	}

	public String getPurchasePerson() {
		return purchasePerson;
	}

	public void setPurchasePerson(String purchasePerson) {
		this.purchasePerson = purchasePerson;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public Date getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Set<PurchaseInboundItems> getPurchaseInboundItems() {
		return purchaseInboundItems;
	}

	public void setPurchaseInboundItems(Set<PurchaseInboundItems> purchaseInboundItems) {
		this.purchaseInboundItems = purchaseInboundItems;
	}

	public String getPurchaseOrg() {
		return purchaseOrg;
	}

	public void setPurchaseOrg(String purchaseOrg) {
		this.purchaseOrg = purchaseOrg;
	}

	/**
	 * @return the purchaseOrgId
	 */
	public String getPurchaseOrgId() {
		return purchaseOrgId;
	}

	/**
	 * @param purchaseOrgId
	 *            the purchaseOrgId to set
	 */
	public void setPurchaseOrgId(String purchaseOrgId) {
		this.purchaseOrgId = purchaseOrgId;
	}

	@Override
	public String getBarCode() {
		return barCode;
	}

	@Override
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

}
