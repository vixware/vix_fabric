package com.vix.crm.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.base.dao.IProjectStatusDao;

@Repository("projectStatusDao")
public class ProjectStatusDaoImpl extends BaseHibernateDaoImpl implements IProjectStatusDao {

	private static final long serialVersionUID = 1L;

}
