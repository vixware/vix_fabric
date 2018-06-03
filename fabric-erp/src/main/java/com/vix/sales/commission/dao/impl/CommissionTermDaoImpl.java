package com.vix.sales.commission.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.sales.commission.dao.ICommissionTermDao;

@Repository("commissionTermDao")
public class CommissionTermDaoImpl extends BaseHibernateDaoImpl implements ICommissionTermDao {

	private static final long serialVersionUID = 1L;

}
