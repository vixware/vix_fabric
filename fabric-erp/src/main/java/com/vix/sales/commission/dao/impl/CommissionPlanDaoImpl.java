package com.vix.sales.commission.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.sales.commission.dao.ICommissionPlanDao;

@Repository("commissionPlanDao")
public class CommissionPlanDaoImpl extends BaseHibernateDaoImpl implements ICommissionPlanDao {

	private static final long serialVersionUID = 1L;

}
