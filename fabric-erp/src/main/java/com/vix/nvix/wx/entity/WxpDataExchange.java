package com.vix.nvix.wx.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.nvix.wx.identifier.WxpDataExchangeIdentifier;

public class WxpDataExchange extends BaseEntity implements WxpDataExchangeIdentifier {
	private static final long serialVersionUID = 1L;
	String title; // 名称
	String namespace; // 配置标识符
	String appType; // 类型:client,server
	String serverProtocol; // 接口协议，http,https
	String serverUrl; // api接口，type为client时为对方api接口；type为server时，为本地接收api，为系统内定值
	String dataType = "json"; // json/text/xml/custom
	String appSecret; // 验证加密串
	Integer useToken; // 是否用token
	String token; // token串，部分接口需要
	Date tokenExpireTime; // token过期时间

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public Integer getUseToken() {
		return useToken;
	}

	public void setUseToken(Integer useToken) {
		this.useToken = useToken;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTokenExpireTime() {
		return tokenExpireTime;
	}

	public void setTokenExpireTime(Date tokenExpireTime) {
		this.tokenExpireTime = tokenExpireTime;
	}

	public String getServerProtocol() {
		return serverProtocol;
	}

	public void setServerProtocol(String serverProtocol) {
		this.serverProtocol = serverProtocol;
	}
}