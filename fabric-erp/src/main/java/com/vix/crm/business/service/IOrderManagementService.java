/**
 * 
 */
package com.vix.crm.business.service;

import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
public interface IOrderManagementService extends IBaseHibernateService {
	public Pager findCustomerPagerBySql(Pager pager, String sql, Map<String, Object> params) throws Exception;
}
