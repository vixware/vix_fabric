package com.vix.mdm.purchase.order.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.sales.delivery.entity.DeliveryDocument;
import com.vix.sales.delivery.entity.SaleReturnForm;

/**
 * 审批意见
 * 
 * @author Ivan 2013-05-24
 */
public class ApprovalOpinion extends BaseEntity {

	private static final long serialVersionUID = 8852305747842394549L;
	/** 审批意见 */
	private String content;
	/** 审批人ID */
	private String checkPersonId;
	/** 审批人 */
	private String checkPerson;
	/** 采购订单 */
	private PurchaseOrder purchaseOrder;
	/** 销售发货 */
	private DeliveryDocument deliveryDocument;
	/** 销售退货 */
	private SaleReturnForm saleReturnForm;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCheckPersonId() {
		return checkPersonId;
	}

	public void setCheckPersonId(String checkPersonId) {
		this.checkPersonId = checkPersonId;
	}

	public String getCheckPerson() {
		return checkPerson;
	}

	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
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

}
