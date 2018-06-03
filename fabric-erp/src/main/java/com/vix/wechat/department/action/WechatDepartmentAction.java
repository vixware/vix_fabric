package com.vix.wechat.department.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.web.Pager;
import com.vix.wechat.base.action.WechatBaseAction;
import com.vix.wechat.base.entity.WxpQyDepartment;
import com.vix.wechat.base.entity.WxpQyWeixinSite;
import com.vix.wechat.base.service.IWechatBaseService;
import com.vix.wechat.base.util.WxQyUtil;

import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class WechatDepartmentAction extends WechatBaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IWechatBaseService wechatBaseService;
	private String id;
	private WxpQyDepartment wxpQyDepartment;
	/** 微信部门管理 */
	private List<WxpQyDepartment> wxpQyDepartmentList = new ArrayList<WxpQyDepartment>();

	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			wxpQyDepartmentList = wechatBaseService.findAllDataByConditions(WxpQyDepartment.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {

			WxpQyWeixinSite wxpWeixinSite = wechatBaseService.findEntityByAttribute(WxpQyWeixinSite.class, "tenantId", SecurityUtil.getCurrentUserTenantId());
			if (wxpWeixinSite != null && wxpWeixinSite.getQiyeAccessToken() != null) {
				String departmentList = WxQyUtil.departmentList(wxpWeixinSite.getQiyeAccessToken());
				System.out.println(departmentList);
			}

			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			pager = wechatBaseService.findPagerByHqlConditions(pager, WxpQyDepartment.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				wxpQyDepartment = wechatBaseService.findEntityById(WxpQyDepartment.class, id);
			} else {
				wxpQyDepartment = new WxpQyDepartment();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (wxpQyDepartment != null && StringUtils.isNotEmpty(wxpQyDepartment.getId()) && !"0".equals(wxpQyDepartment.getId())) {
				isSave = false;
			}
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
					}
				}
			}
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
	 * @return the wxpQyDepartment
	 */
	public WxpQyDepartment getWxpQyDepartment() {
		return wxpQyDepartment;
	}

	/**
	 * @param wxpQyDepartment
	 *            the wxpQyDepartment to set
	 */
	public void setWxpQyDepartment(WxpQyDepartment wxpQyDepartment) {
		this.wxpQyDepartment = wxpQyDepartment;
	}

	/**
	 * @return the wxpQyDepartmentList
	 */
	public List<WxpQyDepartment> getWxpQyDepartmentList() {
		return wxpQyDepartmentList;
	}

	/**
	 * @param wxpQyDepartmentList
	 *            the wxpQyDepartmentList to set
	 */
	public void setWxpQyDepartmentList(List<WxpQyDepartment> wxpQyDepartmentList) {
		this.wxpQyDepartmentList = wxpQyDepartmentList;
	}

}
