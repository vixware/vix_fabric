/**
 * 
 */
package com.process.vreport.service;

import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
public interface ITaskProcessorService extends IBaseHibernateService {
	public <T> List<T> findAllEntityByParams(Class<T> entityClass,Map<String, Object> params) throws Exception;
}
