package com.vix.common.log.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.common.log.dao.ILogDao;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

@Repository("logDao")
public class LogDaoImpl extends BaseHibernateDaoImpl implements ILogDao {

	private static final long serialVersionUID = -7524355023279202816L;

}
