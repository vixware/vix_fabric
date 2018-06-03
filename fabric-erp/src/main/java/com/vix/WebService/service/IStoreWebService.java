/**
 * 
 */
package com.vix.WebService.service;

import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

/**
 * 
 * com.vix.WebService.service.IStoreWebServiceService
 *
 * @author bjitzhang
 *
 * @date 2014年8月11日
 */
public interface IStoreWebService extends IBaseHibernateService {

	public <T> T findEntity(Class<T> entityClass, String attribute, Object value) throws Exception;

	public <T> List<T> findEntityList(String hql, Map<String, Object> params) throws Exception;

	public <T> T findEntity(String hql, Map<String, Object> params) throws Exception;

	public <T> T saveEntity(Object entity) throws Exception;

}
