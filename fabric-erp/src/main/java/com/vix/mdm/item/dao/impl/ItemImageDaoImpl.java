package com.vix.mdm.item.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.mdm.item.dao.IItemImageDao;

@Repository("itemImageDao")
public class ItemImageDaoImpl extends BaseHibernateDaoImpl implements IItemImageDao {

	private static final long serialVersionUID = 1L;
}
