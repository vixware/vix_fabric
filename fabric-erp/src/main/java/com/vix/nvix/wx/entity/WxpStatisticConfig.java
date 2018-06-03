package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.StrUtils;

/**
 * 微信数据统计接口配置
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpStatisticConfig
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */
public class WxpStatisticConfig extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 接口协议 */
	private String syncScheme;
	/** 接口域名 */
	private String syncHost;
	/** 接口路径 */
	private String syncPath;
	/**
	 * 接口url类型：getusersummary,获取用户增减数据接口；
	 * getusercumulate,获取累计用户数据；getarticlesummary,获取图文群发每日数据；
	 * getarticletotal，获取图文群发总数据；
	 * getuserread，获取图文统计数据；getuserreadhour，获取图文统计分时数据；
	 * getusershare，获取图文分享转发数据；getusersharehour，获取图文分享转发分时数据；
	 * getupstreammsg，获取消息发送概况数据；getupstreammsghour，获取消息分送分时数据；
	 * getupstreammsgweek，获取消息发送周数据；getupstreammsgmonth，获取消息发送月数据；
	 * getupstreammsgdist，获取消息发送分布数据；getupstreammsgdistweek，获取消息发送分布周数据；
	 * getupstreammsgdistmonth，获取消息发送分布月数据；getinterfacesummary，获取接口分析数据；
	 * getinterfacesummaryhour，获取接口分析分时数据
	 */
	private String uriType;
	/** 时间跨度 */
	private Integer maxTimeRange;

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
		if (StrUtils.objectIsNotNull(uriType) && "getusersummary".equals(uriType)) {
			name = "获取用户增减数据接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "getusercumulate".equals(uriType)) {
			name = "获取累计用户数据接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "getarticlesummary".equals(uriType)) {
			name = "获取图文群发每日数据";
		} else if (StrUtils.objectIsNotNull(uriType) && "getarticletotal".equals(uriType)) {
			name = "获取图文群发总数据接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "getuserread".equals(uriType)) {
			name = "获取图文统计数据接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "getuserreadhour".equals(uriType)) {
			name = "获取图文统计分时数据接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "getusershare".equals(uriType)) {
			name = "获取图文分享转发数据接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "getusersharehour".equals(uriType)) {
			name = "获取图文分享转发分时数据接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "getupstreammsgweek".equals(uriType)) {
			name = "获取消息发送周数据接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "getupstreammsgmonth".equals(uriType)) {
			name = "获取消息发送月数据接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "getupstreammsgdist".equals(uriType)) {
			name = "获取消息发送分布数据接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "getupstreammsgdistweek".equals(uriType)) {
			name = "获取消息发送分布周数据接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "getupstreammsgdistmonth".equals(uriType)) {
			name = "获取消息发送分布月数据接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "getinterfacesummary".equals(uriType)) {
			name = "获取接口分析数据接口";
		} else if (StrUtils.objectIsNotNull(uriType) && "getinterfacesummaryhour".equals(uriType)) {
			name = "获取接口分析分时数据接口";
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

	public Integer getMaxTimeRange() {
		return maxTimeRange;
	}

	public void setMaxTimeRange(Integer maxTimeRange) {
		this.maxTimeRange = maxTimeRange;
	}
}
