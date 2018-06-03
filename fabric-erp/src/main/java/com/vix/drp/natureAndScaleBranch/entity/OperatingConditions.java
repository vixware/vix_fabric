package com.vix.drp.natureAndScaleBranch.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 经营状况
 * 
 * @author zhanghaibing
 * 
 * @date 2013-5-21
 */
public class OperatingConditions extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 447989117809257525L;
	/**
	 * 客户数量
	 */
	private Integer customerNumbers;
	/**
	 * 仓库规模
	 */
	private Double warehouseScale;
	/**
	 * 配送解决模式
	 */
	private String distributionMode;
	/**
	 * 车辆数量
	 */
	private Integer vehicleNumber;
	/**
	 * 年度
	 */
	private Integer year;
	/**
	 * 销售额
	 */
	private Double salesAmount;
	/**
	 * 销售利润
	 */
	private Double salesProfit;
	/**
	 * 渠道
	 */
	private ChannelDistributor channelDistributor;

	public Integer getCustomerNumbers() {
		return customerNumbers;
	}

	public void setCustomerNumbers(Integer customerNumbers) {
		this.customerNumbers = customerNumbers;
	}

	public Double getWarehouseScale() {
		return warehouseScale;
	}

	public void setWarehouseScale(Double warehouseScale) {
		this.warehouseScale = warehouseScale;
	}

	public String getDistributionMode() {
		return distributionMode;
	}

	public void setDistributionMode(String distributionMode) {
		this.distributionMode = distributionMode;
	}

	public Integer getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(Integer vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Double getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(Double salesAmount) {
		this.salesAmount = salesAmount;
	}

	public Double getSalesProfit() {
		return salesProfit;
	}

	public void setSalesProfit(Double salesProfit) {
		this.salesProfit = salesProfit;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

}
