package com.vix.front.base.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.vix.common.base.action.VixSecAction;
import com.vix.core.utils.JSonUtils;
import com.vix.core.utils.kit.PropKit;
import com.vix.nvix.common.base.service.IVixntBaseService;
import com.vix.nvix.wx.entity.WeixinOAuthToken;
import com.vix.nvix.wx.entity.WxpWeixinSite;
import com.vix.nvix.wx.entity.WxpWeixinTicket;
import com.vix.nvix.wx.util.WxQyUtil;
import com.vix.wechat.OAuthTokenData;

import net.sf.json.JSONObject;

@Controller
@Scope("request")
public class FabricBaseAction extends VixSecAction {
	private static final long serialVersionUID = 1L;
	public static String open_weixin_server = "open.weixin.qq.com";
	public static String customerTag = "YI_YAO_DAI_BIAO";
	public static String channelTag = "CHANNEL";
	public static String api_weixin_server = "api.weixin.qq.com";
	public static String redirect_ip = "www.vixware.cn";
	public static String domain_url = "http://www.vixware.cn";
	public static String oauth_openid_load_scope_snsapi_userinfo = "snsapi_userinfo";
	public static String oauth_openid_load_scope_snsapi_base = "snsapi_base";
	public static String session_oauthed_wx_user_openid = "session_oauthed_wx_user_openid";// openid
	public static String session_oauthed_response_data = "session_oauthed_response_data";
	public static String api_gz_appId;// 微信公众号appId
	public static String imgpath;// 图片存放路径
	private String timestamp; // 必填，生成签名的时间戳
	private String nonceStr; // 必填，生成签名的随机串
	private String signature;// 必填，签名，见附录1
	private String qiyeCorpId;// 公众号id
	private String jsapiTicket;

	public WxpWeixinSite wxpWeixinSite;

	@Autowired
	private IVixntBaseService vixntBaseService;

	static {
		imgpath = PropKit.use("application.properties").get("system.imgpath");
		api_gz_appId = PropKit.use("application.properties").get("api_gz_appId");
	}

