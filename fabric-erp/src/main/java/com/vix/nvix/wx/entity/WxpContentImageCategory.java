package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpContentImageCategory
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */

public class WxpContentImageCategory extends BaseEntity {
	private static final long serialVersionUID = 1L;
	String title;// 标题
	String parentId;
	String folderMark;// 唯一标识

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getFolderMark() {
		return folderMark;
	}

	public void setFolderMark(String folderMark) {
		this.folderMark = folderMark;
	}

}
