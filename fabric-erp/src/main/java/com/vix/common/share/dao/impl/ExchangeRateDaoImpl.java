package com.vix.common.share.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.common.share.dao.IExchangeRateDao;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

@Repository("exchangeRateDao")
public class ExchangeRateDaoImpl extends BaseHibernateDaoImpl implements IExchangeRateDao {

	private static final long serialVersionUID = 1L;

}
