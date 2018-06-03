package com.vix.crm.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.base.dao.IConsumeTimeDao;

@Repository("consumeTimeDao")
public class ConsumeTimeDaoImpl extends BaseHibernateDaoImpl implements IConsumeTimeDao {

	private static final long serialVersionUID = 1L;

}
