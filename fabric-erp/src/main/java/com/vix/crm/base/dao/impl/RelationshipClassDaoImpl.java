package com.vix.crm.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.base.dao.IRelationshipClassDao;

@Repository("relationshipClassDao")
public class RelationshipClassDaoImpl extends BaseHibernateDaoImpl implements IRelationshipClassDao {

	private static final long serialVersionUID = 1L;

}
