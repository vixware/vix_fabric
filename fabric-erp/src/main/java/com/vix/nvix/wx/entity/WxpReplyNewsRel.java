package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 关联微交互，供其他部分图文推送
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpReplyNewsRel
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */
public class WxpReplyNewsRel extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 图文消息标题 */
	private String title;
	/** 图文消息描述 */
	private String description;
	/** 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200 */
	private String imgPath;
	/** 点击图文消息跳转链接 */
	private String pageUrl;
	private WxpReplyRule wxpReplyRule;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	public WxpReplyRule getWxpReplyRule() {
		return wxpReplyRule;
	}

	public void setWxpReplyRule(WxpReplyRule wxpReplyRule) {
		this.wxpReplyRule = wxpReplyRule;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
}
