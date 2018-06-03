package com.vix.wechat;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.security.KeyStore;
import java.util.LinkedList;
import java.util.List;

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

import com.vix.core.utils.StrUtils;
import com.vix.wechat.util.MD5Util;

public class WxRefundUtil {

	public static String preRefundRequest(WxRefundPackage refundPackage, String securityCertificate) {
		String postData = genProductArgs(refundPackage);
		CloseableHttpResponse response = null;
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			FileInputStream instream = new FileInputStream(new File(securityCertificate));
			try {
				keyStore.load(instream, refundPackage.mch_id.toCharArray());
			} finally {
				instream.close();
			}
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, refundPackage.mch_id.toCharArray()).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			HttpPost httppost = null;
			URI uri = new URI(refundPackage.request_url);
			httppost = new HttpPost(uri);
			StringEntity entity = new StringEntity(postData, ContentType.create("plain/text", Consts.UTF_8));
			entity.setChunked(false);
			httppost.setEntity(entity);
			response = httpclient.execute(httppost);
			HttpEntity responseEntity = response.getEntity();
			if (responseEntity != null) {
				try {
					String reponseStr = EntityUtils.toString(responseEntity);
					return reponseStr;
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

	public static String genRefundStatusArgs(WxRefundPackage pack) {
		List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
		packageParams.add(new BasicNameValuePair("appid", pack.appid));
		packageParams.add(new BasicNameValuePair("device_info", pack.device_info));
		packageParams.add(new BasicNameValuePair("mch_id", pack.mch_id));
		packageParams.add(new BasicNameValuePair("nonce_str", pack.nonce_str));
		packageParams.add(new BasicNameValuePair("op_user_id", pack.op_user_id));
		packageParams.add(new BasicNameValuePair("out_trade_no", pack.out_trade_no));
		packageParams.add(new BasicNameValuePair("out_refund_no", pack.out_refund_no));
		packageParams.add(new BasicNameValuePair("refund_account", pack.refund_account));
		packageParams.add(new BasicNameValuePair("refund_fee", pack.refund_fee));
		packageParams.add(new BasicNameValuePair("sign", pack.sign));
		packageParams.add(new BasicNameValuePair("sign_type", pack.sign_type));
		packageParams.add(new BasicNameValuePair("total_fee", pack.total_fee));
		packageParams.add(new BasicNameValuePair("transaction_id", pack.transaction_id));
		// 调用genXml()方法获得xml格式的请求数据
		return genXml(packageParams, pack.private_key);
	}

	private static String genProductArgs(WxRefundPackage pack) {
		List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
		packageParams.add(new BasicNameValuePair("appid", pack.appid));
		if (StrUtils.objectIsNotNull(pack.device_info)) {
			packageParams.add(new BasicNameValuePair("device_info", pack.device_info));
		}
		if (StrUtils.objectIsNotNull(pack.mch_id)) {
			packageParams.add(new BasicNameValuePair("mch_id", pack.mch_id));
		}
		if (StrUtils.objectIsNotNull(pack.nonce_str)) {
			packageParams.add(new BasicNameValuePair("max_value", pack.nonce_str));
		}
		if (StrUtils.objectIsNotNull(pack.op_user_id)) {
			packageParams.add(new BasicNameValuePair("op_user_id", pack.op_user_id));
		}
		if (StrUtils.objectIsNotNull(pack.out_trade_no)) {
			packageParams.add(new BasicNameValuePair("out_trade_no", pack.out_trade_no));
		}
		if (StrUtils.objectIsNotNull(pack.out_refund_no)) {
			packageParams.add(new BasicNameValuePair("out_refund_no", pack.out_refund_no));
		}
		if (StrUtils.objectIsNotNull(pack.refund_account)) {
			packageParams.add(new BasicNameValuePair("refund_account", pack.refund_account));
		}
		if (StrUtils.objectIsNotNull(pack.refund_fee)) {
			packageParams.add(new BasicNameValuePair("refund_fee", pack.refund_fee));
		}
		if (StrUtils.objectIsNotNull(pack.op_user_id)) {
			packageParams.add(new BasicNameValuePair("op_user_id", pack.op_user_id));
		}
		if (StrUtils.objectIsNotNull(pack.total_fee)) {
			packageParams.add(new BasicNameValuePair("total_fee", pack.total_fee));
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
			sb.append(params.get(i).getName());
			sb.append('=');
			sb.append(params.get(i).getValue());
			sb.append('&');
			sb2.append("<" + params.get(i).getName() + ">");
			sb2.append(params.get(i).getValue());
			sb2.append("</" + params.get(i).getName() + ">");
		}
		sb.append("key=");
		sb.append(privateKey);
		String packageSign = null;
		packageSign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		sb2.append("<sign><![CDATA[");
		sb2.append(packageSign);
		sb2.append("]]></sign>");
		sb2.append("</xml>");
		return sb2.toString();
	}
}
