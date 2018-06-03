package com.vix.common.security.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.common.security.dao.IOrginialResourceDao;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

@Repository("orginialResourceDao")
public class OrginialResourceDaoImpl extends BaseHibernateDaoImpl implements IOrginialResourceDao {

	private static final long serialVersionUID = 1L;

}
