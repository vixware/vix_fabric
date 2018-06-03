package com.vix.crm.project.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.crm.salechance.entity.SaleChance;
import com.vix.mdm.crm.entity.CustomerAccount;

/** 项目解决方案 */
public class ProjectSolution extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 主题 */
	private String subject;
	/** 提交时间 */
	private Date submitDate;
	/** 方案内容 */
	private String solutionContent;
	/** 客户反馈 */
	private String customerFeedback;
	/** 项目 */
	private CrmProject crmProject;
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 销售机会 */
	private SaleChance saleChance;
	
	public ProjectSolution(){}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getSubmitDate() {
		return submitDate;
	}
	
	public String getSubmitDateStr() {
		if(submitDate != null){
			return DateUtil.format(submitDate);
		}
		return "";
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public String getSolutionContent() {
		return solutionContent;
	}

	public void setSolutionContent(String solutionContent) {
		this.solutionContent = solutionContent;
	}

	public String getCustomerFeedback() {
		return customerFeedback;
	}

	public void setCustomerFeedback(String customerFeedback) {
		this.customerFeedback = customerFeedback;
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
}
