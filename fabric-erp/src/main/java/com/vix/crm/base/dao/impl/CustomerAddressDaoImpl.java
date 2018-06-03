package com.vix.crm.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.base.dao.ICustomerAddressDao;

@Repository("customerAddressDao")
public class CustomerAddressDaoImpl extends BaseHibernateDaoImpl implements ICustomerAddressDao {

	private static final long serialVersionUID = 1L;

}
