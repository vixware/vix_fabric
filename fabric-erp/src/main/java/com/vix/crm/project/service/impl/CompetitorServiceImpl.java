package com.vix.crm.project.service.impl;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.web.Pager;
import com.vix.crm.project.service.ICompetitorService;

@Service("competitorService")
@Transactional
public class CompetitorServiceImpl extends BaseHibernateServiceImpl implements ICompetitorService {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IBaseHibernateDao baseHibernateDao;
	
	@Override
	public Pager findPagerByHql(Pager pager, String classAlilasName, String hql, Map<String, Object> params) throws Exception {
		return baseHibernateDao.findPagerByHql(pager, classAlilasName, hql, params);
	}
}
