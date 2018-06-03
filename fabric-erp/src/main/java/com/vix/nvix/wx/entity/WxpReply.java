package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpReply
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */

public class WxpReply extends BaseEntity {
	private static final long serialVersionUID = 1L;
	public static String reply_mark_global = "global"; // 全局消息回复
	public static String reply_mark_subscribe = "subscribe"; // 关注消息
	public static String reply_mark_send = "send"; // 群发消息
	// 已经不再使用menu值，统一使用rule
	public static String reply_mark_menu = "rule"; // 菜单事件消息
	public static String reply_mark_rule = "rule"; // 用户消息回复规则
	public static String reply_mark_direct = "direct"; // 直接回复用户消息（客服消息）
	public static String reply_msg_type_news = "news"; // 图文包
	public static String reply_msg_type_article = "article"; // 文章单选
	public static String reply_msg_type_material = "material"; // 素材
	public static String reply_msg_type_other = "other"; //
	public static String reply_msg_type_text = "text";
	public static String reply_msg_type_image = "image";
	public static String reply_msg_type_voice = "voice";
	public static String reply_msg_type_video = "video";
	public static String reply_msg_type_music = "music";

	private String replyMark; // 用于获取全局、关注回复
	private String msgType; // news,text,image,voice,video,music
	private String mediaId; // 图片、语音、视频回复，通过上传多媒体文件，得到的id
	private String textContent; // 文字信息内容
	private String title; // 视频、音乐信息标题
	private String description; // 视频、音乐信息描述
	private String musicUrl; // 音乐链接
	private String musicHqUrl; // 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	private String thumbMediaId;// 音乐信息，缩略图的媒体id，通过上传多媒体文件，得到的id
	private WxpContentImage wxpContentImage;
	private WxpArticleGroup wxpArticleGroup;
	private WxpArticle wxpArticle;
	private WxpReplyRule wxpReplyRule;
	private WxpMaterial wxpMaterial;
	private WxpReplyNewsRel wxpReplyNewsRel;

	private String wxpArticleGroupId;
	private String wxpArticleId;
	private String wxpContentImageId;

	public boolean isGlobalReply() {
		return reply_mark_global.equals(this.getReplyMark());
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

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

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String getMusicHqUrl() {
		return musicHqUrl;
	}

	public void setMusicHqUrl(String musicHqUrl) {
		this.musicHqUrl = musicHqUrl;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	public String getReplyMark() {
		return replyMark;
	}

	public void setReplyMark(String replyMark) {
		this.replyMark = replyMark;
	}

	public WxpArticleGroup getWxpArticleGroup() {
		return wxpArticleGroup;
	}

	public void setWxpArticleGroup(WxpArticleGroup wxpArticleGroup) {
		this.wxpArticleGroup = wxpArticleGroup;
	}

	public WxpArticle getWxpArticle() {
		return wxpArticle;
	}

	public void setWxpArticle(WxpArticle wxpArticle) {
		this.wxpArticle = wxpArticle;
	}

	public WxpReplyRule getWxpReplyRule() {
		return wxpReplyRule;
	}

	public void setWxpReplyRule(WxpReplyRule wxpReplyRule) {
		this.wxpReplyRule = wxpReplyRule;
	}

	public WxpMaterial getWxpMaterial() {
		return wxpMaterial;
	}

	public void setWxpMaterial(WxpMaterial wxpMaterial) {
		this.wxpMaterial = wxpMaterial;
	}

	public WxpReplyNewsRel getWxpReplyNewsRel() {
		return wxpReplyNewsRel;
	}

	public void setWxpReplyNewsRel(WxpReplyNewsRel wxpReplyNewsRel) {
		this.wxpReplyNewsRel = wxpReplyNewsRel;
	}

	public WxpContentImage getWxpContentImage() {
		return wxpContentImage;
	}

	public void setWxpContentImage(WxpContentImage wxpContentImage) {
		this.wxpContentImage = wxpContentImage;
	}

	public String getWxpArticleGroupId() {
		return wxpArticleGroupId;
	}

	public void setWxpArticleGroupId(String wxpArticleGroupId) {
		this.wxpArticleGroupId = wxpArticleGroupId;
	}

	public String getWxpArticleId() {
		return wxpArticleId;
	}

	public void setWxpArticleId(String wxpArticleId) {
		this.wxpArticleId = wxpArticleId;
	}

	public String getWxpContentImageId() {
		return wxpContentImageId;
	}

	public void setWxpContentImageId(String wxpContentImageId) {
		this.wxpContentImageId = wxpContentImageId;
	}
}
