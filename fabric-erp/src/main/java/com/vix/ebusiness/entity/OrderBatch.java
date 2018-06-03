package com.vix.ebusiness.entity;

import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.option.entity.Logistics;
import com.vix.inventory.inbound.entity.StockRecords;
import com.vix.inventory.warehouse.entity.InvWarehouse;

/**
 * 订单批次
 * 
 * com.vix.E_business.entity.OrderBatch
 *
 * @author bjitzhang
 *
 * @date 2014年8月20日
 */
public class OrderBatch extends BaseEntity {

	private static final long serialVersionUID = 2293987058766962645L;
	/**
	 * 物流公司
	 */
	private Logistics logistics;
	/**
	 * 仓库
	 */
	private InvWarehouse invWarehouse;
	/**
	 * 网店
	 */
	private ChannelDistributor channelDistributor;
	/**
	 * 订单
	 */
	private Set<Order> orderList;
	/**
	 * 发货单
	 */
	private Set<InvoiceList> subInvoiceLists;
	/**
	 * 物流单
	 */
	private Set<Shipping> subShppings;
	/**
	 * 出库单
	 */
	private Set<StockRecords> subStockRecords;
	/**
	 * 拣货单
	 */
	private Set<PickingList> subPickingList;

	public Set<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(Set<Order> orderList) {
		this.orderList = orderList;
	}

	public Logistics getLogistics() {
		return logistics;
	}

	public void setLogistics(Logistics logistics) {
		this.logistics = logistics;
	}

	public InvWarehouse getInvWarehouse() {
		return invWarehouse;
	}

	public void setInvWarehouse(InvWarehouse invWarehouse) {
		this.invWarehouse = invWarehouse;
	}

	public Set<InvoiceList> getSubInvoiceLists() {
		return subInvoiceLists;
	}

	public void setSubInvoiceLists(Set<InvoiceList> subInvoiceLists) {
		this.subInvoiceLists = subInvoiceLists;
	}

	public Set<Shipping> getSubShppings() {
		return subShppings;
	}

	public void setSubShppings(Set<Shipping> subShppings) {
		this.subShppings = subShppings;
	}

	public Set<StockRecords> getSubStockRecords() {
		return subStockRecords;
	}

	public void setSubStockRecords(Set<StockRecords> subStockRecords) {
		this.subStockRecords = subStockRecords;
	}

	public Set<PickingList> getSubPickingList() {
		return subPickingList;
	}

	public void setSubPickingList(Set<PickingList> subPickingList) {
		this.subPickingList = subPickingList;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

}
