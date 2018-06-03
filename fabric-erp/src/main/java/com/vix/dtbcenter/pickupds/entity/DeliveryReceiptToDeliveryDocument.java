/**
 * 
 */
package com.vix.dtbcenter.pickupds.entity;

/**
 * 装运运输(派车)单 -销售发货单(交货文件/出货通知单?)
 * 
 * @author zhanghaibing
 * 
 * @date 2013-8-20
 */
public class DeliveryReceiptToDeliveryDocument {
	private Long id;
	private Long deliveryreceiptid;
	private Long deliverydocumentid;

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

	public Long getDeliverydocumentid() {
		return deliverydocumentid;
	}

	public void setDeliverydocumentid(Long deliverydocumentid) {
		this.deliverydocumentid = deliverydocumentid;
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
