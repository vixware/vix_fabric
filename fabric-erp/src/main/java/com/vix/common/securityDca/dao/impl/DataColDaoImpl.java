package com.vix.common.securityDca.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.common.securityDca.dao.IDataColDao;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

@Repository("dataColDao")
public class DataColDaoImpl extends BaseHibernateDaoImpl implements IDataColDao {

	private static final long serialVersionUID = 1L;

}
