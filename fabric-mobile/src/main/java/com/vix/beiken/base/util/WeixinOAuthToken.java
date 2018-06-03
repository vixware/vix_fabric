package com.vix.beiken.base.util;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.share.entity.BaseEntity;

/**
 * "access_token":"ACCESS_TOKEN",
 * 
 * "expires_in":7200,
 * 
 * "refresh_token":"REFRESH_TOKEN",
 * 
 * "openid":"OPENID",
 * 
 * "scope":"SCOPE"
 * 
 * @类全名 com.vix.diandoc.base.entity.WeixinOAuthToken
 *
 * @author zhanghaibing
 *
 * @date 2017年4月16日
 */
public class WeixinOAuthToken extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String appId; // 公众号id
	private String secret;
	private String accessToken;// access_token
	private String refreshToken;// refresh_token
	private String openId;// openid
	private String scope;// scope
	private Date expiresInTime;// expires_in

	public boolean needReloadQiyeAccessToken() {
		if (StringUtils.isEmpty(this.accessToken)) {
			return true;
		} else {
			long timeMill = System.currentTimeMillis();
			if (this.expiresInTime == null || timeMill >= this.expiresInTime.getTime()) {
				return true;
			}
		}
		return false;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Date getExpiresInTime() {
		return expiresInTime;
	}

	public void setExpiresInTime(Date expiresInTime) {
		this.expiresInTime = expiresInTime;
	}
}