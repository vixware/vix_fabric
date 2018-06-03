/**
 * 
 */
package com.vix.traceability.service;

import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

/**
 * 
 * com.traceability.service.ITraceabilityService
 *
 * @author bjitzhang
 *
 * @date 2015年9月23日
 */
public interface ITraceabilityService extends IBaseHibernateService {
	public void insertSql(String sql, Map<String, Object> params) throws Exception;

	public String getTableName(Class<?> clazz);

	public <T> List<T> findAllDataByConditions(Class<T> entityClass, Map<String, Object> params) throws Exception;
}
