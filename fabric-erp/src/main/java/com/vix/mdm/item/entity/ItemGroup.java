package com.vix.mdm.item.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/***
 * 物料组
 */
public class ItemGroup extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** 名称 */
	private String name;
	/** 备注 */
	private String memo;
	/** 物料 */
	private Set<Item> items = new HashSet<Item>();
	
	public ItemGroup(){}

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

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}
}
