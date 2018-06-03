package com.vix.mdm.item.entity;

import com.vix.common.share.entity.BaseEntity;

public class ItemAttachFile extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	/** 文件名称 */
	private String fileName;
	/** 类型 */
	private String type;
	/** 路径 */
	private String path;
	/** 物料 */
	private Item item;
	
	public ItemAttachFile(){}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
