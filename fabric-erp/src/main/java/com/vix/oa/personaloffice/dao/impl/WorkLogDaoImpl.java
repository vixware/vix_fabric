package com.vix.oa.personaloffice.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.oa.personaloffice.dao.IWorkLogDao;

@Repository("workLogDao")
public class WorkLogDaoImpl extends BaseHibernateDaoImpl implements IWorkLogDao {

	private static final long serialVersionUID = 1L;

}
