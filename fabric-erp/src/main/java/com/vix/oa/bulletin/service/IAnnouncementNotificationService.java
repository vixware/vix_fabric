package com.vix.oa.bulletin.service;

import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

/**
 * 
 * @ClassName: IAnnouncementNotificationService
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-4-3 下午1:28:59
 */
public interface IAnnouncementNotificationService extends IBaseHibernateService {
	
	public <T> List<T> findAllDataByConditions(Class<T> entityClass, Map<String, Object> params) throws Exception;

}
