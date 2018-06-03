package com.vix.crm.lead.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.lead.dao.ISaleLeadDao;

@Repository("saleLeadDao")
public class SaleLeadDaoImpl extends BaseHibernateDaoImpl implements ISaleLeadDao {

	private static final long serialVersionUID = 1L;

}
