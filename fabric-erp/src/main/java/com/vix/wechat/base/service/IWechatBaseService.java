/**
 * 
 */
package com.vix.wechat.base.service;

import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

/**
 * 
 * @ClassFullName com.vix.wechat.base.service.IWechatBaseService
 *
 * @author bjitzhang
 *
 * @date 2016年3月15日
 *
 */
public interface IWechatBaseService extends IBaseHibernateService {

	public <T> List<T> findAllDataByConditions(Class<T> entityClass, Map<String, Object> params) throws Exception;
}
