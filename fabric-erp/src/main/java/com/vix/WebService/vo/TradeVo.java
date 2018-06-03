package com.vix.WebService.vo;

import java.util.Date;
import java.util.List;

public class TradeVo {

	/**
	 * 交易编号
	 */
	private Long tradeId;
	/**
	 * 订单编码
	 */
	private String tradeNo;
	/**
	 * 订单外部标识
	 */
	private String outerId;
	/**
	 * 商品购买数量
	 */
	private Long num;
	/**
	 * 交易状态
	 */
	private String status;
	/**
	 * 交易类型
	 */
	private String type;
	/**
	 * 商品价格
	 */
	private String price;
	/**
	 * 交易备注
	 */
	private String tradeMemo;
	/**
	 * 系统优惠金额
	 */
	private String discountFee;
	/**
	 * 商品总金额
	 */
	private String totalFee;
	/**
	 * 交易创建时间
	 */
	private Date created;
	/**
	 * 付款时间
	 */
	private Date payTime;
	/**
	 * 交易修改时间
	 */
	private Date modified;
	/**
	 * 交易结束时间
	 */
	private Date endTime;
	/**
	 * 买家留言
	 */
	private String buyerMessage;
	/**
	 * 卖家备注
	 */
	private String sellerMemo;
	/**
	 * 卖家备注旗帜
	 */
	private Long sellerFlag;
	/**
	 * 买家昵称
	 */
	private String buyerNick;
	/**
	 * 买家下单的地区
	 */
	private String buyerArea;
	/**
	 * 物流方式
	 */
	private String shippingType;
	/**
	 * 卖家手工调整金额
	 */
	private String adjustFee;
	/**
	 * 卖家昵称
	 */
	private String sellerNick;
	/**
	 * 买家备注
	 */
	private String buyerMemo;
	/**
	 * 实付金额
	 */
	private String payment;
	/**
	 * 货到付款服务费
	 */
	private Long codFee;
	/**
	 * 卖家是否已评价
	 */
	private Integer sellerRate;
	/**
	 * 买家是否已评价
	 */
	private Integer buyerRate;
	/**
	 * 邮费
	 */
	private String postFee;
	/**
	 * 买家货到付款服务费。
	 */
	private String buyerCodFee;
	/**
	 * 卖家货到付款服务费。
	 */
	private String sellerCodFee;
	/**
	 * 收货人的姓名
	 */
	private String receiverName;
	/**
	 * 收货人省份
	 */
	private String receiverState;
	/**
	 * 交易号
	 */
	private String payNo;
	/**
	 * 收货人城市
	 */
	private String receiverCity;
	/**
	 * 收货人的所在地区
	 */
	private String receiverDistrict;
	/**
	 * 收货人的详细地址
	 */
	private String receiverAddress;
	/**
	 * 收货人的邮编
	 */
	private String receiverZip;
	/**
	 * 收货人的手机号码
	 */
	private String receiverMobile;
	/**
	 * 收货人的电话号码
	 */
	private String receiverPhone;
	/**
	 * 卖家发货时间
	 */
	private Date consignTime;
	/**
	 * 发票抬头
	 */
	private String invoiceName;
	/**
	 * 支付类型
	 */
	private String payType;

	/**
	 * 支付类型中文
	 */
	private String payTypeCn;
	/**
	 * 订单明细
	 */
	private List<OrderVo> orderVos;
	/**
	 * 买家备注旗帜
	 */
	private Long buyerFlag;
	/**
	 * 快递代收款
	 */
	private Long expressAgencyfee;
	/**
	 * 买家使用积分
	 */
	private Integer pointFee;
	/**
	 * 买家实际使用积分
	 */
	private Integer realPointFee;
	/**
	 * 促销详细信息
	 */
	private String promotion;
	/**
	 * 会员信息
	 */
	private CustomerVo customerVo;
	/**
	 * 处理状态
	 */
	private Integer dealState;
	/**
	 * 买家获得积分
	 */
	private Integer buyerObtainPointFee;
	/**
	 * 卖家配送方式
	 */
	private Integer sellerShippingType;

	/**
	 * 打印面单数据
	 */
	//private OrderPrintData OrderPrintData;
	/**
	 * 交易优惠详情
	 */
	//private List<TradePromotionDetail> tradePromotionDetail;

	public Integer getSellerShippingType() {
		return sellerShippingType;
	}

	public void setSellerShippingType(Integer sellerShippingType) {
		this.sellerShippingType = sellerShippingType;
	}

