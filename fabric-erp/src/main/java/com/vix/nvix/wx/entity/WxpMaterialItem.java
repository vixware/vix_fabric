package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpMaterialItem
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */
public class WxpMaterialItem extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 图文消息的标题 */
	private String title;
	/** 图文消息的封面图片素材id */
	private String thumbMediaId;
	private String imgPath;
	/** 作者 */
	private String author;
	/** 图文消息的摘要 */
	private String digest;
	/** 是否显示封面，0为false，即不显示，1为true，即显示 */
	private String showCoverPic;
	/** 图文消息的具体内容 */
	private String content;
	/** 图文页的URL */
	private String wxurl;
	/** 图文消息的原文地址 */
	private String contentSourceUrl;
	private WxpMaterial wxpMaterial;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getShowCoverPic() {
		return showCoverPic;
	}

	public void setShowCoverPic(String showCoverPic) {
		this.showCoverPic = showCoverPic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWxurl() {
		return wxurl;
	}

	public void setWxurl(String wxurl) {
		this.wxurl = wxurl;
	}

	public String getContentSourceUrl() {
		return contentSourceUrl;
	}

	public void setContentSourceUrl(String contentSourceUrl) {
		this.contentSourceUrl = contentSourceUrl;
	}

	public WxpMaterial getWxpMaterial() {
		return wxpMaterial;
	}

	public void setWxpMaterial(WxpMaterial wxpMaterial) {
		this.wxpMaterial = wxpMaterial;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}
