package com.vix.mdm.purchase.invoice.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.CurrencyType;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;

/**
 * @Description: 采购发票
 * @author ivan
 * @date 2013-08-15
 */
public class PurchaseInvoice extends BaseEntity {

	private static final long serialVersionUID = 774981285396858016L;
	/** 采购订单代码 */
	private Long purchaseOrderCode;
	/** 供应商代码 */
	private String supplierCode;
	/** 供应商名称 */
	private String supplierName;
	/** 单据成组编码 */
	private String groupCode;
	/** 公司代码 */
	private String companyId;
	/** 业务类型 */
	private String businessType;
	/** 单据类型 */
	private String orderType;
	/** 联系人 */
	private String contactPerson;
	/** 需求部门代码 */
	private String requireDepartmentCode;
	/** 需求部门 */
	private String requireDepartment;
	/** 采购组织 */
	private String purchaseOrganization;
	/** 采购人代码 */
	private String purchasePersonId;
	/** 采购人 */
	private String purchasePerson;
	/** 总额 */
	private Double totalAmount;
	/** 币种 */
	private String currency;
	private CurrencyType currencyType;
	/** 税率 */
	private Double taxRate;
	/** 过账日期 */
	private Date postingDate;
	/** 交货日期 */
	private Date deliveryDate;
	/** 采购订单 */
	private PurchaseOrder purchaseOrder;
	/** 采购订单明细 */
	private Set<PurchaseInvoiceItem> purchaseInvoiceItems = new HashSet<PurchaseInvoiceItem>();

	public Long getPurchaseOrderCode() {
		return purchaseOrderCode;
	}

	public void setPurchaseOrderCode(Long purchaseOrderCode) {
		this.purchaseOrderCode = purchaseOrderCode;
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

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	/**
	 * @return the currencyType
	 */
	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	/**
	 * @param currencyType
	 *            the currencyType to set
	 */
	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getRequireDepartmentCode() {
		return requireDepartmentCode;
	}

	public void setRequireDepartmentCode(String requireDepartmentCode) {
		this.requireDepartmentCode = requireDepartmentCode;
	}

	public String getRequireDepartment() {
		return requireDepartment;
	}

	public void setRequireDepartment(String requireDepartment) {
		this.requireDepartment = requireDepartment;
	}

	public String getPurchaseOrganization() {
		return purchaseOrganization;
	}

	public void setPurchaseOrganization(String purchaseOrganization) {
		this.purchaseOrganization = purchaseOrganization;
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

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public Set<PurchaseInvoiceItem> getPurchaseInvoiceItems() {
		return purchaseInvoiceItems;
	}

	public void setPurchaseInvoiceItems(Set<PurchaseInvoiceItem> purchaseInvoiceItems) {
		this.purchaseInvoiceItems = purchaseInvoiceItems;
	}

}
