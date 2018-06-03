/**
 * 
 */
package com.vix.system.remindsCenter.base.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.system.remindsCenter.base.dao.IRemindsCenterDao;
import com.vix.system.remindsCenter.base.service.IRemindsCenterService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Service("remindsCenterService")
public class RemindsCenterServiceImpl extends BaseHibernateServiceImpl implements IRemindsCenterService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IRemindsCenterDao remindsCenterDao;

	@Override
	public <T> List<T> findRemindsList(Class<T> entityClass) throws Exception {

		return this.findAllRemindsList(entityClass, null);
	}

	public <T> List<T> findAllRemindsList(Class<T> entityClass, Map<String, Object> params) throws Exception {

		StringBuilder hqlBuilder = this.genHqlHeadBuilder(entityClass, params);

		return remindsCenterDao.findAllEntityByHql(hqlBuilder.toString(), params);
	}

	@Override
	public <T> List<T> findRemindsList(Class<T> entityClass, Map<String, Object> params) throws Exception {

		return this.findAllRemindsList(entityClass, params);
	}

}
