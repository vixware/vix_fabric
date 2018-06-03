package com.vix.beiken.base.util;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.share.entity.BaseEntity;

/**
 * 微信公众号
 * 
 * @类全名 com.vix.diandoc.base.entity.WxpWeixinSite
 *
 * @author zhanghaibing
 *
 * @date 2017年3月28日
 */
public class WxpWeixinSite extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String appId; // 公众号id
	private String secret;
	private String accessToken;
	private Date expiresInTime;
	private String sToken;
	private String sEncodingAESKey;

	public boolean hasQiyeAccount() {
		return StringUtils.isNotEmpty(this.appId);
	}

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

	public String getsToken() {
		return sToken;
	}

	public void setsToken(String sToken) {
		this.sToken = sToken;
	}

	public String getsEncodingAESKey() {
		return sEncodingAESKey;
	}

	public void setsEncodingAESKey(String sEncodingAESKey) {
		this.sEncodingAESKey = sEncodingAESKey;
	}

	public Date getExpiresInTime() {
		return expiresInTime;
	}

	public void setExpiresInTime(Date expiresInTime) {
		this.expiresInTime = expiresInTime;
	}
}