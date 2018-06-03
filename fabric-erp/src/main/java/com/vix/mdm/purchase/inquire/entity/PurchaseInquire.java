package com.vix.mdm.purchase.inquire.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.core.utils.NumberUtil;

/**
 * 采购询价单
 * 
 * @author Ivan 2013-05-24
 */
public class PurchaseInquire extends BaseEntity {

	private static final long serialVersionUID = -6161839669965191411L;
	/** 业务类型 */
	private String bizType;
	/** 单据成组编码 */
	private String groupCode;
	/** 单据类型 */
	private String formType;
	/** 供应商代码 */
	private String supplierSCode;
	/** 供应商编码 */
	private String supplierCode;
	/** 供应商名称 */
	private String supplierName;
	/** 供应商ID */
	private String supplierId;
	/** 请购部门 */
	private String requireDepartment;
	/** 申请人 */
	private String requirePerson;
	/** 采购组织 */
	private String purchaseOrg;
	/** 采购组织id */
	private String purchaseOrgId;
	/** 审批人 */
	private String checkPerson;
	/** 审批日期 */
	private Date checkTime;
	/** 收货仓库 */
	private String recieveWarehouse;
	/** 项目 */
	private String project;
	/** 采购人 */
	private String purchasePerson;
	/** 申请日期 */
	private Date appDate;
	/** 币种 */
	private String currency;
	/** 税率 */
	private Double rate;
	/** 使用机器IP */
	private String ip;
	/** 采购询价明细 */
	private Set<PurchaseInquiryDetail> purchaseInquiryDetails = new HashSet<PurchaseInquiryDetail>();

	/**
	 * 询价单物料含税总价，不入库，及时计算
	 */
	private Double total;
	private Double amount;
	private String approvalStatus;
	public Double getTotal() {
		if (purchaseInquiryDetails != null) {
			double totalTax = 0;
			double totalFee = 0;
			for (PurchaseInquiryDetail detailItem : purchaseInquiryDetails) {
				Double price = detailItem.getPrice();
				amount = detailItem.getAmount();
				if (price != null && price > 0 && amount != null && amount > 0) {
					Double tax = detailItem.getTaxRate();
					if (tax == null)
						tax = 0d;

					double itemTotal = price * amount;
					double itemTax = itemTotal * tax / 100;

					totalFee += itemTotal;
					totalTax += itemTax;
				}
			}
			this.total = NumberUtil.round(totalFee + totalTax, 2, BigDecimal.ROUND_HALF_UP);
		}
		return total;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getSupplierSCode() {
		return supplierSCode;
	}

	public void setSupplierSCode(String supplierSCode) {
		this.supplierSCode = supplierSCode;
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

	public String getRequireDepartment() {
		return requireDepartment;
	}

	public void setRequireDepartment(String requireDepartment) {
		this.requireDepartment = requireDepartment;
	}

	public String getRequirePerson() {
		return requirePerson;
	}

	public void setRequirePerson(String requirePerson) {
		this.requirePerson = requirePerson;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getPurchaseOrg() {
		return purchaseOrg;
	}

	public void setPurchaseOrg(String purchaseOrg) {
		this.purchaseOrg = purchaseOrg;
	}

	public String getCheckPerson() {
		return checkPerson;
	}

	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getRecieveWarehouse() {
		return recieveWarehouse;
	}

	public void setRecieveWarehouse(String recieveWarehouse) {
		this.recieveWarehouse = recieveWarehouse;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getPurchasePerson() {
		return purchasePerson;
	}

	public void setPurchasePerson(String purchasePerson) {
		this.purchasePerson = purchasePerson;
	}

	public Date getAppDate() {
		return appDate;
	}
	
	public String getAppDateStr() {
		if(appDate != null){
			return DateUtil.format(appDate);
		}
		return "";
	}
	public String getAppDateTimeStr() {
		if(appDate != null){
			return DateUtil.formatTime(appDate);
		}
		return "";
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<PurchaseInquiryDetail> getPurchaseInquiryDetails() {
		return purchaseInquiryDetails;
	}

	public void setPurchaseInquiryDetails(Set<PurchaseInquiryDetail> purchaseInquiryDetails) {
		this.purchaseInquiryDetails = purchaseInquiryDetails;
	}

	public String getPurchaseOrgId() {
		return purchaseOrgId;
	}

	public void setPurchaseOrgId(String purchaseOrgId) {
		this.purchaseOrgId = purchaseOrgId;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
}
