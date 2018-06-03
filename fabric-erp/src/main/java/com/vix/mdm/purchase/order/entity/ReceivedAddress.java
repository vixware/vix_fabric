package com.vix.mdm.purchase.order.entity;

import com.vix.common.share.entity.BaseEntity;

public class ReceivedAddress extends BaseEntity {

	private static final long serialVersionUID = 6416622837190331899L;
	/** 国家 */
	private String country;
	/** 城市 */
	private String city;
	/** 省 */
	private String provience;
	/** 街道 */
	private String street;
	/** 邮政编码 */
	private String postCode;
	/** 邮政信箱 */
	private String postBox;
	/** 采购订单 */
	private PurchaseOrder purchaseOrder;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvience() {
		return provience;
	}

	public void setProvience(String provience) {
		this.provience = provience;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPostBox() {
		return postBox;
	}

	public void setPostBox(String postBox) {
		this.postBox = postBox;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

}
