package com.vix.nvix.wx.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpMenu
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */

public class WxpMenu extends BaseEntity {
	private static final long serialVersionUID = 1L;
	public static String menu_type_menu = "menu";
	public static String menu_type_click = "click";
	public static String menu_type_view = "view";

	private String title; // 菜单名称
	private String menuType; // click菜单点击事件,view菜单跳转访问链接,有子菜单的一级菜单时为null
	// menuKey现在是不可以更新的，创建时由系统生成uuid
	private String menuKey; // type为click时：menu的key；为view时：跳转url,有子菜单的一级菜单时为null
	private String menuUrl;
	private Integer isActive; // 是否使用
	private Integer sortOrder; // 排序
	private String menuViewType;
	private WxpMenu parent; // 父菜单

	private WxpReplyRule replyRule;
	private WxpReply wxpReply;

	private Set<WxpMenu> subMenus = new HashSet<WxpMenu>();

	private Set<WxpReplyRule> replyRules = new HashSet<WxpReplyRule>();
	/** 是否是个性化菜单：0，否；1，是； */
	private String isConditional;
	/** 个性化菜单 */
	private WxpMenuConditional wxpMenuConditional;
	private WxpMenuUrlJump wxpMenuUrlJump;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuKey() {
		return menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public WxpMenu getParent() {
		return parent;
	}

	public void setParent(WxpMenu parent) {
		this.parent = parent;
	}

	public WxpReplyRule getReplyRule() {
		return replyRule;
	}

	public void setReplyRule(WxpReplyRule replyRule) {
		this.replyRule = replyRule;
	}

	public Set<WxpReplyRule> getReplyRules() {
		return replyRules;
	}

	public void setReplyRules(Set<WxpReplyRule> replyRules) {
		this.replyRules = replyRules;
	}

	public Set<WxpMenu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(Set<WxpMenu> subMenus) {
		this.subMenus = subMenus;
	}

	public String getIsConditional() {
		return isConditional;
	}

	public void setIsConditional(String isConditional) {
		this.isConditional = isConditional;
	}

	public WxpMenuConditional getWxpMenuConditional() {
		return wxpMenuConditional;
	}

	public void setWxpMenuConditional(WxpMenuConditional wxpMenuConditional) {
		this.wxpMenuConditional = wxpMenuConditional;
	}

	public String getMenuViewType() {
		return menuViewType;
	}

	public void setMenuViewType(String menuViewType) {
		this.menuViewType = menuViewType;
	}

	public WxpMenuUrlJump getWxpMenuUrlJump() {
		return wxpMenuUrlJump;
	}

	public void setWxpMenuUrlJump(WxpMenuUrlJump wxpMenuUrlJump) {
		this.wxpMenuUrlJump = wxpMenuUrlJump;
	}

	public WxpReply getWxpReply() {
		return wxpReply;
	}

	public void setWxpReply(WxpReply wxpReply) {
		this.wxpReply = wxpReply;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
}
