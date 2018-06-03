package com.vix.ebusiness.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 物流单
 * 
 * com.vix.ebusiness.entity.Shipping
 *
 * @author zhanghaibing
 *
 * @date 2014年9月21日
 */
public class Shipping extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// private String code; // 物流单编号
	/**
	 * 寄件公司
	 */
	private String fromCompany;
	/**
	 * 联络人
	 */
	private String contactPerson;
	/**
	 * 地址
	 */
	private String fromAddress;
	/**
	 * 联系电话
	 */
	private String fromPhone;

	private String tradeNo; // 订单编号
	private String sellerNick; // 卖家昵称
	private String buyerNick; // 买家昵称
	private Date deliveryStart; // 预约取货时间
	private Date deliveryEnd; // 预约取货结束 时间
	private String outSid; // 运单号
	private String itemTitle; // 货物名称
	private String receiverName; // 收件人姓名
	private String receiverPhone; // 收件人电话
	private String receiverMobile;// 收件人手机号码
	private String type; // 物流方式
	private String freightPayer; // 运费承担
	private Integer sellerConfirm;// 卖家是否确认发货
	private Integer isPrint; // 是否已打印 0 未打印   1,  已打印
	private Date dealTime; // 处理时间
	private String invoiceName; // 发票抬头
	private String buyerMemo; // 买家备注
	private Integer isArchived; // 是否已归档
	private Date archivedTime; // 归档时间
	private Integer printCount; // 打印次数
	private Integer printPage; // 打印位置
	private String logisticCode; // 物流公司编码
	private String logisticName; // 物流公司名称
	private String receiverState;//省
	private String receiverCity;//市
	private String receiverDistrict;//区
	private String receiverAddress;//街道
	private String receiverZip;//
	private String canvassName;//
	private String canvassContact;//
	private Date canvassTime;//
	private Long amount;//数量
	private Double shippingFee;//物流费用
	private Integer printIndex;//打印页数
	private Date automaticValidationTime;//自动确认时间
	private Double orderFee;//订单金额
	private Date deliveryTime;//发货时间
	private Integer onPassageTime;//在途时长
	private String isCare;//是否关怀
	/**
	 * 渠道
	 */
	private ChannelDistributor channelDistributor;
	/**
	 * 订单批次
	 */
	private OrderBatch orderBatch;
	/**
	 * 订单
	 */
	private Order order;

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

	public String getCanvassName() {
		return canvassName;
	}

	public void setCanvassName(String canvassName) {
		this.canvassName = canvassName;
	}

	public String getCanvassContact() {
		return canvassContact;
	}

	public void setCanvassContact(String canvassContact) {
		this.canvassContact = canvassContact;
	}

	public Date getCanvassTime() {
		return canvassTime;
	}

	public void setCanvassTime(Date canvassTime) {
		this.canvassTime = canvassTime;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Integer getPrintPage() {
		return printPage;
	}

	public void setPrintPage(Integer printPage) {
		this.printPage = printPage;
	}

	public Double getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(Double shippingFee) {
		this.shippingFee = shippingFee;
	}

	public Integer getPrintIndex() {
		return printIndex;
	}

	public void setPrintIndex(Integer printIndex) {
		this.printIndex = printIndex;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getSellerNick() {
		return sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

	public String getBuyerNick() {
		return buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	public java.util.Date getDeliveryStart() {
		return deliveryStart;
	}

	public void setDeliveryStart(java.util.Date deliveryStart) {
		this.deliveryStart = deliveryStart;
	}

	public java.util.Date getDeliveryEnd() {
		return deliveryEnd;
	}

	public void setDeliveryEnd(java.util.Date deliveryEnd) {
		this.deliveryEnd = deliveryEnd;
	}

	public String getOutSid() {
		return outSid;
	}

	public void setOutSid(String outSid) {
		this.outSid = outSid;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFreightPayer() {
		return freightPayer;
	}

	public void setFreightPayer(String freightPayer) {
		this.freightPayer = freightPayer;
	}

	public java.lang.Integer getSellerConfirm() {
		return sellerConfirm;
	}

	public void setSellerConfirm(java.lang.Integer sellerConfirm) {
		this.sellerConfirm = sellerConfirm;
	}

	public String getLogisticCode() {
		return logisticCode;
	}

	public void setLogisticCode(String logisticCode) {
		this.logisticCode = logisticCode;
	}

	public String getLogisticName() {
		return logisticName;
	}

	public void setLogisticName(String logisticName) {
		this.logisticName = logisticName;
	}

	public Integer getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(Integer isPrint) {
		this.isPrint = isPrint;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public String getInvoiceName() {
		return invoiceName;
	}

	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}

	public String getBuyerMemo() {
		return buyerMemo;
	}

	public void setBuyerMemo(String buyerMemo) {
		this.buyerMemo = buyerMemo;
	}

	public Integer getIsArchived() {
		return isArchived;
	}

	public void setIsArchived(Integer isArchived) {
		this.isArchived = isArchived;
	}

	public Date getArchivedTime() {
		return archivedTime;
	}

	public void setArchivedTime(Date archivedTime) {
		this.archivedTime = archivedTime;
	}

	public Integer getPrintCount() {
		return printCount;
	}

	public void setPrintCount(Integer printCount) {
		this.printCount = printCount;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public OrderBatch getOrderBatch() {
		return orderBatch;
	}

	public void setOrderBatch(OrderBatch orderBatch) {
		this.orderBatch = orderBatch;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Date getAutomaticValidationTime() {
		return automaticValidationTime;
	}

	public void setAutomaticValidationTime(Date automaticValidationTime) {
		this.automaticValidationTime = automaticValidationTime;
	}

	public Double getOrderFee() {
		return orderFee;
	}

	public void setOrderFee(Double orderFee) {
		this.orderFee = orderFee;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Integer getOnPassageTime() {
		return onPassageTime;
	}

	public void setOnPassageTime(Integer onPassageTime) {
		this.onPassageTime = onPassageTime;
	}

	public String getIsCare() {
		return isCare;
	}

	public void setIsCare(String isCare) {
		this.isCare = isCare;
	}

	public String getFromCompany() {
		return fromCompany;
	}

	public void setFromCompany(String fromCompany) {
		this.fromCompany = fromCompany;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getFromPhone() {
		return fromPhone;
	}

	public void setFromPhone(String fromPhone) {
		this.fromPhone = fromPhone;
	}

}
