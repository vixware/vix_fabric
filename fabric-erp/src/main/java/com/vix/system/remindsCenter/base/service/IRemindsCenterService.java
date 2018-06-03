/**
 * 
 */
package com.vix.system.remindsCenter.base.service;

import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
public interface IRemindsCenterService extends IBaseHibernateService {
	public <T> List<T> findRemindsList(Class<T> entityClass) throws Exception;

	public <T> List<T> findRemindsList(Class<T> entityClass, Map<String, Object> params) throws Exception;
}
