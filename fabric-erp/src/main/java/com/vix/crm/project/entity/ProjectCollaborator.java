package com.vix.crm.project.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.crm.base.entity.CollaboratorType;
import com.vix.mdm.crm.entity.CustomerAccount;


/** 项目协作方 */
public class ProjectCollaborator extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 项目 */
	private CrmProject crmProject;
	/** 协作方类型 */
	private CollaboratorType collaboratorType;
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 备注 */
	private String memo;
	
	public ProjectCollaborator(){}

	public CrmProject getCrmProject() {
		return crmProject;
	}

	public void setCrmProject(CrmProject crmProject) {
		this.crmProject = crmProject;
	}

	public CollaboratorType getCollaboratorType() {
		return collaboratorType;
	}

	public void setCollaboratorType(CollaboratorType collaboratorType) {
		this.collaboratorType = collaboratorType;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
