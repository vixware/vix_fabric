package com.vix.crm.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.base.dao.ICustomerTypeDao;

@Repository("customerTypeDao")
public class CustomerTypeDaoImpl extends BaseHibernateDaoImpl implements ICustomerTypeDao {

	private static final long serialVersionUID = 1L;

}
