package com.vix.sales.commission.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.sales.commission.dao.ICommissionPlanItemDao;

@Repository("commissionPlanItemDao")
public class CommissionPlanItemDaoImpl extends BaseHibernateDaoImpl implements ICommissionPlanItemDao {

	private static final long serialVersionUID = 1L;

}
