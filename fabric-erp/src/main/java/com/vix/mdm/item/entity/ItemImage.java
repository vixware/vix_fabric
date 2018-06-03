package com.vix.mdm.item.entity;

import com.vix.common.share.entity.BaseEntity;

/** 物料图片 */
public class ItemImage extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** 物料 */
	private Item item;
	/** 路径*/
	private String imgPath;
	/** 存储名称 */
	private String persisName;
	/** 名称 */
	private String name;
	/** 扩展名 */
	private String expandName;
	
	public ItemImage(){}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getPersisName() {
		return persisName;
	}

	public void setPersisName(String persisName) {
		this.persisName = persisName;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getExpandName() {
		return expandName;
	}

	public void setExpandName(String expandName) {
		this.expandName = expandName;
	}
}
