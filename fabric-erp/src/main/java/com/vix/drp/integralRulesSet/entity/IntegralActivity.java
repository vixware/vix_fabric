/**
 * 
 */
package com.vix.drp.integralRulesSet.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 
 * @类全名 com.vix.drp.integralRulesSet.entity.IntegralActivity
 * 
 * @author zhanghaibing
 *
 * @date 2017年11月16日
 */
public class IntegralActivity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 积分赠送比率
	 */
	private Double conversiorate;
	/**
	 * 积分兑换比率
	 */
	private Double integralConsumptionRatio;
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
	 * 限时开始时间
	 */
	private Date xsStartDate;
	/**
	 * 限时结束时间
	 */
	private Date xsEndDate;
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

	public Double getIntegralConsumptionRatio() {
		return integralConsumptionRatio;
	}

	public void setIntegralConsumptionRatio(Double integralConsumptionRatio) {
		this.integralConsumptionRatio = integralConsumptionRatio;
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

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public String getXsStartDateStr() {
		if (null != xsStartDate) {
			return DateUtil.format(xsStartDate);
		}
		return "";
	}

	public String getXsEndDateStr() {
		if (null != xsEndDate) {
			return DateUtil.format(xsEndDate);
		}
		return "";
	}
}