package com.vix.ebusiness.order.orderProcess.dao.impl;

import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.ebusiness.order.orderProcess.dao.IOrderProcessDao;

@Repository("orderProcessDao")
public class OrderProcessDaoImpl extends BaseHibernateDaoImpl implements IOrderProcessDao {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public <T> T findOrderByHql(String hql, Map<String, Object> params) throws Exception {
		try {
			Query query = getSession().createQuery(hql);
			query.setProperties(params);
			query.setFirstResult(0);
			query.setMaxResults(1);
			Object obj = query.uniqueResult();
			if (null != obj) {
				return (T) obj;
			}
		} catch (Exception e) {
			throw e;
		}
		return null;
	}
}
