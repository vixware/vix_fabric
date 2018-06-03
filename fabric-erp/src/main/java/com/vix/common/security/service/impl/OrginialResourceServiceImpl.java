package com.vix.common.security.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.orginialMeta.entity.OrginialResource;
import com.vix.common.security.dao.IOrginialResourceDao;
import com.vix.common.security.hql.OrginialResourceHqlProvider;
import com.vix.common.security.service.IOrginialResourceService;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.persistence.hibernate.util.HqlTenantIdUtil;
import com.vix.core.web.Pager;

@Service("orginialResourceService")
@Transactional
public class OrginialResourceServiceImpl extends BaseHibernateServiceImpl implements IOrginialResourceService {

	private static final long serialVersionUID = 1L;

	@javax.annotation.Resource(name="orginialResourceDao")
	private IOrginialResourceDao resourceDao;
	
	/* 
	 * @see com.vix.common.security.service.IOrginialResourceService#findAllOrginialResource()
	 */ 
	@Override
	public List<OrginialResource> findAllOrginialResource() throws Exception{
		OrginialResourceHqlProvider orhp = new OrginialResourceHqlProvider();
		StringBuilder hql = new StringBuilder("select ");
		String ename = orhp.entityAsName();
		
		hql.append(ename).append(" from ").append(OrginialResource.class.getName()).append(" ").append(ename);
		hql.append(" left join fetch ").append(ename).append(".authority where 1=1 ");
		
		Map<String,Object> params = new HashMap<String, Object>();
		HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
		
		List<OrginialResource> resList = resourceDao.findAllByHql2(hql.toString(), params);
		return resList;
	}

	/* 
	 * @see com.vix.common.security.service.IOrginialResourceService#findOrginialResourcePager(com.vix.core.web.Pager, java.util.Map)
	 */ 
	@Override
	public Pager findOrginialResourcePager(Pager pager,Map<String,Object> params)throws Exception{
		OrginialResourceHqlProvider orhp = new OrginialResourceHqlProvider();
		StringBuilder hql = orhp.findOrginialResourcePagerList(params, pager);
		
		HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
		
		Pager resPager = resourceDao.findPager2ByHql(pager, orhp.entityAsName(), hql.toString(), params);
		return resPager;
	}
	
	/* 
	 * @see com.vix.common.security.service.IOrginialResourceService#findOrginialResourceForSelect(com.vix.core.web.Pager, java.util.Map)
	 */ 
	@Override
	public Pager findOrginialResourceForSelect(Pager pager,Map<String,Object> params)throws Exception{
		OrginialResourceHqlProvider orhp = new OrginialResourceHqlProvider();
		StringBuilder hql = new StringBuilder();
		
		String ename = orhp.entityAsName();
		
		hql.append("select ").append(ename).append(" from ").append(OrginialResource.class.getName()).append(" ").append(ename);
		hql.append(" left join fetch ").append(ename).append(".authority authority where authority is null ");
		
		HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
		
		//return resourceDao.findPagerByHql(pager, hql.toString(), params);
		return resourceDao.findPager2ByHql(pager, ename, hql.toString(), params);
	}
}
