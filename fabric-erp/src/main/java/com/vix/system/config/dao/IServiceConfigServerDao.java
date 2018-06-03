package com.vix.system.config.dao;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.system.config.entity.ServiceConfigServer;

public interface IServiceConfigServerDao extends IBaseHibernateDao {

	/**
	 * @Title: findServiceConfigServer
	 * @Description: 查询服务地址
	 * @param @param serviceType
	 * @param @return
	 * @param @throws Exception    
	 * @return ServiceConfigServer   
	 * @throws
	 */
	public ServiceConfigServer findServiceConfigServer(String serviceType) throws Exception;
}
