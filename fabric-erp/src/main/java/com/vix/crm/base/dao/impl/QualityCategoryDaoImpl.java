package com.vix.crm.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.base.dao.IQualityCategoryDao;

@Repository("qualityCategoryDao")
public class QualityCategoryDaoImpl extends BaseHibernateDaoImpl implements IQualityCategoryDao {

	private static final long serialVersionUID = 1L;

}
