package com.vix.wechat.base.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Map;

import javax.servlet.ServletInputStream;

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

import com.vix.common.base.action.BaseAction;
import com.vix.core.utils.JSonUtils;
import com.vix.core.utils.kit.PropKit;
import com.vix.hr.organization.entity.Employee;
import com.vix.wechat.base.entity.WxpQyWeixinSite;
import com.vix.wechat.base.entity.WxpQyWeixinTicket;
import com.vix.wechat.base.service.IWechatBaseService;
import com.vix.wechat.base.util.WxQyUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class WechatBaseAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	public static String api_qiye_server = "qyapi.weixin.qq.com";
	public static String oauth_openid_load_scope_snsapi_base = "snsapi_base";
	public static String oauth_openid_load_scope_snsapi_userinfo = "snsapi_userinfo";
	public static String session_oauthed_wx_user_openid = "session_oauthed_wx_user_openid";
	public static String session_oauthed_response_data = "session_oauthed_response_data";
	public static String session_oauthed_qy_user_id = "session_oauthed_qy_user_id";
	public static String api_qiye_tenantid;
	public static String api_qiye_corpid;
	public static String api_qiye_suiteId;
	protected File docToUpload;
	protected String docToUploadFileName;
	public static String taskAgentId;
	public static String redirect_ip;
	@Autowired
	public IWechatBaseService wechatBaseService;

	static {
		api_qiye_tenantid = PropKit.use("path_config.properties").get("api_qiye_tenantid");
		api_qiye_corpid = PropKit.use("path_config.properties").get("api_qiye_qiyecorpid");
		api_qiye_suiteId = PropKit.use("path_config.properties").get("api_qiye_suiteId");
		redirect_ip = PropKit.use("path_config.properties").get("redirect_ip");
		taskAgentId = PropKit.use("wechat_config.properties").get("wechat_qiye_task_agentId");
		// getSession().setAttribute("userId", "bjitzhang");
	}

	public String checkWxQyVisitUser(WxpQyWeixinSite site, String state, String code, String url) throws Exception {
		String userId = null;

		// 首先判断session是否有openId，已测试，微信切换账号访问会清掉session
		userId = (String) getSession().getAttribute("userId");
		if (StringUtils.isNotEmpty(userId)) {
			return userId;
		}
		// 准备去微信服务器进行oauth授权，获取openId
		saveOrUpdateWxpWeixinSite(site);
		if (StringUtils.isNotEmpty(state) && StringUtils.isNotEmpty(code)) {
			try {
				// https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE&agentid=AGENTID
				URI uri = new URIBuilder().setScheme("https").setHost(api_qiye_server).setPath("/cgi-bin/user/getuserinfo").setParameter("access_token", site.getQiyeAccessToken()).setParameter("code", code).build();

				CloseableHttpClient httpclient = HttpClients.createDefault();
				HttpGet httpget = null;
				CloseableHttpResponse response = null;
				try {
					httpget = new HttpGet(uri);
					response = httpclient.execute(httpget);
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						String accessTokenResponseStr = EntityUtils.toString(entity);
						if (StringUtils.isNotEmpty(accessTokenResponseStr)) {
							Map<?, ?> infoMap = JSonUtils.readValue(accessTokenResponseStr, Map.class);
							userId = (String) infoMap.get("UserId");
							getSession().setAttribute("userId", userId);
							return userId;
						} else {
							return null;
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
		} else {
			genOAuthUrlWeixinQiye(site, oauth_openid_load_scope_snsapi_base, url);
			// 页面已经跳转，直接返回
			return null;
		}
		return userId;
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

	public String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	/**
	 * 
	 * @param agentid
	 *            企业应用的id，整型。可在应用的设置页面查看
	 * @param userIds
	 *            成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为@all，
	 *            则向关注该企业应用的全部成员发送
	 * @param title
	 *            标题，不超过128个字节，超过会自动截断
	 * @param description
	 *            描述，不超过512个字节，超过会自动截断
	 * @param id
	 *            新增数据的ID
	 * @param url
	 *            图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80。如不填，在客户端不显示图片
	 * @param tenantId
	 *            承租户ID
	 */
	public void sendMessage(String agentid, String userIds, String title, String description, String id, String url, String tenantId) {
		try {

			String surl = "";
			if (StringUtils.isNotEmpty(agentid)) {
				if ("7".equals(agentid)) {
					surl = redirect_ip + "/wechat/conferenceAssistantAction!goApplicationMg.action?id=" + id;
				} else if ("15".equals(agentid)) {
					surl = redirect_ip + "/wechat/identityVerificationAction!goShowNews.action?id=" + id;
				} else if ("2".equals(agentid)) {
					surl = redirect_ip + "/wechat/weChatDynListAction!goShowAnnouncementNotification.action?id=" + id;
				}
			}
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttribute(WxpQyWeixinSite.class, "tenantId", tenantId);
			WxQyUtil.messageSendNews(agentid, userIds, title, site.getQiyeAccessToken(), description, surl, url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 从腾讯服务器获取oauth信息 */
	public void genOAuthUrlWeixinQiye(WxpQyWeixinSite site, String scope, String myCurrentUrl) {

		String jumpToWxUrl = this.genJumpToOAuthUrl(site.getQiyeCorpId(), myCurrentUrl, oauth_openid_load_scope_snsapi_base);
		System.out.println("go qiye oauth url: " + jumpToWxUrl);
		try {
			getResponse().sendRedirect(jumpToWxUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 需要到微信后台的开发者接口那里，修改oAuth2.0的授权回调域名
	// https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
	public String genJumpToOAuthUrl(String appId, String backUrl, String scope) {
		try {
			StringBuilder sb = new StringBuilder("https://open.weixin.qq.com/connect/oauth2/authorize?appid=").append(appId);
			sb.append("&redirect_uri=").append(URLEncoder.encode(backUrl, "UTF-8"));
			sb.append("&response_type=code");
			sb.append("&scope=");
			if (StringUtils.isEmpty(scope)) {
				scope = oauth_openid_load_scope_snsapi_base;
			}
			sb.append(scope);
			sb.append("&state=").append(appId);
			sb.append("#wechat_redirect");
			// System.out.println(sb.toString());
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 判断某一时间是否在一个区间内
	 * 
	 * @param sourceTime
	 *            时间区间,半闭合,如[10:00-20:00) 全闭合,,如[10:00-20:00]
	 * @param curTime
	 *            需要判断的时间 如10:00
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static boolean isInTime(String sourceTime, String curTime) {
		if (sourceTime == null || !sourceTime.contains("-") || !sourceTime.contains(":")) {
			throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
		}
		if (curTime == null || !curTime.contains(":")) {
			throw new IllegalArgumentException("Illegal Argument arg:" + curTime);
		}
		String[] args = sourceTime.split("-");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try {
			long now = sdf.parse(curTime).getTime();
			long start = sdf.parse(args[0]).getTime();

			long end = sdf.parse(args[1]).getTime();
			if (args[1].equals("00:00:00")) {
				args[1] = "24:00:00";
			}
			if (end < start) {
				if (now > end && now < start) {
					return false;
				} else {
					return true;
				}
			} else {
				if (now >= start && now <= end) {
					return true;
				} else {
					return false;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
		}
	}

	public String[] saveUploadPic() {
		String[] pathAndName = null;
		try {
			if (null != fileToUpload) {
				/** 上传目录 */
				String saveFolder = this.getUploadFileSavePic();
				@SuppressWarnings("resource")
				BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(fileToUpload));
				String[] fileNames = fileToUploadFileName.split("\\.");
				String extFileName = fileNames[fileNames.length - 1];
				String fileName = fileToUploadFileName.substring(0, fileToUploadFileName.length() - extFileName.length() - 1);

				long newFileMark = System.currentTimeMillis() / 1000;
				String saveFileName = fileName + "_" + newFileMark + "." + extFileName;

				String saveFilePath = saveFolder + "/" + saveFileName;

				BufferedOutputStream bufOut = new BufferedOutputStream(new FileOutputStream(saveFilePath));
				byte[] buf = new byte[1024 * 100];
				int len = -1;
				while ((len = bufIn.read(buf)) != -1) {
					bufOut.write(buf, 0, len);
				}
				bufOut.close();

				pathAndName = new String[2];
				pathAndName[0] = saveFolder;
				pathAndName[1] = saveFileName;
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return pathAndName;
	}

	@Override
	public String[] saveDocUploadPic() {
		String[] pathAndName = null;
		try {
			if (null != docToUpload) {
				/** 上传目录 */
				String saveFolder = this.getUploadFileSavePic();
				@SuppressWarnings("resource")
				BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(docToUpload));
				int size = bufIn.available();
				String[] fileNames = docToUploadFileName.split("\\.");
				String extFileName = fileNames[fileNames.length - 1];
				String fileName = docToUploadFileName.substring(0, docToUploadFileName.length() - extFileName.length() - 1);

				long newFileMark = System.currentTimeMillis() / 1000;
				String saveFileName = fileName + "_" + newFileMark + "." + extFileName;

				String saveFilePath = saveFolder + "/" + saveFileName;

				BufferedOutputStream bufOut = new BufferedOutputStream(new FileOutputStream(saveFilePath));
				byte[] buf = new byte[1024 * 100];
				int len = -1;
				while ((len = bufIn.read(buf)) != -1) {
					bufOut.write(buf, 0, len);
				}
				bufOut.close();
				pathAndName = new String[3];
				pathAndName[0] = saveFolder;
				pathAndName[1] = saveFileName;
				if (size > 1024) {
					pathAndName[2] = String.valueOf(Math.ceil(size / 1024) + "KB");
				} else {
					pathAndName[2] = String.valueOf(size + "b");
				}
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return pathAndName;
	}

	// 增量更新成员
	public void updateEmployee(WxpQyWeixinSite wxpWeixinSite) {

		try {
			// 更新成员
			String userList = WxQyUtil.contactGetByDepartmentId("1", wxpWeixinSite.getQiyeAccessToken());
			JSONObject jsonObject = JSONObject.fromObject(userList);
			JSONArray jsonarray = jsonObject.getJSONArray("userlist");
			for (int i = 0; i < jsonarray.size(); i++) {
				JSONObject json = jsonarray.getJSONObject(i);
				if (json != null) {
					String userid = "";
					if (json.has("userid")) {
						userid = json.getString("userid");
					}
					String name = "";
					if (json.has("name")) {
						name = json.getString("name");
					}
					String mobile = "";
					if (json.has("mobile")) {
						mobile = json.getString("mobile");
					}
					String email = "";
					if (json.has("email")) {
						email = json.getString("email");
					}
					String weixinid = "";
					if (json.has("weixinid")) {
						weixinid = json.getString("weixinid");
					}
					String avatar = "";
					if (json.has("avatar")) {
						avatar = json.getString("avatar");
					}
					Employee employee = wechatBaseService.findEntityByAttributeNoTenantId(Employee.class, "userId", userid);
					if (employee != null) {
					} else {
						employee = new Employee();
						employee.setUserId(userid);
						employee.setEmpType("WE");
						employee.setName(name);
						employee.setMobile(mobile);
						employee.setEmail(email);
						employee.setWeixinid(weixinid);
						employee.setHeadImgUrl(avatar);
						if (wxpWeixinSite != null) {
							employee.setTenantId(wxpWeixinSite.getTenantId());
							employee.setCompanyInnerCode(wxpWeixinSite.getCompanyInnerCode());
						}
						employee.setCreateTime(new Date());
						wechatBaseService.mergeOriginal(employee);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getUploadFileSavePic() {

		String baseFolder = "c:/img";

		String newFilePath = "";

		newFilePath = baseFolder;

		File dir = new File(newFilePath);
		if (!dir.exists())
			dir.mkdirs();

		return newFilePath;
	}

	public WxpQyWeixinSite saveOrUpdateWxpWeixinSite(WxpQyWeixinSite wxpWeixinSite) throws Exception {
		if (wxpWeixinSite != null && wxpWeixinSite.needReloadQiyeAccessToken()) {
			if (StringUtils.isNotEmpty(wxpWeixinSite.getQiyeCorpId()) && StringUtils.isNotEmpty(wxpWeixinSite.getQiyeSecret())) {
				Map<?, ?> dataMap = WxQyUtil.reloadAccessToken(wxpWeixinSite.getQiyeCorpId(), wxpWeixinSite.getQiyeSecret());
				if (dataMap != null) {
					if (dataMap.containsKey("access_token")) {
						String newAccessToken = (String) dataMap.get("access_token");
						wxpWeixinSite.setQiyeAccessToken(newAccessToken);
					}
					if (dataMap.containsKey("expires_in")) {
						Integer expireSec = (Integer) dataMap.get("expires_in");
						long expireTime = System.currentTimeMillis() + 1000L * expireSec;
						wxpWeixinSite.setQiyeTokenExpireTime(new Date(expireTime));
					}
					wxpWeixinSite = wechatBaseService.mergeOriginal(wxpWeixinSite);
				}
			}
		}
		return wxpWeixinSite;
	}

	public WxpQyWeixinTicket saveOrUpdateWxpWeixinTicket(WxpQyWeixinTicket wxpWeixinTicket, WxpQyWeixinSite site) throws Exception {
		if (wxpWeixinTicket != null && StringUtils.isNotEmpty(wxpWeixinTicket.getId())) {
			if (wxpWeixinTicket.needReloadQiyeTicket()) {
				String request = WxQyUtil.getJsApiTicket(site.getQiyeAccessToken());
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
							wxpWeixinTicket.setQiyeCorpId(site.getQiyeCorpId());
							wxpWeixinTicket.setQiyeTicket(ticket);
							if (expireSec != null) {
								long expireTime = System.currentTimeMillis() + 1000L * expireSec;
								wxpWeixinTicket.setQiyeTokenExpireTime(new Date(expireTime));
							}
							wxpWeixinTicket = wechatBaseService.mergeOriginal(wxpWeixinTicket);
						}
					}
				}
			}
		} else {
			String request = WxQyUtil.getJsApiTicket(site.getQiyeAccessToken());
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
						wxpWeixinTicket = new WxpQyWeixinTicket();
						wxpWeixinTicket.setQiyeCorpId(site.getQiyeCorpId());
						wxpWeixinTicket.setQiyeTicket(ticket);
						if (expireSec != null) {
							long expireTime = System.currentTimeMillis() + 1000L * expireSec;
							wxpWeixinTicket.setQiyeTokenExpireTime(new Date(expireTime));
						}
						wxpWeixinTicket.setTenantId(site.getTenantId());
						wxpWeixinTicket.setCompanyInnerCode(site.getCompanyInnerCode());
						wxpWeixinTicket.setCompanyCode(site.getCompanyCode());
						wxpWeixinTicket.setCreateTime(new Date());
						wxpWeixinTicket = wechatBaseService.mergeOriginal(wxpWeixinTicket);
					}
				}
			}

		}
		return wxpWeixinTicket;
	}

	public int getTime(Date s1, Date s2) {
		java.util.Calendar c1 = java.util.Calendar.getInstance();
		java.util.Calendar c2 = java.util.Calendar.getInstance();
		c1.setTime(s1);
		c2.setTime(s2);
		int result = c1.compareTo(c2);
		return result;
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

	/**
	 * @return the docToUpload
	 */
	@Override
	public File getDocToUpload() {
		return docToUpload;
	}

	/**
	 * @param docToUpload
	 *            the docToUpload to set
	 */
	@Override
	public void setDocToUpload(File docToUpload) {
		this.docToUpload = docToUpload;
	}

	/**
	 * @return the docToUploadFileName
	 */
	@Override
	public String getDocToUploadFileName() {
		return docToUploadFileName;
	}

	/**
	 * @param docToUploadFileName
	 *            the docToUploadFileName to set
	 */
	@Override
	public void setDocToUploadFileName(String docToUploadFileName) {
		this.docToUploadFileName = docToUploadFileName;
	}
}
