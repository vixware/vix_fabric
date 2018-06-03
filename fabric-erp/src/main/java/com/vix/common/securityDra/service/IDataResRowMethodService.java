package com.vix.common.securityDra.service;

import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

/**
 * 系统配置  hql拦截
 * @author Administrator
 *
 */
public interface IDataResRowMethodService extends IBaseHibernateService {

	/**
	 * 分页查询所有系统配置的hql拦截
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findDataResRowMethodPager(Pager pager,Map<String,Object> params) throws Exception;

	/**
	 * 选择使用 
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findSelectDataResRowMethodPager(Pager pager,Map<String,Object> params) throws Exception;
}
