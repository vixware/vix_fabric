package com.vix.wechat.department.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.id.VixUUID;
import com.vix.common.security.util.SecurityUtil;
import com.vix.wechat.base.controller.WechatBaseController;
import com.vix.wechat.base.entity.WxpQyDepartment;
import com.vix.wechat.base.entity.WxpQyWeixinSite;
import com.vix.wechat.base.service.IWechatBaseService;
import com.vix.wechat.base.util.WxQyUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * com.vix.wechat.department.controller.WechatDepartmentController
 *
 * @author bjitzhang
 *
 * @date 2015年12月29日
 */
@Controller("wechatDepartmentController")
@Scope("prototype")
public class WechatDepartmentController extends WechatBaseController {
	/**
	 * 
	 */
	@Autowired
	private IWechatBaseService wechatBaseService;
	/**
	 * 初始化基础数据
	 */
	@Autowired
	public InitEntityBaseController initEntityBaseController;

	/**
	 * 
	 * @param parentId
	 *            父部门ID(整型)
	 * @param name
	 *            名称
	 * @param sortOrder
	 *            序号
	 * @throws Exception
	 */
	public void saveOrUpdateWxpQyDepartment(String parentId, String name, int sortOrder) throws Exception {
		WxpQyDepartment wxpQyDepartment = new WxpQyDepartment();
		wxpQyDepartment.setParentId(parentId);
		wxpQyDepartment.setName(name);
		wxpQyDepartment.setSortOrder(sortOrder);
		// 创建部门
		initEntityBaseController.initEntityBaseAttribute(wxpQyDepartment);
		wxpQyDepartment.setCode(VixUUID.getUUID());
		WxpQyWeixinSite wxpWeixinSite = wechatBaseService.findEntityByAttribute(WxpQyWeixinSite.class, "tenantId", SecurityUtil.getCurrentUserTenantId());
		if (wxpWeixinSite != null && wxpWeixinSite.getQiyeAccessToken() != null) {
			String returnjson = WxQyUtil.departmentCreate(wxpQyDepartment, wxpWeixinSite.getQiyeAccessToken());
			if (returnjson != null) {
				JSONObject json = JSONObject.fromObject(returnjson);
				if (json.has("errcode") && "0".equals(json.getString("errcode"))) {
					if (json.has("id")) {
						wxpQyDepartment.setSyncId(json.getLong("id"));
					}
					wechatBaseService.merge(wxpQyDepartment);
				} else if (json.has("errcode") && "42001".equals(json.getString("errcode"))) {
					saveWxpWeixinSite(wxpWeixinSite);
					saveOrUpdateWxpQyDepartment(parentId, name, sortOrder);
				}
			}
		}
	}

	// 获取部门列表
	// https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=ACCESS_TOKEN&id=ID
	public void getDepartmentList(String departmentId, String accessToken) {
		if (StringUtils.isEmpty(departmentId)) {
			return;
		}
		String departmentListJson = WxQyUtil.departmentGetById(departmentId, accessToken);
		if (departmentListJson != null) {
			JSONObject json = JSONObject.fromObject(departmentListJson);

			System.out.println(json);
		}

	}

	public void deleteWxpQyDepartment(String departmentId, String accessToken) {
		// 跟进部门ID获取子部门
		String departmentListJson = WxQyUtil.departmentGetById(departmentId, accessToken);
		if (departmentListJson != null) {
			JSONObject json = JSONObject.fromObject(departmentListJson);
			if (json.has("department")) {
				JSONArray jsonarray = JSONArray.fromObject(json.getString("department"));

				if (jsonarray != null && jsonarray.size() > 1) {
					return;
				}

			}
		}
		String qyContactsListJson = WxQyUtil.contactGetByDepartmentId(departmentId, accessToken);
		if (qyContactsListJson != null) {
			JSONObject json = JSONObject.fromObject(qyContactsListJson);
			if (json.has("userlist")) {
				JSONArray jsonarray = JSONArray.fromObject(json.getString("userlist"));
				if (jsonarray != null && jsonarray.size() > 0) {
					return;
				}
			}
		}
		// 删除部门
		String returnjson = WxQyUtil.departmentDeleteById(departmentId, accessToken);
		JSONObject json = JSONObject.fromObject(returnjson);
		if (json.has("errcode") && "0".equals(json.getString("errcode"))) {
			System.out.println("删除成功");
		}
	}

	public static void main(String[] args) {

		String departmentListJson = WxQyUtil.departmentGetById("1", "MgH5sIAQSvf4DiHJ0GdhB2jEvsAqhXCNxDrqZyn-0uutlzSzrHPJFN72GZoXPovCdog0Mrf8sVlSy1mYE8Z5qA");
		if (departmentListJson != null) {
			JSONObject json = JSONObject.fromObject(departmentListJson);
			if (json.has("department")) {
				JSONArray jsonarray = JSONArray.fromObject(json.getString("department"));
				if (jsonarray != null) {
					for (Object object : jsonarray) {
						System.out.println(object);
					}
				}
			}
		}
	}
}
