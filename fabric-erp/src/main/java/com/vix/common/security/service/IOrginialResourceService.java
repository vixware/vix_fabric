package com.vix.common.security.service;

import java.util.List;
import java.util.Map;

import com.vix.common.orginialMeta.entity.OrginialResource;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

public interface IOrginialResourceService extends IBaseHibernateService{

	/**
	 * 加载系统系统所有的权限资源
	 * @return
	 * @throws Exception
	 */
	public List<OrginialResource> findAllOrginialResource() throws Exception;
	
	/**
	 * 分页查询
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findOrginialResourcePager(Pager pager,Map<String,Object> params)throws Exception;
	
	/**
	 * 提供权限的资源选择
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findOrginialResourceForSelect(Pager pager,Map<String,Object> params)throws Exception;
}
