package com.vix.common.securityDra.service;

import java.util.Map;

import com.vix.common.security.entity.DataResRowOwner;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

public interface IDataResRowOwnerService extends IBaseHibernateService {

	/**
	 * 分页查询主体
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findDataResRowOwnerPager(Pager pager, Map<String, Object> params) throws Exception;
	/**
	 * 保存主体
	 * @param addRoleIds
	 * @param delRoleIds
	 * @param resRowOwner
	 * @return
	 * @throws Exception
	 */
	 public DataResRowOwner saveOrUpdate(String addRoleIds,String delRoleIds,DataResRowOwner resRowOwner)throws Exception;
}
