package com.vix.crm.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.base.dao.ICustomerSourceDao;

@Repository("customerSourceDao")
public class CustomerSourceDaoImpl extends BaseHibernateDaoImpl implements ICustomerSourceDao {

	private static final long serialVersionUID = 1L;

}
