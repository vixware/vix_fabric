package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpReplyMatch
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */
public class WxpReplyMatch extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String keyword; // 匹配关键词
	private int fullMatch; // 是否完全匹配
	private Integer isActive;
	private WxpReplyRule replyRule; // wxp_rule表id

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getFullMatch() {
		return fullMatch;
	}

	public void setFullMatch(int fullMatch) {
		this.fullMatch = fullMatch;
	}

	public WxpReplyRule getReplyRule() {
		return replyRule;
	}

	public void setReplyRule(WxpReplyRule replyRule) {
		this.replyRule = replyRule;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
}
