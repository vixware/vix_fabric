/**
 * 
 */
package com.vix.ebusiness.order.orderProcess.service;

import java.util.Map;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
public interface IOrderProcessService extends IBaseHibernateService {
	public <T> T findObjectByHqlNoTenantId(String hql, Map<String, Object> params) throws Exception;

	public BaseEntity findBeforeAndAfterEntityByHql(String hql, Map<String, Object> params) throws Exception;
}
