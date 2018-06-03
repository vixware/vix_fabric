package com.vix.drp.projectFilingManagement.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 项目备案 com.vix.drp.projectFilingManagement.entity.ProjectFilingManagement
 * 
 * @author zhanghaibing
 * 
 * @date 2014-6-25
 */
public class ProjectFilingManagement extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 客户
	 */
	private String customer;
	/**
	 * 联系人
	 */
	private String contact;
	/**
	 * 经销商
	 */
	private ChannelDistributor channelDistributor;

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

}
