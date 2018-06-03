/**
 * 
 */
package com.vix.system.remindsCenter.base.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.system.remindsCenter.base.dao.IRemindsCenterDao;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Service("remindsCenterDao")
public class RemindsCenterDaoImpl extends BaseHibernateDaoImpl implements IRemindsCenterDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAllEntityByHql(String hql, Map<String, Object> params) throws Exception {

		Query query = getSession().createQuery(hql);
		return query.list();
	}

}
