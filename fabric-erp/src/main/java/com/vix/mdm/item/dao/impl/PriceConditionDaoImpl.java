package com.vix.mdm.item.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.mdm.item.dao.IPriceConditionDao;

@Repository("priceConditionDao")
public class PriceConditionDaoImpl extends BaseHibernateDaoImpl implements IPriceConditionDao {

	private static final long serialVersionUID = 1L;

}
