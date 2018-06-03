package com.vix.drp.storesalesrecord.service;

import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

public interface IStoreSalesRecordService extends IBaseHibernateService {
	public <T> T findOrderByHql(String hql, Map<String, Object> params) throws Exception;
}
