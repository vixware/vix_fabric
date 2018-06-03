/**
 * 
 */
package com.vix.WebService.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vix.WebService.dao.WebHibernateDao;
import com.vix.WebService.service.IStoreWebService;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;

/**
 * 
 * com.vix.WebService.service.impl.StoreWebServiceImpl
 *
 * @author bjitzhang
 *
 * @date 2014年8月11日
 */
@Service("storeWebService")
public class StoreWebServiceImpl extends BaseHibernateServiceImpl implements IStoreWebService {
	private static final long serialVersionUID = 1L;

	@Resource(name = "webHibernateDao")
	private WebHibernateDao webHibernateDao;

	@Override
	public <T> T findEntity(Class<T> entityClass, String attribute, Object value) throws Exception {
		return webHibernateDao.findEntityByAttributeNoTenantId(entityClass, attribute, value);
	}

	@Override
	public <T> List<T> findEntityList(String hql, Map<String, Object> params) throws Exception {
		return webHibernateDao.findAllByHql2NoTenantId(hql, params);
	}

	@Override
	public <T> T findEntity(String hql, Map<String, Object> params) throws Exception {
		return webHibernateDao.findObjectByHqlNoTenantId(hql, params);
	}

	@Override
	public <T> T saveEntity(Object entity) throws Exception {
		return webHibernateDao.mergeOriginal(entity);
	}

}
