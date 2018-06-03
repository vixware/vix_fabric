/**
 * 
 */
package com.vix.system.remindsCenter.base.dao;

import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
public interface IRemindsCenterDao extends IBaseHibernateDao {
	public <T> List<T> findAllEntityByHql(String hql, Map<String, Object> params) throws Exception;

}
