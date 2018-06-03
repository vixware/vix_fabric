package com.vix.chain.productview.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseBOEntity;
import com.vix.common.share.entity.CurrencyType;
import com.vix.mdm.srm.share.entity.Supplier;

/**
 * 结算单
 * 
 * @author zhanghaibing
 * 
 * @date 2014-5-27
 */
public class SettlementStatement extends BaseBOEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 结算周期
	 */
	private String billingCycle;
	/**
	 * 结算日期
	 */
	private Date settlementDate;
	/**
	 * 结算方式
	 */
	private String settlement;
	/**
	 * 金额
	 */
	private Double amount;
	/**
	 * 币种
	 */
	private CurrencyType currencyType;
	/**
	 * 供应商
	 */
	private Supplier supplier;
	/**
	 * 订单明细
	 */
	private Set<SettlementStatementItem> settlementStatementItems = new HashSet<SettlementStatementItem>();

	public SettlementStatement() {
	}

	public String getBillingCycle() {
		return billingCycle;
	}

	public void setBillingCycle(String billingCycle) {
		this.billingCycle = billingCycle;
	}

	public Date getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	public String getSettlement() {
		return settlement;
	}

	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Set<SettlementStatementItem> getSettlementStatementItems() {
		return settlementStatementItems;
	}

	public void setSettlementStatementItems(Set<SettlementStatementItem> settlementStatementItems) {
		this.settlementStatementItems = settlementStatementItems;
	}

}
