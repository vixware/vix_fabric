package com.vix.nvix.common.base.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.org.entity.Organization;
import com.vix.common.security.util.SecurityUtil;
import com.vix.drp.channel.entity.OrgSetEntity;

@Controller
@Scope("prototype")
public class VixntOrgSetAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private OrgSetEntity orgSetEntity;

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			Organization organization = vixntBaseService.findEntityByAttribute(Organization.class, "tenantId", SecurityUtil.getCurrentUserTenantId());
			if (organization != null) {
				orgSetEntity = vixntBaseService.findEntityByAttribute(OrgSetEntity.class, "organization.id", organization.getId());
				if (orgSetEntity != null) {
				} else {
					orgSetEntity = new OrgSetEntity();
					orgSetEntity.setOrganization(organization);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}

	/** 新增修改编码规则 */
	public void saveOrUpdate() {

		boolean isSave = true;
		try {
			initEntityBaseController.initEntityBaseAttribute(orgSetEntity);
			orgSetEntity = vixntBaseService.merge(orgSetEntity);
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

	public OrgSetEntity getOrgSetEntity() {
		return orgSetEntity;
	}

	public void setOrgSetEntity(OrgSetEntity orgSetEntity) {
		this.orgSetEntity = orgSetEntity;
	}
}