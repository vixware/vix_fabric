package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.StrUtils;

/**
 * 微信模板消息接口配置
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpTemplateMessageConfig
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */
public class WxpTemplateMessageConfig extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 接口协议 */
	private String syncScheme;
	/** 接口域名 */
	private String syncHost;
	/** 接口路径 */
	private String syncPath;
	/**
	 * 微信模板消息接口url类型：api_set_industry,设置所属行业接口；
	 * get_industry，获取设置的行业信息接口；api_add_template，获得模板ID接口;
	 * get_all_private_template，获取模板列表接口；del_private_template，删除模板接口；
	 * send，发送模板消息接口；
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
		if (StrUtils.objectIsNotNull(uriType) && "api_set_industry".equals(uriType)) {
			name = "设置所属行业接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "get_industry".equals(uriType)) {
			name = "获取设置的行业信息接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "api_add_template".equals(uriType)) {
			name = "获得模板ID接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "get_all_private_template".equals(uriType)) {
			name = "获取模板列表接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "del_private_template".equals(uriType)) {
			name = "删除模板接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "send".equals(uriType)) {
			name = "发送模板消息接口";
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