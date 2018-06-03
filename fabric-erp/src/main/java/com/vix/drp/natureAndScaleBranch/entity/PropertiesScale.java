package com.vix.drp.natureAndScaleBranch.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 性质规模
 * 
 * @author zhanghaibing
 * 
 * @date 2013-5-21
 */
public class PropertiesScale extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1473009110720402605L;
	/**
	 * 所有性质
	 */
	private String allProperties;
	/**
	 * 业务管理信息化状况
	 */
	private String bmic;
	/**
	 * 人员规模
	 */
	private Integer workerScale;
	/**
	 * 销售人员规模
	 */
	private Integer salesmanScale;
	/**
	 * 渠道
	 */
	private ChannelDistributor channelDistributor;

	public String getAllProperties() {
		return allProperties;
	}

	public void setAllProperties(String allProperties) {
		this.allProperties = allProperties;
	}

	public String getBmic() {
		return bmic;
	}

	public void setBmic(String bmic) {
		this.bmic = bmic;
	}

	public Integer getWorkerScale() {
		return workerScale;
	}

	public void setWorkerScale(Integer workerScale) {
		this.workerScale = workerScale;
	}

	public Integer getSalesmanScale() {
		return salesmanScale;
	}

	public void setSalesmanScale(Integer salesmanScale) {
		this.salesmanScale = salesmanScale;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

}
