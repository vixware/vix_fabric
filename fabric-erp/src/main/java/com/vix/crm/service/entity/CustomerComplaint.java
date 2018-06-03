package com.vix.crm.service.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseBOEntity;
import com.vix.crm.base.entity.ComplaintType;
import com.vix.crm.base.entity.ConsumeTime;
import com.vix.crm.base.entity.DealResult;
import com.vix.crm.base.entity.EmergencyLevelType;
import com.vix.crm.project.entity.CrmProject;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;


/** 客户投诉 */
public class CustomerComplaint extends BaseBOEntity {

	private static final long serialVersionUID = 1L;

	/** 投诉主题 */
	private String subject;
	/** 描述 */
	private String description;
	/** 投诉日期 */
	private Date complaintDate;
	/** 投诉时间 */
	private String complaintTime;
	/** 处理过程 */
	private String dealProcess;
	/** 客户反馈 */
	private String customerFeedback;
	/** 回访确认 */
	private String visitConfirmed;
	/** 备注 */
	private String memo;
	/** 接待人 */
	private Employee employee;
	/** 投诉分类 */
	private ComplaintType complaintType;
	/** 处理结果 */
	private DealResult dealResult;
	/** 花费时间 */
	private ConsumeTime consumeTime;
	/** 紧急程度 */
	private EmergencyLevelType emergencyLevelType;
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 联系人 */
	private ContactPerson contactPerson;
	/** 项目 */
	private CrmProject crmProject;
	
	public CustomerComplaint(){}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	public Date getComplaintDate() {
		return complaintDate;
	}

	public void setComplaintDate(Date complaintDate) {
		this.complaintDate = complaintDate;
	}

	public String getComplaintTime() {
		return complaintTime;
	}

	public void setComplaintTime(String complaintTime) {
		this.complaintTime = complaintTime;
	}

	public String getDealProcess() {
		return dealProcess;
	}

	public void setDealProcess(String dealProcess) {
		this.dealProcess = dealProcess;
	}

	public String getCustomerFeedback() {
		return customerFeedback;
	}

	public void setCustomerFeedback(String customerFeedback) {
		this.customerFeedback = customerFeedback;
	}

	public String getVisitConfirmed() {
		return visitConfirmed;
	}

	public void setVisitConfirmed(String visitConfirmed) {
		this.visitConfirmed = visitConfirmed;
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

	public ComplaintType getComplaintType() {
		return complaintType;
	}

	public void setComplaintType(ComplaintType complaintType) {
		this.complaintType = complaintType;
	}

	public DealResult getDealResult() {
		return dealResult;
	}

	public void setDealResult(DealResult dealResult) {
		this.dealResult = dealResult;
	}

	public ConsumeTime getConsumeTime() {
		return consumeTime;
	}

	public void setConsumeTime(ConsumeTime consumeTime) {
		this.consumeTime = consumeTime;
	}

	public EmergencyLevelType getEmergencyLevelType() {
		return emergencyLevelType;
	}

	public void setEmergencyLevelType(EmergencyLevelType emergencyLevelType) {
		this.emergencyLevelType = emergencyLevelType;
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
}
