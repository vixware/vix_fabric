package com.vix.nvix.wx.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpReplyRule
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */

public class WxpReplyRule extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String title; // 规则标题
	private Integer replyAll; // 是否回复全部
	private Integer isActive; // 0停用，1使用，-1草稿
	private String menuId; // 菜单id，如果rule是针对菜单
	private Date createTime; // 创建时间
	/** 类型：null,默认；other,微交互 */
	private String type;
	private Set<WxpReplyMatch> matches = new HashSet<WxpReplyMatch>(); // 匹配关键字

	private Set<WxpReply> replys = new HashSet<WxpReply>(); // 回复内容

	public String getFirstReplyType() {
		if (replys.size() > 0) {
			WxpReply reply = replys.iterator().next();
			String type = "";
			if (WxpReply.reply_msg_type_news.equals(reply.getMsgType())) {
				type = "图文包消息";
			} else if (WxpReply.reply_msg_type_article.equals(reply.getMsgType())) {
				type = "文章消息";
			} else if (WxpReply.reply_msg_type_text.equals(reply.getMsgType())) {
				type = "文本消息";
			} else if (WxpReply.reply_msg_type_image.equals(reply.getMsgType())) {
				type = "图片消息";
			} else if (WxpReply.reply_msg_type_voice.equals(reply.getMsgType())) {
				type = "语音消息";
			} else if (WxpReply.reply_msg_type_video.equals(reply.getMsgType())) {
				type = "视频消息";
			} else if (WxpReply.reply_msg_type_music.equals(reply.getMsgType())) {
				type = "音乐播放";
			}
			return type;
		} else {
			return null;
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<WxpReply> getReplys() {
		return replys;
	}

	public void setReplys(Set<WxpReply> replys) {
		this.replys = replys;
	}

	public Set<WxpReplyMatch> getMatches() {
		return matches;
	}

	public void setMatches(Set<WxpReplyMatch> matches) {
		this.matches = matches;
	}

	public Integer getReplyAll() {
		return replyAll;
	}

	public void setReplyAll(Integer replyAll) {
		this.replyAll = replyAll;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
