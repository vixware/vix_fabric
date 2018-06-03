package com.vix.restService.test.submodule.service;

import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

public interface ISubmoduleRestService extends IBaseHibernateService {

	public <T> T findObjectByHqlNoTenantId(String hql, Map<String, Object> params) throws Exception;
}
