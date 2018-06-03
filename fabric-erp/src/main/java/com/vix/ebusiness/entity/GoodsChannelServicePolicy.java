package com.vix.ebusiness.entity;

import com.vix.common.share.entity.BaseEntity;

public class GoodsChannelServicePolicy extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long goodsId;

	private Long channelId;

	private Integer hasInvoice;

	private Integer hasWarranty;

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Integer getHasInvoice() {
		return hasInvoice;
	}

	public void setHasInvoice(Integer hasInvoice) {
		this.hasInvoice = hasInvoice;
	}

	public Integer getHasWarranty() {
		return hasWarranty;
	}

	public void setHasWarranty(Integer hasWarranty) {
		this.hasWarranty = hasWarranty;
	}

}
