package com.vix.mdm.item.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.mdm.item.dao.IItemInventoryPropertiesDao;

@Repository("itemInventoryPropertiesDao")
public class ItemInventoryPropertiesDaoImpl extends BaseHibernateDaoImpl implements IItemInventoryPropertiesDao {

	private static final long serialVersionUID = 1L;

}
