package com.vix.crm.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.base.dao.ICollaboratorTypeDao;

@Repository("collaboratorTypeDao")
public class CollaboratorTypeDaoImpl extends BaseHibernateDaoImpl implements ICollaboratorTypeDao {

	private static final long serialVersionUID = 1L;

}
