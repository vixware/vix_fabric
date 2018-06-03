package com.vix.mdm.item.entity;

import com.vix.common.share.entity.BaseEntity;

/** 替代物料 */
public class ItemReplace extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** 物料 */
	private Item item;
	/** 优先级 */
	private Long priority;
	/** 备注 */
	private String memo;
	/** 替换物料 */
	private Item replaceItem;
	
	public ItemReplace(){}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Item getReplaceItem() {
		return replaceItem;
	}

	public void setReplaceItem(Item replaceItem) {
		this.replaceItem = replaceItem;
	}
}
