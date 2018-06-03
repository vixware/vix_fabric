package com.vix.system.config.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.vix.core.constant.BizConstant;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.system.config.dao.IServiceConfigServerDao;
import com.vix.system.config.entity.ServiceConfigServer;

@Repository("serviceConfigServerDao")
public class ServiceConfigServerDaoImpl extends BaseHibernateDaoImpl implements IServiceConfigServerDao {

	private static final long serialVersionUID = 1L;

	@Override
	public ServiceConfigServer findServiceConfigServer(String serviceType) throws Exception{
		//StringBuilder hql = userAccountHqlProvider.findUserByAccount(userName);
		StringBuilder sb = new StringBuilder();
		sb.append("select serviceConfigServer from ").append(ServiceConfigServer.class.getName()).append(" serviceConfigServer ");
		sb.append(" where serviceConfigServer.serviceType=:serviceType and serviceConfigServer.status=:status ");
   
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("serviceType", serviceType);
		params.put("status", BizConstant.COMMON_BOOLEAN_YES);
		ServiceConfigServer scs = findFirstByHqlOrginial(sb.toString(), params);
		return scs;
	}
	
}
