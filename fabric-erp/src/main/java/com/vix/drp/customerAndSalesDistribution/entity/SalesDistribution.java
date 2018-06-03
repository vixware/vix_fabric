package com.vix.drp.customerAndSalesDistribution.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 销售分布
 * 
 * @author zhanghaibing
 * 
 * @date 2013-5-21
 */
public class SalesDistribution extends BaseEntity {
	private static final long serialVersionUID = 447989117809257525L;
	/**
	 * 销售经理
	 */
	private String salesManager;
	/**
	 * 内勤人员
	 */
	private String officeStaff;
	/**
	 * 区域范围
	 */
	private String areaCoverage;
	/**
	 * 渠道
	 */
	private ChannelDistributor channelDistributor;

	public String getSalesManager() {
		return salesManager;
	}

	public void setSalesManager(String salesManager) {
		this.salesManager = salesManager;
	}

	public String getOfficeStaff() {
		return officeStaff;
	}

	public void setOfficeStaff(String officeStaff) {
		this.officeStaff = officeStaff;
	}

	public String getAreaCoverage() {
		return areaCoverage;
	}

	public void setAreaCoverage(String areaCoverage) {
		this.areaCoverage = areaCoverage;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

}
