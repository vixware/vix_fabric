package com.vix.crm.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.base.dao.ICustomerStageDao;

@Repository("customerStageDao")
public class CustomerStageDaoImpl extends BaseHibernateDaoImpl implements ICustomerStageDao {

	private static final long serialVersionUID = 1L;

}
