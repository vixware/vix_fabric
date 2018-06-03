package com.vix.crm.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.base.dao.ICustomerServiceStatusDao;

@Repository("customerServiceStatusDao")
public class CustomerServiceStatusDaoImpl extends BaseHibernateDaoImpl implements ICustomerServiceStatusDao {

	private static final long serialVersionUID = 1L;

}
