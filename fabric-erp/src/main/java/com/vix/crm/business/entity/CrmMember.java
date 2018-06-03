package com.vix.crm.business.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.crm.entity.CustomerAccount;

/**
 * 会员消费汇总 com.vix.crm.business.entity.CrmMember
 *
 * @author zhanghaibing
 *
 * @date 2015年1月8日
 */
public class CrmMember extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 购买次数 /交易成功笔数
	 */
	private Long tradeCount;
	/**
	 * 购买金额/交易成功的金额，单位为元
	 */
	private Double tradeAmount;
	/**
	 * 购买件数/购买的商品件数
	 */
	private Long itemNum;
	/**
	 * 退款订单数/交易关闭的的笔数 退货订单量
	 */
	private Long closeTradeCount;
	/**
	 * 退款金额/交易关闭的金额 退货订单金额，单位为元
	 */
	private Double closeTradeAmount;
	/**
	 * 最后交易时间
	 */
	private Date lastTradeTime;
	private Date lastTime;
	/**
	 * 第一次够买时间
	 */
	private Date firstTradeTime;
	/**
	 * 会员等级， 1:店铺普通会员，2:店铺中级会员 3:店铺高级会员 4:店铺VIP会员
	 */
	private String grade;
	/**
	 * 平均客单价，单位为元
	 */
	private Double avgPrice;
	/**
	 * 会员信息
	 */
	private CustomerAccount customerAccount;
	/**
	 * 门店KEY
	 */
	private String channelDistributorId;

	public Long getTradeCount() {
		return tradeCount;
	}

	public void setTradeCount(Long tradeCount) {
		this.tradeCount = tradeCount;
	}

	public Double getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(Double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public Long getItemNum() {
		return itemNum;
	}

	public void setItemNum(Long itemNum) {
		this.itemNum = itemNum;
	}

	public Long getCloseTradeCount() {
		return closeTradeCount;
	}

	public void setCloseTradeCount(Long closeTradeCount) {
		this.closeTradeCount = closeTradeCount;
	}

	public Double getCloseTradeAmount() {
		return closeTradeAmount;
	}

	public void setCloseTradeAmount(Double closeTradeAmount) {
		this.closeTradeAmount = closeTradeAmount;
	}

	public Date getLastTradeTime() {
		return lastTradeTime;
	}

	public void setLastTradeTime(Date lastTradeTime) {
		this.lastTradeTime = lastTradeTime;
	}

	public Date getFirstTradeTime() {
		return firstTradeTime;
	}

	public void setFirstTradeTime(Date firstTradeTime) {
		this.firstTradeTime = firstTradeTime;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Double getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(Double avgPrice) {
		this.avgPrice = avgPrice;
	}

	public String getChannelDistributorId() {
		return channelDistributorId;
	}

	public void setChannelDistributorId(String channelDistributorId) {
		this.channelDistributorId = channelDistributorId;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

}
