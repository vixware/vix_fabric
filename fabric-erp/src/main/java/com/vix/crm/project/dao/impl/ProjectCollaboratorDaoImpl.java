package com.vix.crm.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.project.dao.IProjectCollaboratorDao;

@Repository("projectCollaboratorDao")
public class ProjectCollaboratorDaoImpl extends BaseHibernateDaoImpl implements IProjectCollaboratorDao {

	private static final long serialVersionUID = 1L;

}
