package com.vix.common.share.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.common.share.dao.IScheduleDao;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

@Repository("scheduleDao")
public class ScheduleDaoImpl extends BaseHibernateDaoImpl implements IScheduleDao {

	private static final long serialVersionUID = 1L;

}
