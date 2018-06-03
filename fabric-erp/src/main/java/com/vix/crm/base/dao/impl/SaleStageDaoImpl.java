package com.vix.crm.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.base.dao.ISaleStageDao;

@Repository("saleStageDao")
public class SaleStageDaoImpl extends BaseHibernateDaoImpl implements ISaleStageDao {

	private static final long serialVersionUID = 1L;

}
