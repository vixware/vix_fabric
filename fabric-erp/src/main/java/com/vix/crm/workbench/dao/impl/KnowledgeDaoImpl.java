package com.vix.crm.workbench.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.workbench.dao.IKnowledgeDao;

@Repository("knowledageDao")
public class KnowledgeDaoImpl extends BaseHibernateDaoImpl implements IKnowledgeDao {

	private static final long serialVersionUID = 1L;
 
}
