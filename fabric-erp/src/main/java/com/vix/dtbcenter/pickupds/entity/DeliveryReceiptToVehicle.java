/**
 * 
 */
package com.vix.dtbcenter.pickupds.entity;

/**
 * 装运运输(派车)单-车辆
 * 
 * @author zhanghaibing
 * 
 * @date 2013-8-20
 */
public class DeliveryReceiptToVehicle {
	private Long id;
	private Long deliveryreceiptid;
	private Long vehicleid;

    /** 承租户标识 */
	private String tenantId;
	
	private String companyInnerCode;
	
	private String DeliveryReceiptToVehicle;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDeliveryreceiptid() {
		return deliveryreceiptid;
	}

	public void setDeliveryreceiptid(Long deliveryreceiptid) {
		this.deliveryreceiptid = deliveryreceiptid;
	}

	public Long getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(Long vehicleid) {
		this.vehicleid = vehicleid;
	}

	public String getTenantId()
	{
		return tenantId;
	}

	public void setTenantId(String tenantId)
	{
		this.tenantId = tenantId;
	}

	public String getDeliveryReceiptToVehicle() {
		return DeliveryReceiptToVehicle;
	}

	public void setDeliveryReceiptToVehicle(String deliveryReceiptToVehicle) {
		DeliveryReceiptToVehicle = deliveryReceiptToVehicle;
	}

	public String getCompanyInnerCode() {
		return companyInnerCode;
	}

	public void setCompanyInnerCode(String companyInnerCode) {
		this.companyInnerCode = companyInnerCode;
	}

}
