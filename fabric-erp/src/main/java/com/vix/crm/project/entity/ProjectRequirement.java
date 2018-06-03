package com.vix.crm.project.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.crm.salechance.entity.SaleChance;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;

/** 项目需求 */
public class ProjectRequirement extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 主题 */
	private String subject;
	/** 提供人 */
	private Employee provider;
	/** 记录时间 */
	private Date recordDate;
	/** 描述 */
	private String description;
	/** 项目 */
	private CrmProject crmProject;
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 销售机会 */
	private SaleChance saleChance;
	/** 重要程度 0:非常重要,1:重要,2:一般3:不重要  */
	private String degree;
	
	public ProjectRequirement(){}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Employee getProvider() {
		return provider;
	}

	public void setProvider(Employee provider) {
		this.provider = provider;
	}

	public Date getRecordDate() {
		return recordDate;
	}
	
	public String getRecordDateStr() {
		if(recordDate != null){
			return DateUtil.format(recordDate);
		}
		return "";
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
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

	public SaleChance getSaleChance() {
		return saleChance;
	}

	public void setSaleChance(SaleChance saleChance) {
		this.saleChance = saleChance;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}
}
