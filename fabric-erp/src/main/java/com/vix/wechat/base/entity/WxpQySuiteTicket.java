package com.vix.wechat.base.entity;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.share.entity.BaseEntity;

/**
 * 推送suite_ticket协议
 * 
 * @类全名 com.vix.wechat.base.entity.WxpQySuiteTicket
 *
 * @author zhanghaibing
 *
 * @date 2017年10月20日
 */
public class WxpQySuiteTicket extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String suiteId;
	private String suiteTicket; // suite_ticket协议
	private String suiteSecret;
	private String preAuthCode;
	private Date preAuthCodeExpireTime;
	private String suiteAccessToken;
	private Date suiteAccessTokenExpireTime;

	public boolean needReloadPreAuthCode() {
		if (StringUtils.isEmpty(this.preAuthCode)) {
			return true;
		} else {
			long timeMill = System.currentTimeMillis();
			if (this.preAuthCodeExpireTime == null || timeMill >= this.preAuthCodeExpireTime.getTime()) {
				return true;
			}
		}
		return false;
	}

	public boolean needReloadSuiteAccessToken() {
		if (StringUtils.isEmpty(this.suiteAccessToken)) {
			return true;
		} else {
			long timeMill = System.currentTimeMillis();
			if (this.suiteAccessTokenExpireTime == null || timeMill >= this.suiteAccessTokenExpireTime.getTime()) {
				return true;
			}
		}
		return false;
	}

	public String getSuiteId() {
		return suiteId;
	}

	public void setSuiteId(String suiteId) {
		this.suiteId = suiteId;
	}

	public String getPreAuthCode() {
		return preAuthCode;
	}

	public void setPreAuthCode(String preAuthCode) {
		this.preAuthCode = preAuthCode;
	}

	public Date getPreAuthCodeExpireTime() {
		return preAuthCodeExpireTime;
	}

	public void setPreAuthCodeExpireTime(Date preAuthCodeExpireTime) {
		this.preAuthCodeExpireTime = preAuthCodeExpireTime;
	}

	public String getSuiteAccessToken() {
		return suiteAccessToken;
	}

	public void setSuiteAccessToken(String suiteAccessToken) {
		this.suiteAccessToken = suiteAccessToken;
	}

	public Date getSuiteAccessTokenExpireTime() {
		return suiteAccessTokenExpireTime;
	}

	public void setSuiteAccessTokenExpireTime(Date suiteAccessTokenExpireTime) {
		this.suiteAccessTokenExpireTime = suiteAccessTokenExpireTime;
	}

	public String getSuiteSecret() {
		return suiteSecret;
	}

	public void setSuiteSecret(String suiteSecret) {
		this.suiteSecret = suiteSecret;
	}

	/**
	 * @return the suiteTicket
	 */
	public String getSuiteTicket() {
		return suiteTicket;
	}

	/**
	 * @param suiteTicket
	 *            the suiteTicket to set
	 */
	public void setSuiteTicket(String suiteTicket) {
		this.suiteTicket = suiteTicket;
	}

}