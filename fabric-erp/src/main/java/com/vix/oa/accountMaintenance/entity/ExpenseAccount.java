package com.vix.oa.accountMaintenance.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 费用科目 com.vix.oa.accountMaintenance.entity.ExpenseAccount
 *
 * @author bjitzhang
 *
 * @date 2015年3月31日
 */

public class ExpenseAccount extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 对应财务科目
	 */
	private String financeAccount;
	/**
	 * 财务对方科目
	 */
	private String financialSubjects;

	public String getFinanceAccount() {
		return financeAccount;
	}

	public void setFinanceAccount(String financeAccount) {
		this.financeAccount = financeAccount;
	}

	public String getFinancialSubjects() {
		return financialSubjects;
	}

	public void setFinancialSubjects(String financialSubjects) {
		this.financialSubjects = financialSubjects;
	}

}