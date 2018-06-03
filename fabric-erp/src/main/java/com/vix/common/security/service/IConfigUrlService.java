package com.vix.common.security.service;

import java.util.Map;

import com.vix.common.security.entity.ConfigUrlAdd;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

public interface IConfigUrlService extends IBaseHibernateService{

	/**
	 * 的分页查询
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findConfigUrlPager(Pager pager,Map<String,Object> params)throws Exception;
	
	/**
	 * 保存 
	 * @param module
	 * @return
	 * @throws Exception
	 */
	ConfigUrlAdd saveConfigUrl(ConfigUrlAdd configUrlAdd)throws Exception;

}
