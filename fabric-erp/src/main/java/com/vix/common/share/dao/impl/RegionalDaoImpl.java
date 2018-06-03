package com.vix.common.share.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.common.share.dao.IRegionalDao;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

@Repository("regionalDao")
public class RegionalDaoImpl extends BaseHibernateDaoImpl implements IRegionalDao {

	private static final long serialVersionUID = 1L;

}
