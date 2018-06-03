package com.vix.common.security.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.common.security.dao.IResourceDao;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

@Repository("resourceDao")
public class ResourceDaoImpl extends BaseHibernateDaoImpl implements IResourceDao {

	private static final long serialVersionUID = 1L;

}
