package com.vix.drp.newProductLaunchInformation.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 产品投放信息
 * 
 * @author zhanghaibing
 * 
 * @date 2013-5-21
 */
public class ProductInformation extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 447989117809257525L;

	/** 规格型号 */
	private String specification;
	/** 地区 */
	private String region;
	/** 价格 */
	private Double price;
	/** 生产日期 */
	private Date produceDate;
	/** 保质期 */
	private Float qualityPeriod;
	/** 投放日期 */
	private Date putOnDate;
	/** 主计量单位 */
	private String masterUnit;
	/** 生产企业 */
	private String productEnterprise;
	/** 品牌 */
	private String brand;
	/** 产地 */
	private String origin;

	/**
	 * 渠道
	 */
	private ChannelDistributor channelDistributor;

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	public Float getQualityPeriod() {
		return qualityPeriod;
	}

	public void setQualityPeriod(Float qualityPeriod) {
		this.qualityPeriod = qualityPeriod;
	}

	public Date getPutOnDate() {
		return putOnDate;
	}

	public void setPutOnDate(Date putOnDate) {
		this.putOnDate = putOnDate;
	}

	public String getMasterUnit() {
		return masterUnit;
	}

	public void setMasterUnit(String masterUnit) {
		this.masterUnit = masterUnit;
	}

	public String getProductEnterprise() {
		return productEnterprise;
	}

	public void setProductEnterprise(String productEnterprise) {
		this.productEnterprise = productEnterprise;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

}
