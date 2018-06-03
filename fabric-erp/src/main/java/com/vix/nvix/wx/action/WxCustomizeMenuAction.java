package com.vix.nvix.wx.action;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.SortSet;
import com.vix.core.utils.StrUtils;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.wx.entity.WxpMenu;
import com.vix.nvix.wx.entity.WxpReply;

@Controller
@Scope("prototype")
public class WxCustomizeMenuAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private List<WxpMenu> firstWxpMenuList;
	private List<WxpMenu> secondWxpMenuList;
	private Map<String, List<WxpMenu>> wxpMenuMap = new HashMap<String, List<WxpMenu>>();
	private WxpMenu wxpMenu;
	private WxpReply wxpReply;
	private String wxpMenuId;
	private String wxpMenuName;
	private String id;
	private String type;
	private String parentId;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("parent.id," + SearchCondition.IS, null);
			firstWxpMenuList = vixntBaseService.findAllDataByConditions(WxpMenu.class, params);
			SortSet ss = new SortSet("sortOrder");
			Collections.sort(firstWxpMenuList, ss);
			if (firstWxpMenuList != null && firstWxpMenuList.size() > 0) {
				wxpMenu = firstWxpMenuList.get(0);
				/*
				 * for (WxpMenu wxpMenu1 : firstWxpMenuList) { if(wxpMenu1 !=
				 * null){ params.put("parent.id," + SearchCondition.EQUAL,
				 * wxpMenu1.getId()); secondWxpMenuList =
				 * vixntBaseService.findAllDataByConditions(WxpMenu.class,
				 * params); Collections.sort(secondWxpMenuList,ss);
				 * wxpMenuMap.put(wxpMenu1.getId().trim(), secondWxpMenuList); }
				 * }
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	public String wxpMenuListContent() {
		try {
			if (StrUtils.isNotEmpty(id)) {
				wxpMenu = vixntBaseService.findEntityById(WxpMenu.class, id);
				if (wxpMenu != null && wxpMenu.getWxpReply() != null && StrUtils.isNotEmpty(wxpMenu.getWxpReply().getId())) {
					wxpReply = vixntBaseService.findEntityById(WxpReply.class, wxpMenu.getWxpReply().getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "wxpMenuListContent";
	}

	@SuppressWarnings("unused")
	public void saveOrUpdate() {
		boolean isAdd;
		try {
			isAdd = false;
			if (wxpMenu != null && StrUtils.isNotEmpty(wxpMenu.getId())) {
				isAdd = true;
				wxpMenu.setUpdateTime(new Date());
			} else {
				wxpMenu.setCreateTime(new Date());
				wxpMenu.setUpdateTime(new Date());
			}
			String[] attrArray = { "parent", "wxpReply" };
			checkEntityNullValue(wxpMenu, attrArray);
			wxpMenu = vixntBaseService.merge(wxpMenu);
			renderText("1:保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:保存失败");
		}
	}

	public void wxpMenuDelete() {
		try {
			if (StrUtils.isNotEmpty(id)) {
				wxpMenu = vixntBaseService.findEntityById(WxpMenu.class, id);
				if (wxpMenu != null) {
					vixntBaseService.deleteByEntity(wxpMenu);
					renderText("1:删除成功");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:删除失败");
		}
	}

	/*public void wxpMenuPost() {
		String msg;
		try {
			msg = this.wxpMenuService.wxpMenuPost(getCurrentWxpWeixin());
			if (!(StrUtils.objectIsNotNull(msg)))
				return;
			reloadWeixinReplyMap(getCurrentWxpWeixin().getAppId());
			addWxpOperateRecord("提交自定义菜单到腾讯成功", "wxpMenu", null, new String[0]);
			renderText(msg, new String[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void wxpMenuAllDelete() {
		String msg;
		try {
			msg = this.wxpMenuService.wxpMenuAllDelete(getCurrentWxpWeixin());
			if (!(StrUtils.objectIsNotNull(msg)))
				return;
			reloadWeixinReplyMap(getCurrentWxpWeixin().getAppId());
			addWxpOperateRecord("删除全部微信菜单成功", "wxpMenu", null, new String[0]);
			renderText(msg, new String[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	public List<WxpMenu> getFirstWxpMenuList() {
		return firstWxpMenuList;
	}

	public void setFirstWxpMenuList(List<WxpMenu> firstWxpMenuList) {
		this.firstWxpMenuList = firstWxpMenuList;
	}

	public List<WxpMenu> getSecondWxpMenuList() {
		return secondWxpMenuList;
	}

	public void setSecondWxpMenuList(List<WxpMenu> secondWxpMenuList) {
		this.secondWxpMenuList = secondWxpMenuList;
	}

	public Map<String, List<WxpMenu>> getWxpMenuMap() {
		return wxpMenuMap;
	}

	public void setWxpMenuMap(Map<String, List<WxpMenu>> wxpMenuMap) {
		this.wxpMenuMap = wxpMenuMap;
	}

	public WxpMenu getWxpMenu() {
		return wxpMenu;
	}

	public void setWxpMenu(WxpMenu wxpMenu) {
		this.wxpMenu = wxpMenu;
	}

	public String getWxpMenuId() {
		return wxpMenuId;
	}

	public void setWxpMenuId(String wxpMenuId) {
		this.wxpMenuId = wxpMenuId;
	}

	public String getWxpMenuName() {
		return wxpMenuName;
	}

	public void setWxpMenuName(String wxpMenuName) {
		this.wxpMenuName = wxpMenuName;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public WxpReply getWxpReply() {
		return wxpReply;
	}

	public void setWxpReply(WxpReply wxpReply) {
		this.wxpReply = wxpReply;
	}

}
