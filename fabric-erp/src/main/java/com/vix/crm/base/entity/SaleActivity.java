package com.vix.crm.base.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 销售阶段
 * 销售机会的进展情况，用于统计销售漏斗。常用的阶段有：获取机会，会议，报价，议价，签单。
 * @author Administrator
 *
 */
public class SaleActivity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 是否启用 */
	private String enable;
	/** 是否缺省 */
	private String isDefault;
	/** 状态名称 */
	private String name;
	/** 备注 */
	private String memo;
	
	public SaleActivity(){}
 
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
