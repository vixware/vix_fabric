package com.vix.mdm.crm.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.mdm.crm.dao.ICustomerAccountDao;

@Repository("customerAccountDao")
public class CustomerAccountDaoImpl extends BaseHibernateDaoImpl implements ICustomerAccountDao {

	private static final long serialVersionUID = 1L;

}
