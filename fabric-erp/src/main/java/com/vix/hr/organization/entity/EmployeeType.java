package com.vix.hr.organization.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 员工类型
 * 
 * @类全名 com.vix.hr.organization.entity.EmployeeType
 * 
 * @author zhanghaibing
 *
 * @date 2017年11月16日
 */
public class EmployeeType extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 提成类型 1,固定金额 2,比例
	 */
	private String percentageType;
	/**
	 * 结算类型 1,按订单 固定金额 按照成交一单多少钱提成 2,按月销售 按比例提成 根据每月销售额进行提成
	 */
	private String settlementsType;
	/**
	 * 提成金额
	 */
	private Double amount;
	/**
	 * 提成比例
	 */
	private Double proportion;
	/**
	 * 是否启用
	 */
	private String enabled;
	/**
	 * 门店
	 */
	private ChannelDistributor channelDistributor;

	public String getPercentageType() {
		return percentageType;
	}

	public void setPercentageType(String percentageType) {
		this.percentageType = percentageType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getProportion() {
		return proportion;
	}

	public void setProportion(Double proportion) {
		this.proportion = proportion;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getSettlementsType() {
		return settlementsType;
	}

	public void setSettlementsType(String settlementsType) {
		this.settlementsType = settlementsType;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

}
