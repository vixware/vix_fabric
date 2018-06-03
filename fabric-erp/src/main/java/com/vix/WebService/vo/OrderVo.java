package com.vix.WebService.vo;

import java.util.Date;

public class OrderVo {

	/**
	 * 卖家手工调整金额，精确到2位小数，单位：元。
	 */
	private String adjustFee;
	/**
	 * 买家昵称
	 */
	private String buyerNick;
	/**
	 * 买家是否已经评价
	 */
	private Boolean buyerRate;
	/**
	 * 交易商品对应的类目标识
	 */
	private Long cid;
	/**
	 * 交易标识
	 */
	private Long tradeId;
	/**
	 * 子订单编号
	 */
	private Long orderId;
	/**
	 * 货品标识
	 */
	private Long productId;
	/**
	 * 确认状态
	 */
	private Integer confirm;
	/**
	 * 支付状态
	 */
	private Integer payStatus;
	/**
	 * 发货状态
	 */
	private Integer shipStatus;
	/**
	 * 用户反馈
	 */
	private Integer userStatus;
	/**
	 * 是否配送
	 */
	private Integer isDelivery;
	/**
	 * 商品标识
	 */
	private Long goodsId;
	/**
	 * 是否超卖
	 */
	private Integer isOverSold;
	/**
	 * 套餐标识
	 */
	private String itemMealId;
	/**
	 * 套餐名称
	 */
	private String itemMealName;
	/**
	 * 订单修改时间
	 */
	private Date modified;

	/**
	 * 主订单编号
	 */
	private String tradeNo;
	/**
	 * 交易明细编号
	 */
	private String orderNo;

	/**
	 * 购买数量
	 */
	private Long num;
	/**
	 * 货号
	 */
	private String bn;

	/**
	 * 商品名称
	 */
	private String title;

	/**
	 * 商家外部编码
	 */
	private String outerId;
	/**
	 * 外部商品标识
	 */
	private String outerGoodsId;
	/**
	 * 商家外部SKU编号
	 */
	private String outerSkuId;

	/**
	 * 实付金额
	 */
	private String payment;
	/**
	 * 商品图片绝对路径
	 */
	private String picPath;
	/**
	 * 商品价格
	 */
	private String price;

	/**
	 * 卖家昵称
	 */
	private String sellerNick;

	/**
	 * 卖家是否已经评价
	 */
	private Boolean sellerRate;

	/**
	 * 卖家类型
	 */
	private String sellerType;

	/**
	 * 商品最小库存单位标识
	 */
	private String skuId;
	/**
	 * SKU属性名称
	 */
	private String skuPropertiesName;

	/**
	 * 订单状态
	 */
	private String status;

	/**
	 * 应付金额
	 */
	private String totalFee;

	/**
	 * 优惠金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
	 */
	private String discountFee;

	public Integer getIsDelivery() {
		return isDelivery;
	}

	public void setIsDelivery(Integer isDelivery) {
		this.isDelivery = isDelivery;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public Integer getShipStatus() {
		return shipStatus;
	}

	public void setShipStatus(Integer shipStatus) {
		this.shipStatus = shipStatus;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public String getItemMealId() {
		return itemMealId;
	}

	public void setItemMealId(String itemMealId) {
		this.itemMealId = itemMealId;
	}

	public String getItemMealName() {
		return itemMealName;
	}

	public void setItemMealName(String itemMealName) {
		this.itemMealName = itemMealName;
	}

	public Integer getIsOverSold() {
		return isOverSold;
	}

	public void setIsOverSold(Integer isOverSold) {
		this.isOverSold = isOverSold;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getConfirm() {
		return confirm;
	}

	public void setConfirm(Integer confirm) {
		this.confirm = confirm;
	}

	public String getAdjustFee() {
		return adjustFee;
	}

	public void setAdjustFee(String adjustFee) {
		this.adjustFee = adjustFee;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public String getOuterGoodsId() {
		return outerGoodsId;
	}

	public void setOuterGoodsId(String outerGoodsId) {
		this.outerGoodsId = outerGoodsId;
	}

	public String getBuyerNick() {
		return buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	public Boolean getBuyerRate() {
		return buyerRate;
	}

	public void setBuyerRate(Boolean buyerRate) {
		this.buyerRate = buyerRate;
	}

	public String getBn() {
		return bn;
	}

	public void setBn(String bn) {
		this.bn = bn;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOuterId() {
		return outerId;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	public String getOuterSkuId() {
		return outerSkuId;
	}

	public void setOuterSkuId(String outerSkuId) {
		this.outerSkuId = outerSkuId;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSellerNick() {
		return sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

	public Boolean getSellerRate() {
		return sellerRate;
	}

	public void setSellerRate(Boolean sellerRate) {
		this.sellerRate = sellerRate;
	}

	public String getSellerType() {
		return sellerType;
	}

	public void setSellerType(String sellerType) {
		this.sellerType = sellerType;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getSkuPropertiesName() {
		return skuPropertiesName;
	}

	public void setSkuPropertiesName(String skuPropertiesName) {
		this.skuPropertiesName = skuPropertiesName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getDiscountFee() {
		return discountFee;
	}

	public void setDiscountFee(String discountFee) {
		this.discountFee = discountFee;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}
