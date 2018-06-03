/**
 * 
 */
package com.vix.dtbcenter.pickupds.entity;

/**
 * 装运运输(派车)单-司机
 * 
 * @author zhanghaibing
 * 
 * @date 2013-8-20
 */
public class DeliveryReceiptToDriver {
	private Long id;
	private Long deliveryreceiptid;
	private Long driverid;

    /** 承租户标识 */
	private String tenantId;
	
	private String companyInnerCode;

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

	public Long getDriverid() {
		return driverid;
	}

	public void setDriverid(Long driverid) {
		this.driverid = driverid;
	}

	public String getTenantId()
	{
		return tenantId;
	}

	public void setTenantId(String tenantId)
	{
		this.tenantId = tenantId;
	}

	public String getCompanyInnerCode() {
		return companyInnerCode;
	}

	public void setCompanyInnerCode(String companyInnerCode) {
		this.companyInnerCode = companyInnerCode;
	}

}
