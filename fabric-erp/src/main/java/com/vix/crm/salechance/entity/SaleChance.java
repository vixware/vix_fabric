package com.vix.crm.salechance.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseBOEntity;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.utils.DateUtil;
import com.vix.crm.base.entity.SaleChanceStatus;
import com.vix.crm.base.entity.SaleSource;
import com.vix.crm.base.entity.SaleStage;
import com.vix.crm.base.entity.SaleType;
import com.vix.crm.project.entity.CrmProject;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;

/** 销售机会 */
public class SaleChance extends BaseBOEntity {
 
	private static final long serialVersionUID = 1L;

	
	/** 机会主题 */
	private String subject;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 机会类型  */
	private String type;
	/** 发现时间 */
	private Date findDate;
	/** 来源 */
	private String source;
	/** 提供人 */
	private String sourcePerson;
	/** 对应客户 */
	private CustomerAccount customerAccount;
	/** 客户需求 */
	private String requirement;
	/** 预计签单日期 */
	private Date preOrderDate;
	/** 负责人 */
	private String charger;
	/** 预期金额 */
	private Double expectedValue;
	/** 可能性(百分比) */
	private Float possibility;
	/** 竞争对手可能性(百分比) */
	private Float compaignProbability;
	/** 阶段停留 */
	private String phaseStay;
	/** 阶段 */
	private String phase;
	/** 结算日期 */
	private Date cleanDate;
	/** 输入日期 */
	private Date dateEntered;
	/** 修改日期 */
	private Date dateModified;
	/** 修改用户编号 */
	private String modifiedUserId;
	/** 创建人 */
	private String createdBy;
	/** 分配的用户id */
	private String assignedUserID;
	/** 机会类型 */
	private String chanceType;
	/** 竞整者id */
	private String campaignID;
	/** 领导 */
	private String leadSource;
	/** 金额 */
	private Double amount;
	/** 数量 */
	private Double count;
	/** 货币 */
	private CurrencyType currencyType;
	/** 日期关闭 */
	private Date dateClosed;
	/** 下一步计划 */
	private String nextStepPlan;
	/** 销售阶段  */
	private SaleStage saleStage;
	/** 概率 */
	private Double probability;
	/** 预测结算日期 */
	private Date precastCleanDate;
	/** 预测结束日期 */
	private Date precastCloseDate;
	/** 预计费用 */
	private Double estimatedCost;
	/** 实际费用 */
	private Double acturalCost;
	/** 潜在金额 */
	private Double potentialAmount;
	/** 加权金额 */
	private Double weightedAmount;
	/** 分配的用户id */
	private String assignedUserId;
	/** 毛利率 */
	private Double grossMargin;
	/** 毛利总计 */
	private Double grossTotalMargin;
	/** 销售机会状态 */
	private SaleChanceStatus saleChanceStatus;
	/** 备注 */
	private String memo;
	/** 关联项目 */
	private CrmProject crmProject;
	/** 关联联系人 */
	private ContactPerson contactPerson;
	/**  销售机会类型 */
	private SaleType saleType;
	/** 销售机会来源  */
	private SaleSource saleSource;
	/** 负责人 */
	private Employee employee;
	
	public SaleChance(){}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getFindDate() {
		return findDate;
	}
	
	public String getFindDateStr(){
		if(null != findDate){
			return DateUtil.format(findDate);
		}
		return "";
	}