	public WxpWeixinSite getWxpWeixinSite() {
		try {
			WxpWeixinSite wxpWeixinSite = vixntBaseService.findEntityByAttributeNoTenantId(WxpWeixinSite.class, "appId", api_gz_appId);
			if (wxpWeixinSite != null) {
				wxpWeixinSite = saveOrUpdateWxpWeixinSite(wxpWeixinSite);
				return wxpWeixinSite;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取当前访问的user 正常需要到腾讯服务器获取openId
	 * 
	 * @param forward
	 */
	public void checkWxVisitUser(String forward) {

		// 到微信服务器进行oauth授权，获取openId
		WxpWeixinSite wxpWeixinSite = getWxpWeixinSite();
		OAuthTokenData oauthTokenData = (OAuthTokenData) getSession().getAttribute(session_oauthed_response_data);
		try {
			if (oauthTokenData == null) {
				loadOauthAccessTokenFromWeixin(wxpWeixinSite, forward);
			} else {
				System.out.println("oauthData:" + oauthTokenData.openId);
				// 使用refreshToken获取openId
				OAuthTokenData newOauthData = this.getOpenIdByRefreshToken(wxpWeixinSite, oauthTokenData);
				if (newOauthData != null && StringUtils.isNotEmpty(newOauthData.openId)) {
					// 将返回的oauth信息存入cache
					putOauthDataToCache(newOauthData);
				} else {
					// 异常可能是refreshToken超时从腾讯服务器获取oauth信息
					loadOauthAccessTokenFromWeixin(wxpWeixinSite, forward);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 将OauthData 放到缓存中
	private void putOauthDataToCache(OAuthTokenData oauthTokenData) {
		if (oauthTokenData == null || StringUtils.isEmpty(oauthTokenData.openId))
			return;
		getSession().setAttribute(session_oauthed_response_data, oauthTokenData);
		getSession().setAttribute(session_oauthed_wx_user_openid, oauthTokenData.openId);
	}

	/**
	 * 通过REFRESHTOEN 获取 OPENID
	 * 
	 * https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type
	 * =refresh_token&refresh_token=REFRESH_TOKEN
	 * 
	 * @param wxpWeixinSite
	 * @param oauthTokenData
	 * @return
	 */
	private OAuthTokenData getOpenIdByRefreshToken(WxpWeixinSite wxpWeixinSite, OAuthTokenData oauthTokenData) {
		try {
			URI uri = new URIBuilder().setScheme("https").setHost(open_weixin_server).setPath("/sns/oauth2/refresh_token").setParameter("appid", wxpWeixinSite.getAppId()).setParameter("grant_type", "refresh_token").setParameter("refresh_token", oauthTokenData.refreshToken).build();
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = null;
			CloseableHttpResponse response = null;
			try {
				httpget = new HttpGet(uri);
				response = httpclient.execute(httpget);
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					try {
						// 这里与使用code获取accessToken时返回值一样
						String accessTokenResponseStr = EntityUtils.toString(entity);
						if (StringUtils.isNotEmpty(accessTokenResponseStr)) {
							return new OAuthTokenData(accessTokenResponseStr);
						} else {
							return null;
						}
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
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return null;
	}

	/**
	 * 从腾讯加载accessToken
	 * 
	 * @param wxpWeixinSite
	 * @param scope
	 */
	private void loadOauthAccessTokenFromWeixin(WxpWeixinSite wxpWeixinSite, String forward) {
		try {
			String myCurrentUrl = genMyFullUrl(forward);
			String jumpToWxUrl = this.genJumpToOAuthUrlBySnsapiUserinfo(wxpWeixinSite.getAppId(), myCurrentUrl);
			getResponse().sendRedirect(jumpToWxUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String genMyFullUrl(String forward) {
		String domain = "";
		if (domain.indexOf("http") == -1)
			domain = domain_url;

		String thisUri = getRequest().getRequestURI();
		String contextPath = getRequest().getContextPath();
		if (thisUri != null && thisUri.startsWith(contextPath))
			thisUri = thisUri.substring(contextPath.length()).replace(forward, "wxOAuthCallback");
		StringBuilder backUrl = new StringBuilder();
		backUrl.append(domain).append(thisUri).append("?oauth_back=1");
		Map<String, String[]> paramsMap = getRequest().getParameterMap();
		for (Map.Entry<String, String[]> entry : paramsMap.entrySet()) {
			String[] values = entry.getValue();
			if (values != null && values.length > 0)
				backUrl.append("&").append(entry.getKey()).append("=").append(values[0]);
		}
		System.out.println(backUrl.toString());
		return backUrl.toString();
	}

	/**
	 * 接收腾讯服务器对用户openId获取请求的响应 并使用code从微信服务器获取accessToken
	 */
	public void wxOAuthCallback() {
		System.out.println("wxOAuthCallback:" + getIpAddr(getRequest()));
		String osName = System.getProperty("os.name");
		if (StringUtils.isNotEmpty(osName) && osName.startsWith("Windows")) {
			// return;
		}
		WxpWeixinSite wxpWeixinSite = getWxpWeixinSite();
		String state = getRequestParameter("state");
		if (StringUtils.isNotEmpty(state)) {
			// 已经从微信服务器跳转返回
			String code = getRequestParameter("code");
			if (StringUtils.isNotEmpty(code)) {
				// 授权成功
				OAuthTokenData oauthTokenData = this.getOAuthAccessTokenByCode(wxpWeixinSite, code);
				String openId = "";
				if (oauthTokenData != null) {
					openId = oauthTokenData.openId;
					System.out.println("openId:" + openId);
					// 保存OAuthTokenData
					putOauthDataToCache(oauthTokenData);
					String domain = "";
					if (domain.indexOf("http") == -1)
						domain = domain_url;

					String real_uri = getRequest().getRequestURI();
					String contextPath = getRequest().getContextPath();
					if (real_uri != null && real_uri.startsWith(contextPath))
						real_uri = real_uri.substring(contextPath.length()).replace("wxOAuthCallback", "goLogin");
					System.out.println("wxOAuthCallback: go back to: " + real_uri);
					try {
						StringBuffer backUrl = new StringBuffer();
						backUrl.append(domain_url + real_uri + "?openId=" + openId + "&app_id=" + wxpWeixinSite.getAppId());
						Map<String, String[]> paramsMap = getRequest().getParameterMap();
						for (Map.Entry<String, String[]> entry : paramsMap.entrySet()) {
							String[] values = entry.getValue();
							if (values != null && values.length > 0)
								backUrl.append("&").append(entry.getKey()).append("=").append(values[0]);
						}
						getResponse().sendRedirect(backUrl.toString());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				// 授权失败
				System.out.println("wxOAuthCallback: wx user cancled oauth");
			}
		} else {
			System.out.println("wxOAuthCallback: need wxpWeixinSite data to continue");
		}
	}

	/**
	 * 获取页面授权的accessToken，网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	 * 
	 * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=
	 * SECRET&code=CODE&grant_type=authorization_code
	 * 
	 * @param wxpWeixinSite
	 * @param code
	 * @return
	 */
	private OAuthTokenData getOAuthAccessTokenByCode(WxpWeixinSite wxpWeixinSite, String code) {
		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_server).setPath("/sns/oauth2/access_token").setParameter("appid", wxpWeixinSite.getAppId()).setParameter("secret", wxpWeixinSite.getSecret()).setParameter("code", code).setParameter("grant_type", "authorization_code").build();
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = null;
			CloseableHttpResponse response = null;
			try {
				httpget = new HttpGet(uri);
				response = httpclient.execute(httpget);
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					try {
						String accessTokenResponseStr = EntityUtils.toString(entity);
						if (StringUtils.isNotEmpty(accessTokenResponseStr)) {
							return new OAuthTokenData(accessTokenResponseStr);
						} else {
							return null;
						}
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
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return null;
	}

	/**
	 * 刷新access_token（如果需要）
	 * 
	 * @param weixinOAuthToken
	 * @return
	 */
	// https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN
	public OAuthTokenData getOAuthAccessTokenByRefreshToken(WeixinOAuthToken weixinOAuthToken) {
		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_server).setPath("/sns/oauth2/refresh_token").setParameter("appid", weixinOAuthToken.getAppId()).setParameter("grant_type", "refresh_token").setParameter("refresh_token", weixinOAuthToken.getRefreshToken()).build();
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = null;
			CloseableHttpResponse response = null;
			try {
				httpget = new HttpGet(uri);
				response = httpclient.execute(httpget);
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					try {
						String accessTokenResponseStr = EntityUtils.toString(entity);
						if (StringUtils.isNotEmpty(accessTokenResponseStr)) {
							return new OAuthTokenData(accessTokenResponseStr);
						} else {
							return null;
						}
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
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return null;
	}

	// https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf0e81c3bee622d60&redirect_uri=http%3A%2F%2Fnba.bluewebgame.com%2Foauth_response.php&response_type=
	// code&scope=snsapi_userinfo&state=STATE#wechat_redirect
	public String genJumpToOAuthUrlBySnsapiUserinfo(String appId, String backUrl) {
		try {
			StringBuilder sb = new StringBuilder("https://open.weixin.qq.com/connect/oauth2/authorize?appid=").append(appId);
			sb.append("&redirect_uri=").append(URLEncoder.encode(backUrl, "UTF-8"));
			sb.append("&response_type=code");
			sb.append("&scope=");
			sb.append(oauth_openid_load_scope_snsapi_userinfo);
			sb.append("&state=").append(appId);
			sb.append("#wechat_redirect");
			System.out.println(sb.toString());
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 拉取用户信息(需scope为 snsapi_userinfo)
	 * 
	 * @param weixinOAuthToken
	 * @param openId
	 * @return
	 */
	// https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
	public Map<?, ?> getUserInfo(WeixinOAuthToken weixinOAuthToken, String openId) {
		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_server).setPath("/sns/userinfo").setParameter("access_token", weixinOAuthToken.getAccessToken()).setParameter("openid", openId).setParameter("lang", "zh_CN").build();
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = null;
			CloseableHttpResponse response = null;
			try {
				httpget = new HttpGet(uri);
				response = httpclient.execute(httpget);
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					try {
						String userInfoStr = EntityUtils.toString(entity);
						if (StringUtils.isNotEmpty(userInfoStr)) {
							Map<?, ?> dataMap = JSonUtils.readValue(userInfoStr, Map.class);
							return dataMap;
						} else {
							return null;
						}
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
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return null;
	}

	/**
	 * 检验授权凭证（access_token）是否有效
	 * 
	 * @param weixinOAuthToken
	 * @param openId
	 * @return
	 */
	// https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID
	public Map<?, ?> auth(WeixinOAuthToken weixinOAuthToken, String openId) {
		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_server).setPath("/sns/auth").setParameter("access_token", weixinOAuthToken.getAccessToken()).setParameter("openid", openId).build();
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = null;
			CloseableHttpResponse response = null;
			try {
				httpget = new HttpGet(uri);
				response = httpclient.execute(httpget);
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					try {
						String userInfoStr = EntityUtils.toString(entity);
						if (StringUtils.isNotEmpty(userInfoStr)) {
							Map<?, ?> dataMap = JSonUtils.readValue(userInfoStr, Map.class);
							return dataMap;
						} else {
							return null;
						}
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
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return null;
	}

	// 更新AccessToken
	private WxpWeixinSite saveOrUpdateWxpWeixinSite(WxpWeixinSite wxpWeixinSite) throws Exception {
		if (wxpWeixinSite != null && wxpWeixinSite.needReloadQiyeAccessToken()) {
			if (StringUtils.isNotEmpty(wxpWeixinSite.getAppId()) && StringUtils.isNotEmpty(wxpWeixinSite.getSecret())) {
				Map<?, ?> dataMap = WxQyUtil.reloadAccessToken(wxpWeixinSite.getAppId(), wxpWeixinSite.getSecret());
				if (dataMap != null) {
					if (dataMap.containsKey("access_token")) {
						String newAccessToken = (String) dataMap.get("access_token");
						wxpWeixinSite.setAccessToken(newAccessToken);
					}
					if (dataMap.containsKey("expires_in")) {
						Integer expireSec = (Integer) dataMap.get("expires_in");
						long expireTime = System.currentTimeMillis() + 1000L * (expireSec - 600);
						wxpWeixinSite.setExpiresInTime(new Date(expireTime));
					}
					wxpWeixinSite = vixntBaseService.mergeOriginal(wxpWeixinSite);
				}
			}
		}
		return wxpWeixinSite;
	}

	public String getSign(String jsapiTicket, String nonceStr, String timestamp, String url) {
		String signature = "";
		if (StringUtils.isNotEmpty(jsapiTicket) && StringUtils.isNotEmpty(nonceStr) && StringUtils.isNotEmpty(timestamp) && StringUtils.isNotEmpty(url)) {
			try {
				String sign = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url;
				MessageDigest crypt = MessageDigest.getInstance("SHA-1");
				crypt.reset();
				crypt.update(sign.getBytes("UTF-8"));
				signature = byteToHex(crypt.digest());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return signature;
	}

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

	public WxpWeixinTicket saveOrUpdateWxpWeixinTicket(WxpWeixinTicket wxpWeixinTicket, WxpWeixinSite wxpWeixinSite) throws Exception {
		if (wxpWeixinTicket != null && StringUtils.isNotEmpty(wxpWeixinTicket.getId())) {
			if (wxpWeixinTicket.needReloadQiyeTicket()) {
				String request = WxQyUtil.getJsApiTicket(wxpWeixinSite.getAccessToken());
				if (StringUtils.isNotEmpty(request)) {
					JSONObject json = JSONObject.fromObject(request);
					if (json != null) {
						String errcode = "";
						if (json.has("errcode")) {
							errcode = json.getString("errcode");
						}
						if ("0".equals(errcode)) {
							String ticket = "";
							if (json.has("ticket")) {
								ticket = json.getString("ticket");
							}
							Integer expireSec = null;
							if (json.has("expires_in")) {
								expireSec = Integer.parseInt(json.getString("expires_in"));
							}
							wxpWeixinTicket.setQiyeCorpId(wxpWeixinSite.getAppId());
							wxpWeixinTicket.setQiyeTicket(ticket);
							if (expireSec != null) {
								long expireTime = System.currentTimeMillis() + 1000L * expireSec;
								wxpWeixinTicket.setQiyeTokenExpireTime(new Date(expireTime));
							}
							wxpWeixinTicket = vixntBaseService.mergeOriginal(wxpWeixinTicket);
						}
					}
				}
			}
		} else {
			String request = WxQyUtil.getJsApiTicket(wxpWeixinSite.getAccessToken());
			if (StringUtils.isNotEmpty(request)) {
				System.out.println(request);
				JSONObject json = JSONObject.fromObject(request);
				if (json != null) {
					String errcode = "";
					if (json.has("errcode")) {
						errcode = json.getString("errcode");
					}
					if ("0".equals(errcode)) {
						String ticket = "";
						if (json.has("ticket")) {
							ticket = json.getString("ticket");
						}
						Integer expireSec = null;
						if (json.has("expires_in")) {
							expireSec = Integer.parseInt(json.getString("expires_in"));
						}
						wxpWeixinTicket = new WxpWeixinTicket();
						wxpWeixinTicket.setQiyeCorpId(wxpWeixinSite.getAppId());
						wxpWeixinTicket.setQiyeTicket(ticket);
						if (expireSec != null) {
							long expireTime = System.currentTimeMillis() + 1000L * expireSec;
							wxpWeixinTicket.setQiyeTokenExpireTime(new Date(expireTime));
						}
						wxpWeixinTicket.setTenantId(wxpWeixinSite.getTenantId());
						wxpWeixinTicket.setCompanyInnerCode(wxpWeixinSite.getCompanyInnerCode());
						wxpWeixinTicket.setCompanyCode(wxpWeixinSite.getCompanyCode());
						wxpWeixinTicket.setCreateTime(new Date());
						wxpWeixinTicket = vixntBaseService.mergeOriginal(wxpWeixinTicket);
					}
				}
			}
		}
		return wxpWeixinTicket;
	}

	/**
	 * @throws Exception
	 */
	public void dealWechatTicket() throws Exception {
		WxpWeixinSite wxpWeixinSite = getWxpWeixinSite();
		// 注意这里参数名必须全部小写，且必须有序
		if (wxpWeixinSite != null) {
			qiyeCorpId = wxpWeixinSite.getAppId();
			WxpWeixinTicket wxpWeixinTicket = vixntBaseService.findEntityByAttributeNoTenantId(WxpWeixinTicket.class, "qiyeCorpId", qiyeCorpId);			
			wxpWeixinTicket = saveOrUpdateWxpWeixinTicket(wxpWeixinTicket, wxpWeixinSite);
			if (wxpWeixinTicket != null) {
				jsapiTicket = wxpWeixinTicket.getQiyeTicket();
				nonceStr = create_nonce_str();
				timestamp = create_timestamp();
				StringBuffer urlbuff = getRequest().getRequestURL();
				String url =urlbuff.toString();
				if( getRequest().getQueryString() != null) {
					urlbuff.append("?");
					urlbuff.append(getRequest().getQueryString());
					url = urlbuff.toString();
				}
				System.out.println(url);
				signature = getSign(jsapiTicket, nonceStr, timestamp, url);
				if (StringUtils.isNotEmpty(jsapiTicket) && StringUtils.isNotEmpty(nonceStr) && StringUtils.isNotEmpty(timestamp)) {
					try {
						getSession().setAttribute("jsapi_ticket", jsapiTicket);
						String sign = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url;
						MessageDigest crypt = MessageDigest.getInstance("SHA-1");
						crypt.reset();
						crypt.update(sign.getBytes("UTF-8"));
						signature = byteToHex(crypt.digest());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static Map<String, String> sign(String jsapi_ticket, String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";

		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
		System.out.println(string1);

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (Exception e) {
			e.printStackTrace();
		}

		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	// 获得客户端真实IP地址的方法二：
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getQiyeCorpId() {
		return qiyeCorpId;
	}

	public void setQiyeCorpId(String qiyeCorpId) {
		this.qiyeCorpId = qiyeCorpId;
	}

	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

	public void setWxpWeixinSite(WxpWeixinSite wxpWeixinSite) {
		this.wxpWeixinSite = wxpWeixinSite;
	}

}