package com.vix.sales.credit.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.crm.entity.CustomerAccount;

public class CreditControl extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	/** 客户信息 */
	private CustomerAccount customerAccount;
	/** 信用控制名称 */
	private String ccName;
	/** 单据类型 */
	private String billType;
	/** 业务类型 */
	private String bizType;
	/** 销售组织 */
	private OrganizationUnit saleOrg;
	/** 明细 */
	private Set<CreditControlItem> creditControlItems = new HashSet<CreditControlItem>();
	
	public CreditControl(){}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public String getCcName() {
		return ccName;
	}

	public void setCcName(String ccName) {
		this.ccName = ccName;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public OrganizationUnit getSaleOrg() {
		return saleOrg;
	}

	public void setSaleOrg(OrganizationUnit saleOrg) {
		this.saleOrg = saleOrg;
	}

	public Set<CreditControlItem> getCreditControlItems() {
		return creditControlItems;
	}

	public void setCreditControlItems(Set<CreditControlItem> creditControlItems) {
		this.creditControlItems = creditControlItems;
	}
}
