package com.vix.nvix.wx.service;

import java.util.List;

import com.vix.common.security.entity.UserAccount;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.nvix.wx.entity.WxpMenu;

public interface IWxpMenuService extends IBaseHibernateService {

	boolean saveOrUpdateMenu(UserAccount optUser, WxpMenu menu);

	boolean deleteMenu(UserAccount optUser, String menuId);

	List<List<WxpMenu>> listActiveMenu(UserAccount optUser);
}
