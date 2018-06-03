package com.vix.nvix.wx.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.security.util.SecurityUtil;
import com.vix.nvix.wx.entity.WxpWeixinSite;
import com.vix.wechat.util.WxApiUtil;

@Controller
@Scope("prototype")
public class WxpWeixinSiteAction extends WxBaseAction {
	private static final long serialVersionUID = 1L;

	private String id;
	private WxpWeixinSite wxpWeixinSite;

	@Override
	public String goList() {
		try {
			String tenantId = SecurityUtil.getCurrentUserTenantId();
			wxpWeixinSite = vixntBaseService.findEntityByAttribute(WxpWeixinSite.class, "tenantId", tenantId);
			if (wxpWeixinSite != null) {
			} else {
				wxpWeixinSite = new WxpWeixinSite();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goList";
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (wxpWeixinSite != null && StringUtils.isNotEmpty(wxpWeixinSite.getId())) {
				isSave = false;
			} else {
				wxpWeixinSite = vixntBaseService.merge(wxpWeixinSite);
			}
			update(wxpWeixinSite);
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
	}

	// 更新AccessToken
	private WxpWeixinSite update(WxpWeixinSite wxpWeixinSite) throws Exception {
		if (wxpWeixinSite != null && wxpWeixinSite.needReloadQiyeAccessToken()) {
			if (StringUtils.isNotEmpty(wxpWeixinSite.getAppId()) && StringUtils.isNotEmpty(wxpWeixinSite.getSecret())) {
				Map<?, ?> dataMap = WxApiUtil.reloadAccessToken(wxpWeixinSite.getAppId(), wxpWeixinSite.getSecret());
				if (dataMap != null) {
					if (dataMap.containsKey("access_token")) {
						String newAccessToken = (String) dataMap.get("access_token");
						wxpWeixinSite.setAccessToken(newAccessToken);
					}
					if (dataMap.containsKey("expires_in")) {
						Integer expireSec = (Integer) dataMap.get("expires_in");
						long expireTime = System.currentTimeMillis() + 1000L * expireSec;
						wxpWeixinSite.setExpiresInTime(new Date(expireTime));
					}
					wxpWeixinSite = vixntBaseService.merge(wxpWeixinSite);
				}
			}
		}
		return wxpWeixinSite;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public WxpWeixinSite getWxpWeixinSite() {
		return wxpWeixinSite;
	}

	@Override
	public void setWxpWeixinSite(WxpWeixinSite wxpWeixinSite) {
		this.wxpWeixinSite = wxpWeixinSite;
	}

}