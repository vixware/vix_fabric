package com.vix.oa.bulletin.dao;

import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

/**
 * 
 * @ClassName: IAnnouncementNotificationDao
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-4-3 下午1:26:50
 */
public interface IAnnouncementNotificationDao extends IBaseHibernateDao {
	public <T> List<T> findAllDataByHql(String hql, Map<String, Object> params) throws Exception;
}
