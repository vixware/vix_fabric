package com.vix.ebusiness.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.inventory.warehouse.entity.InvWarehouse;

/**
 * 
 * com.vix.ebusiness.entity.InvoiceListDetail
 *
 * @author zhanghaibing
 *
 * @date 2014年9月21日
 */
public class InvoiceListDetail extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// private String outerOrderNo;// 外部订单编号
	private String goodsName; // 外部商品名称
	private Long amount; // 商品数量
	private Double price; // 售价
	private Double totalFee;// 金额
	private String spec; // 规格
	private String goodsCode; // G商品编码
	private String outerSkuId; // G商品SKU编码
	private String bn; // sku编号
	/**
	 * 发货单
	 */
	private InvoiceList invoiceList;
	/**
	 * 订单明细
	 */
	private OrderDetail orderDetail;
	/**
	 * 仓库
	 */
	private InvWarehouse invWarehouse;

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getOuterSkuId() {
		return outerSkuId;
	}

	public void setOuterSkuId(String outerSkuId) {
		this.outerSkuId = outerSkuId;
	}

	public String getBn() {
		return bn;
	}

	public void setBn(String bn) {
		this.bn = bn;
	}

	public InvoiceList getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(InvoiceList invoiceList) {
		this.invoiceList = invoiceList;
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public InvWarehouse getInvWarehouse() {
		return invWarehouse;
	}

	public void setInvWarehouse(InvWarehouse invWarehouse) {
		this.invWarehouse = invWarehouse;
	}

}
