/**
 * 
 */
package com.vix.drp.activitybudget.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 活动预算
 * 
 * @author zhanghaibing
 * 
 * @date 2013-10-10
 */
public class ActivityBudget extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8007765978230427481L;
	/**
	 * 销售组织
	 */
	private String salesOrganization;
	/**
	 * 负责人
	 */
	private String principal;
	/**
	 * 预算内容
	 */
	private String budgetcontent;
	/**
	 * 预算
	 */
	private Double budget;

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getBudgetcontent() {
		return budgetcontent;
	}

	public void setBudgetcontent(String budgetcontent) {
		this.budgetcontent = budgetcontent;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public String getSalesOrganization() {
		return salesOrganization;
	}

	public void setSalesOrganization(String salesOrganization) {
		this.salesOrganization = salesOrganization;
	}

}
