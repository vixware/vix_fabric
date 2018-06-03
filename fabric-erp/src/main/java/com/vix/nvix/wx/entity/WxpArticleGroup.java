package com.vix.nvix.wx.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.nvix.wx.util.StrUtils;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpArticleGroup
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */
public class WxpArticleGroup extends BaseEntity {
	private static final long serialVersionUID = 1L;

	public static int default_article_limit = 10;
	public static int default_is_active_temp = -1;
	public static int default_is_active_yes = 1;

	public static int type_article_group = 0;
	public static int type_random_group = 1;
	public static int type_latest_group = 2;

	private String title; // 分组名称
	private String image; // 封面
	private int type; // 0：选文章；1：随机；2：最新；最新和随机可以设定栏目或全部
	private Integer articleLimit; // 数量限制，默认10，应小于10
	private Integer isActive; // 0停用，1使用，-1草稿
	private Date createTime; // 创建时间
	// private User createUser; //创建人

	private Set<WxpArticleGroupItem> items = new HashSet<WxpArticleGroupItem>();
	// 2014.12.10
	private WxpArticleGroupCategory groupCategory;

	public String getTitle() {
		if (StrUtils.isEmpty(this.title))
			return "未命名";
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Integer getArticleLimit() {
		// if(this.type == WxpArticleGroup.type_article_group){
		// if(this.items!=null)
		// return this.items.size();
		// }
		return articleLimit;
	}

	public void setArticleLimit(Integer articleLimit) {
		this.articleLimit = articleLimit;
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Set<WxpArticleGroupItem> getItems() {
		return items;
	}

	public void setItems(Set<WxpArticleGroupItem> items) {
		this.items = items;
	}

	public WxpArticleGroupCategory getGroupCategory() {
		return groupCategory;
	}

	public void setGroupCategory(WxpArticleGroupCategory groupCategory) {
		this.groupCategory = groupCategory;
	}

}
