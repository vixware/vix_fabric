package com.vix.crm.base.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 花费时间  例如：半小时，一小时，半个工作日，一个工作日，两个工作日，两个工作日以上*/
public class ConsumeTime extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 是否启用 */
	private String enable;
	/** 是否缺省 */
	private String isDefault;
	/** 客户类型 */
	private String name;
	/** 备注 */
	private String memo;
	
	public ConsumeTime(){}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
