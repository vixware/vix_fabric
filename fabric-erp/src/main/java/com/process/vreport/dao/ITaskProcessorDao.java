/**
 * 
 */
package com.process.vreport.dao;

import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
public interface ITaskProcessorDao extends IBaseHibernateDao {
	/** 查询所有对象,适用于小数据量不需要分页的查询 */
	public <T> List<T> findAllEntityByparams(String hql, Map<String, Object> params) throws Exception;
}
