package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.StrUtils;

/**
 * 微信客服接口配置
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpKefuConfig
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */
public class WxpKefuConfig extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 接口协议 */
	private String syncScheme;
	/** 接口域名 */
	private String syncHost;
	/** 接口路径 */
	private String syncPath;
	/**
	 * 微信客服接口url类型：add,添加客服账号接口； update，设置客服信息接口；uploadheadimg，上传客服头像接口;
	 * del，删除客服账号接口；getkflist,获取客服基本信息接口； getonlinekflistm,获取在线客服接待信息
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
		if (StrUtils.objectIsNotNull(uriType) && "add".equals(uriType)) {
			name = "添加客服账号接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "update".equals(uriType)) {
			name = "设置客服信息接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "uploadheadimg".equals(uriType)) {
			name = "上传客服头像接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "del".equals(uriType)) {
			name = "删除客服账号接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "getkflist".equals(uriType)) {
			name = "获取客服基本信息接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "getonlinekflistm".equals(uriType)) {
			name = "获取在线客服接待信息接口";
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
