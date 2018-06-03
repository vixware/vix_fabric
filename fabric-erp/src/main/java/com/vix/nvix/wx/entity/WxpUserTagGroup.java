package com.vix.nvix.wx.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpUserTagGroup
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */

public class WxpUserTagGroup extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String title; // 名称
	private Integer isMutual; // 是否组内标签互斥,如果是的话，关联这个标签的时候，需要取消掉组内其他标签的关联
	private Date createTime;// 创建时间
	/** 其他平台:snowec,电商； */
	private String otherPlatform;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getIsMutual() {
		return isMutual;
	}

	public void setIsMutual(Integer isMutual) {
		this.isMutual = isMutual;
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOtherPlatform() {
		return otherPlatform;
	}

	public void setOtherPlatform(String otherPlatform) {
		this.otherPlatform = otherPlatform;
	}
}
