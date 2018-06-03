package com.vix.beiken.order.util;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class PadPayUtil
{
	public static final String scan_pay_mark_head = "scanpay-";

	public static final int trade_no_type_jsapi = 0;
	public static final int trade_no_type_native = 1;
	public static final int trade_no_type_native_scan_pay = 2;
	
	//5位
	public static final String trade_no_head_jsapi = "jsuc-";		// js api user create
	public static final String trade_no_head_native = "pdom-";	//pay direct other method
	public static final String trade_no_head_native_scan_pay = "pdsp-"; //pay direct scan pay
	
	@SuppressWarnings("unused")
	private static Integer dateMark;
	@SuppressWarnings("unused")
	private static Long sequenceMark = 1l;

	
	/**
	 * 是否是员工手持设备支付
	 * @param payMark
	 * @return
	 */
	public static boolean isScanPay(String anyId)
	{
		return anyId.startsWith(scan_pay_mark_head);
	}
	
	/**
	 * 生成员工手持设备扫码支付标识
	 * @param payDirectId
	 * @param loginUserId
	 * @return
	 */
	public static String genScanPayMark(Long payDirectId, Long loginUserId)
	{
		return scan_pay_mark_head + payDirectId + "-" + loginUserId;
	}
	
	public static Long[] splitPayMarkToPayDirectIdAndOptUserId(String payMark)
	{
		String temp = payMark.substring(scan_pay_mark_head.length());
		String[] idstr = temp.split("-");
		Long[] ids = new Long[2];
		
		try{
			ids[0] = Long.parseLong(idstr[0]);
			ids[1] = Long.parseLong(idstr[1]);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ids;
	}
	
	/**
	 * 引用自weixin api: RequestHandler.genPackage
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String genWxPayPackage(SortedMap<String, String> packageParams, String parternerKey)
			 throws UnsupportedEncodingException 
	{
		String sign = createSign(packageParams,parternerKey);

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
		//System.out.println("packageValue=" + packageValue);
		return packageValue;
	}

	/**
	 * 引用自weixin api: RequestHandler.UrlEncode
	 * 特殊字符处理
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
	@SuppressWarnings("rawtypes")
	public static String createSign(SortedMap<String, String> packageParams, String parternerKey) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + parternerKey);
		//System.out.println("md5 sb:" + sb);
		String sign = MD5Util.MD5Encode(sb.toString(), "GBK")
				.toUpperCase();

		return sign;

	}
	
	public static String prePayRequest(PrePayPackage prepayPack)
	{
	    //支付xml
		String postData = genProductArgs(prepayPack);
		
		//post数据
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = null;
		CloseableHttpResponse response = null;
		try
		{
			URI uri = new URI("https://api.mch.weixin.qq.com/pay/unifiedorder");
			
			httppost = new HttpPost(uri);
//			System.out.println(postData);
			StringEntity entity = new StringEntity(postData, ContentType.create("plain/text", Consts.UTF_8));
			entity.setChunked(false);
			httppost.setEntity(entity);
			response = httpclient.execute(httppost);

			HttpEntity responseEntity = response.getEntity();
		    if (responseEntity != null) {
		    	//success return: {"errcode":0,"errmsg":"ok"}
		    	try{
			        String reponseStr = EntityUtils.toString(responseEntity);

		    		String retText = new String(reponseStr.getBytes("ISO8859-1"),"UTF-8");
		    		
		    		return retText;

		    	}catch(Exception rwe){
		    		rwe.printStackTrace();
		    	}
		    }
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally
		{
			if(response!=null)
			{
				try{
					response.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	
	
	
	
	private static String genProductArgs(PrePayPackage pack) {  
        List<NameValuePair> packageParams = new LinkedList<NameValuePair>();  
        packageParams.add(new BasicNameValuePair("appid", pack.APPID));  
//        packageParams.add(new BasicNameValuePair("appkey", Constans.key));  
        packageParams.add(new BasicNameValuePair("body", pack.body));  
        packageParams.add(new BasicNameValuePair("input_charset", "UTF-8"));  
        packageParams.add(new BasicNameValuePair("mch_id", pack.mch_id));  
        packageParams.add(new BasicNameValuePair("nonce_str",  pack.nonce_str));  
        packageParams.add(new BasicNameValuePair("notify_url",  pack.notify_url));  
        if(isNotEmpty(pack.openId))
            packageParams.add(new BasicNameValuePair("openid",  pack.openId));
        packageParams.add(new BasicNameValuePair("out_trade_no",  pack.out_trade_no));  
        packageParams.add(new BasicNameValuePair("product_id",  pack.product_id)); 
        packageParams.add(new BasicNameValuePair("spbill_create_ip",  pack.spbill_create_ip));  
        packageParams.add(new BasicNameValuePair("total_fee", pack.total_fee));  
        packageParams.add(new BasicNameValuePair("trade_type",  pack.trade_type));  
        
         //调用genXml()方法获得xml格式的请求数据  
        return genXml(packageParams, pack.private_key);  
    }
	
	public static boolean isNotEmpty(String str){
		if(StringUtils.isEmpty(str) || str.equalsIgnoreCase("null")){
			return false;
		}
		return true;
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
	        if("body".equals(params.get(i).getName()))
	        {
	        	sb2.append("<![CDATA[");
	        }
	        sb2.append(params.get(i).getValue());  
	        if("body".equals(params.get(i).getName()))
	        {
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
//	        return new String(sb2.toString().getBytes(), "ISO8859-1");  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }
	    return "";  
	}  
	
	public static boolean isEmpty(String str){
		if(StringUtils.isEmpty(str) || str.equalsIgnoreCase("null")){
			return true;
		}
		return false;
	}
	
	
	public static void main(String[] args)
	{
	    PrePayPackage prepayPack = new PrePayPackage();
	    prepayPack.APPID = "wxedb07132d074f621";  
	    prepayPack.auth_code = Sha1Util.getNonceStr();  
	    prepayPack.body = "good morning";  
	    prepayPack.device_info = "WEB";  
	    prepayPack.mch_id = "1262989401";  
	    prepayPack.nonce_str = Sha1Util.getNonceStr();  
	    prepayPack.out_trade_no = "134";  
	    prepayPack.product_id = "111111";
	    prepayPack.spbill_create_ip = "127.0.0.1";  
	    //www.mrshu.com.cn/sxs/p/sxs/sxsFrontAction!index.action
	    prepayPack.notify_url = "http://www.vixware.com.cn/SnowX/ec/mobile/pay/ecMobilePaymentAction_payNotify.action";  
	    prepayPack.total_fee = "1";  
	    prepayPack.trade_type = "NATIVE";  
	    prepayPack.private_key = "bfPmvPD5EvvqbwdOlColH1WfMQdzpJFr";

	    System.out.println("response:  " + PadPayUtil.prePayRequest(prepayPack));
	    
//	    System.out.println(new Date(1432889865000L));
	}
	
	
	
}

