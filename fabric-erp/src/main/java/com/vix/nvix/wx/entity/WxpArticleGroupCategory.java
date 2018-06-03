package com.vix.nvix.wx.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpArticleGroupCategory
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */

public class WxpArticleGroupCategory extends BaseEntity {
	private static final long serialVersionUID = 1L;
	String title; // 标题
	String parentId; // 父id
	Date createTime; // 创建时间

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

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
