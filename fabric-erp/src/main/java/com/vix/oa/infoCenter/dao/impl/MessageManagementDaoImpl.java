package com.vix.oa.infoCenter.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.oa.infoCenter.dao.IMessageManagementDao;

@Repository("messageManagementDao")
public class MessageManagementDaoImpl extends BaseHibernateDaoImpl implements IMessageManagementDao {

	private static final long serialVersionUID = 1L;

}
