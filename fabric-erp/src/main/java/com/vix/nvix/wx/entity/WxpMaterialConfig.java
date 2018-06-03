package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.StrUtils;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpMaterialConfig
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */
public class WxpMaterialConfig extends BaseEntity {
	// https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN
	private static final long serialVersionUID = 1L;
	/** 接口协议 */
	private String syncScheme;
	/** 接口域名 */
	private String syncHost;
	/** 接口路径 */
	private String syncPath;
	/**
	 * 微信素材管理接口url类型：upload，新增临时素材；get，获取临时素材；add_news，新增永久素材；
	 * get_material，获取永久素材；del_material，删除永久素材；update_news，修改永久图文素材；
	 * get_materialcount，获取素材总数；batchget_material，获取素材列表;
	 * add_material,新增其他类型永久素材;uploadimg,上传图文消息内的图片获取URL
	 */
	private String uriType;

	@Override
	public String getName() {
		String name = "";
		if (StrUtils.objectIsNotNull(uriType) && "upload".equals(uriType)) {
			name = "新增临时素材";
		} else if (StrUtils.objectIsNotNull(uriType) && "get".equals(uriType)) {
			name = "获取临时素材";
		} else if (StrUtils.objectIsNotNull(uriType) && "add_news".equals(uriType)) {
			name = "新增永久图文素材";
		} else if (StrUtils.objectIsNotNull(uriType) && "get_material".equals(uriType)) {
			name = "获取永久图文素材";
		} else if (StrUtils.objectIsNotNull(uriType) && "del_material".equals(uriType)) {
			name = "删除永久素材";
		} else if (StrUtils.objectIsNotNull(uriType) && "update_news".equals(uriType)) {
			name = "修改永久图文素材";
		} else if (StrUtils.objectIsNotNull(uriType) && "get_materialcount".equals(uriType)) {
			name = "获取素材总数";
		} else if (StrUtils.objectIsNotNull(uriType) && "batchget_material".equals(uriType)) {
			name = "获取素材列表";
		} else if (StrUtils.objectIsNotNull(uriType) && "add_material".equals(uriType)) {
			name = "新增其他类型永久素材";
		} else if (StrUtils.objectIsNotNull(uriType) && "uploadimg".equals(uriType)) {
			name = "上传图文消息内的图片获取URL";
		}
		return name;
	}

	public String getUrl() {
		String url = "";
		if (StrUtils.objectIsNotNull(syncScheme) && StrUtils.objectIsNotNull(syncHost) && StrUtils.objectIsNotNull(syncPath)) {
			url = syncScheme + "://" + syncHost + syncPath;
		}
		return url;
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
