package com.vix.crm.service.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseBOEntity;
import com.vix.core.utils.DateUtil;
import com.vix.crm.base.entity.ConsumeTime;
import com.vix.crm.base.entity.CustomerServiceStatus;
import com.vix.crm.base.entity.ServiceMode;
import com.vix.crm.base.entity.ServiceType;
import com.vix.crm.project.entity.CrmProject;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;

/** 客户服务 */
public class CustomerServices extends BaseBOEntity {

	private static final long serialVersionUID = 1L;

	/** 主题 */
	private String subject;
	/** 开始日期 */
	private Date startDate;
	/** 开始时间 */
	private String startTimeStr;
	/** 花费时间  */
	private ConsumeTime consumeTime;
	/** 服务内容 */
	private String serviceContent;
	/** 客户反馈 */
	private String customerFeedback;
	/** 备注 */
	private String memo;
	/** 执行人 */
	private Employee employee;
	/** 服务方式 */
	private ServiceMode serviceMode;
	/** 服务类型 */
	private ServiceType serviceType;
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 联系人 */
	private ContactPerson contactPerson;
	/** 项目 */
	private CrmProject crmProject;
	/** 状态  */
	private CustomerServiceStatus customerServiceStatus;
	
	public CustomerServices(){}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getStartDate() {
		return startDate;
	}
	
	public String getStartDateStr() {
		if(startDate != null){
			return DateUtil.format(startDate);
		}
		return "";
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
 
	@Override
	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public ConsumeTime getConsumeTime() {
		return consumeTime;
	}

	public void setConsumeTime(ConsumeTime consumeTime) {
		this.consumeTime = consumeTime;
	}

	public String getServiceContent() {
		return serviceContent;
	}

	public void setServiceContent(String serviceContent) {
		this.serviceContent = serviceContent;
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

	public ServiceMode getServiceMode() {
		return serviceMode;
	}

	public void setServiceMode(ServiceMode serviceMode) {
		this.serviceMode = serviceMode;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
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

	public CustomerServiceStatus getCustomerServiceStatus() {
		return customerServiceStatus;
	}

	public void setCustomerServiceStatus(CustomerServiceStatus customerServiceStatus) {
		this.customerServiceStatus = customerServiceStatus;
	}
}
