package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.StrUtils;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpUserTagConfig
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */
public class WxpUserTagConfig extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 接口协议 */
	private String syncScheme;
	/** 接口域名 */
	private String syncHost;
	/** 接口路径 */
	private String syncPath;
	/**
	 * 微信用户分组接口url类型： 创建标签： create 获取公众号已创建的标签： get 获取标签下粉丝列表：getuser
	 * 获取用户身上的标签列表： getidlist 编辑标签： update 批量为用户打标签： batchtagging 批量为用户取消标签：
	 * batchuntagging 删除标签： delete
	 */
	private String uriType;

	public String getUrl() {
		String url = "";
		if (StrUtils.objectIsNotNull(syncScheme) && StrUtils.objectIsNotNull(syncHost) && StrUtils.objectIsNotNull(syncPath)) {
			url = syncScheme + "://" + syncHost + syncPath;
		}
		return url;
	}

	@Override
	public String getName() {
		String name = "";
		if (StrUtils.objectIsNotNull(uriType)) {
			if ("create".equals(uriType)) {
				name = "创建标签";
			} else if ("get".equals(uriType)) {
				name = "获取公众号已创建的标签";
			} else if ("getuser".equals(uriType)) {
				name = "获取标签下粉丝列表";
			} else if ("update".equals(uriType)) {
				name = "编辑标签";
			} else if ("batchtagging".equals(uriType)) {
				name = "批量为用户打标签";
			} else if ("batchuntagging".equals(uriType)) {
				name = "批量为用户取消标签";
			} else if ("delete".equals(uriType)) {
				name = "删除标签";
			} else if ("getidlist".equals(uriType)) {
				name = "获取用户身上的标签列表";
			}
		}
		return name;
	}

	public String getSyncScheme() {
		return syncScheme;
	}

	public void setSyncScheme(String syncScheme) {
		this.syncScheme = syncScheme;
	}

	public String getSyncHost() {
		return syncHost;
	}

	public void setSyncHost(String syncHost) {
		this.syncHost = syncHost;
	}

	public String getUriType() {
		return uriType;
	}

	public void setUriType(String uriType) {
		this.uriType = uriType;
	}

	public String getSyncPath() {
		return syncPath;
	}

	public void setSyncPath(String syncPath) {
		this.syncPath = syncPath;
	}

}
