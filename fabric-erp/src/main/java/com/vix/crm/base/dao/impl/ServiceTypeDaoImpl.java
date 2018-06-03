package com.vix.crm.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.base.dao.IServiceTypeDao;

@Repository("serviceTypeDao")
public class ServiceTypeDaoImpl extends BaseHibernateDaoImpl implements IServiceTypeDao {

	private static final long serialVersionUID = 1L;

}
