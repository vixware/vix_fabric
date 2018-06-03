package com.vix.sales.commission.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.sales.commission.dao.ISaleCommissionDao;

@Repository("saleCommissionDao")
public class SaleCommissionDaoImpl extends BaseHibernateDaoImpl implements ISaleCommissionDao {

	private static final long serialVersionUID = 1L;

}
