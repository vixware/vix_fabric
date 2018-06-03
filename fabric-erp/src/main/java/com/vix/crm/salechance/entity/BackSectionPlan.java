package com.vix.crm.salechance.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.contract.mamanger.entity.Contract;
import com.vix.core.utils.DateUtil;
import com.vix.crm.project.entity.CrmProject;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.sales.order.entity.SalesOrder;

public class BackSectionPlan extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 客户 */
	private CustomerAccount customerAccount;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 销售订单id */
	private String saleOrderId;
	/** 销售订单主题 */
	private String saleOrderTitle;
	/** 项目 */
	private CrmProject crmProject;
	/** 计划回款日期 */
	private Date backSectionPlanDate;
	/** 期次 */
	private Integer stageNumber;
	/** 金额 */
	private Double amount;
	/** 外币备注 */
	private String foreignCurrencyMemo;
	/** 回款状态  0：未回 1：已回 */
	private String backSectionPlanStatus;
	/** 所有者*/
	private Employee owner;
	/** 负责人 */
	private Employee charger;
	/** 备注 */
	private String memo;
	/** 销售订单  */
	private SalesOrder salesOrder;
	/**
	 * 销售合同
	 */
	private Contract contract;
	public BackSectionPlan(){}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public CrmProject getCrmProject() {
		return crmProject;
	}

	public void setCrmProject(CrmProject crmProject) {
		this.crmProject = crmProject;
	}

	public String getSaleOrderId() {
		return saleOrderId;
	}

	public void setSaleOrderId(String saleOrderId) {
		this.saleOrderId = saleOrderId;
	}

	public String getSaleOrderTitle() {
		return saleOrderTitle;
	}

	public void setSaleOrderTitle(String saleOrderTitle) {
		this.saleOrderTitle = saleOrderTitle;
	}

	public Date getBackSectionPlanDate() {
		return backSectionPlanDate;
	}
	
	public String getBackSectionPlanDateStr() {
		if(backSectionPlanDate != null){
			return DateUtil.format(backSectionPlanDate);
		}
		return "";
	}

	public void setBackSectionPlanDate(Date backSectionPlanDate) {
		this.backSectionPlanDate = backSectionPlanDate;
	}

	public Integer getStageNumber() {
		return stageNumber;
	}

	public void setStageNumber(Integer stageNumber) {
		this.stageNumber = stageNumber;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getForeignCurrencyMemo() {
		return foreignCurrencyMemo;
	}

	public void setForeignCurrencyMemo(String foreignCurrencyMemo) {
		this.foreignCurrencyMemo = foreignCurrencyMemo;
	}

	public String getBackSectionPlanStatus() {
		return backSectionPlanStatus;
	}

	public void setBackSectionPlanStatus(String backSectionPlanStatus) {
		this.backSectionPlanStatus = backSectionPlanStatus;
	}

	public Employee getOwner() {
		return owner;
	}
	
	public String getOwnerName() {
		if(owner != null){
			return owner.getName();
		}
		return "";
	}

	public void setOwner(Employee owner) {
		this.owner = owner;
	}

	public Employee getCharger() {
		return charger;
	}

	public void setCharger(Employee charger) {
		this.charger = charger;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}
}
