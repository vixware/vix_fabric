package com.vix.drp.order.service;

import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

public interface IDistributorOrderService extends IBaseHibernateService {
	public <T> T findOrderByHql(String hql, Map<String, Object> params) throws Exception;
}
