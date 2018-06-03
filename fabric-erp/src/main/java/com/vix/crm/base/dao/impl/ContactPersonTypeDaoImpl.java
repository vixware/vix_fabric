package com.vix.crm.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.base.dao.IContactPersonTypeDao;

@Repository("contactPersonTypeDao")
public class ContactPersonTypeDaoImpl extends BaseHibernateDaoImpl implements IContactPersonTypeDao {

	private static final long serialVersionUID = 1L;

}
