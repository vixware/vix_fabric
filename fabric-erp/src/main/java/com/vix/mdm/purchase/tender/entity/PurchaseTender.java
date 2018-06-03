package com.vix.mdm.purchase.tender.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.mdm.srm.share.entity.SupplierTender;

public class PurchaseTender extends BaseEntity {

	private static final long serialVersionUID = 7493145962436073910L;
	/** 招标项目编号 */
	private String tenderCode;
	/** 采购项目名称 */
	private String title;
	/** 需求部门 */
	private String requireOrg;
	/** 采购组织 */
	private String purchaseOrg;
	/** 简介 */
	private String introduction;
	/** 发标时间 */
	private Date publishTime;
	/** 招标公司 */
	private String tenderAgent;
	/** 联系人 */
	private String contact;
	/** 电话 */
	private String telephone;
	/** 邮件地址 */
	private String email;
	/** 是否为父分类 */
	private String isParent;
	/** 父类id */
	private String parentId;
	/** 类型 */
	private String type;
	/** 子分类集合 */
	private Set<PurchaseTender> purchaseTenders = new HashSet<PurchaseTender>();
	/** 明细 */
	private Set<PurchaseTenderItem> purchaseTenderItems = new HashSet<PurchaseTenderItem>();
	/** 附件 */
	private Set<Attachments> attachments = new HashSet<Attachments>();
	/** 父分类 */
	private PurchaseTender purchaseTender;
	/** 投标 */
	private Set<SupplierTender> supplierTenders = new HashSet<SupplierTender>();
	/** 供应商 */
	private Set<Supplier> suppliers = new HashSet<Supplier>();

	public String getTenderCode() {
		return tenderCode;
	}

	public void setTenderCode(String tenderCode) {
		this.tenderCode = tenderCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRequireOrg() {
		return requireOrg;
	}

	public void setRequireOrg(String requireOrg) {
		this.requireOrg = requireOrg;
	}

	public String getPurchaseOrg() {
		return purchaseOrg;
	}

	public void setPurchaseOrg(String purchaseOrg) {
		this.purchaseOrg = purchaseOrg;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getTenderAgent() {
		return tenderAgent;
	}

	public void setTenderAgent(String tenderAgent) {
		this.tenderAgent = tenderAgent;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<PurchaseTender> getPurchaseTenders() {
		return purchaseTenders;
	}

	public void setPurchaseTenders(Set<PurchaseTender> purchaseTenders) {
		this.purchaseTenders = purchaseTenders;
	}

	public PurchaseTender getPurchaseTender() {
		return purchaseTender;
	}

	public void setPurchaseTender(PurchaseTender purchaseTender) {
		this.purchaseTender = purchaseTender;
	}

	public Set<PurchaseTenderItem> getPurchaseTenderItems() {
		return purchaseTenderItems;
	}

	public void setPurchaseTenderItems(Set<PurchaseTenderItem> purchaseTenderItems) {
		this.purchaseTenderItems = purchaseTenderItems;
	}

	public Set<Attachments> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachments> attachments) {
		this.attachments = attachments;
	}

	public Set<SupplierTender> getSupplierTenders() {
		return supplierTenders;
	}

	public void setSupplierTenders(Set<SupplierTender> supplierTenders) {
		this.supplierTenders = supplierTenders;
	}

	public Set<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Set<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

}
