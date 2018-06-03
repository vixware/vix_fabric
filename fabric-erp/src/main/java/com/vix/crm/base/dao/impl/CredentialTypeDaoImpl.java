package com.vix.crm.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.base.dao.ICredentialTypeDao;

@Repository("credentialTypeDao")
public class CredentialTypeDaoImpl extends BaseHibernateDaoImpl implements ICredentialTypeDao {

	private static final long serialVersionUID = 1L;

}
