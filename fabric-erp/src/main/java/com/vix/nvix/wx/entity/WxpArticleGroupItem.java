package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpArticleGroupItem
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */

public class WxpArticleGroupItem extends BaseEntity {
	private static final long serialVersionUID = 1L;

	public static final int type_article_item = 0;
	public static final int type_folder_item = 1;

	private int type; // 0：文章；1：栏目
	private String groupId; // 分组id

	private WxpArticleFolder folder;
	private WxpArticle article;

	private Integer sortOrder;

	/**
	 * group不保存，不插入
	 */
	private WxpArticleGroup group;

	public String getItemTitle() {
		if (type == type_article_item && article != null) {
			return article.getTitle();
		} else if (type == type_folder_item && folder != null) {
			return folder.getTitle();
		}
		return null;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public WxpArticleFolder getFolder() {
		return folder;
	}

	public void setFolder(WxpArticleFolder folder) {
		this.folder = folder;
	}

	public WxpArticle getArticle() {
		return article;
	}

	public void setArticle(WxpArticle article) {
		this.article = article;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public WxpArticleGroup getGroup() {
		return group;
	}

	public void setGroup(WxpArticleGroup group) {
		this.group = group;
	}
}
