/**
 * 
 */
package com.vix.oa.picture.entity;

import com.vix.common.share.entity.BaseBOEntity;

/**
 * 
 * @ClassName: PictureBrowse
 * @Description:  图片浏览 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2015-7-16 上午10:42:02
 */
public class PictureBrowse extends BaseBOEntity {
	private static final long serialVersionUID = 1L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 关键字*/
	private String keyword;
	/** 文件名称*/
	private String fileName;
	/**类型*/
	private String type;
	/**图片树分类*/
	private PictureBrowseCategory pictureBrowseCategory;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	public PictureBrowseCategory getPictureBrowseCategory() {
		return pictureBrowseCategory;
	}

	public void setPictureBrowseCategory(PictureBrowseCategory pictureBrowseCategory) {
		this.pictureBrowseCategory = pictureBrowseCategory;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}
}
