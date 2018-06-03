/**
 * 
 */
package com.vix.dtbcenter.other.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 运输信息
 * 
 * @author zhanghaibing
 * 
 * @date 2013-9-1
 */
public class TransportationInfo extends BaseEntity {
	private static final long serialVersionUID = -3670542066438248984L;
	/** 发货方 */
	private String deliverier;
	/** 发货站点 */
	private String deliveryLocation;
	/** 要求装货时间 */
	private String loadDate;
	/** 发货城区 */
	private String deliveryArea;
	/** 发货联系人 */
	private String deliveryContacter;
	/** 电话 */
	private String phone;
	/** 收货方 */
	private String receiver;
	/** 收货站点 */
	private String receiveLcation;
	/** 要求到货时间 */
	private Date arrivalDate;
	/** 收货地区 */
	private String receiveArea;
	/** 收货联系人 */
	private String receiveContact;
	/** 收货人电话 */
	private String receiverPhone;
	/** 城际间距离 */
	private String distance;

	public String getDeliverier() {
		return deliverier;
	}

	public void setDeliverier(String deliverier) {
		this.deliverier = deliverier;
	}

	public String getDeliveryLocation() {
		return deliveryLocation;
	}

	public void setDeliveryLocation(String deliveryLocation) {
		this.deliveryLocation = deliveryLocation;
	}

	public String getLoadDate() {
		return loadDate;
	}

	public void setLoadDate(String loadDate) {
		this.loadDate = loadDate;
	}

	public String getDeliveryArea() {
		return deliveryArea;
	}

	public void setDeliveryArea(String deliveryArea) {
		this.deliveryArea = deliveryArea;
	}

	public String getDeliveryContacter() {
		return deliveryContacter;
	}

	public void setDeliveryContacter(String deliveryContacter) {
		this.deliveryContacter = deliveryContacter;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiveLcation() {
		return receiveLcation;
	}

	public void setReceiveLcation(String receiveLcation) {
		this.receiveLcation = receiveLcation;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getReceiveArea() {
		return receiveArea;
	}

	public void setReceiveArea(String receiveArea) {
		this.receiveArea = receiveArea;
	}

	public String getReceiveContact() {
		return receiveContact;
	}

	public void setReceiveContact(String receiveContact) {
		this.receiveContact = receiveContact;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

}
