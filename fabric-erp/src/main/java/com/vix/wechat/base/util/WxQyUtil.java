package com.vix.wechat.base.util;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

import com.vix.core.utils.JSonUtils;
import com.vix.core.utils.StrUtils;
import com.vix.hr.organization.entity.Employee;
import com.vix.wechat.base.entity.WxpQyContacts;
import com.vix.wechat.base.entity.WxpQyDepartment;

public class WxQyUtil {
	private static boolean debug = true;
	public static String api_weixin_qy_server = "qyapi.weixin.qq.com";
	/**
	 * 获取发送消息使用的access_token
	 * 
	 * @return
	 */
	/*
	 * public static String getAccessToken(WxpWeixinSite site,
	 * IBaseHibernateService loadService) { if
	 * (site.needReloadQiyeAccessToken()) { WxQyUtil.reloadAccessToken(site,
	 * loadService); } return site.getQiyeAccessToken(); }
	 */

	/**
	 * 从微信服务器获取新access_token数据
	 */
	public static Map<?, ?> reloadAccessToken(String corpId, String secret) {
		// https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=id&corpsecret=secrect

		if (StringUtils.isEmpty(corpId) || StringUtils.isEmpty(secret))
			return null;

		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/gettoken").setParameter("grant_type", "client_credential").setParameter("corpid", corpId).setParameter("corpsecret", secret).build();

			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = null;
			CloseableHttpResponse response = null;
			try {
				httpget = new HttpGet(uri);
				response = httpclient.execute(httpget);

				HttpEntity entity = response.getEntity();
				if (entity != null) {
					try {
						String accessTokenRequestStr = EntityUtils.toString(entity);
						if (debug)
							System.out.println(accessTokenRequestStr);

						return resetAccessToken(accessTokenRequestStr, corpId, secret);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// https://open.weixin.qq.com/connect/oauth2/authorize?appid=CORPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
	public static void verification(String corpId, String url) {
		if (StringUtils.isEmpty(corpId) || StringUtils.isEmpty(url))
			return;
		try {
			URI uri = new URIBuilder().setScheme("https").setHost("open.weixin.qq.com").setPath("/connect/oauth2/authorize").setParameter("response_type", "code").setParameter("appid", corpId).setParameter("state", "STATE").setParameter("redirect_uri", url).setParameter("scope", "snsapi_base").setFragment("#wechat_redirect").build();

			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = null;
			CloseableHttpResponse response = null;
			try {
				httpget = new HttpGet(uri);
				response = httpclient.execute(httpget);

				HttpEntity entity = response.getEntity();
				if (entity != null) {
					try {
						String accessTokenRequestStr = EntityUtils.toString(entity);
						if (debug)
							System.out.println(accessTokenRequestStr);

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE

	/**
	 * 更新系统全局access_token数据
	 * 
	 * @param atJsonStr
	 *            微信服务器返回的access_token json
	 */
	public static Map<?, ?> resetAccessToken(String atJsonStr, String corpId, String secret) {
		try {
			// {"access_token":"3m6UQYhWZyGbVh2H0oQ2zIxwgt-MtLQDvTXXEt1MNTGRMhrF4iH5nSuRxlv0hmSk","expires_in":7200}
			Map<?, ?> dataMap = null;
			if(StrUtils.isNotEmpty(atJsonStr)){
				dataMap = JSonUtils.readValue(atJsonStr, Map.class);
			}

			// String newAccessToken = (String) dataMap.get("access_token");
			// Integer expireSec = (Integer) dataMap.get("expires_in");

			// long expireTime = System.currentTimeMillis() + 1000L * expireSec;

			// site.setQiyeAccessToken(newAccessToken);
			// site.setQiyeTokenExpireTime(new Date(expireTime));

			return dataMap;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("  --- 获取qiye_access_token失败" + e.toString());
			System.out.println("  --- 获取返回信息：" + atJsonStr);
		}
		return null;
	}

	public static String httpPostRequest(URI uri, String postStr) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = null;
		CloseableHttpResponse response = null;
		try {
			httppost = new HttpPost(uri);
			if (StringUtils.isNotEmpty(postStr)) {
				StringEntity entity = new StringEntity(postStr, ContentType.create("plain/text", Consts.UTF_8));
				// entity.setChunked(true);
				httppost.setEntity(entity);
			}
			response = httpclient.execute(httppost);

			HttpEntity responseEntity = response.getEntity();
			if (responseEntity != null) {
				// success return: {"errcode": 0,"errmsg": "created","id": 2}
				try {
					String reponseStr = EntityUtils.toString(responseEntity);
					if (debug)
						System.out.println(reponseStr);
					// Map retMap = JSonUtils.readValue(reponseStr, Map.class);
					// String errcode = String.valueOf(retMap.get("errcode"));

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

	static String httpGetRequest(URI uri) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = null;
		CloseableHttpResponse response = null;
		try {
			httpget = new HttpGet(uri);
			response = httpclient.execute(httpget);

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				try {
					String responseStr = EntityUtils.toString(entity);

					if (debug)
						System.out.println(responseStr);

					return responseStr;

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

	/** 创建部门 */
	public static String departmentCreate(Long syncId, Integer sortOrder, String name, String txParentId, String accessToken) {
		String sendJson = "";
		Map<String, Object> dataMap = new HashMap<String, Object>();

		if (syncId != null && syncId > 0)
			dataMap.put("id", syncId);
		dataMap.put("name", name);
		dataMap.put("parentid", txParentId);
		dataMap.put("order", sortOrder);
		sendJson = JSONObject.toJSONString(dataMap);
		try {
			// https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=ACCESS_TOKEN
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/department/create").setParameter("access_token", accessToken).build();

			String request = WxQyUtil.httpPostRequest(uri, sendJson);
			System.out.println(request);
			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// https://qyapi.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN&agentid=AGENTID
	public static String create(String agentId, String accessToken, String dataMap) {
		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/menu/create").setParameter("access_token", accessToken).setParameter("agentid", agentId).build();

			String request = WxQyUtil.httpPostRequest(uri, dataMap);
			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/** 创建部门 */
	public static String departmentCreate(WxpQyDepartment dept, String accessToken) {
		String sendJson = dept.toJsonQiye();

		try {
			// https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=ACCESS_TOKEN
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/department/create").setParameter("access_token", accessToken).build();

			String request = WxQyUtil.httpPostRequest(uri, sendJson);
			System.out.println(request);
			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/** 部门更新 */
	public static String departmentUpdate(WxpQyDepartment dept, String accessToken) {
		String sendJson = dept.toJsonQiye();

		try {
			// https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=ACCESS_TOKEN
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/department/update").setParameter("access_token", accessToken).build();

			String request = WxQyUtil.httpPostRequest(uri, sendJson);

			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/** 删除部门 */
	public static String departmentDelete(WxpQyDepartment dept, String accessToken) {
		try {
			// https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token=ACCESS_TOKEN&id=2
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/department/delete").setParameter("access_token", accessToken).setParameter("id", String.valueOf(dept.getSyncId())).build();

			String request = WxQyUtil.httpPostRequest(uri, null);

			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String departmentDelete(Long syncId, String accessToken) {
		try {
			// https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token=ACCESS_TOKEN&id=2
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/department/delete").setParameter("access_token", accessToken).setParameter("id", String.valueOf(syncId)).build();

			String request = WxQyUtil.httpPostRequest(uri, null);

			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String departmentDeleteById(String id, String accessToken) {
		try {
			// https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token=ACCESS_TOKEN&id=2
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/department/delete").setParameter("access_token", accessToken).setParameter("id", id).build();

			String request = WxQyUtil.httpPostRequest(uri, null);

			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=ACCESS_TOKEN&id=ID
	public static String departmentGetById(String id, String accessToken) {
		try {
			// https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token=ACCESS_TOKEN&id=2
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/department/list").setParameter("access_token", accessToken).setParameter("id", id).build();

			String request = WxQyUtil.httpPostRequest(uri, null);

			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 获取成员列表list
	// https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD&status=STATUS
	public static String contactGetByDepartmentId(String departmentId, String accessToken) {
		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/user/list").setParameter("access_token", accessToken).setParameter("department_id", departmentId).setParameter("fetch_child", "1").setParameter("status", "0").build();

			String request = WxQyUtil.httpPostRequest(uri, null);

			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/** 获取部门列表 */
	// https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=ACCESS_TOKEN&id=ID
	public static String departmentList(String accessToken) {
		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/department/list").setParameter("access_token", accessToken).build();

			String request = WxQyUtil.httpGetRequest(uri);

			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/** 创建成员 */
	// https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=ACCESS_TOKEN
	public static String contatCreate(Employee employee, List<Long> deptIdList, String accessToken) {
		String sendJson = "";
		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("userid", employee.getUserId());
		dataMap.put("name", employee.getName());
		dataMap.put("position", employee.getPosition());
		dataMap.put("mobile", employee.getMobile());
		dataMap.put("email", employee.getEmail());
		dataMap.put("weixinid", employee.getWeixinid());
		if (deptIdList != null && deptIdList.size() > 0)
			dataMap.put("department", deptIdList);
		sendJson = JSONObject.toJSONString(dataMap);
		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/user/create").setParameter("access_token", accessToken).build();

			String request = WxQyUtil.httpPostRequest(uri, sendJson);

			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String contactUpdate(Employee employee, List<Long> deptIdList, String accessToken) {
		String sendJson = "";
		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("userid", employee.getUserId());
		dataMap.put("name", employee.getName());
		dataMap.put("position", employee.getPosition());
		dataMap.put("mobile", employee.getMobile());
		dataMap.put("email", employee.getEmail());
		dataMap.put("weixinid", employee.getWeixinid());
		if (deptIdList != null && deptIdList.size() > 0)
			dataMap.put("department", deptIdList);
		sendJson = JSONObject.toJSONString(dataMap);
		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/user/update").setParameter("access_token", accessToken).build();

			String request = WxQyUtil.httpPostRequest(uri, sendJson);

			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/** 更新成员 */
	// https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=ACCESS_TOKEN
	public static String contactUpdate(WxpQyContacts contact, String accessToken) {
		String sendJson = contact.toJsonQiye();

		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/user/update").setParameter("access_token", accessToken).build();

			String request = WxQyUtil.httpPostRequest(uri, sendJson);

			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=ACCESS_TOKEN
	public static String getJsApiTicket(String accessToken) {

		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/get_jsapi_ticket").setParameter("access_token", accessToken).build();

			String request = WxQyUtil.httpPostRequest(uri, null);

			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/** 删除成员 */
	// https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=ACCESS_TOKEN&userid=lisi
	public static String contactDelete(WxpQyContacts contact, String accessToken) {
		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/user/delete").setParameter("access_token", accessToken).setParameter("userid", contact.getUserId()).build();

			String request = WxQyUtil.httpPostRequest(uri, null);

			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=ACCESS_TOKEN&userid=lisi
	public static String contactDelete(String userId, String accessToken) {
		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/user/delete").setParameter("access_token", accessToken).setParameter("userid", userId).build();

			String request = WxQyUtil.httpPostRequest(uri, null);

			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/** 删除成员 */
	// https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=ACCESS_TOKEN&userid=lisi
	public static String contactDeleteById(String id, String accessToken) {
		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/user/delete").setParameter("access_token", accessToken).setParameter("userid", id).build();

			String request = WxQyUtil.httpPostRequest(uri, null);

			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/** 发送消息 */
	// https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN
	public static String messageSend(String agentId, String userIdStr, String text, String accessToken) {
		if (StringUtils.isEmpty(userIdStr))
			return null;

		userIdStr = userIdStr.replace(",", "|");

		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"touser\":\"").append(userIdStr).append("\"");
		sb.append(",\"msgtype\":\"text\"");
		sb.append(",\"agentid\":\"").append(agentId).append("\"");
		sb.append(",\"text\":{");
		sb.append("\"content\":\"").append(text).append("\"");
		sb.append("}");
		sb.append("}");

		String sendJson = sb.toString();
		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/message/send").setParameter("access_token", accessToken).build();

			String request = WxQyUtil.httpPostRequest(uri, sendJson);
			System.out.println(request.getBytes());
			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN
	public static String messageSendNews(String agentId, String userIdStr, String text, String accessToken, String description, String url, String picurl) {
		if (StringUtils.isEmpty(userIdStr))
			return null;

		userIdStr = userIdStr.replace(",", "|");

		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"touser\":\"").append(userIdStr).append("\"");
		sb.append(",\"msgtype\":\"news\"");
		sb.append(",\"agentid\":\"").append(agentId).append("\"");
		sb.append(",\"news\":{\"articles\":[{\"title\":\"").append(text).append("\"");
		sb.append(",\"description\":\"").append(description).append("\"");
		sb.append(",\"url\":\"").append(url).append("\"");
		sb.append(",\"picurl\":\"").append(picurl).append("\"");
		sb.append("}]}");
		sb.append("}");

		String sendJson = sb.toString();
		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/message/send").setParameter("access_token", accessToken).build();

			String request = WxQyUtil.httpPostRequest(uri, sendJson);
			System.out.println(request.getBytes());
			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// https://qyapi.weixin.qq.com/cgi-bin/invite/send?access_token=ACCESS_TOKEN
	public static String wxpQyContactsCreate(String userid, String accessToken) {
		String sendJson = "";
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("userid", userid);
		sendJson = JSONObject.toJSONString(dataMap);
		try {
			// https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=ACCESS_TOKEN
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/invite/send").setParameter("access_token", accessToken).build();

			String request = WxQyUtil.httpPostRequest(uri, sendJson);
			System.out.println(request);
			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID
	public static String getMediaByMediaId(String mediaId, String accessToken) {
		String sendJson = "";
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("media_id", mediaId);
		sendJson = JSONObject.toJSONString(dataMap);
		try {
			// https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=ACCESS_TOKEN
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/media/get").setParameter("access_token", accessToken).build();

			String request = WxQyUtil.httpPostRequest(uri, sendJson);
			System.out.println(request);
			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 关注成功
	public static String authsucc(String userid, String accessToken) {
		try {
			// https://qyapi.weixin.qq.com/cgi-bin/user/authsucc?access_token=ACCESS_TOKEN&userid=USERID
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/user/authsucc").setParameter("access_token", accessToken).setParameter("userid", userid).build();
			String request = WxQyUtil.httpPostRequest(uri, null);
			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 获取应用素材素材列表。
	// https://qyapi.weixin.qq.com/cgi-bin/material/batchget?access_token=ACCESS_TOKEN
	public static String batchgetMaterial(String accessToken) {
		try {
			String sendJson = "";
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("type", "image");
			dataMap.put("agentid", 4);
			dataMap.put("offset", 0);
			dataMap.put("count", 10);
			sendJson = JSONObject.toJSONString(dataMap);
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/material/batchget").setParameter("access_token", accessToken).build();
			String request = WxQyUtil.httpPostRequest(uri, sendJson);
			return request;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String toJsonWxpQyContacts(String userid) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("userid", userid);
		return JSONObject.toJSONString(dataMap);
	}

	/**
	 * 获取应用套件令牌
	 * 
	 * https://qyapi.weixin.qq.com/cgi-bin/service/get_suite_token
	 * 
	 * @param accessToken
	 * @return { "suite_access_token":
	 *         "61W3mEpU66027wgNZ_MhGHNQDHnFATkDa9-2llqrMBjUwxRSNPbVsMmyD-yq8wZETSoE5NQgecigDrSHkPtIYA",
	 *         "expires_in":7200 }
	 */
	public static String getSuiteToken(String suite_id, String suite_secret, String suite_ticket) {
		String sendJson = "";
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("suite_id", suite_id);
		dataMap.put("suite_secret", suite_secret);
		dataMap.put("suite_ticket", suite_ticket);
		sendJson = JSONObject.toJSONString(dataMap);
		try {
			// https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=ACCESS_TOKEN
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/service/get_suite_token").build();

			String request = WxQyUtil.httpPostRequest(uri, sendJson);
			System.out.println(request);

			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取预授权码
	 * 
	 * @param suite_id
	 * @param suite_secret
	 * @param suite_ticket
	 * @param accessToken
	 * @return
	 */
	// https://qyapi.weixin.qq.com/cgi-bin/service/get_pre_auth_code?suite_access_token=SUITE_ACCESS_TOKEN
	public static String getPreAuthCode(String suite_id, String suite_access_token) {
		String sendJson = "";
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("suite_id", suite_id);
		sendJson = JSONObject.toJSONString(dataMap);
		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/service/get_pre_auth_code").setParameter("suite_access_token", suite_access_token).build();
			String request = WxQyUtil.httpPostRequest(uri, sendJson);

			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取企业号登录用户信息
	 * 
	 * @param auth_code
	 * @param access_token
	 * @return
	 */
	// https://qyapi.weixin.qq.com/cgi-bin/service/get_login_info?access_token=ACCESS_TOKEN
	public static String getLoginInfo(String auth_code, String access_token) {
		String sendJson = "";
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("auth_code", auth_code);
		sendJson = JSONObject.toJSONString(dataMap);
		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/service/get_login_info").setParameter("access_token", access_token).build();
			String request = WxQyUtil.httpPostRequest(uri, sendJson);
			return request;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 设置授权配置
	 * 
	 * { "pre_auth_code":"xxxxx", "session_info": { "appid":[1,2,3] } }
	 * 
	 * TODO
	 * 
	 * @param suite_id
	 * @param suite_access_token
	 * @return
	 */
	// https://qyai.weixin.qq.com/cgi-bin/service/set_session_info?suite_access_token=SUITE_ACCESS_TOKEN
	public static String set_session_info(String suite_id, String suite_access_token) {
		String sendJson = "";
		Map<String, Object> dataMap = new HashMap<String, Object>();
		sendJson = JSONObject.toJSONString(dataMap);
		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/service/set_session_info").setParameter("suite_access_token", suite_access_token).build();
			String request = WxQyUtil.httpPostRequest(uri, sendJson);

			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取企业号的永久授权码
	 * 
	 * { "suite_id":"id_value" , "auth_code": "auth_code_value" }
	 * 
	 * @param suite_id
	 * @param suite_access_token
	 * @return
	 */
	// https://qyapi.weixin.qq.com/cgi-bin/service/get_permanent_code?suite_access_token=SUITE_ACCESS_TOKEN
	public static String getPermanentCode(String suite_id, String auth_code, String suite_access_token) {
		String sendJson = "";
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("suite_id", suite_id);
		dataMap.put("auth_code", auth_code);
		sendJson = JSONObject.toJSONString(dataMap);
		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/service/get_permanent_code").setParameter("suite_access_token", suite_access_token).build();
			String request = WxQyUtil.httpPostRequest(uri, sendJson);

			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取企业号的授权信息 { "suite_id":"suite_id_value", "auth_corpid":
	 * "auth_corpid_value", "permanent_code": "code_value" }
	 * 
	 * @param suite_id
	 * @param auth_code
	 * @param suite_access_token
	 * @return
	 */
	// https://qyapi.weixin.qq.com/cgi-bin/service/get_auth_info?suite_access_token=SUITE_ACCESS_TOKEN
	public static String getAuthInfo(String suite_id, String auth_corpid, String permanent_code, String suite_access_token) {
		String sendJson = "";
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("suite_id", suite_id);
		dataMap.put("auth_corpid", auth_corpid);
		dataMap.put("permanent_code", permanent_code);
		sendJson = JSONObject.toJSONString(dataMap);
		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/service/get_auth_info").setParameter("suite_access_token", suite_access_token).build();
			String request = WxQyUtil.httpPostRequest(uri, sendJson);

			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取企业号access_token
	 * 
	 * { "suite_id":"suite_id_value", "auth_corpid": "auth_corpid_value",
	 * "permanent_code": "code_value" }
	 * 
	 * @param suite_id
	 * @param auth_corpid
	 * @param permanent_code
	 * @param suite_access_token
	 * @return
	 */
	// https://qyapi.weixin.qq.com/cgi-bin/service/get_corp_token?suite_access_token=SUITE_ACCESS_TOKEN
	public static String getCorpToken(String suite_id, String auth_corpid, String permanent_code, String suite_access_token) {
		String sendJson = "";
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("suite_id", suite_id);
		dataMap.put("auth_corpid", auth_corpid);
		dataMap.put("permanent_code", permanent_code);
		sendJson = JSONObject.toJSONString(dataMap);
		try {
			URI uri = new URIBuilder().setScheme("https").setHost(api_weixin_qy_server).setPath("/cgi-bin/service/get_corp_token").setParameter("suite_access_token", suite_access_token).build();
			String request = WxQyUtil.httpPostRequest(uri, sendJson);

			return request;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * CorpID wx1a67071803f6077f Secret
	 * q66dvUHuqCHsvVjolzycM8KP1ERXn_KDGClPfIu4A_94FI5AsRqKojpL9Arl2CC4
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// System.out.println(messageSend("", "", "小伙子",
		// "3g1EibUbdf7nChnVVsi1Vvb1W0T_UYnSwMmGTb1ejc2B2mFoxLrGFAnfSrDolDgDy7VwpUxl7RV61cxuyvfOhw"));
		// verification("wxb0a00105cc4da5ef",
		// "http://120.55.116.139:8080/vixnt-erp/");
		// wxpQyContactsCreate("bd3929673de543a6b05fd4a7bd731e05",
		// "3qmvNPm6elapdUpyH2cZywCvFFBX5pVYXgFmeayxLdenxVVH_yX-5DiBSxFRkZuRpIQCt1eCUCBA9t3mewnNFQ");
		// reloadAccessToken("wx1a67071803f6077f",
		// "q66dvUHuqCHsvVjolzycM8KP1ERXn_KDGClPfIu4A_94FI5AsRqKojpL9Arl2CC4");
		// contactGetByDepartmentId(String departmentId, String accessToken)
		// contactGetByDepartmentId("1",
		// "MgH5sIAQSvf4DiHJ0GdhB2jEvsAqhXCNxDrqZyn-0uutlzSzrHPJFN72GZoXPovCdog0Mrf8sVlSy1mYE8Z5qA");
		// contactDelete(contactID, accessToken)
		// messageSend("2", "niuping", "hello baby",
		// "xC6IWvNK0pCUjq7rylG9mmhdw42hlVSQ6N5FTpR-v0TDezbmf1mbUdxqIzNmo8gfE2pX2hn2mmSv5WpRkDjHcw");
		// System.out.println(departmentList("54Og9KOQ7oV2UVVnePT5L3a4kevxUs3W55rrQBel6-p4X5khe0una3Gr_qZscEja"));
		// System.out.println(contactGetByDepartmentId("1",
		// "54Og9KOQ7oV2UVVnePT5L3a4kevxUs3W55rrQBel6-p4X5khe0una3Gr_qZscEja"));
		// WxQyUtil.messageSendNews("3", "bjitzhang", "有新任务",
		// "y8msktvN9Ou-3VdJ846Dgdl-L8Hus4lybqvARGiKXkAYrW3MGTeh89UD6YqHk6W4",
		// "链接", "http://www.baidu.com",
		// "http://shp.qpic.cn/bizmp/flDBtlvCqqmLpGMBbsYhW01DiaHCUAoUXm7h63B5E0eRqyfLyEEZicvQ/");
		// WxQyUtil.batchgetMaterial("jcuPv8ssQEPW4A1yF67yAcVb_VVQU4Eo2g-170HQ7WxrRCDWTLh5a72SclC3zHXK");
		WxQyUtil.getJsApiTicket("F_PFBnZ7KUcMHiK8FNamOQfq5I50Yy4ko6-P657q_uc1DSmHuvGeii2Riw96UH-3");
		// WxQyUtil.batchgetMaterial("F_PFBnZ7KUcMHiK8FNamOQfq5I50Yy4ko6-P657q_uc1DSmHuvGeii2Riw96UH-3");
		// WxQyUtil.create("42",
		// "42NTvg0CR2VjdodD7h3cdPxClcRQNc1V2g1wpU2ErpskeDd2CcJJrwXoK3yOtuP5");
	}
}
