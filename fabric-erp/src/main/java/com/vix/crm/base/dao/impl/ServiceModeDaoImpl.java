package com.vix.crm.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.base.dao.IServiceModeDao;

@Repository("serviceModeDao")
public class ServiceModeDaoImpl extends BaseHibernateDaoImpl implements IServiceModeDao {

	private static final long serialVersionUID = 1L;

}
