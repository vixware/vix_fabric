package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.StrUtils;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpODBConfig
 *
 * @author zhanghaibing
 *
 * @date 2017年10月18日
 */
public class WxpODBConfig extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 接口协议 :https */
	private String syncScheme;
	/** 接口域名 :saas.sapchinese.com */
	private String syncHost;
	/** 接口端口 :8000 */
	private String syncPort;
	/** 接口命名空间 :/restapi/ */
	private String syncNameSpace;
	/**
	 * odb对接url类型：ZF_CRM_VIP_BIND_WEIXIN，会员绑定；ZF_CRM_VIP_GET，会员信息查询；
	 * ZF_CRM_VIPCARD_DEPOSIT，会员充值 ；ZF_CRM_VIPCARD_UNDEPOSIT，会员充值取消；
	 * ZF_CRM_INTEGRAL_CONSUME，会员积分兑换；ZF_CRM_INTEGRAL_UNCONSUME，会员积分兑换取消；
	 * ZF_CRM_PROMOTION_CREATE，促销策略更新；YF_BP_DICT_QUERY，获取数据字典；
	 * ZF_POS_VIP_CREATE,会员注册；ZF_POS_INTEGRAL_ADD，会员积分增加；
	 * ZF_FIT_EMPLOYEE_QUERY,私教员工信息下载；ZF_FIT_PTCLASS_QUERY，私教课程信息下载；
	 * ZF_FIT_PUBCLASS_QUERY，团课课程信息下载；ZF_CRM_VIP_BIND_WEIXIN_SPORT，会员绑定；
	 * ZF_FIT_PTCLASS_CREATE，私教预约及取消；ZF_FIT_PUBCLASS_CREATE，团课预约及取消；
	 * ZF_POS_VIPTYPE_QUERY，会员卡类型查询；
	 */
	private String uriType;
	private String urlType;

	public String getUrl() {
		String url = "";
		if (StrUtils.objectIsNotNull(syncScheme) && StrUtils.objectIsNotNull(syncHost) && StrUtils.objectIsNotNull(syncPort) && StrUtils.objectIsNotNull(syncNameSpace) && StrUtils.objectIsNotNull(urlType)) {
			url = syncScheme + "://" + syncHost + ":" + syncPort + syncNameSpace + urlType;
		}
		return url;
	}

	@Override
	public String getName() {
		String name = "";
		if (StrUtils.objectIsNotNull(uriType)) {
			if ("ZF_CRM_VIP_BIND_WEIXIN".equals(uriType)) {
				name = "会员绑定";
			} else if ("ZF_CRM_VIP_GET".equals(uriType)) {
				name = "会员信息查询";
			} else if ("ZF_CRM_VIPCARD_DEPOSIT".equals(uriType)) {
				name = "会员充值 ";
			} else if ("ZF_CRM_VIPCARD_UNDEPOSIT".equals(uriType)) {
				name = "会员充值取消";
			} else if ("ZF_CRM_INTEGRAL_CONSUME".equals(uriType)) {
				name = "会员积分兑换";
			} else if ("ZF_CRM_INTEGRAL_UNCONSUME".equals(uriType)) {
				name = "会员积分兑换取消";
			} else if ("ZF_CRM_PROMOTION_CREATE".equals(uriType)) {
				name = "促销策略更新";
			} else if ("YF_BP_DICT_QUERY".equals(uriType)) {
				name = "获取数据字典";
			} else if ("ZF_POS_VIP_CREATE".equals(uriType)) {
				name = "会员注册";
			} else if ("ZF_POS_INTEGRAL_ADD".equals(uriType)) {
				name = "会员积分增加";
			} else if ("ZF_FIT_EMPLOYEE_QUERY".equals(uriType)) {
				name = "私教员工信息下载";
			} else if ("ZF_FIT_PTCLASS_QUERY".equals(uriType)) {
				name = "私教课程信息下载";
			} else if ("ZF_FIT_PUBCLASS_QUERY".equals(uriType)) {
				name = "团课课程信息下载";
			} else if ("ZF_FIT_PTCLASS_CREATE".equals(uriType)) {
				name = "私教预约及取消";
			} else if ("ZF_FIT_PUBCLASS_CREATE".equals(uriType)) {
				name = "团课预约及取消";
			} else if ("ZF_POS_VIPTYPE_QUERY".equals(uriType)) {
				name = "会员卡类型查询";
			}

		}
		return name;
	}

	public String getUriType() {
		return uriType;
	}

	public void setUriType(String uriType) {
		this.uriType = uriType;
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

	public String getSyncPort() {
		return syncPort;
	}

	public void setSyncPort(String syncPort) {
		this.syncPort = syncPort;
	}

	public String getSyncNameSpace() {
		return syncNameSpace;
	}

	public void setSyncNameSpace(String syncNameSpace) {
		this.syncNameSpace = syncNameSpace;
	}

	public String getUrlType() {
		return urlType;
	}

	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}
}
