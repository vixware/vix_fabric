package com.vix.dtbcenter.expresssingle.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseBOEntity;

/**
 * 快递单
 * 
 * @author zhanghaibing
 * 
 * @date 2014-3-3
 */
public class ExpressSingle extends BaseBOEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 客户编码
	 */
	private String senderCustomerAccountNo;
	/**
	 * 寄件公司
	 */
	private String senderFromCompany;
	/**
	 * 联络人
	 */
	private String senderContactPerson;
	/**
	 * 地址
	 */
	private String senderAddress;
	/**
	 * 区号
	 */
	private String senderAreaCode;
	/**
	 * 联系电话
	 */
	private String senderTel;
	/**
	 * 签收短信通知
	 */
	private String senderMsg;
	/**
	 * 客户编码
	 */
	private String receiverCustomerAccountNo;
	/**
	 * 收件公司
	 */
	private String receiverToCompany;
	/**
	 * 联络人
	 */
	private String receiverContactPerson;
	/**
	 * 地址
	 */
	private String receiverAddress;
	/**
	 * 区号
	 */
	private String receiverAreaCode;
	/**
	 * 固定电话
	 */
	private String receiverTel;
	/**
	 * 拖寄物内容
	 */
	private String goodsDescription;
	/**
	 * 数量
	 */
	private Integer goodsQuantity;
	/**
	 * 长(cm)
	 */
	private Double goodsLength;
	/**
	 * 宽(cm)
	 */
	private Double goodsWidth;
	/**
	 * 高(cm)
	 */
	private Double goodsHeight;
	/**
	 * 代收贷款卡号
	 */
	private String creditCardNo;
	/**
	 * 金额
	 */
	private Double amount;
	/**
	 * 签回单
	 */
	private Double returnReceiptService;
	/**
	 * 回单号
	 */
	private String returnTracking;
	/**
	 * 燃油附加费
	 */
	private Double fuelSurcharge;
	/**
	 * 夜晚收件费用
	 */
	private Double eveningCollectionCharge;
	/**
	 * 委托件费
	 */
	private Double authorizationPickupService;
	/**
	 * 等通知派送费用
	 */
	private Double deliveryUponNotice;
	/**
	 * 包装费
	 */
	private Double packageCharge;
	/**
	 * 特殊保价费用
	 */
	private Double specialoffer;
	/**
	 * 特殊配送
	 */
	private Double specialDelivery;
	/**
	 * 其他个性化服务费用
	 */
	private Double otherPersonalizedServices;
	/**
	 * 声明保价物品价值
	 */
	private Double declaredValue;
	/**
	 * 普通保价费用
	 */
	private Double chargeValue;
	/**
	 * 件数
	 */
	private Integer amountOfPieces;
	/**
	 * 计费重量
	 */
	private Double chargedWeight;
	/**
	 * 运费
	 */
	private Double freight;
	/**
	 * 实际重量
	 */
	private Double actualWeight;
	/**
	 * 费用合计
	 */
	private Double totalCharge;
	/**
	 * 原寄地
	 */
	private String origin;
	/**
	 * 目的地
	 */
	private String des;
	/**
	 * 自取件
	 */
	private String selfPickup;
	/**
	 * 自寄件
	 */
	private String selfSend;
	/**
	 * 寄方付
	 */
	private String shipper;
	/**
	 * 收方付
	 */
	private String consignee;
	/**
	 * 第三方付
	 */
	private String thirdParty;
	/**
	 * 月结账号
	 */
	private String monthlyPaymentNo;
	/**
	 * 第三方付款地区
	 */
	private String thirdPartyAreaCode;
	/**
	 * 收件员
	 */
	private String pickupBy;
	/**
	 * 派件员
	 */
	private String deliveredBy;
	/**
	 * 寄件日期
	 */
	private Date mailingDate;
	/**
	 * 收件人签名
	 */
	private String signature;
	/**
	 * 收件日期
	 */
	private Date receivedDate;
	/**
	 * 即日到
	 */
	private String sameDay;
	/**
	 * 普货
	 */
	private String cargo;
	/**
	 * 四日件
	 */
	private String fourDay;
	/**
	 * A标
	 */
	private String alabel;
	/**
	 * 陆运
	 */
	private String groundDelivery;
	/** 订单明细 */
	private Set<ExpressSingleDetail> expressSingleDetails = new HashSet<ExpressSingleDetail>();

	public ExpressSingle() {
	}

	public String getSenderCustomerAccountNo() {
		return senderCustomerAccountNo;
	}

	public void setSenderCustomerAccountNo(String senderCustomerAccountNo) {
		this.senderCustomerAccountNo = senderCustomerAccountNo;
	}

	public String getSenderFromCompany() {
		return senderFromCompany;
	}

	public void setSenderFromCompany(String senderFromCompany) {
		this.senderFromCompany = senderFromCompany;
	}

	public String getSenderContactPerson() {
		return senderContactPerson;
	}

	public void setSenderContactPerson(String senderContactPerson) {
		this.senderContactPerson = senderContactPerson;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public String getSenderAreaCode() {
		return senderAreaCode;
	}

	public void setSenderAreaCode(String senderAreaCode) {
		this.senderAreaCode = senderAreaCode;
	}

	public String getSenderTel() {
		return senderTel;
	}

	public void setSenderTel(String senderTel) {
		this.senderTel = senderTel;
	}

	public String getSenderMsg() {
		return senderMsg;
	}

	public void setSenderMsg(String senderMsg) {
		this.senderMsg = senderMsg;
	}

	public String getReceiverCustomerAccountNo() {
		return receiverCustomerAccountNo;
	}

	public void setReceiverCustomerAccountNo(String receiverCustomerAccountNo) {
		this.receiverCustomerAccountNo = receiverCustomerAccountNo;
	}

	public String getReceiverToCompany() {
		return receiverToCompany;
	}

	public void setReceiverToCompany(String receiverToCompany) {
		this.receiverToCompany = receiverToCompany;
	}

	public String getReceiverContactPerson() {
		return receiverContactPerson;
	}

	public void setReceiverContactPerson(String receiverContactPerson) {
		this.receiverContactPerson = receiverContactPerson;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getReceiverAreaCode() {
		return receiverAreaCode;
	}

	public void setReceiverAreaCode(String receiverAreaCode) {
		this.receiverAreaCode = receiverAreaCode;
	}

	public String getReceiverTel() {
		return receiverTel;
	}

	public void setReceiverTel(String receiverTel) {
		this.receiverTel = receiverTel;
	}

	public String getGoodsDescription() {
		return goodsDescription;
	}

	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}

	public Integer getGoodsQuantity() {
		return goodsQuantity;
	}

	public void setGoodsQuantity(Integer goodsQuantity) {
		this.goodsQuantity = goodsQuantity;
	}

	public Double getGoodsLength() {
		return goodsLength;
	}

	public void setGoodsLength(Double goodsLength) {
		this.goodsLength = goodsLength;
	}

	public Double getGoodsWidth() {
		return goodsWidth;
	}

	public void setGoodsWidth(Double goodsWidth) {
		this.goodsWidth = goodsWidth;
	}

	public Double getGoodsHeight() {
		return goodsHeight;
	}

	public void setGoodsHeight(Double goodsHeight) {
		this.goodsHeight = goodsHeight;
	}

	public String getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getReturnReceiptService() {
		return returnReceiptService;
	}

	public void setReturnReceiptService(Double returnReceiptService) {
		this.returnReceiptService = returnReceiptService;
	}

	public String getReturnTracking() {
		return returnTracking;
	}

	public void setReturnTracking(String returnTracking) {
		this.returnTracking = returnTracking;
	}

	public Double getFuelSurcharge() {
		return fuelSurcharge;
	}

	public void setFuelSurcharge(Double fuelSurcharge) {
		this.fuelSurcharge = fuelSurcharge;
	}

	public Double getEveningCollectionCharge() {
		return eveningCollectionCharge;
	}

	public void setEveningCollectionCharge(Double eveningCollectionCharge) {
		this.eveningCollectionCharge = eveningCollectionCharge;
	}

	public Double getAuthorizationPickupService() {
		return authorizationPickupService;
	}

	public void setAuthorizationPickupService(Double authorizationPickupService) {
		this.authorizationPickupService = authorizationPickupService;
	}

	public Double getDeliveryUponNotice() {
		return deliveryUponNotice;
	}

	public void setDeliveryUponNotice(Double deliveryUponNotice) {
		this.deliveryUponNotice = deliveryUponNotice;
	}

	public Double getPackageCharge() {
		return packageCharge;
	}

	public void setPackageCharge(Double packageCharge) {
		this.packageCharge = packageCharge;
	}

	public Double getSpecialoffer() {
		return specialoffer;
	}

	public void setSpecialoffer(Double specialoffer) {
		this.specialoffer = specialoffer;
	}

	public Double getSpecialDelivery() {
		return specialDelivery;
	}

	public void setSpecialDelivery(Double specialDelivery) {
		this.specialDelivery = specialDelivery;
	}

	public Double getOtherPersonalizedServices() {
		return otherPersonalizedServices;
	}

	public void setOtherPersonalizedServices(Double otherPersonalizedServices) {
		this.otherPersonalizedServices = otherPersonalizedServices;
	}

	public Double getDeclaredValue() {
		return declaredValue;
	}

	public void setDeclaredValue(Double declaredValue) {
		this.declaredValue = declaredValue;
	}

	public Double getChargeValue() {
		return chargeValue;
	}

	public void setChargeValue(Double chargeValue) {
		this.chargeValue = chargeValue;
	}

	public Integer getAmountOfPieces() {
		return amountOfPieces;
	}

	public void setAmountOfPieces(Integer amountOfPieces) {
		this.amountOfPieces = amountOfPieces;
	}

	public Double getChargedWeight() {
		return chargedWeight;
	}

	public void setChargedWeight(Double chargedWeight) {
		this.chargedWeight = chargedWeight;
	}

	public Double getFreight() {
		return freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	public Double getActualWeight() {
		return actualWeight;
	}

	public void setActualWeight(Double actualWeight) {
		this.actualWeight = actualWeight;
	}

	public Double getTotalCharge() {
		return totalCharge;
	}

	public void setTotalCharge(Double totalCharge) {
		this.totalCharge = totalCharge;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getSelfPickup() {
		return selfPickup;
	}

	public void setSelfPickup(String selfPickup) {
		this.selfPickup = selfPickup;
	}

	public String getSelfSend() {
		return selfSend;
	}

	public void setSelfSend(String selfSend) {
		this.selfSend = selfSend;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getThirdParty() {
		return thirdParty;
	}

	public void setThirdParty(String thirdParty) {
		this.thirdParty = thirdParty;
	}

	public String getMonthlyPaymentNo() {
		return monthlyPaymentNo;
	}

	public void setMonthlyPaymentNo(String monthlyPaymentNo) {
		this.monthlyPaymentNo = monthlyPaymentNo;
	}

	public String getThirdPartyAreaCode() {
		return thirdPartyAreaCode;
	}

	public void setThirdPartyAreaCode(String thirdPartyAreaCode) {
		this.thirdPartyAreaCode = thirdPartyAreaCode;
	}

	public String getPickupBy() {
		return pickupBy;
	}

	public void setPickupBy(String pickupBy) {
		this.pickupBy = pickupBy;
	}

	public String getDeliveredBy() {
		return deliveredBy;
	}

	public void setDeliveredBy(String deliveredBy) {
		this.deliveredBy = deliveredBy;
	}

	public Date getMailingDate() {
		return mailingDate;
	}

	public void setMailingDate(Date mailingDate) {
		this.mailingDate = mailingDate;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getSameDay() {
		return sameDay;
	}

	public void setSameDay(String sameDay) {
		this.sameDay = sameDay;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getFourDay() {
		return fourDay;
	}

	public void setFourDay(String fourDay) {
		this.fourDay = fourDay;
	}

	public String getAlabel() {
		return alabel;
	}

	public void setAlabel(String alabel) {
		this.alabel = alabel;
	}

	public String getGroundDelivery() {
		return groundDelivery;
	}

	public void setGroundDelivery(String groundDelivery) {
		this.groundDelivery = groundDelivery;
	}

	public Set<ExpressSingleDetail> getExpressSingleDetails() {
		return expressSingleDetails;
	}

	public void setExpressSingleDetails(Set<ExpressSingleDetail> expressSingleDetails) {
		this.expressSingleDetails = expressSingleDetails;
	}

}
