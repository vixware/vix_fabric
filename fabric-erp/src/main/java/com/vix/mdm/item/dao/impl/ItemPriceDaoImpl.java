package com.vix.mdm.item.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.mdm.item.dao.IItemPriceDao;

@Repository("itemPriceDao")
public class ItemPriceDaoImpl extends BaseHibernateDaoImpl implements IItemPriceDao {

	private static final long serialVersionUID = 1L;
}
