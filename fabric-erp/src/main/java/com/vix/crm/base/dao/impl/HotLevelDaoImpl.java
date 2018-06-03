package com.vix.crm.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.base.dao.IHotLevelDao;

@Repository("hotLevelDao")
public class HotLevelDaoImpl extends BaseHibernateDaoImpl implements IHotLevelDao {

	private static final long serialVersionUID = 1L;

}
