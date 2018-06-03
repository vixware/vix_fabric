package com.vix.mdm.crm.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.mdm.crm.dao.IContactPersonDao;

@Repository("contactPersonDao")
public class ContactPersonDaoImpl extends BaseHibernateDaoImpl implements IContactPersonDao {

	private static final long serialVersionUID = 1L;

}
