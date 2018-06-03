package com.vix.drp.installation.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.installation.controller.InstallationController;
import com.vix.drp.installation.entity.InstallationOrder;

@Controller
@Scope("prototype")
public class InstallationAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private InstallationController installationController;
	private String id;
	private InstallationOrder installationOrder;
	private List<InstallationOrder> installationOrderList;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = installationController.findPagerByHqlConditions(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				installationOrder = installationController.doListInstallationOrderById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (installationOrder != null && StringUtils.isNotEmpty(installationOrder.getId()) && !"0".equals(installationOrder.getId())) {
				isSave = false;
			}
			installationOrder.setIsTemp("");
			installationOrder = installationController.doSaveInstallationOrder(installationOrder);
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

	/** 处理删除操作 */
	public String deleteById() {
		try {
			InstallationOrder installationOrder = installationController.doListInstallationOrderById(id);
			if (null != installationOrder) {
				installationController.doDeleteByEntity(installationOrder);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public InstallationOrder getInstallationOrder() {
		return installationOrder;
	}

	public void setInstallationOrder(InstallationOrder installationOrder) {
		this.installationOrder = installationOrder;
	}

	public List<InstallationOrder> getInstallationOrderList() {
		return installationOrderList;
	}

	public void setInstallationOrderList(List<InstallationOrder> installationOrderList) {
		this.installationOrderList = installationOrderList;
	}

}
