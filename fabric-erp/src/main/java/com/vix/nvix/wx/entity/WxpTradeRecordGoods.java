package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpTradeRecordGoods
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */

public class WxpTradeRecordGoods extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String goodsId; // 商品id
	String goodsTitle; // 商品名称
	Double price; // 商品购买价格
	Double priceOri; // 商品原价，一般情况下与购买价格相同
	Integer amount; // 商品购买数量

	/**
	 * cateId，cateTitle，只有在生成订单时有带入类别id时（buyCateId）才保存，没有的情况下不保存
	 * 商品可以有多个分类，只有在购买时，通过某个分类进入（目前没有这个处理）时，带入类别id才保存对应属性
	 */
	String cateId; // 商品分类id
	String cateTitle; // 商品分类名称
	String note; // 备注

	WxpTradeRecord tradeRecord; // 交易单

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsTitle() {
		return goodsTitle;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPriceOri() {
		return priceOri;
	}

	public void setPriceOri(Double priceOri) {
		this.priceOri = priceOri;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getCateId() {
		return cateId;
	}

	public void setCateId(String cateId) {
		this.cateId = cateId;
	}

	public String getCateTitle() {
		return cateTitle;
	}

	public void setCateTitle(String cateTitle) {
		this.cateTitle = cateTitle;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public WxpTradeRecord getTradeRecord() {
		return tradeRecord;
	}

	public void setTradeRecord(WxpTradeRecord tradeRecord) {
		this.tradeRecord = tradeRecord;
	}

}
