package com.vix.common.share.entity;
/**
 * 采购允许误差规则
 * @author jackie
 *
 */

import com.vix.mdm.purchase.order.entity.BizType;

public class PurchasePermissibleError extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 单据性质
	 */
	private String entityType;
	/**
	 * 商品名称
	 */
	private String itemName;
	/**
	 * 商品id
	 */
	private String itemId;
	/**
	 * 供应商id
	 */
	private String supplierId;
	/**
	 * 供应商名称
	 */
	private String supplierName;
	/**
	 * 业务类型
	 */
	private BizType bizType;
	/**
	 * 允许数量偏差百分比
	 */
	private Long number;
	/**
	 * 允许金额偏差百分比
	 */
	private Long money;
	/**
	 * 允许迟到天数
	 */
	private Long beLate;
	/**
	 * 允许早到天数
	 */
	private Long earlyArrival;
	
	private String isDefault;
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getBizTypeName() {
		if(bizType != null) {
			return bizType.getName();
		}
		return "";
	}
	public BizType getBizType() {
		return bizType;
	}
	public void setBizType(BizType bizType) {
		this.bizType = bizType;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public Long getMoney() {
		return money;
	}
	public void setMoney(Long money) {
		this.money = money;
	}
	public Long getBeLate() {
		return beLate;
	}
	public void setBeLate(Long beLate) {
		this.beLate = beLate;
	}
	public Long getEarlyArrival() {
		return earlyArrival;
	}
	public void setEarlyArrival(Long earlyArrival) {
		this.earlyArrival = earlyArrival;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
}
