package com.vix.mdm.item.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 商品包
 * 
 * @类全名 com.vix.mdm.item.entity.ItemPackage
 * 
 * @author zhanghaibing
 *
 * @date 2017年12月5日
 */
public class ItemPackage extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 商品 */
	private Set<Item> subItems = new HashSet<Item>();

	public ItemPackage() {
	}

	public Set<Item> getSubItems() {
		return subItems;
	}

	public void setSubItems(Set<Item> subItems) {
		this.subItems = subItems;
	}

}
