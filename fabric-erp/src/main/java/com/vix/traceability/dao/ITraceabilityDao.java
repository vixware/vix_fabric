/**
 * 
 */
package com.vix.traceability.dao;

import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

/**
 * 
 * com.traceability.dao.ITraceabilityDao
 *
 * @author bjitzhang
 *
 * @date 2015年9月23日
 */
public interface ITraceabilityDao extends IBaseHibernateDao {
	public String getTableName(Class<?> clazz);

	public <T> List<T> findAllDataByHql(String hql, Map<String, Object> params) throws Exception;
}
