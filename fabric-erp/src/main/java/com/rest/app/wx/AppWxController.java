package com.rest.app.wx;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rest.core.base.BaseRestController;
import com.vix.traceability.service.ITraceabilityService;
import com.vix.wechat.base.entity.WxpQySuiteTicket;
import com.vix.wechat.base.util.WxQyUtil;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassFullName com.rest.app.project.AppProjectController
 *
 * @author bjitzhang
 *
 * @date 2016年2月1日
 *
 */

@Controller
@RequestMapping(value = "restService/app/appwx")
public class AppWxController extends BaseRestController {

	@Resource(name = "traceabilityService")
	private ITraceabilityService traceabilityService;

	@RequestMapping(value = "getparm.rs", method = RequestMethod.POST)
	public void getparm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str = "";
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			List<WxpQySuiteTicket> wxpSuiteTicketList = traceabilityService.findAllDataByConditions(WxpQySuiteTicket.class, params);
			// 倒序
			Collections.sort(wxpSuiteTicketList, new Comparator<WxpQySuiteTicket>() {
				@Override
				public int compare(WxpQySuiteTicket o1, WxpQySuiteTicket o2) {
					return getTime(o2.getCreateTime(), o1.getCreateTime());
				}
			});

			if (wxpSuiteTicketList != null && wxpSuiteTicketList.size() > 0) {
				for (WxpQySuiteTicket wxpSuiteTicket : wxpSuiteTicketList) {
					if (wxpSuiteTicket != null) {
						if (wxpSuiteTicket.needReloadPreAuthCode()) {
							String suiteAccessToken = null;
							// 通过suite_access_token获取预授权码
							if (wxpSuiteTicket.needReloadSuiteAccessToken()) {
								String r = WxQyUtil.getSuiteToken(wxpSuiteTicket.getSuiteId(), wxpSuiteTicket.getSuiteSecret(), wxpSuiteTicket.getSuiteTicket());
								if (StringUtils.isNotEmpty(r)) {
									JSONObject json = JSONObject.fromObject(r);
									System.out.println(json);
									if (json != null) {
										String suite_access_token = "";
										if (json.has("suite_access_token")) {
											suite_access_token = json.getString("suite_access_token");
										}
										Integer expires_in = null;
										if (json.has("expires_in")) {
											expires_in = Integer.parseInt(json.getString("expires_in"));
										}
										long expireTime = System.currentTimeMillis() + 1000L * expires_in;
										wxpSuiteTicket.setSuiteAccessToken(suite_access_token);
										wxpSuiteTicket.setSuiteAccessTokenExpireTime(new Date(expireTime));
										wxpSuiteTicket = traceabilityService.mergeOriginal(wxpSuiteTicket);
										suiteAccessToken = wxpSuiteTicket.getSuiteAccessToken();
									}
								}
							} else {
								suiteAccessToken = wxpSuiteTicket.getSuiteAccessToken();
							}
							String request1 = WxQyUtil.getPreAuthCode(wxpSuiteTicket.getSuiteId(), suiteAccessToken);
							if (StringUtils.isNotEmpty(request1)) {
								JSONObject json1 = JSONObject.fromObject(request1);
								System.out.println(json1);
								if (json1 != null) {
									String errcode = "";
									if (json1.has("errcode")) {
										errcode = json1.getString("errcode");
									}
									if ("0".equals(errcode)) {
										String pre_auth_code = "";
										if (json1.has("pre_auth_code")) {
											pre_auth_code = json1.getString("pre_auth_code");
										}
										Integer expires_in1 = null;
										if (json1.has("expires_in")) {
											expires_in1 = Integer.parseInt(json1.getString("expires_in"));
										}
										long expireTime1 = System.currentTimeMillis() + 1000L * expires_in1;
										wxpSuiteTicket.setPreAuthCode(pre_auth_code);
										wxpSuiteTicket.setPreAuthCodeExpireTime(new Date(expireTime1));
										wxpSuiteTicket = traceabilityService.mergeOriginal(wxpSuiteTicket);
										str = wxpSuiteTicket.getSuiteId() + "," + pre_auth_code + "," + "www.vixware.cn";
									}
								}
							}

						} else {
							str = wxpSuiteTicket.getSuiteId() + "," + wxpSuiteTicket.getPreAuthCode() + "," + "www.vixware.cn";
						}
						renderResult(response, str);
						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int getTime(Date s1, Date s2) {
		java.util.Calendar c1 = java.util.Calendar.getInstance();
		java.util.Calendar c2 = java.util.Calendar.getInstance();
		c1.setTime(s1);
		c2.setTime(s2);
		int result = c1.compareTo(c2);
		return result;
	}
}
