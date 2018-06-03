package com.vix.mdm.item.entity;

import com.vix.common.share.entity.BaseBOEntity;

/**
 * 商品基础类
 * 
 * @类全名 com.vix.mdm.item.entity.ItemBaseEntity
 *
 * @author zhanghaibing
 *
 * @date 2017年9月27日
 */
public class ItemBaseEntity extends BaseBOEntity {

	private static final long serialVersionUID = 1L;

	/** 行业领域 */
	private String industry;

	public ItemBaseEntity() {
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

}