	public void setFindDate(Date findDate) {
		this.findDate = findDate;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourcePerson() {
		return sourcePerson;
	}

	public void setSourcePerson(String sourcePerson) {
		this.sourcePerson = sourcePerson;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public Date getPreOrderDate() {
		return preOrderDate;
	}
	
	public String getPreOrderDateStr() {
		if(null != preOrderDate){
			return DateUtil.format(preOrderDate);
		}
		return "";
	}

	public void setPreOrderDate(Date preOrderDate) {
		this.preOrderDate = preOrderDate;
	}

	public String getCharger() {
		return charger;
	}

	public void setCharger(String charger) {
		this.charger = charger;
	}

	public Double getExpectedValue() {
		return expectedValue;
	}

	public void setExpectedValue(Double expectedValue) {
		this.expectedValue = expectedValue;
	}

	public Float getPossibility() {
		return possibility;
	}

	public void setPossibility(Float possibility) {
		this.possibility = possibility;
	}

	public Float getCompaignProbability() {
		return compaignProbability;
	}

	public void setCompaignProbability(Float compaignProbability) {
		this.compaignProbability = compaignProbability;
	}

	public String getPhaseStay() {
		return phaseStay;
	}

	public void setPhaseStay(String phaseStay) {
		this.phaseStay = phaseStay;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public Date getCleanDate() {
		return cleanDate;
	}
	
	public String getCleanDateStr(){
		if(null != cleanDate){
			return DateUtil.format(cleanDate);
		}
		return "";
	}

	public void setCleanDate(Date cleanDate) {
		this.cleanDate = cleanDate;
	}

	public Date getDateEntered() {
		return dateEntered;
	}
	
	public String getDateEnteredStr(){
		if(null != dateEntered){
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
	
	public String getDateModifiedStr(){
		if(null != dateModified ){
			return DateUtil.format(dateModified);
		}
		return "";
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public String getModifiedUserId() {
		return modifiedUserId;
	}

	public void setModifiedUserId(String modifiedUserId) {
		this.modifiedUserId = modifiedUserId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getAssignedUserID() {
		return assignedUserID;
	}

	public void setAssignedUserID(String assignedUserID) {
		this.assignedUserID = assignedUserID;
	}

	public String getChanceType() {
		return chanceType;
	}

	public void setChanceType(String chanceType) {
		this.chanceType = chanceType;
	}

	public String getCampaignID() {
		return campaignID;
	}

	public void setCampaignID(String campaignID) {
		this.campaignID = campaignID;
	}

	public String getLeadSource() {
		return leadSource;
	}

	public void setLeadSource(String leadSource) {
		this.leadSource = leadSource;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public Date getDateClosed() {
		return dateClosed;
	}
	
	public String getDateClosedStr(){
		if(null != dateClosed){
			return DateUtil.format(dateClosed);
		}
		return "";
	}

	public void setDateClosed(Date dateClosed) {
		this.dateClosed = dateClosed;
	}

	public String getNextStepPlan() {
		return nextStepPlan;
	}

	public void setNextStepPlan(String nextStepPlan) {
		this.nextStepPlan = nextStepPlan;
	}

	public SaleStage getSaleStage() {
		return saleStage;
	}

	public void setSaleStage(SaleStage saleStage) {
		this.saleStage = saleStage;
	}

	public Double getProbability() {
		return probability;
	}

	public void setProbability(Double probability) {
		this.probability = probability;
	}

	public Date getPrecastCleanDate() {
		return precastCleanDate;
	}
	
	public String getPrecastCleanDateStr(){
		if(null != precastCleanDate){
			return DateUtil.format(precastCleanDate);
		}
		return "";
	}

	public void setPrecastCleanDate(Date precastCleanDate) {
		this.precastCleanDate = precastCleanDate;
	}

	public Date getPrecastCloseDate() {
		return precastCloseDate;
	}
	
	public String getPrecastCloseDateStr(){
		if(null != precastCloseDate){
			return DateUtil.format(precastCloseDate);
		}
		return "";
	}

	public void setPrecastCloseDate(Date precastCloseDate) {
		this.precastCloseDate = precastCloseDate;
	}

	public Double getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(Double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public Double getActuralCost() {
		return acturalCost;
	}

	public void setActuralCost(Double acturalCost) {
		this.acturalCost = acturalCost;
	}

	public Double getPotentialAmount() {
		return potentialAmount;
	}

	public void setPotentialAmount(Double potentialAmount) {
		this.potentialAmount = potentialAmount;
	}

	public Double getWeightedAmount() {
		return weightedAmount;
	}

	public void setWeightedAmount(Double weightedAmount) {
		this.weightedAmount = weightedAmount;
	}

	public String getAssignedUserId() {
		return assignedUserId;
	}

	public void setAssignedUserId(String assignedUserId) {
		this.assignedUserId = assignedUserId;
	}

	public Double getGrossMargin() {
		return grossMargin;
	}

	public void setGrossMargin(Double grossMargin) {
		this.grossMargin = grossMargin;
	}

	public Double getGrossTotalMargin() {
		return grossTotalMargin;
	}

	public void setGrossTotalMargin(Double grossTotalMargin) {
		this.grossTotalMargin = grossTotalMargin;
	}

	public SaleChanceStatus getSaleChanceStatus() {
		return saleChanceStatus;
	}

	public void setSaleChanceStatus(SaleChanceStatus saleChanceStatus) {
		this.saleChanceStatus = saleChanceStatus;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public CrmProject getCrmProject() {
		return crmProject;
	}

	public void setCrmProject(CrmProject crmProject) {
		this.crmProject = crmProject;
	}

	public ContactPerson getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(ContactPerson contactPerson) {
		this.contactPerson = contactPerson;
	}

	public SaleType getSaleType() {
		return saleType;
	}

	public void setSaleType(SaleType saleType) {
		this.saleType = saleType;
	}

	public SaleSource getSaleSource() {
		return saleSource;
	}

	public void setSaleSource(SaleSource saleSource) {
		this.saleSource = saleSource;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	/** 阶段停留  */
	public Integer getStagnateDay(){
		if(getUpdateTime() != null){
			long from = DateUtil.praseSqlDate(getUpdateTimeStr()).getTime();
			long to = DateUtil.praseSqlDate(DateUtil.format(new Date())).getTime();
			int day = (int)((to-from)/(24*60*60*1000));
			return day;
		}
		return 0;
	}
}
