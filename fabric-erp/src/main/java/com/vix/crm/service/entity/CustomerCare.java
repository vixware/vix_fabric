package com.vix.crm.service.entity;

import com.vix.common.share.entity.BaseBOEntity;
import com.vix.crm.base.entity.CustomerCareType;
import com.vix.crm.project.entity.CrmProject;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;

/** 客户关怀 */
public class CustomerCare extends BaseBOEntity {

	private static final long serialVersionUID = 1L;

	/** 关怀主题 */
	private String subject;
	/** 关怀内容 */
	private String careContent;
	/** 客户反馈 */
	private String customerFeedback;
	/** 备注 */
	private String memo;
	/** 执行人 */
	private Employee employee;
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 联系人 */
	private ContactPerson contactPerson;
	/** 项目 */
	private CrmProject crmProject;
	/** 关怀类型 */
	private CustomerCareType customerCareType;
	
	public CustomerCare(){}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCareContent() {
		return careContent;
	}

	public void setCareContent(String careContent) {
		this.careContent = careContent;
	}

	public String getCustomerFeedback() {
		return customerFeedback;
	}

	public void setCustomerFeedback(String customerFeedback) {
		this.customerFeedback = customerFeedback;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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

	public CrmProject getCrmProject() {
		return crmProject;
	}

	public void setCrmProject(CrmProject crmProject) {
		this.crmProject = crmProject;
	}

	public CustomerCareType getCustomerCareType() {
		return customerCareType;
	}

	public void setCustomerCareType(CustomerCareType customerCareType) {
		this.customerCareType = customerCareType;
	}
}
