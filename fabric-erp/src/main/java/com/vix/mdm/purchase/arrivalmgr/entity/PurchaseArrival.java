package com.vix.mdm.purchase.arrivalmgr.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.core.utils.DateUtil;
import com.vix.mdm.purchase.PurchaseEntity;

/**
 * 采购到货单
 * 
 * @author Ivan 2013-05-29
 */
public class PurchaseArrival extends PurchaseEntity {

	private static final long serialVersionUID = 487796801827240399L;
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
	/** 需求部门代码 */
	private String requireDepartmentCode;
	/** 需求部门 */
	private String requireDepartment;
	/** 采购组织 */
	private String purchaseOrg;
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
	/** 项目 */
	private String project;
	/** 到货明细 */
	private Set<PurchaseArrivalItems> purchaseArrivalItems = new HashSet<PurchaseArrivalItems>();

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
	
	public String getDeliveryDateStr() {
		if(deliveryDate != null){
			return DateUtil.format(deliveryDate);
		}
		return "";
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Set<PurchaseArrivalItems> getPurchaseArrivalItems() {
		return purchaseArrivalItems;
	}

	public void setPurchaseArrivalItems(Set<PurchaseArrivalItems> purchaseArrivalItems) {
		this.purchaseArrivalItems = purchaseArrivalItems;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

}
