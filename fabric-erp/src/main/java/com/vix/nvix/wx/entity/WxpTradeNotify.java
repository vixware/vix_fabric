package com.vix.nvix.wx.entity;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.vix.common.share.entity.BaseEntity;
import com.vix.nvix.wx.util.StrUtils;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpTradeNotify
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */

public class WxpTradeNotify extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String tradeNo; // 交易单号
	String openId; // 购买者微信标识
	Integer tradeMode; // 1-即时到账
	Integer tradeState; // 支付结果：0—成功
	String partner; // partnerid,由微信统一分配的10 位正整数
	String bankType; // 银行类型，在微信中使用WX
	String bankBillno; // 银行订单号
	Integer totalFee; // 支付金额，单位为分，如果discount有值，通知的total_fee + discount
						// =请求的total_fee
	Integer feeType; // 默认值是1-人民币
	String notifyId; // 通知ID
	String transactionId; // 交易号，28 位长的数值，其中前10位为商户号，之后8
							// 位为订单产生的日期，如20090415，最后10 位是流水号。
	String attach; // 商户数据包，原样返回
	String timeEnd; // 支付完成时间yyyyMMddhhmmss
	Integer transportFee; // 物流费用，单位分，默认0
	Integer productFee; // 物品费用，单位分
	Integer discount; // 折扣价格，单位分，如果有值，通知的total_fee + discount = 请求的total_fee

	Date createTime; // 通知创建时间

	Integer isSubscribe;
	WxpUser user; // 支付的微信用户

	// for sign check
	String sign;
	String signType;
	String charset;

	// for xml data
	String appId;
	String timestamp;
	String noncestr;
	String appSign;
	String signMethod;

	WxpTradeRecord tradeRecord;

	public WxpTradeNotify() {
	}

	public WxpTradeNotify(String xmlStr) {
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
			this.loadXmlDataToAttribute(xmlData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadXmlDataToAttribute(Document xmlData) throws Exception {
		// 通用属性
		Element root = xmlData.getRootElement();
		this.openId = root.elementTextTrim("OpenId");
		this.appId = root.elementTextTrim("AppId");
		this.isSubscribe = Integer.parseInt(root.elementTextTrim("IsSubscribe"));
		this.timestamp = root.elementTextTrim("TimeStamp");
		this.noncestr = root.elementTextTrim("NonceStr");
		this.appSign = root.elementTextTrim("AppSignature");
		this.signMethod = root.elementTextTrim("SignMethod");
	}

	public void fillRequestParams(HttpServletRequest request) {
		this.tradeNo = request.getParameter("out_trade_no");
		String tradeModeStr = request.getParameter("trade_mode");
		this.tradeMode = StrUtils.isEmpty(tradeModeStr) ? 1 : Integer.parseInt(tradeModeStr);
		String tradeStateStr = request.getParameter("trade_state");
		this.tradeState = StrUtils.isEmpty(tradeStateStr) ? 0 : Integer.parseInt(tradeStateStr);
		this.partner = request.getParameter("partner");
		this.bankType = request.getParameter("bank_type");
		this.bankBillno = request.getParameter("bank_billno");
		String totalFeeStr = request.getParameter("total_fee");
		this.totalFee = StrUtils.isEmpty(totalFeeStr) ? 0 : Integer.parseInt(totalFeeStr);
		String feeTypeStr = request.getParameter("fee_type");
		this.feeType = StrUtils.isEmpty(feeTypeStr) ? 0 : Integer.parseInt(feeTypeStr);
		this.notifyId = request.getParameter("notify_id");
		this.transactionId = request.getParameter("transaction_id");
		this.attach = request.getParameter("attach");
		this.timeEnd = request.getParameter("time_end");
		String transportFeeStr = request.getParameter("transport_fee");
		this.transportFee = StrUtils.isEmpty(transportFeeStr) ? 0 : Integer.parseInt(transportFeeStr);
		String productFeeStr = request.getParameter("product_fee");
		this.productFee = StrUtils.isEmpty(productFeeStr) ? 0 : Integer.parseInt(productFeeStr);
		String discountStr = request.getParameter("discount");
		this.discount = StrUtils.isEmpty(discountStr) ? 0 : Integer.parseInt(discountStr);

		this.sign = request.getParameter("sign");
		this.signType = request.getParameter("sign_type");
		if (StrUtils.isEmpty(this.signType))
			this.signType = "MD5";
		this.charset = request.getParameter("input_charset");
		if (StrUtils.isEmpty(this.charset))
			this.charset = "GBK";
	}

	public String timeEndStr() {
		// yyyyMMddhhmmss
		if (this.timeEnd == null || this.timeEnd.length() != 14)
			return "";
		else
			return this.timeEnd.substring(0, 4) + "-" + this.timeEnd.substring(4, 6) + "-" + this.timeEnd.substring(6, 8) + " " + this.timeEnd.substring(8, 10) + ":" + this.timeEnd.substring(10, 12) + ":" + this.timeEnd.substring(12, 14);
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getTradeMode() {
		return tradeMode;
	}

	public void setTradeMode(Integer tradeMode) {
		this.tradeMode = tradeMode;
	}

	public Integer getTradeState() {
		return tradeState;
	}

	public void setTradeState(Integer tradeState) {
		this.tradeState = tradeState;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getBankBillno() {
		return bankBillno;
	}

	public void setBankBillno(String bankBillno) {
		this.bankBillno = bankBillno;
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

	public Integer getFeeType() {
		return feeType;
	}

	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}

	public String getNotifyId() {
		return notifyId;
	}

	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Integer getTransportFee() {
		return transportFee;
	}

	public void setTransportFee(Integer transportFee) {
		this.transportFee = transportFee;
	}

	public Integer getProductFee() {
		return productFee;
	}

	public void setProductFee(Integer productFee) {
		this.productFee = productFee;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	@Override
	public String getAppId() {
		return appId;
	}

	@Override
	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Integer getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(Integer isSubscribe) {
		this.isSubscribe = isSubscribe;
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

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

}
