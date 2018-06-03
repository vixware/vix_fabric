package com.vix.nvix.wx.entity;

import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 个性化菜单
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpMenuConditional
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */
public class WxpMenuConditional extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String title;
	/** 是否使用:0,否；1，是； */
	private Integer isActive;
	/** 是否删除:0,否；1，是； */
	private Integer isDelete;
	/** 性别：1，男；2，女； */
	private String sex;
	/** 客户端版本：IOS(1), Android(2),Others(3) */
	private String clientPlatformType;
	/** 用户标签 */
	private WxpUserTag wxpUserTag;
	/** 菜单 */
	private Set<WxpMenu> wxpMenus;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getClientPlatformType() {
		return clientPlatformType;
	}

	public void setClientPlatformType(String clientPlatformType) {
		this.clientPlatformType = clientPlatformType;
	}

	public WxpUserTag getWxpUserTag() {
		return wxpUserTag;
	}

	public void setWxpUserTag(WxpUserTag wxpUserTag) {
		this.wxpUserTag = wxpUserTag;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Set<WxpMenu> getWxpMenus() {
		return wxpMenus;
	}

	public void setWxpMenus(Set<WxpMenu> wxpMenus) {
		this.wxpMenus = wxpMenus;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
}
