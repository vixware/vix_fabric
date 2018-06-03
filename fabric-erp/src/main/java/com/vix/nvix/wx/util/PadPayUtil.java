package com.vix.nvix.wx.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.utils.DateUtil;
import com.vix.nvix.wx.entity.WxpPayDirectRecord;
import com.vix.nvix.wx.entity.WxpTradeNotify;
import com.vix.wechat.util.MD5Util;
import com.vix.wechat.util.Sha1Util;

public class PadPayUtil {
	public static final String scan_pay_mark_head = "scanpay-";

	public static final int trade_no_type_jsapi = 0;
	public static final int trade_no_type_native = 1;
	public static final int trade_no_type_native_scan_pay = 2;

	// 5位
	public static final String trade_no_head_jsapi = "jsuc-"; // js api user
																// create
	public static final String trade_no_head_native = "pdom-"; // pay direct
																// other method
	public static final String trade_no_head_native_scan_pay = "pdsp-"; // pay
																		// direct
																		// scan
																		// pay

	private static Integer dateMark;
	private static Long sequenceMark = 1l;

	/**
	 * 生成交易单号，32位以内
	 * 
	 * @param tradeType
	 *            jsapi，native，native-scanpay
	 * @param PayIdIfExist
	 *            关联交易id，如payDirectIdf
	 * @return
	 */
	public static String genTradeNo(int tradeType, String payId, String tenantId, IBaseHibernateService service) throws Exception {
		// 交易单号，32位以内
		String head = null;// 5位
		if (tradeType == trade_no_type_native_scan_pay)
			head = trade_no_head_native_scan_pay;
		else if (tradeType == trade_no_type_native)
			head = trade_no_head_native;
		else if (tradeType == trade_no_type_jsapi)
			head = trade_no_head_jsapi;
		else
			throw new Exception("订单类型tradeType无效");

		String uuid = StrUtils.genShortUUID();// 8位
		String date = DateUtil.format(new Date(), "yyyyMMdd");// 8位

		/*
		 * String payidStr = "";//11位 if(payId!=null && payId>0) { payidStr =
		 * String.valueOf(payId % 100000000000L);//payDirectId,确保在11位以内 } else {
		 * //生成自然序号 try{ payidStr = loadTradeSequence(); } catch(Exception e){
		 * e.printStackTrace(); } }
		 * 
		 * return head + uuid + date + payidStr;
		 */
		String payidStr = PadStrUtil.genSequenceNo(tenantId, PadStrUtil.sequence_mark_trade_no, head + uuid + date, date, 11, service);

		return payidStr;
	}

	private static synchronized String loadTradeSequence() throws Exception {
		String ret = null;

		if (dateMark == null) {
			// 第一次调用
			dateMark = Integer.parseInt(DateUtil.format(new Date(), "yyyyMMdd"));
			sequenceMark = 1L;
		} else {
			// 是否需要重置
			Integer todayMark = Integer.parseInt(DateUtil.format(new Date(), "yyyyMMdd"));
			if (todayMark > dateMark) {
				dateMark = todayMark;
				sequenceMark = 1L;
			} else {
				// 无需处理
			}
		}

		long maxSeq = 100000000000L;
		if (maxSeq <= sequenceMark) {
			return "99999999999";
		}
		long seq = maxSeq + sequenceMark;
		ret = String.valueOf(seq);
		ret = ret.substring(1);

		sequenceMark++; // 累加

		return ret;
	}

	public static boolean checkPayNotifyParamSign(WxpTradeNotify notify, String partnerKey) {
		// 文档写的不够清楚，暂时不做检查，只检查xml里的内容
		return true;
	}

	public static boolean isTradeNumberJsapiPayHead(String tradeNo) {
		return tradeNo.startsWith(trade_no_head_jsapi);
	}

	public static boolean isTradeNumberNativePayHead(String tradeNo) {
		return tradeNo.startsWith(trade_no_head_native);
	}

	public static boolean isTradeNumberScanPayHead(String tradeNo) {
		return tradeNo.startsWith(trade_no_head_native_scan_pay);
	}

