/**
 * 
 */
package com.vix.drp.activityplan.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 活动计划
 * 
 * @author zhanghaibing
 * 
 * @date 2013-10-10
 */
public class ActivityPlan extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8007765978230427481L;
	/**
	 * 负责人
	 */
	private String principal;
	/**
	 * 销售组织
	 */
	private String salesOrganization;
	/**
	 * 销售部门
	 */
	private String salesDepartment;

	/**
	 * 活动预算
	 */
	private Double activityBudget;
	/**
	 * 币种
	 */
	private String currency;
	private Set<ActivityPlanDetail> activityPlanDetails = new HashSet<ActivityPlanDetail>();

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getSalesOrganization() {
		return salesOrganization;
	}

	public void setSalesOrganization(String salesOrganization) {
		this.salesOrganization = salesOrganization;
	}

	public String getSalesDepartment() {
		return salesDepartment;
	}

	public void setSalesDepartment(String salesDepartment) {
		this.salesDepartment = salesDepartment;
	}

	public Double getActivityBudget() {
		return activityBudget;
	}

	public void setActivityBudget(Double activityBudget) {
		this.activityBudget = activityBudget;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Set<ActivityPlanDetail> getActivityPlanDetails() {
		return activityPlanDetails;
	}

	public void setActivityPlanDetails(Set<ActivityPlanDetail> activityPlanDetails) {
		this.activityPlanDetails = activityPlanDetails;
	}

}
