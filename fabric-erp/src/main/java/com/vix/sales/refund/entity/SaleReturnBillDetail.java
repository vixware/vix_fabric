package com.vix.sales.refund.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.item.entity.Item;
import com.vix.sales.order.entity.SaleOrderItem;

/** 返款单明细 */
public class SaleReturnBillDetail extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 单据类型 */
	private String billType;
	/** 单据编码 */
	private String billCode;
	/** 返款单据的总金额 */
	private Double totalAmount;
	/**
	 * 商品
	 */
	private Item item;
	/**
	 * 数量
	 */
	private Double amount;
	/**
	 * 销售单明细
	 */
	private SaleOrderItem saleOrderItem;
	/**
	 * 单据金额
	 */
	private Double acount;
	/** 返款单 */
	private SaleReturnBill saleReturnBill;
	
	public SaleReturnBillDetail(){}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public SaleReturnBill getSaleReturnBill() {
		return saleReturnBill;
	}

	public void setSaleReturnBill(SaleReturnBill saleReturnBill) {
		this.saleReturnBill = saleReturnBill;
	}

	public Item getItem() {
		return item;
	}
	public String getItemName() {
		if(item != null) {
			return item.getName();
		}
		return "";
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public SaleOrderItem getSaleOrderItem() {
		return saleOrderItem;
	}

	public void setSaleOrderItem(SaleOrderItem saleOrderItem) {
		this.saleOrderItem = saleOrderItem;
	}

	public Double getAcount() {
		return acount;
	}

	public void setAcount(Double acount) {
		this.acount = acount;
	}
}
