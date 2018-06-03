package com.vix.dtbcenter.orderprocessing.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseBOEntity;

/**
 * 销售订单
 * 
 * @author Administrator
 * 
 */
public class WayBill extends BaseBOEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 托单编号
	 */
	private String shippingOrderCode;
	/**
	 * 委托时间
	 */
	private Date committedTime;
	/**
	 * 托运类型
	 */
	private String consignmentType;
	/**
	 * 主要运输方式
	 */
	private String mainTransportType;
	/**
	 * 托运方
	 */
	private String consignmentSide;
	/**
	 * 接单方
	 */
	private String receivingOrders;
	/**
	 * 紧急程度
	 */
	private String emergency;
	/**
	 * 客户业务编号
	 */
	private String customerServiceNumber;
	/**
	 * 结算方式
	 */
	private String clearingForm;
	/**
	 * 业务员
	 */
	private String salesman;
	/**
	 * 作业要求
	 */
	private String workRequest;
	/**
	 * 运输性质
	 */
	private String transportationNature;
	/**
	 * 发货方
	 */
	private String shipper;
	/**
	 * 站点
	 */
	private String shipperSite;
	/**
	 * 装货时间
	 */
	private Date shipperLoadTime;
	/**
	 * 地址
	 */
	private String shipperAddress;
	/**
	 * 联系人
	 */
	private String shipperContact;
	/**
	 * 电话
	 */
	private String shipperTel;
	/**
	 * 收货方
	 */
	private String consignee;
	/**
	 * 站点
	 */
	private String consigneeSite;
	/**
	 * 到货时间
	 */
	private Date consigneeLoadTime;
	/**
	 * 地址
	 */
	private String consigneeAddress;
	/**
	 * 联系人
	 */
	private String consigneeContact;
	/**
	 * 电话
	 */
	private String consigneeTel;
	/** 订单明细 */
	private Set<WayBillItem> wayBillItems = new HashSet<WayBillItem>();

	public WayBill() {
	}

	public String getShippingOrderCode() {
		return shippingOrderCode;
	}

	public void setShippingOrderCode(String shippingOrderCode) {
		this.shippingOrderCode = shippingOrderCode;
	}

	public Date getCommittedTime() {
		return committedTime;
	}

	public void setCommittedTime(Date committedTime) {
		this.committedTime = committedTime;
	}

	public String getConsignmentType() {
		return consignmentType;
	}

	public void setConsignmentType(String consignmentType) {
		this.consignmentType = consignmentType;
	}

	public String getMainTransportType() {
		return mainTransportType;
	}

	public void setMainTransportType(String mainTransportType) {
		this.mainTransportType = mainTransportType;
	}

	public String getConsignmentSide() {
		return consignmentSide;
	}

	public void setConsignmentSide(String consignmentSide) {
		this.consignmentSide = consignmentSide;
	}

	public String getReceivingOrders() {
		return receivingOrders;
	}

	public void setReceivingOrders(String receivingOrders) {
		this.receivingOrders = receivingOrders;
	}

	public String getEmergency() {
		return emergency;
	}

	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}

	public String getCustomerServiceNumber() {
		return customerServiceNumber;
	}

	public void setCustomerServiceNumber(String customerServiceNumber) {
		this.customerServiceNumber = customerServiceNumber;
	}

	public String getClearingForm() {
		return clearingForm;
	}

	public void setClearingForm(String clearingForm) {
		this.clearingForm = clearingForm;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getWorkRequest() {
		return workRequest;
	}

	public void setWorkRequest(String workRequest) {
		this.workRequest = workRequest;
	}

	public String getTransportationNature() {
		return transportationNature;
	}

	public void setTransportationNature(String transportationNature) {
		this.transportationNature = transportationNature;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public String getShipperSite() {
		return shipperSite;
	}

	public void setShipperSite(String shipperSite) {
		this.shipperSite = shipperSite;
	}

	public Date getShipperLoadTime() {
		return shipperLoadTime;
	}

	public void setShipperLoadTime(Date shipperLoadTime) {
		this.shipperLoadTime = shipperLoadTime;
	}

	public String getShipperAddress() {
		return shipperAddress;
	}

	public void setShipperAddress(String shipperAddress) {
		this.shipperAddress = shipperAddress;
	}

	public String getShipperContact() {
		return shipperContact;
	}

	public void setShipperContact(String shipperContact) {
		this.shipperContact = shipperContact;
	}

	public String getShipperTel() {
		return shipperTel;
	}

	public void setShipperTel(String shipperTel) {
		this.shipperTel = shipperTel;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getConsigneeSite() {
		return consigneeSite;
	}

	public void setConsigneeSite(String consigneeSite) {
		this.consigneeSite = consigneeSite;
	}

	public Date getConsigneeLoadTime() {
		return consigneeLoadTime;
	}

	public void setConsigneeLoadTime(Date consigneeLoadTime) {
		this.consigneeLoadTime = consigneeLoadTime;
	}

	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public String getConsigneeContact() {
		return consigneeContact;
	}

	public void setConsigneeContact(String consigneeContact) {
		this.consigneeContact = consigneeContact;
	}

	public String getConsigneeTel() {
		return consigneeTel;
	}

	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
	}

	public Set<WayBillItem> getWayBillItems() {
		return wayBillItems;
	}

	public void setWayBillItems(Set<WayBillItem> wayBillItems) {
		this.wayBillItems = wayBillItems;
	}

}