	public static boolean checkPayNotifyXmlSign(WxpTradeNotify notify, String paySignKey) {
		// appid、appkey、productid、timestamp、noncestr、openid、issubscribe
		SortedMap<String, String> signParams = new TreeMap<String, String>();
		signParams.put("appid", notify.getAppId());
		signParams.put("appkey", paySignKey);
		signParams.put("issubscribe", String.valueOf(notify.getIsSubscribe()));
		signParams.put("noncestr", notify.getNoncestr());
		signParams.put("openid", notify.getOpenId());
		signParams.put("timestamp", notify.getTimestamp());

		try {
			String sign = Sha1Util.createSHA1Sign(signParams);

			return notify.getAppSign().equals(sign);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean checkNativePayCallbackSign(WxpPayDirectRecord pdRecord, String paySignKey) {
		// appid、appkey、productid、timestamp、noncestr、openid、issubscribe
		SortedMap<String, String> signParams = new TreeMap<String, String>();
		signParams.put("appid", pdRecord.getAppId());
		signParams.put("appkey", paySignKey);
		signParams.put("issubscribe", String.valueOf(pdRecord.getIsSubscribe()));
		signParams.put("noncestr", pdRecord.getNoncestr());
		signParams.put("openid", pdRecord.getOpenId());
		signParams.put("productid", pdRecord.getProductIdStr());
		signParams.put("timestamp", pdRecord.getTimestamp());

		try {
			String sign = Sha1Util.createSHA1Sign(signParams);

			return pdRecord.getAppSign().equals(sign);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 是否是员工手持设备支付
	 * 
	 * @param payMark
	 * @return
	 */
	public static boolean isScanPay(String anyId) {
		return anyId.startsWith(scan_pay_mark_head);
	}

	/**
	 * 生成员工手持设备扫码支付标识
	 * 
	 * @param payDirectId
	 * @param loginUserId
	 * @return
	 */
	public static String genScanPayMark(String payDirectId, String loginUserId) {
		return scan_pay_mark_head + payDirectId + "-" + loginUserId;
	}

	public static String[] splitPayMarkToPayDirectIdAndOptUserId(String payMark) {
		String temp = payMark.substring(scan_pay_mark_head.length());
		String[] idstr = temp.split("-");
		String[] ids = new String[2];

		try {
			ids[0] = idstr[0];
			ids[1] = idstr[1];
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ids;
	}

	/**
	 * 引用自weixin api: RequestHandler.genPackage
	 * 
	 * @return
	 */
	public static String genWxPayPackage(SortedMap<String, String> packageParams, String parternerKey) throws UnsupportedEncodingException {
		String sign = createSign(packageParams, parternerKey);

		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append(k + "=" + UrlEncode(v) + "&");
		}

		// 去掉最后一个&
		String packageValue = sb.append("sign=" + sign).toString();
		// System.out.println("packageValue=" + packageValue);
		return packageValue;
	}

	/**
	 * 引用自weixin api: RequestHandler.UrlEncode 特殊字符处理
	 * 
	 * @param src
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String UrlEncode(String src) throws UnsupportedEncodingException {
		return URLEncoder.encode(src, "GBK").replace("+", "%20");
	}

	/**
	 * 引用自weixin api: RequestHandler.createSign
	 * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 */
	public static String createSign(SortedMap<String, String> packageParams, String parternerKey) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + parternerKey);
		// System.out.println("md5 sb:" + sb);
		String sign = MD5Util.MD5Encode(sb.toString(), "GBK").toUpperCase();

		return sign;

	}

	public static String prePayRequest(PrePayPackage prepayPack) {
		// 支付xml
		String postData = genProductArgs(prepayPack);
		CloseableHttpResponse response = null;
		CloseableHttpClient httpclient = null;
		try {
			if (prepayPack.isCertFicate) {
				KeyStore keyStore = KeyStore.getInstance("PKCS12");
				FileInputStream instream = new FileInputStream(new File(prepayPack.security_certificate));
				try {
					keyStore.load(instream, prepayPack.mch_id.toCharArray());
				} finally {
					instream.close();
				}
				// Trust own CA and all self-signed certs
				SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, prepayPack.mch_id.toCharArray()).build();
				// Allow TLSv1 protocol only
				SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
				httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			} else {
				// post数据
				//
				httpclient = HttpClients.createDefault();
			}
			HttpPost httppost = null;
			URI uri = new URI(prepayPack.request_url);

			httppost = new HttpPost(uri);
			// System.out.println(postData);
			StringEntity entity = new StringEntity(postData, ContentType.create("plain/text", Consts.UTF_8));
			entity.setChunked(false);
			httppost.setEntity(entity);
			response = httpclient.execute(httppost);

			HttpEntity responseEntity = response.getEntity();
			if (responseEntity != null) {
				// success return: {"errcode":0,"errmsg":"ok"}
				try {
					String reponseStr = EntityUtils.toString(responseEntity);

					String retText = new String(reponseStr.getBytes("ISO8859-1"), "UTF-8");

					return retText;

				} catch (Exception rwe) {
					rwe.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private static String genProductArgs(PrePayPackage pack) {
		List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
		packageParams.add(new BasicNameValuePair("appid", pack.APPID));
		// packageParams.add(new BasicNameValuePair("appkey", Constans.key));
		if (StrUtils.objectIsNotNull(pack.auth_code)) {
			packageParams.add(new BasicNameValuePair("auth_code", pack.auth_code));
		}
		if (StrUtils.objectIsNotNull(pack.body)) {
			packageParams.add(new BasicNameValuePair("body", pack.body));
			packageParams.add(new BasicNameValuePair("input_charset", "UTF-8"));
		}
		packageParams.add(new BasicNameValuePair("mch_id", pack.mch_id));
		packageParams.add(new BasicNameValuePair("nonce_str", pack.nonce_str));
		if (StrUtils.objectIsNotNull(pack.notify_url)) {
			packageParams.add(new BasicNameValuePair("notify_url", pack.notify_url));
		}
		if (StrUtils.objectIsNotNull(pack.openId)) {
			packageParams.add(new BasicNameValuePair("openid", pack.openId));
		}
		if (StrUtils.objectIsNotNull(pack.out_trade_no)) {
			packageParams.add(new BasicNameValuePair("out_trade_no", pack.out_trade_no));
		}
		if (StrUtils.objectIsNotNull(pack.product_id)) {
			packageParams.add(new BasicNameValuePair("product_id", pack.product_id));
		}
		if (StrUtils.objectIsNotNull(pack.spbill_create_ip)) {
			packageParams.add(new BasicNameValuePair("spbill_create_ip", pack.spbill_create_ip));
		}
		if (StrUtils.objectIsNotNull(pack.total_fee)) {
			packageParams.add(new BasicNameValuePair("total_fee", pack.total_fee));
		}
		if (StrUtils.objectIsNotNull(pack.trade_type)) {
			packageParams.add(new BasicNameValuePair("trade_type", pack.trade_type));
		}
		if (StrUtils.objectIsNotNull(pack.transaction_id)) {
			packageParams.add(new BasicNameValuePair("transaction_id", pack.transaction_id));
		}
		// 调用genXml()方法获得xml格式的请求数据
		return genXml(packageParams, pack.private_key);
	}

	private static String genXml(List<NameValuePair> params, String privateKey) {
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		sb2.append("<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><xml>");
		for (int i = 0; i < params.size(); i++) {
			// sb是用来计算签名的
			sb.append(params.get(i).getName());
			sb.append('=');
			sb.append(params.get(i).getValue());
			sb.append('&');
			// sb2是用来做请求的xml参数
			sb2.append("<" + params.get(i).getName() + ">");
			if ("body".equals(params.get(i).getName())) {
				sb2.append("<![CDATA[");
			}
			sb2.append(params.get(i).getValue());
			if ("body".equals(params.get(i).getName())) {
				sb2.append("]]>");
			}
			sb2.append("</" + params.get(i).getName() + ">");
		}
		sb.append("key=");
		sb.append(privateKey);
		String packageSign = null;
		// 生成签名
		packageSign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		sb2.append("<sign><![CDATA[");
		sb2.append(packageSign);
		sb2.append("]]></sign>");
		sb2.append("</xml>");

		// 这一步最关键 我们把字符转为 字节后,再使用“ISO8859-1”进行编码，得到“ISO8859-1”的字符串
		try {
			return sb2.toString();
			// return new String(sb2.toString().getBytes(), "ISO8859-1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void main(String[] args) {
		PrePayPackage prepayPack = new PrePayPackage();
		prepayPack.APPID = "wx980b1cbfd9047e1b";
		prepayPack.auth_code = Sha1Util.getNonceStr();
		prepayPack.body = "good morning";
		prepayPack.device_info = "WEB";
		prepayPack.mch_id = "1230975202";
		prepayPack.nonce_str = Sha1Util.getNonceStr();
		prepayPack.out_trade_no = "134auuyuyuaa";
		prepayPack.product_id = "111998111";
		prepayPack.spbill_create_ip = "127.0.0.1";
		// www.mrshu.com.cn/sxs/p/sxs/sxsFrontAction!index.action
		prepayPack.notify_url = "http://www.mrshu.com.cn/sxs/p/sxs/sxsFrontMgrAction!payNotify.action";
		prepayPack.total_fee = "1";
		prepayPack.trade_type = "NATIVE";
		prepayPack.private_key = "sxnjlkj001sxnjlkj001sxnjlkj00100";

		System.out.println("response:  " + PadPayUtil.prePayRequest(prepayPack));

		// System.out.println(new Date(1432889865000L));
	}

}
