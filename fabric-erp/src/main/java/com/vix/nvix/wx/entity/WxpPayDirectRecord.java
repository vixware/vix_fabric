package com.vix.nvix.wx.entity;

import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.nvix.wx.util.PadPayUtil;

public class WxpPayDirectRecord extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int pay_status_scaned = 0;
	public static final int pay_status_paid = 1;

	String tradeNo; // 交易单号,同时也是wxp_trade_record表的主键
	String openId; // 用户id
	Integer isSubscribe; // 是否订阅
	Date scanTime; // 扫码回调时间,即创建时间
	Date expireTime; // 过期时间
	Date payTime; // 付款时间
	Integer payStatus; // 支付状态，0新建，未支付；1支付成功
	Integer retCode; // 回调返回值，0可以支付；其他是异常
	String retMsg; // 回调返回错误信息
	String retPackage; // 回调返回商品信息包

	String title;
	Double totalFee; // 总费用=商品费用+物流费用
	Double transportFee; // 物流费用
	Double productFee; // 商品费用

	String payDirectId;
	WxpPayDirect payDirect; // pay_direct_id

	WxpUser user; // 扫码的微信用户

	WxpTradeRecord tradeRecord;

	// 以下不存库
	String appId;
	String timestamp;
	String noncestr;
	String appSign;
	String signMethod;

	String productIdStr;
	Integer isScanPay = 0;

	public WxpPayDirectRecord() {
	}

	public WxpPayDirectRecord(String xmlStr, IBaseHibernateService service) {
		if (xmlStr == null || "".equals(xmlStr.trim()))
			return;

		Document xmlData = null;
		try {
			xmlData = DocumentHelper.parseText(xmlStr);
		} catch (Exception exml) {
			exml.printStackTrace();
			return;
		}
		if (null == xmlData) {
			return;
		}

		try {
			this.loadXmlDataToAttribute(xmlData, service);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadXmlDataToAttribute(Document xmlData, IBaseHibernateService service) throws Exception {
		// 通用属性
		Element root = xmlData.getRootElement();
		this.openId = root.elementTextTrim("OpenId");
		this.appId = root.elementTextTrim("AppId");
		this.isSubscribe = Integer.parseInt(root.elementTextTrim("IsSubscribe"));
		this.productIdStr = root.elementTextTrim("ProductId");
		// 判断是否是员工手持设备扫码支付,如果是，productid是payMark，如果不是则为payDirectId
		if (PadPayUtil.isScanPay(this.productIdStr)) {
			this.isScanPay = 1;
			String[] dpIdAndOptUserId = PadPayUtil.splitPayMarkToPayDirectIdAndOptUserId(this.productIdStr);
			this.payDirectId = dpIdAndOptUserId[0];
		} else {
			this.payDirectId = productIdStr;
		}

		timestamp = root.elementTextTrim("TimeStamp");
		noncestr = root.elementTextTrim("NonceStr");
		appSign = root.elementTextTrim("AppSignature");
		signMethod = root.elementTextTrim("SignMethod");

		this.tradeNo = PadPayUtil.genTradeNo(PadPayUtil.trade_no_type_native_scan_pay, this.payDirectId, this.getTenantId(), service);
	}

	public boolean isExpired() {
		if (this.payStatus == pay_status_paid)
			return false;
		else if (this.expireTime == null)
			return false;
		else {
			Date now = new Date();
			if (this.expireTime.getTime() < now.getTime())
				return true;
		}
		return false;
	}

	public void setProductInfo(WxpPayDirect payDirect) {
		this.title = payDirect.title;

		this.productFee = payDirect.getProductFee();
		this.transportFee = payDirect.getTransportFee();
		if (productFee != null) {
			this.totalFee = this.productFee;
			if (this.transportFee != null)
				this.totalFee += this.transportFee;
		}
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(Integer isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getRetCode() {
		return retCode;
	}

	public void setRetCode(Integer retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public String getRetPackage() {
		return retPackage;
	}

	public void setRetPackage(String retPackage) {
		this.retPackage = retPackage;
	}

	public WxpPayDirect getPayDirect() {
		return payDirect;
	}

	public void setPayDirect(WxpPayDirect payDirect) {
		this.payDirect = payDirect;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	@Override
	public String getAppId() {
		return appId;
	}

	@Override
	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public Double getTransportFee() {
		return transportFee;
	}

	public void setTransportFee(Double transportFee) {
		this.transportFee = transportFee;
	}

	public Double getProductFee() {
		return productFee;
	}

	public void setProductFee(Double productFee) {
		this.productFee = productFee;
	}

	public String getProductIdStr() {
		return productIdStr;
	}

	public void setProductIdStr(String productIdStr) {
		this.productIdStr = productIdStr;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public String getAppSign() {
		return appSign;
	}

	public void setAppSign(String appSign) {
		this.appSign = appSign;
	}

	public String getSignMethod() {
		return signMethod;
	}

	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Date getScanTime() {
		return scanTime;
	}

	public void setScanTime(Date scanTime) {
		this.scanTime = scanTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public WxpUser getUser() {
		return user;
	}

	public void setUser(WxpUser user) {
		this.user = user;
	}

	public WxpTradeRecord getTradeRecord() {
		return tradeRecord;
	}

	public void setTradeRecord(WxpTradeRecord tradeRecord) {
		this.tradeRecord = tradeRecord;
	}

	public Integer getIsScanPay() {
		return isScanPay;
	}

	public void setIsScanPay(Integer isScanPay) {
		this.isScanPay = isScanPay;
	}

	public String getPayDirectId() {
		return payDirectId;
	}

	public void setPayDirectId(String payDirectId) {
		this.payDirectId = payDirectId;
	}

}