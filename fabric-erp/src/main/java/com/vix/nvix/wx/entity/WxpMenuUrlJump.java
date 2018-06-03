package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpMenuUrlJump
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */

public class WxpMenuUrlJump extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 菜单跳转链接 */
	private String jumpUrl;
	/** 菜单跳转链接类型：games,游戏；snowec,微商；other,其他； */
	private String urlType;
	/** 菜单跳转链接编码 */
	private String urlCode;
	/** 菜单跳转链接名称 */
	private String urlName;
	/** 开发与否：open,close */
	private String isOpen;

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

	public String getUrlType() {
		return urlType;
	}

	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}

	public String getUrlCode() {
		return urlCode;
	}

	public void setUrlCode(String urlCode) {
		this.urlCode = urlCode;
	}

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}
}