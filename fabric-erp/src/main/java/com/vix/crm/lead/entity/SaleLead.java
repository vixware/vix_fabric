package com.vix.crm.lead.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseBOEntity;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.utils.DateUtil;
import com.vix.core.utils.StrUtils;
import com.vix.crm.project.entity.CrmProject;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;

/** 销售线索 */
public class SaleLead extends BaseBOEntity {

	private static final long serialVersionUID = 1L;
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 联系人 */
	private ContactPerson contactPerson;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 线索主题 */
	private String subject;
	/** 姓*/
	private String lastName;
	/** 名*/
	private String firstName;
	/** 头衔  */
	private String title;
	/** 部门
	private Department department; */
	/** 类型 */
	private String type;
	/** 发现时间 */
	private Date findOutTime;
	/** 来源 */
	private String leadSource;
	/** 提供人 */
	private String sourcePerson;
	/** 客户编码 */
	private String customerCode;
	/** 客户名称*/
	private String customerName;
	/** 客户需求*/
	private String requirement;
	/** 输入日期 */
	private Date dateEntered;
	/** 修改日期 */
	private Date dateModified;
	/** 修改用户编号*/
	private Long modifiedUserId;
	/** 创建人 */
	private String createdBy;
	/** 分配的用户id */
	private String assignedUserId;
	/** 金额 */
	private Double amount;
	/** 金额（美元） */
	private Double usAmount;
	/** 货币类型 */
	private CurrencyType currencyType;
	/** 关闭日期 */
	private Date dateClosed;
	/** 下一步计划 */
	private String nextStepPlan;
	/** 概率 */
	private Double probability;
	/** 备注 */
	private String memo;
	/** 项目  */
	private CrmProject crmProject;

	public SaleLead(){}

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

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getFindOutTime() {
		return findOutTime;
	}
	
	public String getFindOutTimeStr() {
		if(findOutTime != null){
			return DateUtil.format(findOutTime);
		}
		return "";
	}

	public void setFindOutTime(Date findOutTime) {
		this.findOutTime = findOutTime;
	}

	public String getLeadSource() {
		return leadSource;
	}

	public void setLeadSource(String leadSource) {
		this.leadSource = leadSource;
	}

	public String getSourcePerson() {
		return sourcePerson;
	}

	public void setSourcePerson(String sourcePerson) {
		this.sourcePerson = sourcePerson;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public Date getDateEntered() {
		return dateEntered;
	}
	
	public String getDateEnteredStr() {
		if(dateEntered != null){
			return DateUtil.format(dateEntered);
		}
		return "";
	}

	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}

	public Date getDateModified() {
		return dateModified;
	}
	
	public String getDateModifiedStr() {
		if(dateModified != null){
			return DateUtil.format(dateModified);
		}
		return "";
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public Long getModifiedUserId() {
		return modifiedUserId;
	}

	public void setModifiedUserId(Long modifiedUserId) {
		this.modifiedUserId = modifiedUserId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getAssignedUserId() {
		return assignedUserId;
	}

	public void setAssignedUserId(String assignedUserId) {
		this.assignedUserId = assignedUserId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getDateClosed() {
		return dateClosed;
	}
	
	public String getDateClosedStr() {
		if(dateClosed != null){
			return DateUtil.format(dateClosed);
		}
		return "";
	}

	public void setDateClosed(Date dateClosed) {
		this.dateClosed = dateClosed;
	}

	public Double getUsAmount() {
		return usAmount;
	}

	public void setUsAmount(Double usAmount) {
		this.usAmount = usAmount;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public String getNextStepPlan() {
		return nextStepPlan;
	}

	public void setNextStepPlan(String nextStepPlan) {
		this.nextStepPlan = nextStepPlan;
	}

	public Double getProbability() {
		return probability;
	}

	public void setProbability(Double probability) {
		this.probability = probability;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public String getLFName(String lastName,String fristName){
		if(StrUtils.isNotEmpty(lastName) && StrUtils.isNotEmpty(fristName)){
			return lastName + fristName;
		}
		return "";
	}

	public CrmProject getCrmProject() {
		return crmProject;
	}

	public void setCrmProject(CrmProject crmProject) {
		this.crmProject = crmProject;
	}
}
