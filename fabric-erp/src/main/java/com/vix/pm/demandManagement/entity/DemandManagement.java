package com.vix.pm.demandManagement.entity;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseBOEntity;

/**
 * 
 * @ClassName: DemandManagement
 * @Description: 需求管理 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-2-20 下午5:55:10
 */
public class DemandManagement extends BaseBOEntity {

	private static final long serialVersionUID = -3635381736924712330L;
	/** 子分类集合 */
	private Set<DemandManagement> subCategorys = new HashSet<DemandManagement>();
	/** 父分类 */
	private DemandManagement parentCategory;
	
	/** 描述 *//*
	private String description;*/
	/** 使用情况*/
	private String serviceCondition;
	/** 耗损率*/
	private String consumeRate;
	/** 耗损单位 */
	private String consumeUnit;
	/** 寿命 */
	private String lifetime;
	/** 优先级 */
	private String priority;
	/** 预计工时 */
	private String expectedTime;
	/** 由谁评审 */
	private String review;
	
	
	public Set<DemandManagement> getSubCategorys() {
		return subCategorys;
	}
	public void setSubCategorys(Set<DemandManagement> subCategorys) {
		this.subCategorys = subCategorys;
	}
	public DemandManagement getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(DemandManagement parentCategory) {
		this.parentCategory = parentCategory;
	}
	public String getServiceCondition() {
		return serviceCondition;
	}
	public void setServiceCondition(String serviceCondition) {
		this.serviceCondition = serviceCondition;
	}
	public String getConsumeRate() {
		return consumeRate;
	}
	public void setConsumeRate(String consumeRate) {
		this.consumeRate = consumeRate;
	}
	public String getConsumeUnit() {
		return consumeUnit;
	}
	public void setConsumeUnit(String consumeUnit) {
		this.consumeUnit = consumeUnit;
	}
	public String getLifetime() {
		return lifetime;
	}
	public void setLifetime(String lifetime) {
		this.lifetime = lifetime;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getExpectedTime() {
		return expectedTime;
	}
	public void setExpectedTime(String expectedTime) {
		this.expectedTime = expectedTime;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}

}
