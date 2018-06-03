package com.vix.hr.attendance.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.hr.attendance.dao.IPunchCardDao;

@Repository("punchcarddao")
public class PunchCardDaoImpl extends BaseHibernateDaoImpl implements IPunchCardDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
