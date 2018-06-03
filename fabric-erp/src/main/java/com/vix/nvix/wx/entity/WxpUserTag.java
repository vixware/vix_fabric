package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpUserTag
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */

public class WxpUserTag extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 微信服务器分组id */
	private Integer tagId;
	/** 标签下粉丝数 */
	private Integer count;
	/** 名称 */
	private String title;
	/** 备注说明 */
	private String note;
	private int isLinked;
	/** 编码：vip,会员；share,销客 */
	private String code;
	/** 是否是微信标签：0，否；1，是； */
	private String isWxTag;
	private WxpUserTagGroup tagGroup;

	public int getIsLinked() {
		return isLinked;
	}

	public void setIsLinked(int isLinked) {
		this.isLinked = isLinked;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public WxpUserTagGroup getTagGroup() {
		return tagGroup;
	}

	public void setTagGroup(WxpUserTagGroup tagGroup) {
		this.tagGroup = tagGroup;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getIsWxTag() {
		return isWxTag;
	}

	public void setIsWxTag(String isWxTag) {
		this.isWxTag = isWxTag;
	}
}
