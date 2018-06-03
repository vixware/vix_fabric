package com.vix.pm.meetingManagement.entity;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseBOEntity;

/**
 * 
 * @ClassName: SortingDevice
 * @Description: 设备分类  
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-2-18 下午4:30:28
 */
public class MeetingManagement extends BaseBOEntity {

	private static final long serialVersionUID = -3635381736924712330L;
	/** 子分类集合 */
	private Set<MeetingManagement> subCategorys = new HashSet<MeetingManagement>();
	/** 父分类 */
	private MeetingManagement parentCategory;
	
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
	
	
	public Set<MeetingManagement> getSubCategorys() {
		return subCategorys;
	}
	public void setSubCategorys(Set<MeetingManagement> subCategorys) {
		this.subCategorys = subCategorys;
	}
	public MeetingManagement getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(MeetingManagement parentCategory) {
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

}
