package com.vix.crm.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.base.dao.ICredentialDao;

@Repository("credentialDao")
public class CredentialDaoImpl extends BaseHibernateDaoImpl implements ICredentialDao {

	private static final long serialVersionUID = 1L;

}
