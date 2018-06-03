package com.vix.nvix.wx.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.vix.common.security.entity.UserAccount;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.utils.StrUtils;
import com.vix.nvix.wx.entity.WxpMenu;
import com.vix.nvix.wx.entity.WxpMenuUrlJump;
import com.vix.nvix.wx.entity.WxpWeixin;
import com.vix.nvix.wx.service.IWxpMenuService;
import com.vix.nvix.wx.util.PadUtils;

@Service("menuService")
public class WxpMenuServiceImpl extends BaseHibernateServiceImpl implements IWxpMenuService {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean saveOrUpdateMenu(UserAccount optUser, WxpMenu menu) {
		try {
			if (menu != null) {
				if (StrUtils.objectIsNotNull(menu.getMenuViewType()) && !"menuAdd".equals(menu.getMenuViewType())) {
					WxpWeixin wxpWeixin = this.findEntityByAttribute(WxpWeixin.class, "appId", optUser.getAppId());
					WxpMenuUrlJump wxpMenuUrlJump = this.findEntityByAttribute(WxpMenuUrlJump.class, "urlCode",
							menu.getMenuViewType());
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
						sb.append(optUser.getAppId());
						menu.setMenuKey(sb.toString());
						menu.setWxpMenuUrlJump(wxpMenuUrlJump);
					}
				}
				if (StrUtils.objectIsNotNull(menu.getId())) {
					this.merge(menu);
				} else {
					// menu.setWxId(optUser.getWxId());
					if (menu.getParent() != null && StrUtils.objectIsNotNull(menu.getParent().getId())) {
						// 二级菜单
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("parent.id," + SearchCondition.EQUAL, menu.getParent().getId());
						params.put("isActive," + SearchCondition.EQUAL, 1);
						params.put("appId," + SearchCondition.EQUAL, optUser.getAppId());
						if (StrUtils.objectIsNotNull(menu.getIsConditional()) && "0".equals(menu.getIsConditional())) {
							params.put("isConditional," + SearchCondition.EQUAL, "0");
						} else if (StrUtils.objectIsNotNull(menu.getIsConditional())
								&& "1".equals(menu.getIsConditional()) && menu.getWxpMenuConditional() != null
								&& StrUtils.objectIsNotNull(menu.getWxpMenuConditional().getId())) {
							params.put("wxpMenuConditional.id," + SearchCondition.EQUAL,
									menu.getWxpMenuConditional().getId());
						}
						long countRet = this.findDataCountByHqlConditions(WxpMenu.class, params);
						if (countRet >= 5)
							menu.setIsActive(0);
					} else {
						// 一级菜单
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("parent," + SearchCondition.IS, null);
						params.put("isActive," + SearchCondition.EQUAL, 1);
						params.put("appId," + SearchCondition.EQUAL, optUser.getAppId());
						if (StrUtils.objectIsNotNull(menu.getIsConditional()) && "0".equals(menu.getIsConditional())) {
							params.put("isConditional," + SearchCondition.EQUAL, "0");
						} else if (StrUtils.objectIsNotNull(menu.getIsConditional())
								&& "1".equals(menu.getIsConditional()) && menu.getWxpMenuConditional() != null
								&& StrUtils.objectIsNotNull(menu.getWxpMenuConditional().getId())) {
							params.put("wxpMenuConditional.id," + SearchCondition.EQUAL,
									menu.getWxpMenuConditional().getId());
						}
						long countRet = this.findDataCountByHqlConditions(WxpMenu.class, params);
						if (countRet >= 3)
							menu.setIsActive(0);

						// 默认一级菜单menuType是menu
						if (menu.getMenuType() == null)
							menu.setMenuType(WxpMenu.menu_type_menu);
					}

					// menukey在创建时由系统生成，不可修改
					if (WxpMenu.menu_type_click.equals(menu.getMenuType())) {
						menu.setMenuKey(PadUtils.genUUID());
					}
					menu.setAppId(optUser.getAppId());
					this.merge(menu);
				}

				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteMenu(UserAccount optUser, String menuId) {
		try {
			if (StrUtils.objectIsNotNull(menuId)) {
				/*
				 * WxpMenu delMenu = this.getMenuService().findEntityById(WxpMenu.class,
				 * this.menuId); if(delMenu!=null){ delMenu.setIsDelete(1);
				 * this.getMenuService().save(delMenu); //暂时不删除子菜单，界面不显示 }
				 */

				WxpMenu delMenu = this.findEntityByAttribute(WxpMenu.class, "id", menuId, optUser.getAppId());
				if (delMenu != null && delMenu.getAppId().equals(optUser.getAppId())) {
					// 删除菜单
					this.deleteByEntity(delMenu);
					// this.deleteById(WxpMenu.class, menuId);

					// 删除子菜单,set级联删除
					// this.deleteByAttribute(WxpMenu.class, "parent.id", menuId);

					// 删除关联的replyRule,set级联删除

					return true;
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
	public List<List<WxpMenu>> listActiveMenu(UserAccount optUser) {
		List<List<WxpMenu>> retList = new ArrayList<List<WxpMenu>>();

		StringBuilder sbHql = new StringBuilder("from WxpMenu hentity where hentity.appId='");
		sbHql.append(optUser.getAppId())
				.append("' and hentity.isActive=1 and hentity.menuType='click' and hentity.parent.isActive=1 ")
				.append(" order by hentity.parent.sortOrder asc,hentity.sortOrder asc");

		try {
			List<WxpMenu> mList = this.findAllEntityByHql(sbHql, null);
			if (mList != null && mList.size() > 0) {
				String tempPid = "0";
				List<WxpMenu> addList = null;
				for (WxpMenu menu : mList) {
					String pid = menu.getParent() == null ? menu.getId() : menu.getParent().getId();
					if (tempPid != pid) {
						addList = new ArrayList<WxpMenu>();
						retList.add(addList);
						tempPid = pid;
					}

					addList.add(menu);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retList;
	}
}
