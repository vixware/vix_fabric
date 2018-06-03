package com.vix.oa.picture.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @ClassName: PictureBrowseCategory
 * @Description: 图片树分类  
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-6-27 上午10:43:26
 */
public class PictureBrowseCategory extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 子分类集合 */
	private Set<PictureBrowseCategory> pictureBrowseCategorys = new HashSet<PictureBrowseCategory>();
	/**
	 * 个人文件柜
	 */
	private Set<PictureBrowse> pictureBrowse = new HashSet<PictureBrowse>();
	/** 父分类 */
	private PictureBrowseCategory pictureBrowseCategoryss;
	public Set<PictureBrowseCategory> getPictureBrowseCategorys() {
		return pictureBrowseCategorys;
	}
	public void setPictureBrowseCategorys(Set<PictureBrowseCategory> pictureBrowseCategorys) {
		this.pictureBrowseCategorys = pictureBrowseCategorys;
	}
	public Set<PictureBrowse> getPictureBrowse() {
		return pictureBrowse;
	}
	public void setPictureBrowse(Set<PictureBrowse> pictureBrowse) {
		this.pictureBrowse = pictureBrowse;
	}
	public PictureBrowseCategory getPictureBrowseCategoryss() {
		return pictureBrowseCategoryss;
	}
	public void setPictureBrowseCategoryss(PictureBrowseCategory pictureBrowseCategoryss) {
		this.pictureBrowseCategoryss = pictureBrowseCategoryss;
	}
}
