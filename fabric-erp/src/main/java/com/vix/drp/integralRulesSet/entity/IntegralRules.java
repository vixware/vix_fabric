/**
 * 
 */
package com.vix.drp.integralRulesSet.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 积分规则
 * 
 * @author zhanghaibing
 * 
 * @date 2013-10-20
 */
public class IntegralRules extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 积分兑换类型 1,根据消费额按照比率进行积分;2,根据会员等级设置积分兑换比率
	 */
	private String presentType;
	/**
	 * 积分赠送比率
	 */
	private Double conversiorate;
	/**
	 * 积分兑换比率
	 */
	private Double integralConsumptionRatio;
	/**
	 * 会员生日积分比率
	 */
	private Double memberBirthdayRatio;
	/**
	 * 是否注册送积分 1,是 2,否
	 */
	private String isZc;
	/**
	 * 注册赠送积分值
	 */
	private Double presentZc;
	/**
	 * 是否限时赠送积分 1,是 2,否
	 */
	private String isXszc;

	/**
	 * 限时赠送积分系数
	 */
	private Double xsConversiorate;
	/**
	 * 限时开始时间
	 */
	private Date xsStartDate;
	/**
	 * 限时结束时间
	 */
	private Date xsEndDate;
	/**
	 * 是否设置积分清零及有效期 1,是 2,否
	 */
	private String isJfyxq;
	/**
	 * 积分清零类型 1,月 2,年
	 */
	private String yxType;
	/**
	 * 门店
	 */
	private ChannelDistributor channelDistributor;

	public Double getConversiorate() {
		return conversiorate;
	}

	public void setConversiorate(Double conversiorate) {
		this.conversiorate = conversiorate;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public String getPresentType() {
		return presentType;
	}

	public void setPresentType(String presentType) {
		this.presentType = presentType;
	}

	public String getIsZc() {
		return isZc;
	}

	public void setIsZc(String isZc) {
		this.isZc = isZc;
	}

	public Double getPresentZc() {
		return presentZc;
	}

	public void setPresentZc(Double presentZc) {
		this.presentZc = presentZc;
	}

	public String getIsXszc() {
		return isXszc;
	}

	public void setIsXszc(String isXszc) {
		this.isXszc = isXszc;
	}

	public Double getXsConversiorate() {
		return xsConversiorate;
	}

	public void setXsConversiorate(Double xsConversiorate) {
		this.xsConversiorate = xsConversiorate;
	}

	public Double getIntegralConsumptionRatio() {
		return integralConsumptionRatio;
	}

	public void setIntegralConsumptionRatio(Double integralConsumptionRatio) {
		this.integralConsumptionRatio = integralConsumptionRatio;
	}

	public Date getXsStartDate() {
		return xsStartDate;
	}

	public void setXsStartDate(Date xsStartDate) {
		this.xsStartDate = xsStartDate;
	}

	public Date getXsEndDate() {
		return xsEndDate;
	}

	public void setXsEndDate(Date xsEndDate) {
		this.xsEndDate = xsEndDate;
	}

	public String getIsJfyxq() {
		return isJfyxq;
	}

	public void setIsJfyxq(String isJfyxq) {
		this.isJfyxq = isJfyxq;
	}

	public String getYxType() {
		return yxType;
	}

	public void setYxType(String yxType) {
		this.yxType = yxType;
	}

	public Double getMemberBirthdayRatio() {
		return memberBirthdayRatio;
	}

	public void setMemberBirthdayRatio(Double memberBirthdayRatio) {
		this.memberBirthdayRatio = memberBirthdayRatio;
	}

}
