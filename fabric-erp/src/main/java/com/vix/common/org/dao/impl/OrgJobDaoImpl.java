package com.vix.common.org.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.common.org.dao.IOrgJobDao;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

@Repository("orgJobDao")
public class OrgJobDaoImpl extends BaseHibernateDaoImpl implements IOrgJobDao {

	private static final long serialVersionUID = -7524355023279202816L;

}
