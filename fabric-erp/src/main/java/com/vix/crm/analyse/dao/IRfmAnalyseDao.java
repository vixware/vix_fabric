package com.vix.crm.analyse.dao;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

public interface IRfmAnalyseDao extends IBaseHibernateDao {
	
	public Long findOrderCountBySql(String sql) throws Exception;
	
}
