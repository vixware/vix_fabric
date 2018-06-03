/**
 * 
 */
package com.vix.drp.channel.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.drp.channel.entity.ChannelDistributorMessageSet
 * 
 * @author zhanghaibing
 *
 * @date 2017年10月31日
 */
public class ChannelDistributorMessageSet extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 余额
	 */
	private Double amount;
	/**
	 * 发送短信次数
	 */
	private Double num;
	/**
	 * 短信是否可用 0,可用 1,不可用
	 */
	private String isEnable;

	private ChannelDistributor channelDistributor;

	private String salesInform;// 消费自动通知：开卡/充值/消费/兑换短信通知
	private String birthdayInform;// 生日自动通知
	private String commemorationInform;// 纪念日通知：如结婚纪念日、持卡人子女生日
	private String servicePlansInform;// 服务计划提醒
	private String businessVolumeInform;// 营业额短信通知
	private String holidayInform;// 节假日问候/促销群发
	private String startBusinessInform;// 新店开业促销群发
	private String newItemInform;// 新品上市促销群发
	private String sleepingMemberInform;// 沉睡会员唤醒群发

	public ChannelDistributorMessageSet() {
		super();
	}

	public ChannelDistributorMessageSet(String id) {
		super();
		setId(id);
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public String getSalesInform() {
		return salesInform;
	}

	public void setSalesInform(String salesInform) {
		this.salesInform = salesInform;
	}

	public String getBirthdayInform() {
		return birthdayInform;
	}

	public void setBirthdayInform(String birthdayInform) {
		this.birthdayInform = birthdayInform;
	}

	public String getCommemorationInform() {
		return commemorationInform;
	}

	public void setCommemorationInform(String commemorationInform) {
		this.commemorationInform = commemorationInform;
	}

	public String getServicePlansInform() {
		return servicePlansInform;
	}

	public void setServicePlansInform(String servicePlansInform) {
		this.servicePlansInform = servicePlansInform;
	}

	public String getBusinessVolumeInform() {
		return businessVolumeInform;
	}

	public void setBusinessVolumeInform(String businessVolumeInform) {
		this.businessVolumeInform = businessVolumeInform;
	}

	public String getHolidayInform() {
		return holidayInform;
	}

	public void setHolidayInform(String holidayInform) {
		this.holidayInform = holidayInform;
	}

	public String getStartBusinessInform() {
		return startBusinessInform;
	}

	public void setStartBusinessInform(String startBusinessInform) {
		this.startBusinessInform = startBusinessInform;
	}

	public String getNewItemInform() {
		return newItemInform;
	}

	public void setNewItemInform(String newItemInform) {
		this.newItemInform = newItemInform;
	}

	public String getSleepingMemberInform() {
		return sleepingMemberInform;
	}

	public void setSleepingMemberInform(String sleepingMemberInform) {
		this.sleepingMemberInform = sleepingMemberInform;
	}

}
