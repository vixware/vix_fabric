package com.vix.common.share.entity;

import com.vix.core.utils.StrUtils;
import com.vix.nvix.common.message.constant.MessageCompanyConstant;

/**
 * 短信配置
 * 
 * @类全名 com.vix.common.share.entity.EcMessageConfig
 *
 * @author zhanghaibing
 *
 * @date 2017年1月13日
 */
public class EcMessageConfig extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 接口类型 MessageCompanyConstant */
	private String msgType;
	/** 发送接口url */
	private String msgUrl;
	/** 余额查询url */
	private String msgBalanceUrl;
	/** 短信接口账号 */
	private String msgAccount;
	/** 短信接口密码 */
	private String msgPassword;
	/** 短信内容前缀 */
	private String msgContentPrefix;
	/** 短信内容后缀 */
	private String msgContentSuffix;
	/** 是否启用：1：启用 0：禁用 */
	private String enable;

	private String apikey;

	public EcMessageConfig() {
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getMsgTypeStr() {
		if (StrUtils.objectIsNotNull(msgType)) {
			return MessageCompanyConstant.getMessageCompany().get(msgType);
		}
		return msgType;
	}

	public String getMsgUrl() {
		return msgUrl;
	}

	public void setMsgUrl(String msgUrl) {
		this.msgUrl = msgUrl;
	}

	public String getMsgBalanceUrl() {
		return msgBalanceUrl;
	}

	public void setMsgBalanceUrl(String msgBalanceUrl) {
		this.msgBalanceUrl = msgBalanceUrl;
	}

	public String getMsgAccount() {
		return msgAccount;
	}

	public void setMsgAccount(String msgAccount) {
		this.msgAccount = msgAccount;
	}

	public String getMsgPassword() {
		return msgPassword;
	}

	public void setMsgPassword(String msgPassword) {
		this.msgPassword = msgPassword;
	}

	public String getMsgContentPrefix() {
		if (null == msgContentPrefix) {
			return "";
		}
		return msgContentPrefix;
	}

	public void setMsgContentPrefix(String msgContentPrefix) {
		this.msgContentPrefix = msgContentPrefix;
	}

	public String getMsgContentSuffix() {
		if (null == msgContentSuffix) {
			return "";
		}
		return msgContentSuffix;
	}

	public void setMsgContentSuffix(String msgContentSuffix) {
		this.msgContentSuffix = msgContentSuffix;
	}

	public String getEnable() {
		if (null == enable) {
			return "";
		}
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

}