	public Integer getBuyerObtainPointFee() {
		return buyerObtainPointFee;
	}

	public void setBuyerObtainPointFee(Integer buyerObtainPointFee) {
		this.buyerObtainPointFee = buyerObtainPointFee;
	}

	public Integer getDealState() {
		return dealState;
	}

	public void setDealState(Integer dealState) {
		this.dealState = dealState;
	}

	public Long getCodFee() {
		return codFee;
	}

	public void setCodFee(Long codFee) {
		this.codFee = codFee;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public Integer getRealPointFee() {
		return realPointFee;
	}

	public void setRealPointFee(Integer realPointFee) {
		this.realPointFee = realPointFee;
	}

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

	public Long getBuyerFlag() {
		return buyerFlag;
	}

	public void setBuyerFlag(Long buyerFlag) {
		this.buyerFlag = buyerFlag;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Long getExpressAgencyfee() {
		return expressAgencyfee;
	}

	public void setExpressAgencyfee(Long expressAgencyfee) {
		this.expressAgencyfee = expressAgencyfee;
	}

	public String getBuyerMemo() {
		return buyerMemo;
	}

	public void setBuyerMemo(String buyerMemo) {
		this.buyerMemo = buyerMemo;
	}

	public String getSellerCodFee() {
		return sellerCodFee;
	}

	public void setSellerCodFee(String sellerCodFee) {
		this.sellerCodFee = sellerCodFee;
	}

	public String getOuterId() {
		return outerId;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	public Integer getPointFee() {
		return pointFee;
	}

	public void setPointFee(Integer pointFee) {
		this.pointFee = pointFee;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBuyerCodFee() {
		return buyerCodFee;
	}

	public void setBuyerCodFee(String buyerCodFee) {
		this.buyerCodFee = buyerCodFee;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayTypeCn() {
		return payTypeCn;
	}

	public void setPayTypeCn(String payTypeCn) {
		this.payTypeCn = payTypeCn;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDiscountFee() {
		return discountFee;
	}

	public void setDiscountFee(String discountFee) {
		this.discountFee = discountFee;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getInvoiceName() {
		return invoiceName;
	}

	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getBuyerMessage() {
		return buyerMessage;
	}

	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
	}

	public String getSellerMemo() {
		return sellerMemo;
	}

	public void setSellerMemo(String sellerMemo) {
		this.sellerMemo = sellerMemo;
	}

	public Long getSellerFlag() {
		return sellerFlag;
	}

	public void setSellerFlag(Long sellerFlag) {
		this.sellerFlag = sellerFlag;
	}

	public String getBuyerNick() {
		return buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	public String getBuyerArea() {
		return buyerArea;
	}

	public void setBuyerArea(String buyerArea) {
		this.buyerArea = buyerArea;
	}

	public String getShippingType() {
		return shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	public String getAdjustFee() {
		return adjustFee;
	}

	public void setAdjustFee(String adjustFee) {
		this.adjustFee = adjustFee;
	}

	public String getSellerNick() {
		return sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public Integer getSellerRate() {
		return sellerRate;
	}

	public void setSellerRate(Integer sellerRate) {
		this.sellerRate = sellerRate;
	}

	public Integer getBuyerRate() {
		return buyerRate;
	}

	public void setBuyerRate(Integer buyerRate) {
		this.buyerRate = buyerRate;
	}

	public String getPostFee() {
		return postFee;
	}

	public void setPostFee(String postFee) {
		this.postFee = postFee;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverState() {
		return receiverState;
	}

	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}

	public String getReceiverCity() {
		return receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	public String getReceiverDistrict() {
		return receiverDistrict;
	}

	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getReceiverZip() {
		return receiverZip;
	}

	public void setReceiverZip(String receiverZip) {
		this.receiverZip = receiverZip;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public Date getConsignTime() {
		return consignTime;
	}

	public void setConsignTime(Date consignTime) {
		this.consignTime = consignTime;
	}

	public String getTradeMemo() {
		return tradeMemo;
	}

	public void setTradeMemo(String tradeMemo) {
		this.tradeMemo = tradeMemo;
	}

	public List<OrderVo> getOrderVos() {
		return orderVos;
	}

	public void setOrderVos(List<OrderVo> orderVos) {
		this.orderVos = orderVos;
	}

	public CustomerVo getCustomerVo() {
		return customerVo;
	}

	public void setCustomerVo(CustomerVo customerVo) {
		this.customerVo = customerVo;
	}

}
