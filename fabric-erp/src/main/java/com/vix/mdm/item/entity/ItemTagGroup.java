package com.vix.mdm.item.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 产品标签组
 * 
 * @类全名 com.vix.mdm.item.entity.ItemTagGroup
 * 
 * @author zhanghaibing
 *
 * @date 2017年12月5日
 */
public class ItemTagGroup extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 是否启用 1：启用 0：禁用 */
	private String enable;
	/** 标签 */
	private Set<ItemTag> subItemTags = new HashSet<ItemTag>();

	public ItemTagGroup() {
	}

	public String getEnable() {
		if (null == enable) {
			return "1";
		}
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public Set<ItemTag> getSubItemTags() {
		return subItemTags;
	}

	public void setSubItemTags(Set<ItemTag> subItemTags) {
		this.subItemTags = subItemTags;
	}

}
