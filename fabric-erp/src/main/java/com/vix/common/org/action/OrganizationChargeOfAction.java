package com.vix.common.org.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationChargeOf;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.org.service.IOrganizationChargeOfService;
import com.vix.common.org.service.IOrganizationService;
import com.vix.common.org.service.IOrganizationUnitService;
import com.vix.core.utils.StrUtils;

/**
 * 职员分管 公司或者部门
 */
@Controller
@Scope("prototype")
public class OrganizationChargeOfAction extends BaseAction {

	@Autowired
	private IOrganizationService organizationService;

	@Autowired
	private IOrganizationUnitService organizationUnitService;

	@Autowired
	private IOrganizationChargeOfService organizationChargeOfService;

	/**
	 * 分管
	 */
	private OrganizationChargeOf entity;

	/**
	 * 分管 接收参数
	 */
	private OrganizationChargeOf entityForm;

	/**
	 * 职员id
	 */
	private String empId;

	/** 类型 C 公司 O 部门 */
	private String orgType;
	/** 选择的公司id */
	private String orgId;
	/** 选择的部门id */
	private String orgUnitIds;

	/**
	 * 删除时使用 公司或者部门id
	 */
	private String orgOrUnitId;

	/**
	 * 分管部门列表
	 */
	public void goChargeOfOrgUnitList() {
		try {
			// params.put("roleCode,"+SearchCondition.NOEQUAL,
			// BizConstant.ROLE_SUPERADMIN);
			/*
			 * if(StringUtils.isNotEmpty(metaDataName)){
			 * params.put("metaDataName", "%"+metaDataName+"%"); }
			 */
			List<OrganizationUnit> orgUnitList = organizationChargeOfService.findChargeOfOrgUnitListByEmpId(empId);
			Long dataSize = orgUnitList == null ? 0L : orgUnitList.size();
			renderHtml(convertListToJsonNoPage(orgUnitList, dataSize));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return GO_SINGLE_LIST;
	}

	/**
	 * 分管部门列表
	 */
	public void goChargeOfOrgList() {
		try {
			// params.put("roleCode,"+SearchCondition.NOEQUAL,
			// BizConstant.ROLE_SUPERADMIN);
			/*
			 * if(StringUtils.isNotEmpty(metaDataName)){
			 * params.put("metaDataName", "%"+metaDataName+"%"); }
			 */
			List<Organization> orgList = organizationChargeOfService.findChargeOfOrgListByEmpId(empId);
			Long dataSize = orgList == null ? 0L : orgList.size();
			renderHtml(convertListToJsonNoPage(orgList, dataSize));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return GO_SINGLE_LIST;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		try {

			if (empId != null && StrUtils.isNotEmpty(orgType)) {
				if (StringUtils.isNotEmpty(orgUnitIds)) {
					orgUnitIds = StringUtils.replace(orgUnitIds, "O", "");
				}
				organizationChargeOfService.saveChargeOf(orgType, empId, orgId, orgUnitIds);

				setMessage(OPER_SUCCESS);
			} else {
				setMessage("没有可操作的数据");
			}

		} catch (Exception e) {
			e.printStackTrace();
			this.setMessage(OPER_FAIL);
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			if (StringUtils.isNotEmpty(orgType) && empId != null && orgOrUnitId != null) {
				organizationChargeOfService.deleteChargeOf(orgType, empId, orgOrUnitId);
			}

			setMessage(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	public OrganizationChargeOf getEntity() {
		return entity;
	}

	public void setEntity(OrganizationChargeOf entity) {
		this.entity = entity;
	}

	public OrganizationChargeOf getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(OrganizationChargeOf entityForm) {
		this.entityForm = entityForm;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgUnitIds() {
		return orgUnitIds;
	}

	public void setOrgUnitIds(String orgUnitIds) {
		this.orgUnitIds = orgUnitIds;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getOrgOrUnitId() {
		return orgOrUnitId;
	}

	public void setOrgOrUnitId(String orgOrUnitId) {
		this.orgOrUnitId = orgOrUnitId;
	}

}
