package com.vix.core.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.UriUtils;

/**
 * 参数的相关处理
 * @author shadow
 *
 */
public class URLParamsUtil {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		/**
		 if(isDecodeUrl){
				try {
					url = UriUtils.encodeHttpUrl(url, "utf-8");
					System.out.println(url);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		 */
		//http://www.163.com?a=1&b=王
		//String url = "http://www.163.com?a=1&b=%E7%8E%8B";
		//String url = "?a=1&b=%E7%8E%8B";
		//String url = "http://www.163.com?a=1&b=王&c=3&d=李";
		String url = "http://localhost:8080/vix/common/vixAction!goLogin.action?a=1";
		System.out.println(UriUtils.encodeHttpUrl(url, "utf-8"));
		String aValue = getUrlParamMap(true, url, "d");
		System.out.println(aValue);
	}
	
	/**
     * Create a map from String to String that represents the contents of the query
     * portion of a URL. For each x=y, x is the key and y is the value.
     * @param s the query part of the URI.
     * @return the map.
     */
    public static Map<String, String> parseQueryString(String s) {
        Map<String, String> ht = new HashMap<String, String>();
        StringTokenizer st = new StringTokenizer(s, "&");
        while (st.hasMoreTokens()) {
            String pair = st.nextToken();
            int pos = pair.indexOf('=');
            if (pos == -1) {
                ht.put(pair.toLowerCase(), "");
            } else {
                ht.put(pair.substring(0, pos).toLowerCase(),
                       pair.substring(pos + 1));
            }
        }
        return ht;
    }
    
    /**
     * Decodes using URLDecoder - use when queries or form post values are decoded
     * @param value value to decode
     * @param enc encoding
     */
    public static String urlDecode(String value, String enc) {
        try {
            value = URLDecoder.decode(value, enc);
        } catch (UnsupportedEncodingException e) {
        	e.printStackTrace();
        }
        return value;
    }
    
    public static String urlDecode(String value) {
        return urlDecode(value, "UTF-8");
    }
    
    
	/**
	 *  根据url和参数key 取得参数值
	 * @param isDecodeUrl 是否对返回的参数值解码
	 * @param url
	 * @param paramName
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String getUrlParamMap(boolean isDecodeUrl,String url,String paramName) {
		if(StrUtils.isNotEmpty(url) && StrUtils.isNotEmpty(paramName)){
			//截取url的？后的内容 
			url = StringUtils.substringAfter(url, "?");
			Map<String,String> paramMap = parseQueryString(url);
			//System.out.println(paramMap);
			if(paramMap.containsKey(paramName)){
				String paramValue = paramMap.get(paramName);
				if(isDecodeUrl){
					paramValue = urlDecode(paramValue);
				}
				return paramValue;
			}
		}
		return null;
	}
	
	/**
	 * 根据url得到tenantId值
	 * @param url
	 * @return
	 */
	public static String getTenantIdByURL(String url) {
		String tenantId = getUrlParamMap(false, url, "tenantId");
		return tenantId;
	}
}
