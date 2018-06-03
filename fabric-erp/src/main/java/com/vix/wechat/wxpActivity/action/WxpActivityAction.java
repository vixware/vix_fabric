package com.vix.wechat.wxpActivity.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.wechat.base.action.WechatBaseAction;
import com.vix.wechat.base.entity.WxpActivity;
import com.vix.wechat.base.entity.WxpQyPicture;
import com.vix.wechat.base.entity.WxpQyWeixinSite;

@Controller
@Scope("prototype")
public class WxpActivityAction extends WechatBaseAction {

	private static final long serialVersionUID = 1L;
	/**
	 * 活动
	 */
	private List<WxpActivity> wxpActivityList;
	/**
	 * 活动
	 */
	private WxpActivity wxpActivity;
	private String id;

	//公告通知
	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tenantId," + SearchCondition.EQUAL, api_qiye_tenantid);
			wxpActivityList = wechatBaseService.findAllDataByConditions(WxpActivity.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "goList";
	}

	/**
	 * 发布公告
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {

		try {
			if (StringUtils.isNotEmpty(id)) {
				wxpActivity = wechatBaseService.findEntityById(WxpActivity.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}

	/**
	 * 显示公告详情
	 * 
	 * @return
	 */
	public String goShowWxpActivity() {

		try {
			if (StringUtils.isNotEmpty(id)) {
				wxpActivity = wechatBaseService.findEntityById(WxpActivity.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowWxpActivity";
	}

	public String goShowMap() {
		return "goShowMap";
	}

	/**
	 * 保存公告
	 */
	public void saveOrUpdate() {
		try {
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "tenantId", api_qiye_tenantid);
			//WxQyUtil.verification(wechatNo, redirect_uri);
			wxpActivity.setTenantId(site.getTenantId());
			wxpActivity.setCompanyInnerCode(site.getCompanyInnerCode());
			wxpActivity.setCreateTime(new Date());
			wxpActivity.setUpdateTime(new Date());
			wxpActivity = wechatBaseService.mergeOriginal(wxpActivity);

			// 保存图片
			String[] savePathAndName = saveUploadPic();
			if (savePathAndName != null && savePathAndName.length == 2) {
				WxpQyPicture wxpQyPicture = new WxpQyPicture();
				wxpQyPicture.setPictureUrl("/img/wechat/" + savePathAndName[1].toString());
				wxpQyPicture.setWxpActivity(wxpActivity);
				wxpQyPicture = wechatBaseService.mergeOriginal(wxpQyPicture);
				wxpActivity.setFirstPictureUrl(wxpQyPicture.getPictureUrl());
			}
			wxpActivity = wechatBaseService.mergeOriginal(wxpActivity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the wxpActivityList
	 */
	public List<WxpActivity> getWxpActivityList() {
		return wxpActivityList;
	}

	/**
	 * @param wxpActivityList
	 *            the wxpActivityList to set
	 */
	public void setWxpActivityList(List<WxpActivity> wxpActivityList) {
		this.wxpActivityList = wxpActivityList;
	}

	/**
	 * @return the wxpActivity
	 */
	public WxpActivity getWxpActivity() {
		return wxpActivity;
	}

	/**
	 * @param wxpActivity
	 *            the wxpActivity to set
	 */
	public void setWxpActivity(WxpActivity wxpActivity) {
		this.wxpActivity = wxpActivity;
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

}
