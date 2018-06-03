package com.vix.wechat.base.entity;

import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

public class WxpActivity extends BaseEntity {

	/**
	 * 企业活动实体类
	 */
	private static final long serialVersionUID = 1L;

	// 活动标题
	private String title;
	// 状态
	private String state;
	private String content;

	//收款参数
	private String collection;
	/**
	 * 图片
	 */
	private Set<WxpQyPicture> subWxpQyPictures;
	/**
	 * 
	 */
	private String firstPictureUrl;

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	/**
	 * @return the subWxpQyPictures
	 */
	public Set<WxpQyPicture> getSubWxpQyPictures() {
		return subWxpQyPictures;
	}

	/**
	 * @param subWxpQyPictures
	 *            the subWxpQyPictures to set
	 */
	public void setSubWxpQyPictures(Set<WxpQyPicture> subWxpQyPictures) {
		this.subWxpQyPictures = subWxpQyPictures;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the firstPictureUrl
	 */
	public String getFirstPictureUrl() {
		return firstPictureUrl;
	}

	/**
	 * @param firstPictureUrl
	 *            the firstPictureUrl to set
	 */
	public void setFirstPictureUrl(String firstPictureUrl) {
		this.firstPictureUrl = firstPictureUrl;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

}
