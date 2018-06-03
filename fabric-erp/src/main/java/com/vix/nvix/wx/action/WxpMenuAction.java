package com.vix.nvix.wx.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.common.org.entity.Organization;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.nvix.wx.entity.WxpMenu;
import com.vix.nvix.wx.entity.WxpMenuUrlJump;
import com.vix.nvix.wx.entity.WxpWeixin;
import com.vix.nvix.wx.util.StrUtils;

@Controller
@Scope("prototype")
public class WxpMenuAction extends WxBaseAction {
	private static final long serialVersionUID = 1L;

	private String id;
	private String parentId;
	private WxpMenu wxpMenu;

	/** 获取列表数据 */
	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String title = getDecodeRequestParameter("title");
			if (StringUtils.isNotEmpty(title)) {
				params.put("title," + SearchCondition.ANYLIKE, title);
			}
			if (StringUtils.isNotEmpty(parentId)) {
				params.put("parent.id," + SearchCondition.EQUAL, parentId);
			}
			vixntBaseService.findPagerByHqlConditions(pager, WxpMenu.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				wxpMenu = vixntBaseService.findEntityById(WxpMenu.class, id);
			} else {
				wxpMenu = new WxpMenu();
				if (StringUtils.isNotEmpty(parentId)) {
					WxpMenu ic = vixntBaseService.findEntityById(WxpMenu.class, parentId);
					if (ic != null) {
						wxpMenu.setParent(ic);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (wxpMenu != null && null != wxpMenu.getId()) {
				isSave = false;
			}

			if (wxpMenu.getParent() == null || StringUtils.isEmpty(wxpMenu.getParent().getId())) {
				wxpMenu.setParent(null);
			}
			wxpMenu = vixntBaseService.merge(wxpMenu);
			saveOrUpdateMenu(wxpMenu);
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

	/**
	 * 
	 */

	/** 处理删除操作 */
	public void deleteById() {
		try {
			WxpMenu pb = vixntBaseService.findEntityById(WxpMenu.class, id);
			if (null != pb) {
				if (pb.getSubMenus() != null && pb.getSubMenus().size() > 0) {
					renderText("含有子菜单的菜单不能删除!");
				} else {
					vixntBaseService.deleteByEntity(pb);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<WxpMenu> wxpMenuList = new ArrayList<WxpMenu>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id)) {
				wxpMenuList = vixntBaseService.findAllSubEntity(WxpMenu.class, "parent.id", id, params);
			} else {
				wxpMenuList = vixntBaseService.findAllSubEntity(WxpMenu.class, "parent.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			strSb = loadAllWxpMenu(strSb, wxpMenuList);
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private StringBuilder loadAllWxpMenu(StringBuilder strSb, List<WxpMenu> wxpMenuList) throws Exception {
		for (int i = 0; i < wxpMenuList.size(); i++) {
			WxpMenu ic = wxpMenuList.get(i);
			if (ic.getSubMenus().size() > 0) {
				strSb.append("{id:\"");
				strSb.append(ic.getId());
				strSb.append("\",name:\"");
				strSb.append(ic.getTitle());
				strSb.append("\",open:false,isParent:true");
				strSb.append("}");
			} else {
				strSb.append("{id:\"");
				strSb.append(ic.getId());
				strSb.append("\",name:\"");
				strSb.append(ic.getTitle());
				strSb.append("\",open:false,isParent:false}");
			}
			if (i < wxpMenuList.size() - 1) {
				strSb.append(",");
			}
		}
		return strSb;
	}

	public boolean saveOrUpdateMenu(WxpMenu wxpMenu) {
		try {
			Organization organization = getOrganization();
			if (organization != null) {
				String appId = organization.getAppId();
				if (StringUtils.isNotEmpty(appId)) {
					if (wxpMenu != null) {
						if (StrUtils.objectIsNotNull(wxpMenu.getMenuViewType()) && !"menuAdd".equals(wxpMenu.getMenuViewType())) {
							WxpWeixin wxpWeixin = vixntBaseService.findEntityByAttribute(WxpWeixin.class, "appId", appId);
							WxpMenuUrlJump wxpMenuUrlJump = vixntBaseService.findEntityByAttribute(WxpMenuUrlJump.class, "urlCode", wxpMenu.getMenuViewType());
							if (wxpMenuUrlJump != null && wxpWeixin != null) {
								String domain = wxpWeixin.getDomain();
								if (domain.indexOf("http") == -1)
									domain = "http://" + domain + "/wx";
								StringBuilder sb = new StringBuilder(domain);
								sb.append(wxpMenuUrlJump.getJumpUrl());
								if (wxpMenuUrlJump.getJumpUrl().contains("?")) {
									sb.append("&app_id=");
								} else {
									sb.append("?app_id=");
								}
								sb.append(appId);
								wxpMenu.setMenuKey(sb.toString());
								wxpMenu.setWxpMenuUrlJump(wxpMenuUrlJump);
							}
						}

						if (StrUtils.objectIsNotNull(wxpMenu.getId())) {
							vixntBaseService.merge(wxpMenu);
						} else {
							if (wxpMenu.getParent() != null && StrUtils.objectIsNotNull(wxpMenu.getParent().getId())) {
								// 二级菜单
								Map<String, Object> params = new HashMap<String, Object>();
								params.put("parent.id," + SearchCondition.EQUAL, wxpMenu.getParent().getId());
								params.put("isActive," + SearchCondition.EQUAL, 1);
								params.put("appId," + SearchCondition.EQUAL, appId);
								if (StrUtils.objectIsNotNull(wxpMenu.getIsConditional()) && "0".equals(wxpMenu.getIsConditional())) {
									params.put("isConditional," + SearchCondition.EQUAL, "0");
								} else if (StrUtils.objectIsNotNull(wxpMenu.getIsConditional()) && "1".equals(wxpMenu.getIsConditional()) && wxpMenu.getWxpMenuConditional() != null && StrUtils.objectIsNotNull(wxpMenu.getWxpMenuConditional().getId())) {
									params.put("wxpMenuConditional.id," + SearchCondition.EQUAL, wxpMenu.getWxpMenuConditional().getId());
								}
								long countRet = vixntBaseService.findDataCountByHqlConditions(WxpMenu.class, params);
								if (countRet >= 5)
									wxpMenu.setIsActive(0);
							} else {
								// 一级菜单
								Map<String, Object> params = new HashMap<String, Object>();
								params.put("parent," + SearchCondition.IS, null);
								params.put("isActive," + SearchCondition.EQUAL, 1);
								params.put("appId," + SearchCondition.EQUAL, appId);
								if (StrUtils.objectIsNotNull(wxpMenu.getIsConditional()) && "0".equals(wxpMenu.getIsConditional())) {
									params.put("isConditional," + SearchCondition.EQUAL, "0");
								} else if (StrUtils.objectIsNotNull(wxpMenu.getIsConditional()) && "1".equals(wxpMenu.getIsConditional()) && wxpMenu.getWxpMenuConditional() != null && StrUtils.objectIsNotNull(wxpMenu.getWxpMenuConditional().getId())) {
									params.put("wxpMenuConditional.id," + SearchCondition.EQUAL, wxpMenu.getWxpMenuConditional().getId());
								}
								long countRet = vixntBaseService.findDataCountByHqlConditions(WxpMenu.class, params);
								if (countRet >= 3)
									wxpMenu.setIsActive(0);
								// 默认一级菜单menuType是menu
								if (wxpMenu.getMenuType() == null)
									wxpMenu.setMenuType(WxpMenu.menu_type_menu);
							}
							// menukey在创建时由系统生成，不可修改
							if (WxpMenu.menu_type_click.equals(wxpMenu.getMenuType())) {
								wxpMenu.setMenuKey(VixUUID.getUUID());
							}
							wxpMenu.setAppId(appId);
							vixntBaseService.merge(wxpMenu);
						}
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public WxpMenu getWxpMenu() {
		return wxpMenu;
	}

	public void setWxpMenu(WxpMenu wxpMenu) {
		this.wxpMenu = wxpMenu;
	}

}