package com.vix.crm.analyse.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.crm.analyse.dao.IRfmAnalyseDao;

@Repository("rfmAnalyseDao")
public class RfmAnalyseDaoImpl extends BaseHibernateDaoImpl implements IRfmAnalyseDao {

	private static final long serialVersionUID = 1L;
	
	@Override
	public Long findOrderCountBySql(String sql) throws Exception{
		Long count = 0l;
		Query query = getSession().createSQLQuery(sql);
		Object obj  = query.uniqueResult();
		if(null != obj && !"".equals(obj)){
			count = Long.parseLong(obj.toString());
		}
		return count;
	}
}
