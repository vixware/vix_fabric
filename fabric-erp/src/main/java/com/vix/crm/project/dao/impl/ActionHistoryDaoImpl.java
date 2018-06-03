package com.vix.crm.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.project.dao.IActionHistoryDao;

@Repository("actionHistoryDao")
public class ActionHistoryDaoImpl extends BaseHibernateDaoImpl implements IActionHistoryDao {

	private static final long serialVersionUID = 1L;

}
