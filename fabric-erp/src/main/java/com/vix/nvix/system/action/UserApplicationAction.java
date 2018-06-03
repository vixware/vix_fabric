package com.vix.nvix.system.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.org.entity.UserApplication;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 用户信息管理
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.nvix.system.action.UserApplicationAction
 *
 * @date 2018年1月11日
 */
@Controller
@Scope("prototype")
public class UserApplicationAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	private String id;
	private UserApplication userApplication;
	public void goSingleList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			pager = vixntBaseService.findPagerByHqlConditions(pager, UserApplication.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				userApplication = vixntBaseService.findEntityByAttribute(UserApplication.class, "id", id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
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
	 * @return the userApplication
	 */
	public UserApplication getUserApplication() {
		return userApplication;
	}
	/**
	 * @param userApplication
	 *            the userApplication to set
	 */
	public void setUserApplication(UserApplication userApplication) {
		this.userApplication = userApplication;
	}

}