package com.vix.mdm.item.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 产品标签
 * 
 * @类全名 com.vix.mdm.item.entity.ItemTag
 * 
 * @author zhanghaibing
 *
 * @date 2017年12月5日
 */
public class ItemTag extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 标签组 */
	private ItemTagGroup itemTagGroup;
	/** 商品 */
	private Set<Item> subItems = new HashSet<Item>();

	public ItemTag() {
	}

	public ItemTagGroup getItemTagGroup() {
		return itemTagGroup;
	}

	public void setItemTagGroup(ItemTagGroup itemTagGroup) {
		this.itemTagGroup = itemTagGroup;
	}

	public Set<Item> getSubItems() {
		return subItems;
	}

	public void setSubItems(Set<Item> subItems) {
		this.subItems = subItems;
	}

}
