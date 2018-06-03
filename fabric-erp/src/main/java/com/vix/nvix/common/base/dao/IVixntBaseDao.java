/**
 * 
 */
package com.vix.nvix.common.base.dao;

import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

/**
 * 
 * @ClassFullName com.vix.nvix.common.base.dao.IVixntBaseDao
 *
 * @author bjitzhang
 *
 * @date 2016年4月7日
 *
 */
public interface IVixntBaseDao extends IBaseHibernateDao {

	public <T> List<T> findAllDataByHql(String hql, Map<String, Object> params) throws Exception;

	@Override
	public <T> T findObjectBySql(String sql, Map<String, Object> params) throws Exception;

	public <T> List<T> findAllDataByHqlOrginial(String hql, Map<String, Object> params) throws Exception;

	/**
	 * 
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> findObjectList(String hql) throws Exception;
}
