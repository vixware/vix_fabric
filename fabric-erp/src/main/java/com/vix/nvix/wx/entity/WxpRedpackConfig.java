package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.StrUtils;

/**
 * 微信红包接口配置
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpRedpackConfig
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */
public class WxpRedpackConfig extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 接口协议 */
	private String syncScheme;
	/** 接口域名 */
	private String syncHost;
	/** 接口路径 */
	private String syncPath;
	/**
	 * 微信客服接口url类型：sendredpack,普通红包发放接口；
	 * gethbinfo，红包查询接口；sendgroupredpack，裂变红包发放接口;
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
		if (StrUtils.objectIsNotNull(uriType) && "sendredpack".equals(uriType)) {
			name = "普通红包发放接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "gethbinfo".equals(uriType)) {
			name = "红包查询接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "sendgroupredpack".equals(uriType)) {
			name = "裂变红包发放接口";
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
