package com.vix.crm.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.base.dao.ISaleActivityDao;

@Repository("saleActivityDao")
public class SaleActivityDaoImpl extends BaseHibernateDaoImpl implements ISaleActivityDao {

	private static final long serialVersionUID = 1L;

}
