package com.vix.nvix.wx.entity;

import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.StrUtils;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpMaterial
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */
public class WxpMaterial extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 素材名称 */
	private String title;
	/** 图文消息的id */
	private String mediaId;
	/** 是否显示封面，0为false，即不显示，1为true，即显示 */
	private String showCoverPic;
	/** 图文数量 */
	private Integer articleGroupLimit;
	private Set<WxpMaterialItem> wxpMaterialItems;

	public String getShowCoverPicName() {
		String showCoverPicName = "";
		if (StrUtils.objectIsNotNull(showCoverPic) && "1".equals(showCoverPic)) {
			showCoverPicName = "是";
		} else if (StrUtils.objectIsNotNull(showCoverPic) && "0".equals(showCoverPic)) {
			showCoverPicName = "否";
		}
		return showCoverPicName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getShowCoverPic() {
		return showCoverPic;
	}

	public void setShowCoverPic(String showCoverPic) {
		this.showCoverPic = showCoverPic;
	}

	public Set<WxpMaterialItem> getWxpMaterialItems() {
		return wxpMaterialItems;
	}

	public void setWxpMaterialItems(Set<WxpMaterialItem> wxpMaterialItems) {
		this.wxpMaterialItems = wxpMaterialItems;
	}

	public Integer getArticleGroupLimit() {
		return articleGroupLimit;
	}

	public void setArticleGroupLimit(Integer articleGroupLimit) {
		this.articleGroupLimit = articleGroupLimit;
	}
}
