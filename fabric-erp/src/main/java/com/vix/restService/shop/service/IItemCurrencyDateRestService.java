package com.vix.restService.shop.service;

import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

public interface IItemCurrencyDateRestService extends IBaseHibernateService {

	public <T> T findObjectByHqlNoTenantId(String hql, Map<String, Object> params) throws Exception;
}
