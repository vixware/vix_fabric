/**
 * 
 */
package com.vix.wechat;

import java.util.Map;

import com.vix.core.utils.JSonUtils;

/**
 * @类全名 com.vix.diandoc.base.wechat.OAuthTokenData
 *
 * @author zhanghaibing
 *
 * @date 2017年4月2日
 */
public class OAuthTokenData {

	public String accessToken;
	public long expireTime;
	public String refreshToken;
	public String openId;
	public String scope;

	public OAuthTokenData(String retJsonStr) {
		try {
			Map<?, ?> dataMap = JSonUtils.readValue(retJsonStr, Map.class);
			if (dataMap.get("access_token") != null) {
				this.accessToken = (String) dataMap.get("access_token");
				Integer expireSec = (Integer) dataMap.get("expires_in");
				this.expireTime = System.currentTimeMillis() + 1000L * expireSec;
				this.refreshToken = (String) dataMap.get("refresh_token");
				this.scope = (String) dataMap.get("scope");
				this.openId = (String) dataMap.get("openid");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("  --- 页面oauth获取access_token失败" + e.toString());
			System.out.println("  --- 获取返回信息：" + retJsonStr);
		}
	}

}
