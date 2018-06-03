package com.vix.mdm.crm.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.web.Pager;
import com.vix.mdm.crm.service.ICustomerAccountService;

@Service("customerAccountService")
public class CustomerAccountServiceImpl extends BaseHibernateServiceImpl implements ICustomerAccountService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IBaseHibernateDao baseHibernateDao;
	
	@Override
	public Pager findPagerByHql(Pager pager, String classAlilasName, String hql, Map<String, Object> params) throws Exception {
		return baseHibernateDao.findPagerByHql(pager, classAlilasName, hql, params);
	}

	/* (non-Javadoc)
	 * @see com.vix.mdm.crm.service.ICustomerAccountService#findCustomerAccountPager(com.vix.core.web.Pager, java.util.Map)
	 */
}