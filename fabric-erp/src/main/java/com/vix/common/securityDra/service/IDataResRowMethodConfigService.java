package com.vix.common.securityDra.service;

import com.google.common.collect.BiMap;
import com.vix.common.security.entity.DataResRowMethodConfig;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

public interface IDataResRowMethodConfigService extends IBaseHibernateService {

	/**
	 * 保存 hql方法拦截配置信息
	 * @param addMetaDataIds
	 * @param methodConfig
	 * @return
	 * @throws Exception
	 */
	public DataResRowMethodConfig saveOrUpdate(DataResRowMethodConfig methodConfig) throws Exception;


	/**
	 * 加载平台hql拦截配置信息 
	 * @return
	 * @throws Exception
	 */
	public BiMap<String,String> findDataResRowMethodMap()throws Exception;
}
