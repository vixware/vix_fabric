package com.vix.wechat.enterprisewechat.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.wechat.base.action.WechatBaseAction;
import com.vix.wechat.base.entity.WxpQyWeixinSite;
import com.vix.wechat.base.util.WxQyUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class EnterpriseWechatAccountAction extends WechatBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private WxpQyWeixinSite wxpWeixinSite;

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			pager = wechatBaseService.findPagerByHqlConditions(pager, WxpQyWeixinSite.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				wxpWeixinSite = wechatBaseService.findEntityById(WxpQyWeixinSite.class, id);
			} else {
				wxpWeixinSite = new WxpQyWeixinSite();
				wxpWeixinSite.setCode(VixUUID.createCode(10));
				Employee employee = getEmployee();
				if (employee != null) {
					wxpWeixinSite.setCreator(employee.getName());
				} else {
					wxpWeixinSite.setCreator(SecurityUtil.getCurrentUserName());
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
			if (wxpWeixinSite != null && StringUtils.isNotEmpty(wxpWeixinSite.getId()) && !"0".equals(wxpWeixinSite.getId())) {
				isSave = false;
			}
			WxpQyWeixinSite w = null;
			if (wxpWeixinSite.getQiyeCorpId() != null) {
				w = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", wxpWeixinSite.getQiyeCorpId());
			}
			saveWxpWeixinSite(wxpWeixinSite, w);

			// 更新成员
			String userList = WxQyUtil.contactGetByDepartmentId("1", w.getQiyeAccessToken());
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
						if (w != null) {
							employee.setTenantId(w.getTenantId());
							employee.setCompanyInnerCode(w.getCompanyInnerCode());
						}
						employee.setCreateTime(new Date());
						wechatBaseService.mergeOriginal(employee);
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

	public void saveWxpWeixinSite(WxpQyWeixinSite newwxpWeixinSite, WxpQyWeixinSite oldwxpWeixinSite) throws Exception {
		Map<?, ?> dataMap = WxQyUtil.reloadAccessToken(newwxpWeixinSite.getQiyeCorpId(), newwxpWeixinSite.getQiyeSecret());
		String newAccessToken = (String) dataMap.get("access_token");
		Integer expireSec = (Integer) dataMap.get("expires_in");

		long expireTime = System.currentTimeMillis() + 1000L * expireSec;
		newwxpWeixinSite.setQiyeAccessToken(newAccessToken);
		newwxpWeixinSite.setQiyeTokenExpireTime(new Date(expireTime));
		if (oldwxpWeixinSite == null) {
			// initEntityBaseController.initEntityBaseAttribute(newwxpWeixinSite);
			wechatBaseService.mergeOriginal(newwxpWeixinSite);
		} else {
			oldwxpWeixinSite.setQiyeAccessToken(newAccessToken);
			wechatBaseService.mergeOriginal(oldwxpWeixinSite);
		}
	}

	public String goSearch() {
		return "goSearch";
	}

	public String getId() {
		return id;
	}

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
