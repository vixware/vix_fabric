package com.vix.nvix.warehouse.action;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.security.util.SecurityUtil;
import com.vix.inventory.option.entity.InventoryParameters;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 库存设置
 * 
 * @类全名 com.vix.nvix.warehouse.action.VixntInventorySetAction
 * 
 * @author zhanghaibing
 *
 * @date 2017年11月30日
 */

@Controller
@Scope("prototype")
public class VixntInventorySetAction extends VixntBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private InventoryParameters inventoryParameters;

	@Override
	public String goList() {
		try {
			inventoryParameters = vixntBaseService.findEntityByAttribute(InventoryParameters.class, "tenantId", SecurityUtil.getCurrentUserTenantId());
			if (inventoryParameters != null) {
			} else {
				inventoryParameters = new InventoryParameters();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(inventoryParameters.getId())) {
				isSave = false;
			}
			inventoryParameters.setCreateTime(new Date());
			initEntityBaseController.initEntityBaseAttribute(inventoryParameters);
			inventoryParameters = vixntBaseService.merge(inventoryParameters);
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

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public InventoryParameters getInventoryParameters() {
		return inventoryParameters;
	}

	public void setInventoryParameters(InventoryParameters inventoryParameters) {
		this.inventoryParameters = inventoryParameters;
	}

}