package com.vix.crm.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.project.dao.ICrmProjectDao;

@Repository("crmProjectDao")
public class CrmProjectDaoImpl extends BaseHibernateDaoImpl implements ICrmProjectDao {

	private static final long serialVersionUID = 1L;

}
