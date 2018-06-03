package com.vix.nvix.wx.entity;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 微信公众号
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpWeixinSite
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */
public class WxpWeixinSite extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String appId; // 公众号id
	private String secret;
	private String accessToken;
	private Date expiresInTime;
	private String sToken;
	private String sEncodingAESKey;

	// 微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
	private String mchID;
	/** 支付秘钥 */
	private String privateKey;
	/** 应用服务器ip */
	private String spbillCreateIp;
	// 受理模式下给子商户分配的子商户号
	private String subMchID = "";
	// HTTPS证书的本地路径C:/apiclient_cert/apiclient_cert.p12
	private String certLocalPath;
	// HTTPS证书密码，默认密码等于商户号MCHID
	private String certPassword;
	private ChannelDistributor channelDistributor;

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

	@Override
	public String getAppId() {
		return appId;
	}

	@Override
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

	public String getMchID() {
		return mchID;
	}

	public void setMchID(String mchID) {
		this.mchID = mchID;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}

	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}

	public String getSubMchID() {
		return subMchID;
	}

	public void setSubMchID(String subMchID) {
		this.subMchID = subMchID;
	}

	public String getCertLocalPath() {
		return certLocalPath;
	}

	public void setCertLocalPath(String certLocalPath) {
		this.certLocalPath = certLocalPath;
	}

	public String getCertPassword() {
		return certPassword;
	}

	public void setCertPassword(String certPassword) {
		this.certPassword = certPassword;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

}