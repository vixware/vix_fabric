package com.vix.common.share.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.common.share.dao.ICurrencyTypeDao;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

@Repository("currencyTypeDao")
public class CurrencyTypeDaoImpl extends BaseHibernateDaoImpl implements ICurrencyTypeDao {

	private static final long serialVersionUID = 1L;

}
