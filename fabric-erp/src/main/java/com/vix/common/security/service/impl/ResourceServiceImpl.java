package com.vix.common.security.service.impl;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.security.dao.IResourceDao;
import com.vix.common.security.entity.Resource;
import com.vix.common.security.hql.ResourceHqlProvider;
import com.vix.common.security.service.IResourceService;
import com.vix.common.security.vo.ResourceVO;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.persistence.hibernate.util.HqlTenantIdUtil;
import com.vix.core.web.Pager;

@Service("resourceService")
@Transactional
public class ResourceServiceImpl extends BaseHibernateServiceImpl implements IResourceService {

	private static final long serialVersionUID = 1L;

	@javax.annotation.Resource(name="resourceDao")
	private IResourceDao resourceDao;
	
	/* (non-Javadoc)
	 * @see com.vix.common.security.service.IResourceService#findAllResource()
	 */
	@Override
	public List<Resource> findAllResource() throws Exception{
		ResourceHqlProvider rhp = new ResourceHqlProvider();
		StringBuilder hql = new StringBuilder("select ");
		String ename = rhp.entityAsName();
		
		hql.append(ename).append(" from ").append(Resource.class.getName()).append(" ").append(ename);
		hql.append(" left join fetch ").append(ename).append(".authority where 1=1 ");
		
		Map<String,Object> params = new HashMap<String, Object>();
		//HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
		
		List<Resource> resList = resourceDao.findAllByHql2(hql.toString(), params);
		return resList;
	}
	
	@Override
	public List<ResourceVO> findAllResourceByUserAccountId(String userAccountId,String tenantId){
		/*
		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("SELECT res.url, sa.authorityCode   ");//.append(ename);
		sql.append("FROM CMN_SEC_RESOURCE res ");
		sql.append("INNER JOIN CMN_SEC_AUTHORITY_RES sar ON sar.Resource_ID = res.id ");
		sql.append("INNER JOIN CMN_SEC_AUTHORITY sa ON sa.ID = sar.AUTHORITY_ID ");
		sql.append("INNER JOIN CMN_SEC_ROLE_AUTHORITY sra ON sra.AUTHORITY_ID = sa.ID ");
		sql.append("INNER JOIN CMN_SEC_USERACCOUNT_ROLE sur ON sur.Role_ID = sra.Role_ID  ");//直接关联关系表
		sql.append("WHERE sur.UserAccount_ID = ? ");
		//sql.append(" and auth.id <= 13628 ");
		params.add(userAccountId);
		*/
		
		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("SELECT DISTINCT R1.url,R2.authorityCode  FROM CMN_SEC_RESOURCE R1  ");//.append(ename);
		
		sql.append("LEFT JOIN ( ");
			sql.append("SELECT res.ID,sa.authorityCode FROM CMN_SEC_RESOURCE res ");
			sql.append("LEFT JOIN CMN_SEC_AUTHORITY_RES sar ON sar.Resource_ID = res.id ");
			sql.append("LEFT JOIN CMN_SEC_AUTHORITY sa ON sa.ID = sar.AUTHORITY_ID ");
			sql.append("LEFT JOIN CMN_SEC_ROLE_AUTHORITY sra ON sra.AUTHORITY_ID = sa.ID ");
			sql.append("LEFT JOIN CMN_SEC_USERACCOUNT_ROLE sur ON sur.Role_ID = sra.Role_ID ");
			sql.append("WHERE res.TENANTID = ? and sur.UserAccount_ID = ? ");
			params.add(tenantId);
			params.add(userAccountId);
			
		sql.append(") R2 ON R2.ID = R1.ID ");
		sql.append(" where R1.TENANTID = ? ");
		params.add(tenantId);
		//sql.append(" and auth.id <= 13628 ");
		List<ResourceVO> resVoList = resourceDao.queryObjectListBySql(sql.toString(), ResourceVO.class, params.toArray());
		//List<Resource> resList = resourceDao.findAllByHql2(hql.toString(), params);
		return resVoList;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.security.service.IResourceService#findResourcePager(com.vix.core.web.Pager, java.util.Map)
	 */
	@Override
	public Pager findResourcePager(Pager pager,Map<String,Object> params)throws Exception{
		ResourceHqlProvider rhp = new ResourceHqlProvider();
		StringBuilder hql = rhp.findResourcePagerList(params, pager);
		
		//HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
		
		Pager resPager = resourceDao.findPager2ByHql(pager, rhp.entityAsName(), hql.toString(), params);
		return resPager;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.security.service.IResourceService#findResourceForSelect(com.vix.core.web.Pager, java.util.Map)
	 */
	@Override
	public Pager findResourceForSelect(Pager pager,Map<String,Object> params)throws Exception{
		ResourceHqlProvider rhp = new ResourceHqlProvider();
		StringBuilder hql = new StringBuilder();
		//hql.append("select r from Resource r left join r.authority au where au is null ");
		String ename = rhp.entityAsName();
		
		hql.append("select ").append(ename).append(" from ").append(Resource.class.getName()).append(" ").append(ename);
		hql.append(" left join fetch ").append(ename).append(".authority authority where authority is null ");
		
		//HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
		
		//return resourceDao.findPagerByHql(pager, hql.toString(), params);
		return resourceDao.findPager2ByHql(pager, ename, hql.toString(), params);
	}
}
