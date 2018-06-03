package com.vix.mm.settings.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @Description:设置-资源资料明细
 * @author 李大鹏
 */
public class ResourceiIformationDetail extends BaseEntity {

	private static final long serialVersionUID = -359496033574612503L;
	/** 工作中心 */
	private String org;
	/** 工作中心名称 */
	private String orgName;
	/** 计算产能 */
	private String productivityCalculation;
	/** 可用数量 */
	private String quantityAvailable;
	/** 利用率 */
	private String utilizationRate;
	/** 效率 */
	private String efficiency;
	/** 有效排程相关 */
	private String effectiveSchedulingRelated;
	/** 关键资源 */
	private String criticalResources;
	/** 资课费率 */
	private String zikeRate;
	/** 超载百分比 */
	private String overloadPercentage;
	/** 资源资料 */
	private ResourceiIformation resourceiIformation;

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getProductivityCalculation() {
		return productivityCalculation;
	}

	public void setProductivityCalculation(String productivityCalculation) {
		this.productivityCalculation = productivityCalculation;
	}

	public String getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(String quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public String getUtilizationRate() {
		return utilizationRate;
	}

	public void setUtilizationRate(String utilizationRate) {
		this.utilizationRate = utilizationRate;
	}

	public String getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(String efficiency) {
		this.efficiency = efficiency;
	}

	public String getEffectiveSchedulingRelated() {
		return effectiveSchedulingRelated;
	}

	public void setEffectiveSchedulingRelated(String effectiveSchedulingRelated) {
		this.effectiveSchedulingRelated = effectiveSchedulingRelated;
	}

	public String getCriticalResources() {
		return criticalResources;
	}

	public void setCriticalResources(String criticalResources) {
		this.criticalResources = criticalResources;
	}

	public String getZikeRate() {
		return zikeRate;
	}

	public void setZikeRate(String zikeRate) {
		this.zikeRate = zikeRate;
	}

	public ResourceiIformation getResourceiIformation() {
		return resourceiIformation;
	}

	public void setResourceiIformation(ResourceiIformation resourceiIformation) {
		this.resourceiIformation = resourceiIformation;
	}

	public String getOverloadPercentage() {
		return overloadPercentage;
	}

	public void setOverloadPercentage(String overloadPercentage) {
		this.overloadPercentage = overloadPercentage;
	}

}
