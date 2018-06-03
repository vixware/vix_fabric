package com.vix.sales.commission.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.sales.commission.dao.ISaleAmountItemDao;

@Repository("saleAmountItemDao")
public class SaleAmountItemDaoImpl extends BaseHibernateDaoImpl implements ISaleAmountItemDao {

	private static final long serialVersionUID = 1L;

}
