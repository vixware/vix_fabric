package com.vix.wechat.wechatAuthorize.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import javax.servlet.ServletInputStream;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.vix.wechat.base.action.WechatBaseAction;
import com.vix.wechat.base.entity.WxpQySuiteTicket;

@Controller
@Scope("prototype")
public class WechatAuthorizeAction extends WechatBaseAction {

	private static final long serialVersionUID = 1L;

	/**
	 * 成员UserID
	 */
	private String userId;
	private String timestamp; // 必填，生成签名的时间戳
	private String nonceStr; // 必填，生成签名的随机串
	private String signature;// 必填，签名，见附录1
	private String qiyeCorpId;//企业号CorpID
	private String jsapiTicket;

	//
	@Override
	public String readStreamParameter(ServletInputStream in) {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return buffer.toString();
	}

	/**
	 * 获取suite_ticket
	 */
	public void getWechatAuthorize() {
		try {
			String sToken = "sKSNSzo";
			String sCorpID = "tjf16b1ff7ab8739b4";
			String sEncodingAESKey = "kqjAyTKuSfWXX9LgRdduwq4VfLdzTkWzzxk0RKgVWJ7";
			WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);

			String sVerifyMsgSig = getRequest().getParameter("msg_signature");
			String sVerifyTimeStamp = getRequest().getParameter("timestamp");
			String sVerifyNonce = getRequest().getParameter("nonce");

			String postStrXml = this.readStreamParameter(getRequest().getInputStream());
			String postStrXml123 = wxcpt.DecryptMsg(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, postStrXml);
			if (StringUtils.isNotEmpty(postStrXml123)) {
				Document xmlData = DocumentHelper.parseText(postStrXml123);
				Element root = xmlData.getRootElement();
				String suiteId = root.elementText("SuiteId");
				String infoType = root.elementText("InfoType");
				String createTime = root.elementText("TimeStamp");
				String suiteTicket = root.elementText("SuiteTicket");
				System.out.println("suiteId:--------------" + suiteId);
				System.out.println("infoType:------------" + infoType);
				System.out.println("createTime:-----------" + createTime);
				System.out.println("suiteTicket:---------------" + suiteTicket);
				if (StringUtils.isNotEmpty(suiteTicket)) {
					WxpQySuiteTicket wxpSuiteTicket = new WxpQySuiteTicket();
					wxpSuiteTicket.setCreateTime(new Date());
					wxpSuiteTicket.setSuiteTicket(suiteTicket);
					wxpSuiteTicket.setSuiteId("tjf16b1ff7ab8739b4");
					wxpSuiteTicket.setSuiteSecret("JBg2ixNGZI-HA-WT0DsOAYHfgewkJGca02jFjrSqewJ72ly-DHc7fLQKzuuKGbfd");
					wxpSuiteTicket = wechatBaseService.mergeOriginal(wxpSuiteTicket);
					renderText("success");
				}
			}
		} catch (Exception e) {
			//验证URL失败，错误原因请查看异常
			//e.printStackTrace();
		}
	}

	//授权回调地址
	public void getRedirectUri() {
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the nonceStr
	 */
	public String getNonceStr() {
		return nonceStr;
	}

	/**
	 * @param nonceStr
	 *            the nonceStr to set
	 */
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * @param signature
	 *            the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * @return the qiyeCorpId
	 */
	public String getQiyeCorpId() {
		return qiyeCorpId;
	}

	/**
	 * @param qiyeCorpId
	 *            the qiyeCorpId to set
	 */
	public void setQiyeCorpId(String qiyeCorpId) {
		this.qiyeCorpId = qiyeCorpId;
	}

	/**
	 * @return the jsapiTicket
	 */
	public String getJsapiTicket() {
		return jsapiTicket;
	}

	/**
	 * @param jsapiTicket
	 *            the jsapiTicket to set
	 */
	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

}