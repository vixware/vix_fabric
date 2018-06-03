package com.vix.mdm.crm.service;

import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;


public interface ICustomerAccountService extends IBaseHibernateService{

	
	public Pager findPagerByHql(Pager pager, String classAlilasName, String hql, Map<String, Object> params) throws Exception ;

	/**
	 * @param pager
	 * @param searchParam
	 * @return
	 */
}
