package com.vix.common.share.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.common.share.dao.ITaskDao;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

@Repository("taskDao")
public class TaskDaoImpl extends BaseHibernateDaoImpl implements ITaskDao {

	private static final long serialVersionUID = 1L;

}
