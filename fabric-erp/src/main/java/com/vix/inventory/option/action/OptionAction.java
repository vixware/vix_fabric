package com.vix.inventory.option.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.inventory.option.entity.InventoryParameters;

@Controller
@Scope("prototype")
public class OptionAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(OptionAction.class);
	private Long id;
	private String ids;
	private String pageNo;

	/**
	 * 库存参数
	 */
	private InventoryParameters inventoryParameters;

	@Override
	public String goList() {
		try {
			if (SecurityUtil.getCurrentUserTenantId() != null) {
				inventoryParameters = baseHibernateService.findEntityByAttribute(InventoryParameters.class, "tenantId", SecurityUtil.getCurrentUserTenantId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 数据字典
	 * 
	 * @return
	 */
	public String goSetting() {
		return "goSetting";
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (SecurityUtil.getCurrentUserTenantId() != null) {
				inventoryParameters = baseHibernateService.findEntityByAttribute(InventoryParameters.class, "tenantId", SecurityUtil.getCurrentUserTenantId());
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
			if (StringUtils.isNotEmpty(inventoryParameters.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(inventoryParameters);
			inventoryParameters = baseHibernateService.merge(inventoryParameters);
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public InventoryParameters getInventoryParameters() {
		return inventoryParameters;
	}

	public void setInventoryParameters(InventoryParameters inventoryParameters) {
		this.inventoryParameters = inventoryParameters;
	}

}
