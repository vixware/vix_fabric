package com.vix.mdm.srm.share.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.trainingManagement.entity.TrainingManagement;
import com.vix.mdm.purchase.apply.entity.PurchaseApply;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.plan.entity.PurchasePlan;
import com.vix.mdm.purchase.tender.entity.PurchaseTender;
import com.vix.sales.delivery.entity.DeliveryDocument;
import com.vix.sales.delivery.entity.SaleReturnForm;

public class Attachments extends BaseEntity {

	private static final long serialVersionUID = 8938886466815909573L;
	/** 业务对象类型 */
	private String bizObjectType;
	/** 路径 */
	private String path;
	/** 供应商 */
	private Supplier supplier;
	/** 采购订单 */
	private PurchaseOrder purchaseOrder;
	/** 采购计划 */
	private PurchasePlan purchasePlan;
	/** 采购申请 */
	private PurchaseApply purchaseApply;
	/** 采购招标 */
	private PurchaseTender purchaseTender;
	/** 销售发货单 */
	private DeliveryDocument deliveryDocument;
	/** 销售退货单 */
	private SaleReturnForm saleReturnForm;
	/** 培训活动 */
	private TrainingManagement trainingManagement;

	public String getBizObjectType() {
		return bizObjectType;
	}

	public void setBizObjectType(String bizObjectType) {
		this.bizObjectType = bizObjectType;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public PurchasePlan getPurchasePlan() {
		return purchasePlan;
	}

	public void setPurchasePlan(PurchasePlan purchasePlan) {
		this.purchasePlan = purchasePlan;
	}

	public PurchaseApply getPurchaseApply() {
		return purchaseApply;
	}

	public void setPurchaseApply(PurchaseApply purchaseApply) {
		this.purchaseApply = purchaseApply;
	}

	public PurchaseTender getPurchaseTender() {
		return purchaseTender;
	}

	public void setPurchaseTender(PurchaseTender purchaseTender) {
		this.purchaseTender = purchaseTender;
	}

	public DeliveryDocument getDeliveryDocument() {
		return deliveryDocument;
	}

	public void setDeliveryDocument(DeliveryDocument deliveryDocument) {
		this.deliveryDocument = deliveryDocument;
	}

	public SaleReturnForm getSaleReturnForm() {
		return saleReturnForm;
	}

	public void setSaleReturnForm(SaleReturnForm saleReturnForm) {
		this.saleReturnForm = saleReturnForm;
	}

	public TrainingManagement getTrainingManagement() {
		return trainingManagement;
	}

	public void setTrainingManagement(TrainingManagement trainingManagement) {
		this.trainingManagement = trainingManagement;
	}

}
