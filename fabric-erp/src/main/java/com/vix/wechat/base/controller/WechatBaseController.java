package com.vix.wechat.base.controller;

import java.net.URI;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.vix.common.security.util.SecurityUtil;
import com.vix.core.utils.ContextUtil;
import com.vix.core.utils.JSonUtils;
import com.vix.wechat.base.entity.WxpQyWeixinSite;
import com.vix.wechat.base.service.IWechatBaseService;
import com.vix.wechat.base.util.WxQyUtil;

/**
 * 
 * com.vix.wechat.department.controller.WechatDepartmentController
 *
 * @author bjitzhang
 *
 * @date 2015年12月29日
 */
@Controller("wechatBaseController")
@Scope("prototype")
public class WechatBaseController {

	@Autowired
	private IWechatBaseService wechatBaseService;

	private static String api_qiye_server = "qyapi.weixin.qq.com";

	String oauth_openid_load_scope_snsapi_base = "snsapi_base";
	String oauth_openid_load_scope_snsapi_userinfo = "snsapi_userinfo";
	String session_oauthed_wx_user_openid = "session_oauthed_wx_user_openid";
	String session_oauthed_response_data = "session_oauthed_response_data";
	String session_oauthed_qy_user_id = "session_oauthed_qy_user_id";

	/**
	 * 取得HttpSession的简化函数.
	 */
	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * 取得HttpRequest的简化函数.
	 */
	public static HttpServletRequest getRequest() {
		return ContextUtil.getRequest();
	}

	/**
	 * 取得HttpResponse的简化函数.
	 */
	public static HttpServletResponse getResponse() {
		return ContextUtil.getResponse4Struts();
	}

	/**
	 * 取得Request Parameter的简化方法.
	 */
	public static String getRequestParameter(String name) {
		return getRequest().getParameter(name);
	}

	/**
	 * 根据access_token 获取成员信息
	 * 
	 * @throws Exception
	 */
	public String checkWxQyVisitUser() throws Exception {
		String userId = null;

		//首先判断session是否有openId，已测试，微信切换账号访问会清掉session
		userId = (String) getSession().getAttribute(session_oauthed_qy_user_id);
		if (StringUtils.isNotEmpty(userId)) {
			return userId;
		}

		//准备去微信服务器进行oauth授权，获取openId
		System.out.println("准备去oauth qiye授权");

		WxpQyWeixinSite site = wechatBaseService.findEntityByAttribute(WxpQyWeixinSite.class, "tenantId", SecurityUtil.getCurrentUserTenantId());

		//检查是否为已从微信验证后跳转回来
		String state = getRequestParameter("state");
		if (StringUtils.isNotEmpty(state)) {
			System.out.println("state");
			//已经从微信服务器跳转返回
			String code = getRequestParameter("code");
			if (StringUtils.isNotEmpty(code)) {
				System.out.println("code");
				try {
					if (site.needReloadQiyeAccessToken()) {
						WxQyUtil.reloadAccessToken(site.getQiyeCorpId(), site.getQiyeSecret());
					}

					//https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE&agentid=AGENTID
					URI uri = new URIBuilder().setScheme("https").setHost(api_qiye_server).setPath("/cgi-bin/user/getuserinfo").setParameter("access_token", site.getQiyeAccessToken()).setParameter("code", code).setParameter("agentid", site.getQiyeAgentId()).build();

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
									Map<?, ?> infoMap = JSonUtils.readValue(accessTokenResponseStr, Map.class);
									//return (String)infoMap.get("DeviceId");
									userId = (String) infoMap.get("UserId");

									getSession().setAttribute(session_oauthed_qy_user_id, userId);

									return userId;
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
			}
		}

		try {
			//从腾讯服务器获取oauth信息
			genOAuthUrlWeixinQiye(site, oauth_openid_load_scope_snsapi_base, "http://vixnt-hb.ngrok.cc/vixnt-erp/wechat/identityVerificationAction!goList.action");
			//页面已经跳转，直接返回
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userId;
	}

	/** 从腾讯服务器获取oauth信息 */
	private void genOAuthUrlWeixinQiye(WxpQyWeixinSite site, String scope, String myCurrentUrl) {

		String jumpToWxUrl = this.genJumpToOAuthUrl(site.getQiyeCorpId(), myCurrentUrl, oauth_openid_load_scope_snsapi_base);
		System.out.println("go qiye oauth url: " + jumpToWxUrl);
		try {
			getResponse().sendRedirect(jumpToWxUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 跳转到微信用户openId获取申请链接
	 * 
	 * @param appId
	 * @param backUrl
	 * @param scope
	 * @return
	 */
	private String genJumpToOAuthUrl(String appId, String backUrl, String scope) {
		//需要到微信后台的开发者接口那里，修改oAuth2.0的授权回调域名
		//https://open.weixin.qq.com/connect/oauth2/authorize?
		//appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
		try {
			StringBuilder sb = new StringBuilder("https://open.weixin.qq.com/connect/oauth2/authorize?appid=").append(appId);
			sb.append("&redirect_uri=").append(URLEncoder.encode(backUrl, "UTF-8"));
			sb.append("&response_type=code");
			sb.append("&scope=");
			if (StringUtils.isEmpty(scope))
				scope = oauth_openid_load_scope_snsapi_base;
			sb.append(scope);
			sb.append("&state=").append(appId);
			sb.append("#wechat_redirect");
			//			System.out.println(sb.toString());
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void saveWxpWeixinSite(WxpQyWeixinSite wxpWeixinSite) throws Exception {
		Map<?, ?> dataMap = WxQyUtil.reloadAccessToken(wxpWeixinSite.getQiyeCorpId(), wxpWeixinSite.getQiyeSecret());
		String newAccessToken = (String) dataMap.get("access_token");
		Integer expireSec = (Integer) dataMap.get("expires_in");

		long expireTime = System.currentTimeMillis() + 1000L * expireSec;
		wxpWeixinSite.setQiyeAccessToken(newAccessToken);
		wxpWeixinSite.setQiyeTokenExpireTime(new Date(expireTime));
		if (wxpWeixinSite != null) {
			wxpWeixinSite.setQiyeAccessToken(newAccessToken);
			wechatBaseService.merge(wxpWeixinSite);
		}
	}
}
