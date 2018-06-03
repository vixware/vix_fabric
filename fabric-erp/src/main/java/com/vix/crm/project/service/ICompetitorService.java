package com.vix.crm.project.service;

import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

public interface ICompetitorService extends IBaseHibernateService {

	public Pager findPagerByHql(Pager pager, String classAlilasName, String hql, Map<String, Object> params) throws Exception ;

	/**
	 * @param pager
	 * @param searchParam
	 * @return
	 */
}
