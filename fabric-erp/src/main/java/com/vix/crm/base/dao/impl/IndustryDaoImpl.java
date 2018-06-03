package com.vix.crm.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.base.dao.IIndustryDao;

@Repository("industryDao")
public class IndustryDaoImpl extends BaseHibernateDaoImpl implements IIndustryDao {

	private static final long serialVersionUID = 1L;

}
