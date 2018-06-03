package com.vix.sales.commission.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.sales.commission.dao.ICommissionTermItemDao;

@Repository("commissionTermItemDao")
public class CommissionTermItemDaoImpl extends BaseHibernateDaoImpl implements ICommissionTermItemDao {

	private static final long serialVersionUID = 1L;

}
