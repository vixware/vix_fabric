package com.vix.ebusiness.entity;

import java.util.Date;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 
 * com.vix.ebusiness.entity.InvoiceList
 *
 * @author zhanghaibing
 *
 * @date 2014年9月21日
 */
public class InvoiceList extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date confirmTime;
	// private String code;// 发货单编号
	private Date invoiceTime;// 发货日期
	private Double total;// 合单总金额
	private Integer isPrint; // /是否已打印
	private Double discountFee;// 优惠
	private Double postageFee; // 运费
	private Double payment; // 实付金额
	private Integer channelModelType;
	private Date dealTime;
	private Integer printSet;
	private String printContent;
	private String orderCode;
	private String receiverName;
	private String receiverState;
	private String receiverCity;
	private String receiverDistrict;
	private String receiverAddress;
	private String receiverZip;
	private String receiverMobile;
	private String receiverPhone;
	private String logisticName;
	private String shippingType;
	private Integer isArchived;
	private Date archivedTime;
	private Integer LBPindex;
	private Integer SOPindex;
	private Integer printCount; // 打印次数
	private Integer printIndex; // 打印编号
	private Integer totalNum; // 总件数
	private String greetings;
	private String fromCompany;
	/**
	 * 订单
	 */
	private Order order;
	/**
	 * 订单批次
	 */
	private OrderBatch orderBatch;
	/**
	 * 渠道
	 */
	/* private Channel channel; */
	private ChannelDistributor channelDistributor;
	/**
	 * 发货单明细
	 */
	private Set<InvoiceListDetail> subInvoiceListDetails;

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getLogisticName() {
		return logisticName;
	}

	public void setLogisticName(String logisticName) {
		this.logisticName = logisticName;
	}

	public Date getInvoiceTime() {
		return invoiceTime;
	}

	public void setInvoiceTime(Date invoiceTime) {
		this.invoiceTime = invoiceTime;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(Integer isPrint) {
		this.isPrint = isPrint;
	}

	public Double getDiscountFee() {
		return discountFee;
	}

	public void setDiscountFee(Double discountFee) {
		this.discountFee = discountFee;
	}

	public Double getPostageFee() {
		return postageFee;
	}

	public void setPostageFee(Double postageFee) {
		this.postageFee = postageFee;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public Integer getChannelModelType() {
		return channelModelType;
	}

	public void setChannelModelType(Integer channelModelType) {
		this.channelModelType = channelModelType;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public Integer getPrintSet() {
		return printSet;
	}

	public void setPrintSet(Integer printSet) {
		this.printSet = printSet;
	}

	public String getPrintContent() {
		return printContent;
	}

	public void setPrintContent(String printContent) {
		this.printContent = printContent;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
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

	public String getFromCompany() {
		return fromCompany;
	}

	public void setFromCompany(String fromCompany) {
		this.fromCompany = fromCompany;
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

	public String getShippingType() {
		return shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
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

	public Integer getLBPindex() {
		return LBPindex;
	}

	public void setLBPindex(Integer lBPindex) {
		LBPindex = lBPindex;
	}

	public Integer getSOPindex() {
		return SOPindex;
	}

	public void setSOPindex(Integer sOPindex) {
		SOPindex = sOPindex;
	}

	public Integer getPrintCount() {
		return printCount;
	}

	public void setPrintCount(Integer printCount) {
		this.printCount = printCount;
	}

	public Integer getPrintIndex() {
		return printIndex;
	}

	public void setPrintIndex(Integer printIndex) {
		this.printIndex = printIndex;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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

	public Set<InvoiceListDetail> getSubInvoiceListDetails() {
		return subInvoiceListDetails;
	}

	public void setSubInvoiceListDetails(Set<InvoiceListDetail> subInvoiceListDetails) {
		this.subInvoiceListDetails = subInvoiceListDetails;
	}

	public String getGreetings() {
		return greetings;
	}

	public void setGreetings(String greetings) {
		this.greetings = greetings;
	}

}
