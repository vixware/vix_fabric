package com.vix.crm.base.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 行业
 * 
 * @类全名 com.vix.crm.base.entity.Industry
 *
 * @author zhanghaibing
 *
 * @date 2017年10月13日
 */
public class Industry extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 是否启用 */
	private String enable;
	/** 是否缺省 */
	private String isDefault;
	/** 客户类型 */
	private String name;
	/** 备注 */
	private String memo;

	public Industry() {
	}

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
