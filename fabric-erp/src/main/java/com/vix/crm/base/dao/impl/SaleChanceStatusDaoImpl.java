package com.vix.crm.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.base.dao.ISaleChanceStatusDao;

@Repository("saleOpportunityStatusDao")
public class SaleChanceStatusDaoImpl extends BaseHibernateDaoImpl implements ISaleChanceStatusDao {

	private static final long serialVersionUID = 1L;

}
