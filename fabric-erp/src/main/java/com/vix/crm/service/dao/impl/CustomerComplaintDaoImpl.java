package com.vix.crm.service.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.service.dao.ICustomerComplaintDao;

@Repository("customerComplaintDao")
public class CustomerComplaintDaoImpl extends BaseHibernateDaoImpl implements ICustomerComplaintDao {

	private static final long serialVersionUID = 1L;

}
