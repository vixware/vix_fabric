package com.vix.ebusiness.order.orderProcess.dao;

import java.util.Map;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

public interface IOrderProcessDao extends IBaseHibernateDao {
	public <T> T findOrderByHql(String hql, Map<String, Object> params) throws Exception;
}
