package com.vix.mdm.item.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.mdm.item.dao.IItemCodesDao;

@Repository("itemCodesDao")
public class ItemCodesDaoImpl extends BaseHibernateDaoImpl implements IItemCodesDao {

	private static final long serialVersionUID = 1L;

}
