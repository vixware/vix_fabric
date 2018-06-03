/**
 * 
 */
package com.vix.dtbcenter.pickupds.entity;

/**
 * 装运运输(派车)单-运输路线
 * 
 * @author zhanghaibing
 * 
 * @date 2013-8-20
 */
public class DeliveryReceiptToRoute {
	private String id;

	private String deliveryreceiptid;

	private String routeid;

	/** 承租户标识 */
	private String tenantId;

	private String companyInnerCode;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the deliveryreceiptid
	 */
	public String getDeliveryreceiptid() {
		return deliveryreceiptid;
	}

	/**
	 * @param deliveryreceiptid
	 *            the deliveryreceiptid to set
	 */
	public void setDeliveryreceiptid(String deliveryreceiptid) {
		this.deliveryreceiptid = deliveryreceiptid;
	}

	/**
	 * @return the routeid
	 */
	public String getRouteid() {
		return routeid;
	}

	/**
	 * @param routeid
	 *            the routeid to set
	 */
	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getCompanyInnerCode() {
		return companyInnerCode;
	}

	public void setCompanyInnerCode(String companyInnerCode) {
		this.companyInnerCode = companyInnerCode;
	}

}
