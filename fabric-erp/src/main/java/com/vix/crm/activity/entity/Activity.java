package com.vix.crm.activity.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.vix.common.share.entity.BaseBOEntity;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.utils.DateUtil;
import com.vix.crm.base.entity.SaleActivity;
import com.vix.crm.project.entity.CrmProject;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;

/**
 * 活动
 * 
 * @author Administrator
 *
 */
public class Activity extends BaseBOEntity {

	private static final long serialVersionUID = 1L;

	/** 活动主题 */
	private String title;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 活动 */
	private String activity;
	/** 类型(字典 可配置) */
	private String type;
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 项目 */
	private CrmProject crmProject;
	/** 时间 */
	private Date date;
	/** 内容 */
	private String content;
	/** 预计费用 */
	private Double estimatedCost;
	/** 发生费用 */
	private Double costsIncurred;
	/** 批准费用 */
	private Double approvalOfFees;
	/** 预计销售额 */
	private Double projectedSales;
	/** 机会销售额 */
	private Double salesOpportunities;
	/** 实际销售额 */
	private Double actualSales;
	/** 预计销售量 */
	private Double expectedSales;
	/** 实际机会数 */
	private Double actualNumberOfOpportunities;
	/** 开始时间-日期 */
	private Date startDate;
	/** 开始时间-小时分 */
	private String startTimeStr;
	/** 结束时间-日期 */
	private Date endDate;
	/** 结束时间-小时分 */
	private String endTimeStr;
	/** 实际费用 */
	private Double acturalCost;
	/** 输入日期 */
	private Date dateEntered;
	/** 修改日期 */
	private Date dateModified;
	/** 活动地点 */
	private String address;
	/** 分配的用户id */
	private Double assignedUserId;
	/** 修改用户id */
	private Double modifiedUserId;
	/** 创建人 */
	private Employee created_by;
	/** 是否删除 */
	private String isDeleted;
	/** 货币类型 */
	private CurrencyType currencyType;
	/** 负责人 */
	private Employee personInCharge;
	/** 销售活动类型 */
	private SaleActivity saleActivity;

	public Activity() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public CrmProject getCrmProject() {
		return crmProject;
	}

	public void setCrmProject(CrmProject crmProject) {
		this.crmProject = crmProject;
	}

	public Date getDate() {
		return date;
	}
	
	public String getDateStr() {
		if(date != null){
			return DateUtil.format(date);
		}
		return "";
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Double getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(Double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public Double getCostsIncurred() {
		return costsIncurred;
	}

	public void setCostsIncurred(Double costsIncurred) {
		this.costsIncurred = costsIncurred;
	}

	public Double getApprovalOfFees() {
		return approvalOfFees;
	}

	public void setApprovalOfFees(Double approvalOfFees) {
		this.approvalOfFees = approvalOfFees;
	}

	public Double getProjectedSales() {
		return projectedSales;
	}

	public void setProjectedSales(Double projectedSales) {
		this.projectedSales = projectedSales;
	}

	public Double getSalesOpportunities() {
		return salesOpportunities;
	}

	public void setSalesOpportunities(Double salesOpportunities) {
		this.salesOpportunities = salesOpportunities;
	}

	public Double getActualSales() {
		return actualSales;
	}

	public void setActualSales(Double actualSales) {
		this.actualSales = actualSales;
	}

	public Double getExpectedSales() {
		return expectedSales;
	}

	public void setExpectedSales(Double expectedSales) {
		this.expectedSales = expectedSales;
	}

	public Double getActualNumberOfOpportunities() {
		return actualNumberOfOpportunities;
	}

	public void setActualNumberOfOpportunities(Double actualNumberOfOpportunities) {
		this.actualNumberOfOpportunities = actualNumberOfOpportunities;
	}

	public Date getStartDate() {
		return startDate;
	}

	public String getStartDateStr() {
		if (null != startDate) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(startDate);
		} else {
			return "";
		}
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

	@Override
	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getEndDateStr() {
		if (null != endDate) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(endDate);
		} else {
			return "";
		}
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getActuralCost() {
		return acturalCost;
	}

	public void setActuralCost(Double acturalCost) {
		this.acturalCost = acturalCost;
	}

	public Date getDateEntered() {
		return dateEntered;
	}

	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getAssignedUserId() {
		return assignedUserId;
	}

	public void setAssignedUserId(Double assignedUserId) {
		this.assignedUserId = assignedUserId;
	}

	public Double getModifiedUserId() {
		return modifiedUserId;
	}

	public void setModifiedUserId(Double modifiedUserId) {
		this.modifiedUserId = modifiedUserId;
	}

	public Employee getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Employee created_by) {
		this.created_by = created_by;
	}

	@Override
	public String getIsDeleted() {
		return isDeleted;
	}

	@Override
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public Employee getPersonInCharge() {
		return personInCharge;
	}

	public void setPersonInCharge(Employee personInCharge) {
		this.personInCharge = personInCharge;
	}

	public SaleActivity getSaleActivity() {
		return saleActivity;
	}

	public void setSaleActivity(SaleActivity saleActivity) {
		this.saleActivity = saleActivity;
	}

}
