package com.vix.sales.commission.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.sales.commission.dao.ICommissionRatioItemDao;

@Repository("commissionRatioItemDao")
public class CommissionRatioItemDaoImpl extends BaseHibernateDaoImpl implements ICommissionRatioItemDao {

	private static final long serialVersionUID = 1L;

}
