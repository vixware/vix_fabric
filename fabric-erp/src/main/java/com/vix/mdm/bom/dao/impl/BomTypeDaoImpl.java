package com.vix.mdm.bom.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.mdm.bom.dao.IBomTypeDao;

@Repository("bomTypeDao")
public class BomTypeDaoImpl extends BaseHibernateDaoImpl implements IBomTypeDao {

	private static final long serialVersionUID = -7524355023279202816L;

}
