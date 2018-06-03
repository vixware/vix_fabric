package com.vix.nvix.wx.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpArticleFolder
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */
public class WxpArticleFolder extends BaseEntity {

	private static final long serialVersionUID = 1L;

	String title;
	String folderMark;

	private WxpArticleFolder parentWxpArticleFolder;
	Set<WxpArticleFolder> subFolders = new HashSet<WxpArticleFolder>();
	Set<WxpArticle> articles = new HashSet<WxpArticle>();

	WxpArticleFolderEx extData;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public WxpArticleFolder getParentWxpArticleFolder() {
		return parentWxpArticleFolder;
	}

	public void setParentWxpArticleFolder(WxpArticleFolder parentWxpArticleFolder) {
		this.parentWxpArticleFolder = parentWxpArticleFolder;
	}

	public Set<WxpArticleFolder> getSubFolders() {
		return subFolders;
	}

	public void setSubFolders(Set<WxpArticleFolder> subFolders) {
		this.subFolders = subFolders;
	}

	public Set<WxpArticle> getArticles() {
		return articles;
	}

	public void setArticles(Set<WxpArticle> articles) {
		this.articles = articles;
	}

	public WxpArticleFolderEx getExtData() {
		return extData;
	}

	public void setExtData(WxpArticleFolderEx extData) {
		this.extData = extData;
	}

	public String getFolderMark() {
		return folderMark;
	}

	public void setFolderMark(String folderMark) {
		this.folderMark = folderMark;
	}
}
