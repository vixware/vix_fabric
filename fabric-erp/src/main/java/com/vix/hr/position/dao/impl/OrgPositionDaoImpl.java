package com.vix.hr.position.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.hr.position.dao.IOrgPositionDao;

@Repository("orgPositionDao")
public class OrgPositionDaoImpl extends BaseHibernateDaoImpl implements IOrgPositionDao {

	private static final long serialVersionUID = -7524355023279202816L;

}
