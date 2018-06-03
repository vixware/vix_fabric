package com.vix.crm.service.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.service.dao.ICustomerServiceDao;

@Repository("customerServiceDao")
public class CustomerServiceDaoImpl extends BaseHibernateDaoImpl implements ICustomerServiceDao {

	private static final long serialVersionUID = 1L;

}
