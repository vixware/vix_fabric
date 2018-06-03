package com.vix.nvix.wx.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpContentImage
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */

public class WxpContentImage extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String content_image_type = "media_content";// 满意
	String image; // 图片路径
	String title; // 图片注释
	String mediaId;
	Date uploadTime; // 图片上传时间
	Date expireTime; // 图片过期时间
	String imageType;
	String iamgeUrl;

	WxpContentImageCategory category; // 图片所属分类

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public WxpContentImageCategory getCategory() {
		return category;
	}

	public void setCategory(WxpContentImageCategory category) {
		this.category = category;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public static String getContentImageType() {
		return content_image_type;
	}

	public String getIamgeUrl() {
		return iamgeUrl;
	}

	public void setIamgeUrl(String iamgeUrl) {
		this.iamgeUrl = iamgeUrl;
	}

}
