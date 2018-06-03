package com.vix.mdm.purchase.apply.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.mdm.purchase.PurchaseEntity;
import com.vix.mdm.srm.share.entity.Attachments;

/**
 * 请购单/采购申请
 * 
 * @author Ivan 2013-07-24
 */
public class PurchaseApply extends PurchaseEntity {

	private static final long serialVersionUID = -4928964719664070661L;
	/** 业务类型 */
	private String bizType;
	/** 单据成组编码 */
	private String groupCode;
	/** 单据类型 */
	private String formType;
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
	/** 采购员 */
	private String purchasePerson;
	/** 审核人 */
	private String checker;
	/** 批准人 */
	private String approval;
	/** 总额 */
	private Double total;
	/** 币种 */
	private String currency;
	/** 税率 */
	private Double rate;
	private String isDeleted;
	/** 明细 */
	private Set<PurchaseApplyDetails> purchaseApplyDetails = new HashSet<PurchaseApplyDetails>();
	/** 附件 */
	private Set<Attachments> attachments = new HashSet<Attachments>();
	/**
	 * 审批状态
	 */
	private String approvalStatus;
	/**
	 * 申请理由
	 */
	private String applyReason;
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

	public String getRequirePerson() {
		return requirePerson;
	}

	public void setRequirePerson(String requirePerson) {
		this.requirePerson = requirePerson;
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

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
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

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Set<PurchaseApplyDetails> getPurchaseApplyDetails() {
		return purchaseApplyDetails;
	}

	public void setPurchaseApplyDetails(Set<PurchaseApplyDetails> purchaseApplyDetails) {
		this.purchaseApplyDetails = purchaseApplyDetails;
	}

	public Set<Attachments> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachments> attachments) {
		this.attachments = attachments;
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

	/**
	 * @return the isDeleted
	 */
	@Override
	public String getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted
	 *            the isDeleted to set
	 */
	@Override
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}
}
