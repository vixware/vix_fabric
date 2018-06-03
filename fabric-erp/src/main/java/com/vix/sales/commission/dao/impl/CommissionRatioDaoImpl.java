package com.vix.sales.commission.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.sales.commission.dao.ICommissionRatioDao;

@Repository("commissionRatioDao")
public class CommissionRatioDaoImpl extends BaseHibernateDaoImpl implements ICommissionRatioDao {

	private static final long serialVersionUID = 1L;

}
