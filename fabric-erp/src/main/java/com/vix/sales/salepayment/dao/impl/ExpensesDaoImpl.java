package com.vix.sales.salepayment.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.sales.salepayment.dao.IExpensesDao;

@Repository("expensesDao")
public class ExpensesDaoImpl extends BaseHibernateDaoImpl implements IExpensesDao {

	private static final long serialVersionUID = 1L;

}
