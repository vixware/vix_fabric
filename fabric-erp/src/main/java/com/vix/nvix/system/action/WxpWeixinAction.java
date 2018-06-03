package com.vix.nvix.system.action;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.wechat.base.entity.WxpQySuiteTicket;
import com.vix.wechat.base.entity.WxpQyWeixinSite;
import com.vix.wechat.base.util.WxQyUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class WxpWeixinAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private WxpQyWeixinSite wxpWeixinSite;
	private String authCode;

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			Employee e = getEmployee();
			if (e != null) {
				wxpWeixinSite = vixntBaseService.findEntityByAttribute(WxpQyWeixinSite.class, "tenantId", e.getTenantId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			String qiyeCorpId = getRequestParameter("qiyeCorpId");
			if (StringUtils.isNotEmpty(qiyeCorpId)) {
				params.put("qiyeCorpId," + SearchCondition.EQUAL, qiyeCorpId);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, WxpQyWeixinSite.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				wxpWeixinSite = vixntBaseService.findEntityById(WxpQyWeixinSite.class, id);
			} else {
				wxpWeixinSite = new WxpQyWeixinSite();
				wxpWeixinSite.setCode(VixUUID.createCode(10));
				wxpWeixinSite.setCreateTime(new Date());
				Employee employee = getEmployee();
				if (employee != null) {
					wxpWeixinSite.setCreator(employee.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (wxpWeixinSite != null && StringUtils.isNotEmpty(wxpWeixinSite.getId())) {
				isSave = false;
			}
			wxpWeixinSite = saveOrUpdateWxpWeixinSite(wxpWeixinSite);
			// updateAccessToken(wxpWeixinSite);
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
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("tenantId", wxpWeixinSite.getTenantId());
					params.put("userId", userid);
					StringBuilder hql = findEmployeeHql(params);
					Employee employee = vixntBaseService.findObjectByHql(hql.toString(), params);
					if (employee != null) {
						employee.setName(name);
						employee.setMobile(mobile);
						employee.setEmail(email);
						employee.setWeixinid(weixinid);
						employee.setHeadImgUrl(avatar);
						vixntBaseService.merge(employee);
					} else {
						employee = new Employee();
						employee.setUserId(userid);
						employee.setEmpType("S");
						employee.setName(name);
						employee.setMobile(mobile);
						employee.setEmail(email);
						employee.setWeixinid(weixinid);
						employee.setHeadImgUrl(avatar);
						initEntityBaseController.initEntityBaseAttribute(employee);
						vixntBaseService.merge(employee);
					}
				}
			}

			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText("更新成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText("更新失败");
			}
		}
		return UPDATE;
	}

	public StringBuilder findEmployeeHql(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		String ename = "employee";
		hql.append("select ").append(ename);
		hql.append(" from Employee ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("tenantId")) {
				Object tenantId = params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append(ename).append(".tenantId is null");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append(ename).append(".tenantId = :tenantId ");
				}
			}
			if (params.containsKey("userId")) {
				Object userId = params.get("userId");
				if (userId == null) {
					hql.append(" and ").append(ename).append(".userId is null");
					params.remove("userId");
				} else {
					hql.append(" and ").append(ename).append(".userId = :userId ");
				}
			}
		} else {

		}
		return hql;
	}

	// http://www.vixware.cn/?auth_code=VAEZ2ozCNZgfKSYq33yxuHXTQhuOI_EX2NcBkp3hl0Xp6xMqD42mDHOtc82vLs45IqLC3KEd2qtYMhbkwNh3eA&state=1234&expires_in=1200
	public void saveOrUpdateWxpWeixinSite() {

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			List<WxpQySuiteTicket> wxpSuiteTicketList = vixntBaseService.findAllByConditions(WxpQySuiteTicket.class, params);
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
						if (authCode.split("=").length == 4) {
							authCode = authCode.split("=")[1].replace("&state", "");
							// 获取永久授权码
							String request = WxQyUtil.getPermanentCode(wxpSuiteTicket.getSuiteId(), authCode, wxpSuiteTicket.getSuiteAccessToken());
							if (StringUtils.isNotEmpty(request)) {
								JSONObject json = JSONObject.fromObject(request);
								String corpid = "";
								if (json != null) {
									WxpQyWeixinSite wxpWeixinSite = null;
									String auth_corp_info = "";
									if (json.has("auth_corp_info")) {
										auth_corp_info = json.getString("auth_corp_info");
										JSONObject authcorpinfo = JSONObject.fromObject(auth_corp_info);
										if (authcorpinfo != null) {
											if (authcorpinfo.has("corpid")) {
												corpid = authcorpinfo.getString("corpid");
												System.out.println("corpid=" + corpid);
												if (StringUtils.isNotEmpty(corpid)) {
													wxpWeixinSite = vixntBaseService.findEntityByAttribute(WxpQyWeixinSite.class, "qiyeCorpId", corpid);
													if (wxpWeixinSite != null) {

													} else {
														wxpWeixinSite = new WxpQyWeixinSite();
													}
												}
												wxpWeixinSite.setQiyeCorpId(corpid);
											}
											String corp_name = "";
											if (authcorpinfo.has("corp_name")) {
												corp_name = authcorpinfo.getString("corp_name");
												wxpWeixinSite.setCorpName(corp_name);
											}
										}
									}
									String permanent_code = "";
									if (json.has("permanent_code")) {
										permanent_code = json.getString("permanent_code");
									}
									String access_token = "";
									if (json.has("access_token")) {
										access_token = json.getString("access_token");
									}
									Integer expires_in = null;
									if (json.has("expires_in")) {
										expires_in = Integer.parseInt(json.getString("expires_in"));
									}
									long expireTime = System.currentTimeMillis() + 1000L * expires_in;

									wxpWeixinSite.setQiyeTokenExpireTime(new Date(expireTime));
									wxpWeixinSite.setPermanentCode(permanent_code);
									wxpWeixinSite.setQiyeAccessToken(access_token);
									initEntityBaseController.initEntityBaseAttribute(wxpWeixinSite);
									wxpWeixinSite = vixntBaseService.merge(wxpWeixinSite);
									String authinfo = WxQyUtil.getAuthInfo(wxpSuiteTicket.getSuiteId(), corpid, permanent_code, wxpSuiteTicket.getSuiteAccessToken());
									JSONObject authinfojson = JSONObject.fromObject(authinfo);
									if (StringUtils.isNotEmpty(authinfojson.toString())) {
										if (authinfojson.has("auth_info")) {
											String authInfo = String.valueOf(authinfojson.getString("auth_info"));
											System.out.println("authInfo:" + authInfo);
											JSONObject jsonobject = JSONObject.fromObject(authInfo);
											JSONArray jsonArray = JSONArray.fromObject(jsonobject.getString("agent"));
											System.out.println("jsonArray:" + jsonArray);
											for (int i = 0; i < jsonArray.size(); i++) {
												JSONObject j = jsonArray.getJSONObject(i);
												String agentid = "";
												if (j.has("agentid")) {
													agentid = j.getString("agentid");
													System.out.println("agentid:" + agentid);
												}
												String appid = "";
												if (j.has("appid")) {
													appid = j.getString("appid");
													System.out.println("appid:" + appid);
												}
												String dataMap = "";
												if ("5".equals(appid)) {
													dataMap = "{\"button\":[{\"name\":\"日志管理\",\"sub_button\":[{\"type\":\"view\",\"name\":\"我的日志\",\"url\":\"http://www.vixware.cn/wechat/jobLogAction!goList.action?corpid=" + corpid + "\"},{\"type\":\"view\",\"name\":\"下属的日志\",\"url\":\"http://www.vixware.cn/wechat/jobLogAction!goSubordinateList.action?corpid=" + corpid + "\"},{\"type\":\"view\",\"name\":\"草稿\",\"url\":\"http://www.vixware.cn/wechat/jobLogAction!goDraftList.action?corpid=" + corpid + "\"}]},{\"type\":\"view\",\"name\":\"日志编写\",\"url\":\"http://www.vixware.cn/wechat/jobLogAction!goSaveOrUpdate.action?corpid=" + corpid + "\"}]}";
												} else if ("6".equals(appid)) {
													dataMap = "{\"button\":[{\"type\":\"view\",\"name\":\"新建会议\",\"url\":\"http://www.vixware.cn/wechat/conferenceAssistantAction!goSaveOrUpdate.action?corpid=" + corpid + "\"},{\"name\":\"我发起的\",\"sub_button\":[{\"type\":\"view\",\"name\":\"待进行的会议\",\"url\":\"http://www.vixware.cn/wechat/conferenceAssistantAction!goMyList.action?corpid=" + corpid + "\"},{\"type\":\"view\",\"name\":\"已结束的会议\",\"url\":\"http://www.vixware.cn/wechat/conferenceAssistantAction!goIssuerHistoryMettingList.action?corpid=" + corpid + "\"}]},{\"name\":\"我参与的\",\"sub_button\":[{\"type\":\"view\",\"name\":\"待进行的会议\",\"url\":\"http://www.vixware.cn/wechat/conferenceAssistantAction!goList.action?corpid=" + corpid + "\"},{\"type\":\"view\",\"name\":\"已结束的会议\",\"url\":\"http://www.vixware.cn/wechat/conferenceAssistantAction!goHistoryMettingList.action?corpid=" + corpid + "\"}]}]}";
												} else if ("4".equals(appid)) {
													dataMap = "{\"button\":[{\"name\":\"任务管理\",\"sub_button\":[{\"type\":\"view\",\"name\":\"草稿\",\"url\":\"http://www.vixware.cn/wechat/weChatTaskPlanAction!goMyDraftTaskList.action?corpid=" + corpid + "\"},{\"type\":\"view\",\"name\":\"新任务\",\"url\":\"http://www.vixware.cn/wechat/weChatTaskPlanAction!goWechatTask.action?corpid=" + corpid + "\"},{\"type\":\"view\",\"name\":\"待办任务\",\"url\":\"http://www.vixware.cn/wechat/weChatTaskPlanAction!goMyTaskList.action?corpid=" + corpid + "\"},{\"type\":\"view\",\"name\":\"下达的任务\",\"url\":\"http://www.vixware.cn/wechat/weChatTaskPlanAction!goTaskList.action?corpid=" + corpid + "\"},{\"type\":\"view\",\"name\":\"历史任务\",\"url\":\"http://www.vixware.cn/wechat/weChatTaskPlanAction!goFinishedTaskList.action?corpid=" + corpid + "\"}]},{\"name\":\"计划管理\",\"sub_button\":[{\"type\":\"view\",\"name\":\"新增计划\",\"url\":\"http://www.vixware.cn/wechat/weChatTaskPlanAction!newPlan.action?corpid=" + corpid + "\"},{\"type\":\"view\",\"name\":\"计划列表\",\"url\":\"http://www.vixware.cn/wechat/weChatTaskPlanAction!planList.action?corpid=" + corpid + "\"}]}]}";
												}
												String menuRequest = WxQyUtil.create(agentid, access_token, dataMap);
												System.out.println(menuRequest);
											}
										}
									}
								}
							}
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 企业号登录授权
	 */
	public void goLoginPage() {
		try {
			WxpQyWeixinSite site = vixntBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", api_qiye_corpid);
			String auth_code = getRequestParameter("auth_code");
			String request = WxQyUtil.getLoginInfo(auth_code, site.getQiyeAccessToken());
			System.out.println(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取应用套件令牌
	 */
	public void getSuiteToken() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			List<WxpQySuiteTicket> wxpSuiteTicketList = vixntBaseService.findAllByConditions(WxpQySuiteTicket.class, params);
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
								String request = WxQyUtil.getSuiteToken(wxpSuiteTicket.getSuiteId(), wxpSuiteTicket.getSuiteSecret(), wxpSuiteTicket.getSuiteTicket());
								if (StringUtils.isNotEmpty(request)) {
									JSONObject json = JSONObject.fromObject(request);
									// System.out.println(json);
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
										wxpSuiteTicket = vixntBaseService.mergeOriginal(wxpSuiteTicket);
										suiteAccessToken = wxpSuiteTicket.getSuiteAccessToken();
									}
								}
							} else {
								suiteAccessToken = wxpSuiteTicket.getSuiteAccessToken();
							}
							String request1 = WxQyUtil.getPreAuthCode(wxpSuiteTicket.getSuiteId(), suiteAccessToken);
							if (StringUtils.isNotEmpty(request1)) {
								JSONObject json1 = JSONObject.fromObject(request1);
								// System.out.println(json1);
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
										wxpSuiteTicket = vixntBaseService.mergeOriginal(wxpSuiteTicket);
										renderText(pre_auth_code);
									}
								}
							}

						} else {
							renderText(wxpSuiteTicket.getPreAuthCode());
						}
						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveWxpWeixinSite(WxpQyWeixinSite newwxpWeixinSite, WxpQyWeixinSite oldwxpWeixinSite) throws Exception {
		Map<?, ?> dataMap = WxQyUtil.reloadAccessToken(newwxpWeixinSite.getQiyeCorpId(), newwxpWeixinSite.getQiyeSecret());
		String newAccessToken = (String) dataMap.get("access_token");
		Integer expireSec = (Integer) dataMap.get("expires_in");

		long expireTime = System.currentTimeMillis() + 1000L * expireSec;
		newwxpWeixinSite.setQiyeAccessToken(newAccessToken);
		newwxpWeixinSite.setQiyeTokenExpireTime(new Date(expireTime));
		if (oldwxpWeixinSite == null) {
			initEntityBaseController.initEntityBaseAttribute(newwxpWeixinSite);
			newwxpWeixinSite = vixntBaseService.merge(newwxpWeixinSite);
		} else {
			oldwxpWeixinSite.setQiyeAccessToken(newAccessToken);
			newwxpWeixinSite = vixntBaseService.merge(oldwxpWeixinSite);
		}
	}

	public void deleteById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				wxpWeixinSite = vixntBaseService.findEntityById(WxpQyWeixinSite.class, id);
				if (null != wxpWeixinSite) {
					vixntBaseService.deleteByEntity(wxpWeixinSite);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public String goSearch() {
		return "goSearch";
	}

	/**
	 * @return the authCode
	 */
	public String getAuthCode() {
		return authCode;
	}

	/**
	 * @param authCode
	 *            the authCode to set
	 */
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the wxpWeixinSite
	 */
	public WxpQyWeixinSite getWxpWeixinSite() {
		return wxpWeixinSite;
	}

	/**
	 * @param wxpWeixinSite
	 *            the wxpWeixinSite to set
	 */
	public void setWxpWeixinSite(WxpQyWeixinSite wxpWeixinSite) {
		this.wxpWeixinSite = wxpWeixinSite;
	}

}
