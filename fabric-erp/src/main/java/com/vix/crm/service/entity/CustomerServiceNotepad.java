package com.vix.crm.service.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseBOEntity;
import com.vix.crm.project.entity.CrmProject;
import com.vix.mdm.crm.entity.CustomerAccount;

/** 客服记事 */
public class CustomerServiceNotepad extends BaseBOEntity {

	private static final long serialVersionUID = 1L;

	/** 记录日期 */
	private Date noteDate;
	/** 记录内容 */
	private String content;
	/** 项目 */
	private CrmProject crmProject;
	/** 客户 */
	private CustomerAccount customerAccount;
	
	public CustomerServiceNotepad(){}

	public Date getNoteDate() {
		return noteDate;
	}

	public void setNoteDate(Date noteDate) {
		this.noteDate = noteDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
}
