package com.vix.crm.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.base.dao.ICompetitiveDao;

@Repository("competitiveDao")
public class CompetitiveDaoImpl extends BaseHibernateDaoImpl implements ICompetitiveDao {

	private static final long serialVersionUID = 1L;

}
