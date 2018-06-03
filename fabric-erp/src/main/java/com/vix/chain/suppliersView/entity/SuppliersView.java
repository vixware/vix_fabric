package com.vix.chain.suppliersView.entity;

/**
 * 
 * com.vix.chain.suppliersView.entity.SuppliersView
 *
 * @author zhanghaibing
 *
 * @date   2014年10月28日
 */

public class SuppliersView {
	private String id;

	/** 承租户标识 */
	private String tenantId;
	/** 公司标识 */
	private String companyCode;

	private String companyInnerCode;
	/** 门店编码 */
	private String channelDistributorCode;
	/** 门店名称 */
	private String channelDistributorName;
	/** 金额(净单价*数量) */
	private Double netTotal;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyInnerCode() {
		return companyInnerCode;
	}

	public void setCompanyInnerCode(String companyInnerCode) {
		this.companyInnerCode = companyInnerCode;
	}

	public String getChannelDistributorCode() {
		return channelDistributorCode;
	}

	public void setChannelDistributorCode(String channelDistributorCode) {
		this.channelDistributorCode = channelDistributorCode;
	}

	public String getChannelDistributorName() {
		return channelDistributorName;
	}

	public void setChannelDistributorName(String channelDistributorName) {
		this.channelDistributorName = channelDistributorName;
	}

	public Double getNetTotal() {
		return netTotal;
	}

	public void setNetTotal(Double netTotal) {
		this.netTotal = netTotal;
	}

}