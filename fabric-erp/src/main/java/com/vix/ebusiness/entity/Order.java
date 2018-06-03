package com.vix.ebusiness.entity;

import java.util.Date;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.option.entity.Logistics;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.warehouse.entity.InvWarehouse;

/**
 * 交易订单
 * 
 * com.vix.ebusiness.entity.Order
 *
 * @author zhanghaibing
 *
 * @date 2014年9月25日
 */
public class Order extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 网店
	 */
	private ChannelDistributor channelDistributor;
	/**
	 * 员工
	 */
	private Employee employee;

	/**
	 * 订单编码
	 */
	private String outerId;
	/**
	 * 商品购买数量
	 */
	private Double num;
	/**
	 * 交易类型
	 */
	private String type;
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
	 * 付款时间
	 */
	private Date payTime;
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
	 * 商品图片绝对途径
	 */
	private String picPath;

	/**
	 * 支付类型中文
	 */
	private String payTypeCn;
	/**
	 * 订单明细
	 */
	private Set<OrderDetail> orderDetailList;
	/**
	 * 订单明细
	 */
	private String orderDetails;
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
	 * 处理状态: 1,未处理 2,已分拣 3,代发货 4,已发货 5,已完成 6,分拣中 7,已分仓 8,已打单
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
	private OrderPrintData orderPrintData;

	private Integer orderDetailsNum;
	private Integer tradeClass;

	private String distributionCenterName;
	private String partnerName;
	private String sortingCode;
	private String distributionCenterCode;
	private String shippingCompanyCode;
	private String shippingCompanyName;

	private Integer packageNum;// 包裹数量
	private String shippingNo; // 物流单号
	private Integer outInteractingState;
	private String latestInteractingError; // 最近一次交互错误
	private Date latestInteractingErrorTime;// 最近一次交互错误时间

	/**
	 * 重量
	 */
	private Double weight;

	/**
	 * 交易优惠详情
	 */
	/* private List<TradePromotionDetail> tradePromotionDetail; */
	/**
	 * 会员信息
	 */
	private BusinessCustomer customer;
	/**
	 * 区域
	 */
	private DistributionCenter distributionCenter;
	/**
	 * 仓库
	 */
	private InvWarehouse invWarehouse;
	/**
	 * 物流
	 */
	private Logistics logistics;
	/**
	 * 订单批次
	 */
	private OrderBatch orderBatch;
	/**
	 * 是否称重0 未称重 1 已称重
	 */
	private String isWeight;

	public String getLatestInteractingError() {
		return latestInteractingError;
	}

	public void setLatestInteractingError(String latestInteractingError) {
		this.latestInteractingError = latestInteractingError;
	}

	public Date getLatestInteractingErrorTime() {
		return latestInteractingErrorTime;
	}

	public void setLatestInteractingErrorTime(Date latestInteractingErrorTime) {
		this.latestInteractingErrorTime = latestInteractingErrorTime;
	}

	public Integer getSellerShippingType() {
		return sellerShippingType;
	}

	public void setSellerShippingType(Integer sellerShippingType) {
		this.sellerShippingType = sellerShippingType;
	}

	public OrderPrintData getOrderPrintData() {
		return orderPrintData;
	}

	public void setOrderPrintData(OrderPrintData orderPrintData) {
		this.orderPrintData = orderPrintData;
	}

	public Integer getBuyerObtainPointFee() {
		return buyerObtainPointFee;
	}

	public Integer getPackageNum() {
		return packageNum;
	}

	public void setPackageNum(Integer packageNum) {
		this.packageNum = packageNum;
	}

	public String getShippingNo() {
		return shippingNo;
	}

	public void setShippingNo(String shippingNo) {
		this.shippingNo = shippingNo;
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

	public Double getNum() {
		if (orderDetailList != null && orderDetailList.size() > 0) {
			Double num = 0D;
			for (OrderDetail orderDetail : orderDetailList) {
				if (orderDetail != null) {
					num += orderDetail.getNum();
				}
			}
			return num;
		}
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
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

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
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

	public Set<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(Set<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	public String getTradeMemo() {
		return tradeMemo;
	}

	public void setTradeMemo(String tradeMemo) {
		this.tradeMemo = tradeMemo;
	}

	public Integer getOrderDetailsNum() {
		return orderDetailsNum;
	}

	public void setOrderDetailsNum(Integer orderDetailsNum) {
		this.orderDetailsNum = orderDetailsNum;
	}

	public Integer getTradeClass() {
		return tradeClass;
	}

	public void setTradeClass(Integer tradeClass) {
		this.tradeClass = tradeClass;
	}

	public String getDistributionCenterName() {
		return distributionCenterName;
	}

	public void setDistributionCenterName(String distributionCenterName) {
		this.distributionCenterName = distributionCenterName;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getSortingCode() {
		return sortingCode;
	}

	public void setSortingCode(String sortingCode) {
		this.sortingCode = sortingCode;
	}

	public String getDistributionCenterCode() {
		return distributionCenterCode;
	}

	public void setDistributionCenterCode(String distributionCenterCode) {
		this.distributionCenterCode = distributionCenterCode;
	}

	public String getShippingCompanyCode() {
		return shippingCompanyCode;
	}

	public void setShippingCompanyCode(String shippingCompanyCode) {
		this.shippingCompanyCode = shippingCompanyCode;
	}

	public String getShippingCompanyName() {
		return shippingCompanyName;
	}

	public void setShippingCompanyName(String shippingCompanyName) {
		this.shippingCompanyName = shippingCompanyName;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public BusinessCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(BusinessCustomer customer) {
		this.customer = customer;
	}

	public InvWarehouse getInvWarehouse() {
		return invWarehouse;
	}

	public void setInvWarehouse(InvWarehouse invWarehouse) {
		this.invWarehouse = invWarehouse;
	}

	public Logistics getLogistics() {
		return logistics;
	}

	public void setLogistics(Logistics logistics) {
		this.logistics = logistics;
	}

	public DistributionCenter getDistributionCenter() {
		return distributionCenter;
	}

	public void setDistributionCenter(DistributionCenter distributionCenter) {
		this.distributionCenter = distributionCenter;
	}

	public OrderBatch getOrderBatch() {
		return orderBatch;
	}

	public void setOrderBatch(OrderBatch orderBatch) {
		this.orderBatch = orderBatch;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public String getChannelDistributorName() {
		if (channelDistributor != null) {
			return channelDistributor.getName();
		}
		return "";
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getIsWeight() {
		return isWeight;
	}

	public void setIsWeight(String isWeight) {
		this.isWeight = isWeight;
	}

	public String getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Integer getOutInteractingState() {
		return outInteractingState;
	}

	public void setOutInteractingState(Integer outInteractingState) {
		this.outInteractingState = outInteractingState;
	}

}
