package com.vix.nvix.wx.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpMediaFile
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */

public class WxpMediaFile extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String media_type_image = WxpUserMessage.MSG_TYPE_IMAGE;
	public static final String media_type_voice = WxpUserMessage.MSG_TYPE_VOICE;
	public static final String media_type_thumb = "thumb";

	String mediaId; // 腾讯服务器上的媒体id，上传3天后过期
	String mediaType; // 类型,image/voice/video/thumb,实际没有video，腾讯禁止下载视频，video只有thumb类型的预览图
	Integer isPost; // 0:从微信服务器get（接收户消息）；1：post到微信服务器（发给用户），视频不支持下载
	String mediaFile; // 保存相对路径及文件名
	String mediaFolder; // 保存文件夹路径
	Date createTime; // 创建/下载时间
	Date expireTime; // 过期时间（上传后media_id有3天有效期）
	Integer autoUpload = 0; // 是否自动从新post

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public Integer getIsPost() {
		return isPost;
	}

	public void setIsPost(Integer isPost) {
		this.isPost = isPost;
	}

	public String getMediaFile() {
		return mediaFile;
	}

	public void setMediaFile(String mediaFile) {
		this.mediaFile = mediaFile;
	}

	public String getMediaFolder() {
		return mediaFolder;
	}

	public void setMediaFolder(String mediaFolder) {
		this.mediaFolder = mediaFolder;
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Integer getAutoUpload() {
		return autoUpload;
	}

	public void setAutoUpload(Integer autoUpload) {
		this.autoUpload = autoUpload;
	}

}
