package com.vix.nvix.customer.action;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.security.util.SecurityUtil;
import com.vix.core.utils.StrUtils;
import com.vix.crm.base.entity.CrmSet;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class NvixCrmSetAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private CrmSet crmSet;

	/** 跳转至用户修改页面 */
	@Override
	public String goList() {
		try {
			String tenantId = SecurityUtil.getCurrentUserTenantId();
			if (StringUtils.isNotEmpty(tenantId)) {
				crmSet = vixntBaseService.findEntityByAttribute(CrmSet.class, "tenantId", tenantId);
				if (crmSet != null) {
				} else {
					crmSet = new CrmSet();
					crmSet.setCreateTime(new Date());;
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
			initEntityBaseController.initEntityBaseAttribute(crmSet);
			if(StrUtils.isNotEmpty(crmSet.getId())){
				crmSet.setUpdateTime(new Date());
			}else{
				crmSet.setCreateTime(new Date());
				crmSet.setUpdateTime(new Date());
			}
			crmSet = vixntBaseService.merge(crmSet);
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

	public CrmSet getCrmSet() {
		return crmSet;
	}

	public void setCrmSet(CrmSet crmSet) {
		this.crmSet = crmSet;
	}

}
