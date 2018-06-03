package com.vix.beiken.order.util;

import com.vix.common.share.entity.BaseEntity;

/** 微支付 */
public class WxPayConfig extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** appid */
	private String appId;
	/** 商户号 */
	private String mchId;
	/** 订单名称 */
	private String body;
	/** 设备信息 */
	private String deviceInfo;
	/** 交易类型 */
	private String tradeType;
	/** 支付链接 */
	private String notifyUrl;
	/** 店铺服务器ip */
	private String spbillCreateIp;
	/** 支付秘钥 */
	private String privateKey;

	public WxPayConfig(){}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}

	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
}
