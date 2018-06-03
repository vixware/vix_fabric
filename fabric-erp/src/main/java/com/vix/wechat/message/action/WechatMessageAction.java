package com.vix.wechat.message.action;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.JSonUtils;
import com.vix.oa.infoCenter.entity.MessageManagement;
import com.vix.oa.share.entity.Trends;
import com.vix.traceability.service.ITraceabilityService;
import com.vix.wechat.base.action.WechatBaseAction;
import com.vix.wechat.base.entity.WxpQyContacts;
import com.vix.wechat.base.entity.WxpQyWeixinSite;
import com.vix.wechat.base.util.WxQyUtil;

@Controller
@Scope("prototype")
public class WechatMessageAction extends WechatBaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private ITraceabilityService traceabilityService;

	/**
	 * 微信号
	 */
	private String wechatNo;
	private String id;
	private Trends trends;
	/** 新闻管理 */
	private List<MessageManagement> messageManagementList;

	@Override
	public String goList() {
		try {
			//检查是否为已从微信验证后跳转回来
			String state = getRequestParameter("state");
			String code = getRequestParameter("code");
			System.out.println("state:" + state);
			//已经从微信服务器跳转返回
			System.out.println("code:" + code);
			WxpQyWeixinSite site = traceabilityService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "tenantId", api_qiye_tenantid);
			if (site != null && StringUtils.isNotEmpty(site.getQiyeCorpId())) {
				String userId = checkWxQyVisitUser(state, code, api_qiye_tenantid);
				if (userId != null) {
					System.out.println("QIYE needGoOAuth : " + userId);
					WxpQyContacts wxpQyContacts = traceabilityService.findEntityByAttributeNoTenantId(WxpQyContacts.class, "userId", userId);
					if (wxpQyContacts != null) {
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("tenantId," + SearchCondition.EQUAL, api_qiye_tenantid);
						messageManagementList = traceabilityService.findAllByConditions(MessageManagement.class, params);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			WxQyUtil.verification(wechatNo, redirect_ip);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/**
	 * 根据access_token 获取成员信息
	 * 
	 * @throws Exception
	 */
	public String checkWxQyVisitUser(String state, String code, String tenantId) throws Exception {
		String userId = null;

		//首先判断session是否有openId，已测试，微信切换账号访问会清掉session
		userId = (String) getSession().getAttribute(session_oauthed_qy_user_id);
		if (StringUtils.isNotEmpty(userId)) {
			return userId;
		}
		//准备去微信服务器进行oauth授权，获取openId
		if (tenantId != null) {
			WxpQyWeixinSite site = traceabilityService.findEntityByAttribute(WxpQyWeixinSite.class, "tenantId", tenantId);
			if (StringUtils.isNotEmpty(state) && StringUtils.isNotEmpty(code)) {
				try {
					//https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE&agentid=AGENTID
					URI uri = new URIBuilder().setScheme("https").setHost(api_qiye_server).setPath("/cgi-bin/user/getuserinfo").setParameter("access_token", site.getQiyeAccessToken()).setParameter("code", code).build();

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
			try {
				//从腾讯服务器获取oauth信息
				if (StringUtils.isNotEmpty(state) && StringUtils.isNotEmpty(code)) {
				} else {
					genOAuthUrlWeixinQiye(site, oauth_openid_load_scope_snsapi_base, redirect_ip + "/wechat/identityVerificationAction!goList.action");
				}
				//页面已经跳转，直接返回
				return null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return userId;
	}

	public String goShowNews() {
		try {
			if (id != null && !"".equals(id)) {
				trends = traceabilityService.findEntityById(Trends.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowNews";
	}

	/**
	 * @return the messageManagementList
	 */
	public List<MessageManagement> getMessageManagementList() {
		return messageManagementList;
	}

	/**
	 * @param messageManagementList
	 *            the messageManagementList to set
	 */
	public void setMessageManagementList(List<MessageManagement> messageManagementList) {
		this.messageManagementList = messageManagementList;
	}

	/**
	 * @return the wechatNo
	 */
	public String getWechatNo() {
		return wechatNo;
	}

	/**
	 * @param wechatNo
	 *            the wechatNo to set
	 */
	public void setWechatNo(String wechatNo) {
		this.wechatNo = wechatNo;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the trends
	 */
	public Trends getTrends() {
		return trends;
	}

	/**
	 * @param trends
	 *            the trends to set
	 */
	public void setTrends(Trends trends) {
		this.trends = trends;
	}
}
