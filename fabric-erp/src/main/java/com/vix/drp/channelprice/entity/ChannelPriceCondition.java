package com.vix.drp.channelprice.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.CurrencyType;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.mdm.item.entity.Item;

/**
 * 
 * com.vix.drp.channelprice.entity.ChannelPriceCondition
 *
 * @author bjitzhang
 *
 * @date 2015年3月17日
 */
public class ChannelPriceCondition extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 渠道分销商
	 */
	private ChannelDistributor channelDistributor;
	private Item item;
	/** 币种 */
	private CurrencyType currencyType;
	/** 税率 */
	private Double defaultTax;
	/** 开始有效时间 */
	private Date startEffectiveTime;
	/** 结束有效时间 */
	private Date endEffectiveTime;
	/** 是否为临时对象 */
	private String isTemp;
	/** 定价条件类型 */
	private String priceConditionType;
	/** 数量条件规则 */
	private Set<ChannelPriceConditionCountArea> channelPriceConditionCountAreas = new HashSet<ChannelPriceConditionCountArea>();
	/** 金额条件规则 */
	private Set<ChannelPriceConditionPriceArea> channelPriceConditionPriceAreas = new HashSet<ChannelPriceConditionPriceArea>();

	public ChannelPriceCondition() {
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public Double getDefaultTax() {
		return defaultTax;
	}

	public void setDefaultTax(Double defaultTax) {
		this.defaultTax = defaultTax;
	}

	public Date getStartEffectiveTime() {
		return startEffectiveTime;
	}

	public void setStartEffectiveTime(Date startEffectiveTime) {
		this.startEffectiveTime = startEffectiveTime;
	}

	public Date getEndEffectiveTime() {
		return endEffectiveTime;
	}

	public void setEndEffectiveTime(Date endEffectiveTime) {
		this.endEffectiveTime = endEffectiveTime;
	}

	@Override
	public String getIsTemp() {
		return isTemp;
	}

	@Override
	public void setIsTemp(String isTemp) {
		this.isTemp = isTemp;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public String getPriceConditionType() {
		return priceConditionType;
	}

	public void setPriceConditionType(String priceConditionType) {
		this.priceConditionType = priceConditionType;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Set<ChannelPriceConditionCountArea> getChannelPriceConditionCountAreas() {
		return channelPriceConditionCountAreas;
	}

	public void setChannelPriceConditionCountAreas(Set<ChannelPriceConditionCountArea> channelPriceConditionCountAreas) {
		this.channelPriceConditionCountAreas = channelPriceConditionCountAreas;
	}

	public Set<ChannelPriceConditionPriceArea> getChannelPriceConditionPriceAreas() {
		return channelPriceConditionPriceAreas;
	}

	public void setChannelPriceConditionPriceAreas(Set<ChannelPriceConditionPriceArea> channelPriceConditionPriceAreas) {
		this.channelPriceConditionPriceAreas = channelPriceConditionPriceAreas;
	}

}
