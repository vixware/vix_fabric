package com.vix.sales.commission.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.sales.commission.dao.ISaleAmountDao;

@Repository("saleAmountDao")
public class SaleAmountDaoImpl extends BaseHibernateDaoImpl implements ISaleAmountDao {

	private static final long serialVersionUID = 1L;

}
