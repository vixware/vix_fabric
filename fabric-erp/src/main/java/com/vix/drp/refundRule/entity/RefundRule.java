/**
 * 
 */
package com.vix.drp.refundRule.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.mdm.crm.entity.CustomerAccount;

/**
 * 返款规则
 * 
 * @author zhanghaibing
 * 
 * @date 2013-10-10
 */
public class RefundRule extends BaseEntity {
	private static final long serialVersionUID = -8007765978230427481L;
	/** 返款规则名称 */
	private String rrName;
	/** 返款规则类型 */
	private String rrType;
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 库存 */
	private InventoryCurrentStock inventoryCurrentStock;
	/** 计算对象 */
	private String computeTarget;
	/** 计算方式 */
	private String computeStyle;
	/**
	 * 返款规则明细
	 */
	private Set<RefundRuleDetail> refundRuleDetails = new HashSet<RefundRuleDetail>();

	public String getRrName() {
		return rrName;
	}

	public void setRrName(String rrName) {
		this.rrName = rrName;
	}

	public String getRrType() {
		return rrType;
	}

	public void setRrType(String rrType) {
		this.rrType = rrType;
	}

	public String getComputeTarget() {
		return computeTarget;
	}

	public void setComputeTarget(String computeTarget) {
		this.computeTarget = computeTarget;
	}

	public String getComputeStyle() {
		return computeStyle;
	}

	public void setComputeStyle(String computeStyle) {
		this.computeStyle = computeStyle;
	}

	public Set<RefundRuleDetail> getRefundRuleDetails() {
		return refundRuleDetails;
	}

	public void setRefundRuleDetails(Set<RefundRuleDetail> refundRuleDetails) {
		this.refundRuleDetails = refundRuleDetails;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public InventoryCurrentStock getInventoryCurrentStock() {
		return inventoryCurrentStock;
	}

	public void setInventoryCurrentStock(InventoryCurrentStock inventoryCurrentStock) {
		this.inventoryCurrentStock = inventoryCurrentStock;
	}

}
