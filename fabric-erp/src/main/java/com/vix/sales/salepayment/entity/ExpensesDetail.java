package com.vix.sales.salepayment.entity;

import com.vix.common.share.entity.BaseEntity;

public class ExpensesDetail extends BaseEntity {
 
	private static final long serialVersionUID = 1L;
	
	private String expensesItem;
	
	private Double amount;
	
	private String goodsName;
	
	private String specification;
	
	private Expenses expenses;
	
	public ExpensesDetail(){}

	public String getExpensesItem() {
		return expensesItem;
	}

	public void setExpensesItem(String expensesItem) {
		this.expensesItem = expensesItem;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Expenses getExpenses() {
		return expenses;
	}

	public void setExpenses(Expenses expenses) {
		this.expenses = expenses;
	}
}
