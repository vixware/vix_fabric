package com.vix.nvix.wx.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpFileUploadFolder
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */

public class WxpFileUploadFolder extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title; // 标题
	private Date createTime; // 创建时间

	private WxpFileUploadFolder parentFileUploadFolder;
	private Set<WxpFileUploadFolder> subFolder = new HashSet<WxpFileUploadFolder>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<WxpFileUploadFolder> getSubFolder() {
		return subFolder;
	}

	public void setSubFolder(Set<WxpFileUploadFolder> subFolder) {
		this.subFolder = subFolder;
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public WxpFileUploadFolder getParentFileUploadFolder() {
		return parentFileUploadFolder;
	}

	public void setParentFileUploadFolder(WxpFileUploadFolder parentFileUploadFolder) {
		this.parentFileUploadFolder = parentFileUploadFolder;
	}
}
