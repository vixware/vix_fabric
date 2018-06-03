package com.vix.ebusiness.option.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.ebusiness.option.entity.OnLineStoreParameters;

@Controller
@Scope("prototype")
public class ShopOptionAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	/**
	 * 网店参数
	 */
	private OnLineStoreParameters onLineStoreParameters;

	@Override
	public String goList() {
		try {
			if (SecurityUtil.getCurrentUserTenantId() != null) {
				onLineStoreParameters = baseHibernateService.findEntityByAttribute(OnLineStoreParameters.class, "tenantId", SecurityUtil.getCurrentUserTenantId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (SecurityUtil.getCurrentUserTenantId() != null) {
				onLineStoreParameters = baseHibernateService.findEntityByAttribute(OnLineStoreParameters.class, "tenantId", SecurityUtil.getCurrentUserTenantId());
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
			if (null != onLineStoreParameters.getId() && !"".equals(onLineStoreParameters.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(onLineStoreParameters);
			onLineStoreParameters = baseHibernateService.merge(onLineStoreParameters);
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

	public OnLineStoreParameters getOnLineStoreParameters() {
		return onLineStoreParameters;
	}

	public void setOnLineStoreParameters(OnLineStoreParameters onLineStoreParameters) {
		this.onLineStoreParameters = onLineStoreParameters;
	}

}
