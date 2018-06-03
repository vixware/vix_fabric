package com.vix.crm.project.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;

/** 行动历史 */
public class ActionHistory extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 主题 */
	private String subject;
	/** 行动日期 */
	private Date actionDate;
	/** 描述 */
	private String description;
	/** 项目 */
	private CrmProject crmProject;
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 联系人 */
	private ContactPerson contactPerson;
	
	public ActionHistory(){}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getActionDate() {
		return actionDate;
	}
	
	public String getActionDateStr() {
		if(actionDate != null){
			return DateUtil.format(actionDate);
		}
		return "";
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	public CrmProject getCrmProject() {
		return crmProject;
	}

	public void setCrmProject(CrmProject crmProject) {
		this.crmProject = crmProject;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public ContactPerson getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(ContactPerson contactPerson) {
		this.contactPerson = contactPerson;
	}
}
