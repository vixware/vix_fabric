package com.vix.mdm.item.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.mdm.item.dao.IItemGroupDao;

@Repository("itemGroupDao")
public class ItemGroupDaoImpl extends BaseHibernateDaoImpl implements IItemGroupDao {

	private static final long serialVersionUID = 1L;
}
