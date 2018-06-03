package com.vix.common.orginialMeta.entity;

import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: BaseMetaData
 * @Description: 元数据对象 分类
 * @author wangmingchen
 * @date 2013-4-20 上午10:43:29
 *
 */
public class OrginialBaseMetaDataCategory extends BaseEntity {

	/** 元数据对象分类 名称 */
	private String categoryName;
	
	/** 元数据对象 */
	private Set<OrginialBaseMetaData> baseMetaDatas;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<OrginialBaseMetaData> getBaseMetaDatas() {
		return baseMetaDatas;
	}

	public void setBaseMetaDatas(Set<OrginialBaseMetaData> baseMetaDatas) {
		this.baseMetaDatas = baseMetaDatas;
	}
	
}
