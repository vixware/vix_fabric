package com.vix.mdm.purchase.order.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

public class DeliveryPlan extends BaseEntity {
	   private static final long serialVersionUID = 8316368765945063464L;
	   /** 计划行编码 */
	   private String planItemCode;
	   /** 发货时间 */
	   private Date deliveryTime;
	   /** 发货人 */
	   private String deliver;
	   /** 发货点 */
	   private String deliverPointRef;
	   /** 收货人 */
	   private String reciever;
	   /** 收货点 */
	   private String recievePointRef;
	   /** 物料编码 */
	   private String itemCode;
	   /** 物料名称 */
	   private String itemName;
	   /** 物料类型 */
	   private String itemType;
	   /** 规格型号 */
	   private String specification;
	   /** 订货数量 */
	   private Double amount;
	   /** 辅计算量 */
	   private String assitAmount;
	   /** 计量单位 */
	   private String unit;
	   /** 辅助计量单位 */
	   private String assitUnit;
	   /** 主辅计量单位换算率 */
	   private Double unitExchange;
	   /** 采购订单 */
	   private PurchaseOrder purchaseOrder;
	public String getPlanItemCode() {
		return planItemCode;
	}
	public void setPlanItemCode(String planItemCode) {
		this.planItemCode = planItemCode;
	}
	public Date getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public String getDeliver() {
		return deliver;
	}
	public void setDeliver(String deliver) {
		this.deliver = deliver;
	}
	public String getDeliverPointRef() {
		return deliverPointRef;
	}
	public void setDeliverPointRef(String deliverPointRef) {
		this.deliverPointRef = deliverPointRef;
	}
	public String getReciever() {
		return reciever;
	}
	public void setReciever(String reciever) {
		this.reciever = reciever;
	}
	public String getRecievePointRef() {
		return recievePointRef;
	}
	public void setRecievePointRef(String recievePointRef) {
		this.recievePointRef = recievePointRef;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getAssitAmount() {
		return assitAmount;
	}
	public void setAssitAmount(String assitAmount) {
		this.assitAmount = assitAmount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getAssitUnit() {
		return assitUnit;
	}
	public void setAssitUnit(String assitUnit) {
		this.assitUnit = assitUnit;
	}
	public Double getUnitExchange() {
		return unitExchange;
	}
	public void setUnitExchange(Double unitExchange) {
		this.unitExchange = unitExchange;
	}
	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}
	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}
	   
}
