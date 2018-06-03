package com.vix.nvix.system.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.security.util.SecurityUtil;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.entity.VixEcSet;

@Controller
@Scope("prototype")
public class VixntEcSetAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private VixEcSet vixEcSet;

	/** 跳转至用户修改页面 */
	@Override
	public String goList() {
		try {
			String tenantId = SecurityUtil.getCurrentUserTenantId();
			if (StringUtils.isNotEmpty(tenantId)) {
				vixEcSet = vixntBaseService.findEntityByAttribute(VixEcSet.class, "tenantId", tenantId);
				if (vixEcSet != null) {
				} else {
					vixEcSet = new VixEcSet();
					vixEcSet.setIsUploadToEc("1");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 新增修改编码规则 */
	public void saveOrUpdate() {

		boolean isSave = true;
		try {
			initEntityBaseController.initEntityBaseAttribute(vixEcSet);
			vixEcSet = vixntBaseService.merge(vixEcSet);
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
	 * @return the vixEcSet
	 */
	public VixEcSet getVixEcSet() {
		return vixEcSet;
	}

	/**
	 * @param vixEcSet
	 *            the vixEcSet to set
	 */
	public void setVixEcSet(VixEcSet vixEcSet) {
		this.vixEcSet = vixEcSet;
	}

}
