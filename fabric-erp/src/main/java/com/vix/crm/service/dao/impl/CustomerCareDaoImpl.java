package com.vix.crm.service.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.service.dao.ICustomerCareDao;

@Repository("customerCareDao")
public class CustomerCareDaoImpl extends BaseHibernateDaoImpl implements ICustomerCareDao {

	private static final long serialVersionUID = 1L;

}
