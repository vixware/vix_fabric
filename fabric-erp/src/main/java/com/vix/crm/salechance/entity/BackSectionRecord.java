package com.vix.crm.salechance.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.PaymentCategory;
import com.vix.common.share.entity.PaymentType;
import com.vix.core.utils.DateUtil;
import com.vix.crm.project.entity.CrmProject;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.sales.order.entity.SalesOrder;

public class BackSectionRecord extends BaseEntity {

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
	/** 回款日期 */
	private Date backSectionDate;
	/** 期次 */
	private Integer stageNumber;
	/** 金额 */
	private Double amount;
	/** 外币备注 */
	private String foreignCurrencyMemo;
	/** 开票状态  0：未开 1：已开 2：不开 */
	private String isBilling;
	/** 所有者*/
	private Employee owner;
	/** 付款方式 */
	private PaymentType paymentType;
	/** 付款分类 */
	private PaymentCategory paymentCategory;
	/** 备注 */
	private String memo;
	/** 销售订单  */
	private SalesOrder salesOrder;
	
	public BackSectionRecord(){}

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

	public CrmProject getCrmProject() {
		return crmProject;
	}

	public void setCrmProject(CrmProject crmProject) {
		this.crmProject = crmProject;
	}

	public Date getBackSectionDate() {
		return backSectionDate;
	}
	
	public String getBackSectionDateStr() {
		if(backSectionDate != null){
			return DateUtil.format(backSectionDate);
		}
		return "";
	}

	public void setBackSectionDate(Date backSectionDate) {
		this.backSectionDate = backSectionDate;
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

	public String getIsBilling() {
		return isBilling;
	}

	public void setIsBilling(String isBilling) {
		this.isBilling = isBilling;
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

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public PaymentCategory getPaymentCategory() {
		return paymentCategory;
	}

	public void setPaymentCategory(PaymentCategory paymentCategory) {
		this.paymentCategory = paymentCategory;
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
}
