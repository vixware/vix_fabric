/**
 * 
 */
package com.vix.crm.business.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.web.Pager;
import com.vix.crm.business.service.IOrderManagementService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Service("orderManagementService")
public class OrderManagementServiceImpl extends BaseHibernateServiceImpl implements IOrderManagementService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IBaseHibernateDao baseHibernateDao;

	@Override
	public Pager findCustomerPagerBySql(Pager pager, String hql, Map<String, Object> params) throws Exception {
		return baseHibernateDao.findPagerBySql(pager, hql, params);
	}

}
