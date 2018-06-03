package com.vix.nvix.wx.entity;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpArticle
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */
public class WxpArticle extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String shopId;
	private String title;
	private String author;
	private String source; // 原文链接
	private String uuid;
	private String summary; // 摘要
	private String content;
	/**
	 * 
	 * 发送被动图文消息写的是： 大图360*200，小图200*200 发送客服图文消息写的是： 大图640*320，小图80*80
	 */
	private String image; // wx大图，360*200
	private String imageSmall; // wx小图，200*200
	private String thumbMediaId; // 群发图片预览id
	private Date thumbUploadTime; // 预览上传时间
	private Date thumbExpireTime; // 预览过期时间
	private Date createTime;
	private int isDisable;
	private int sortOrder;
	private Date vaildStart; // 有效期起始
	private Date vaildEnd; // 有效期结束
	private WxpArticleFolder folder;
	// private User createUser;
	private WxpArticleEx extData;

	public boolean isThumbImageExpire() {
		if (StringUtils.isEmpty(thumbMediaId))
			return true;

		if (thumbExpireTime == null)
			return false;

		if (thumbExpireTime != null && thumbExpireTime.after(new Date()))
			return false;

		return true;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getUuid() {
		return uuid;
	}

	@Override
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImageSmall() {
		return imageSmall == null ? image : imageSmall;
	}

	public void setImageSmall(String imageSmall) {
		this.imageSmall = imageSmall;
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public WxpArticleFolder getFolder() {
		return folder;
	}

	public void setFolder(WxpArticleFolder folder) {
		this.folder = folder;
	}

	/*
	 * public User getCreateUser() { return createUser; } public void
	 * setCreateUser(User createUser) { this.createUser = createUser; }
	 */
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public WxpArticleEx getExtData() {
		return extData;
	}

	public void setExtData(WxpArticleEx extData) {
		this.extData = extData;
	}

	public int getIsDisable() {
		return isDisable;
	}

	public void setIsDisable(int isDisable) {
		this.isDisable = isDisable;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Date getVaildStart() {
		return vaildStart;
	}

	public void setVaildStart(Date vaildStart) {
		this.vaildStart = vaildStart;
	}

	public Date getVaildEnd() {
		return vaildEnd;
	}

	public void setVaildEnd(Date vaildEnd) {
		this.vaildEnd = vaildEnd;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	public Date getThumbUploadTime() {
		return thumbUploadTime;
	}

	public void setThumbUploadTime(Date thumbUploadTime) {
		this.thumbUploadTime = thumbUploadTime;
	}

	public Date getThumbExpireTime() {
		return thumbExpireTime;
	}

	public void setThumbExpireTime(Date thumbExpireTime) {
		this.thumbExpireTime = thumbExpireTime;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
}
