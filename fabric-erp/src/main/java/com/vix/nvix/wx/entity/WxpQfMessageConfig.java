package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.StrUtils;

/**
 * 微信群发消息接口配置
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpQfMessageConfig
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */
public class WxpQfMessageConfig extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 接口协议 */
	private String syncScheme;
	/** 接口域名 */
	private String syncHost;
	/** 接口路径 */
	private String syncPath;
	/**
	 * 微信群发消息接口url类型：sendall,根据分组进行群发接口； send，根据OpenID列表群发接口；delete，删除群发接口;
	 * preview，预览接口接口；get,查询群发消息发送状态接口；
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
		if (StrUtils.objectIsNotNull(uriType) && "sendall".equals(uriType)) {
			name = "根据分组进行群发接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "send".equals(uriType)) {
			name = "根据OpenID列表群发接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "delete".equals(uriType)) {
			name = "删除群发接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "preview".equals(uriType)) {
			name = "预览接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "get".equals(uriType)) {
			name = "查询群发消息发送状态接口";
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
