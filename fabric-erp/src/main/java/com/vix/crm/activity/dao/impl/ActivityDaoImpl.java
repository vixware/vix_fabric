package com.vix.crm.activity.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.activity.dao.IActivityDao;

@Repository("activityDao")
public class ActivityDaoImpl extends BaseHibernateDaoImpl implements IActivityDao {

	private static final long serialVersionUID = 1L;

}
