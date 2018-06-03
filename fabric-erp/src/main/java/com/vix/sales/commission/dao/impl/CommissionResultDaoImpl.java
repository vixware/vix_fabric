package com.vix.sales.commission.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.sales.commission.dao.ICommissionResultDao;

@Repository("commissionResultDao")
public class CommissionResultDaoImpl extends BaseHibernateDaoImpl implements ICommissionResultDao {

	private static final long serialVersionUID = 1L;

}
