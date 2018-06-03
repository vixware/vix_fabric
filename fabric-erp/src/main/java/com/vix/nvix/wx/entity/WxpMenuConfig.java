package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.StrUtils;

/**
 * 微信自定义菜单接口配置
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpMenuConfig
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */
public class WxpMenuConfig extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 接口协议 */
	private String syncScheme;
	/** 接口域名 */
	private String syncHost;
	/** 接口路径 */
	private String syncPath;
	/**
	 * 微信自定义菜单接口url类型：create,自定义菜单创建接口； get,自定义菜单查询接口；delete,自定义菜单删除接口；
	 * addconditional，创建个性化自定义菜单接口； delconditional，删除个性化自定义菜单接口
	 * trymatch，测试个性化菜单匹配结果
	 */
	private String uriType;
	private String isDelete;

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
		if (StrUtils.objectIsNotNull(uriType) && "create".equals(uriType)) {
			name = "自定义菜单创建接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "get".equals(uriType)) {
			name = "自定义菜单查询接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "delete".equals(uriType)) {
			name = "自定义菜单删除接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "addconditional".equals(uriType)) {
			name = "创建个性化自定义菜单接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "delconditional".equals(uriType)) {
			name = "删除个性化自定义菜单接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "trymatch".equals(uriType)) {
			name = "测试个性化菜单匹配结果";
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

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
}
