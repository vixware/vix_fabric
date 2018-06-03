package com.vix.sales.order.entity;

import com.vix.common.share.entity.BaseEntity;

public class SalesTicketDetail extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 销售发票 */
	private SalesTicket salesTicket;
	private SaleOrderItem saleOrderItem;
	/** 开票项数量 */
	private Long ticketItemCount;
	
	public SalesTicketDetail(){}

	public SalesTicket getSalesTicket() {
		return salesTicket;
	}

	public void setSalesTicket(SalesTicket salesTicket) {
		this.salesTicket = salesTicket;
	}

	public SaleOrderItem getSaleOrderItem() {
		return saleOrderItem;
	}

	public void setSaleOrderItem(SaleOrderItem saleOrderItem) {
		this.saleOrderItem = saleOrderItem;
	}

	public Long getTicketItemCount() {
		return ticketItemCount;
	}

	public void setTicketItemCount(Long ticketItemCount) {
		this.ticketItemCount = ticketItemCount;
	}
}
