package com.vix.common.security.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.security.dao.IIndexPageDataConfigDao;
import com.vix.common.security.entity.IndexPageDataConfig;
import com.vix.common.security.entity.Role;
import com.vix.common.security.service.IIndexPageDataConfigService;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;

@Service("indexPageDataConfigService")
@Transactional
public class IndexPageDataConfigServiceImpl extends BaseHibernateServiceImpl implements IIndexPageDataConfigService {

    @javax.annotation.Resource(name = "indexPageDataConfigDao")
    private IIndexPageDataConfigDao indexPageDataConfigDao;

    @Override
	public Pager findIndexPageDataConfigPager(Pager pager,Map<String,Object> params)throws Exception{
    	StringBuilder sb = new StringBuilder();
    	sb.append("select  pdc from ").append(IndexPageDataConfig.class.getName()).append(" pdc ");
    	sb.append(" where 1=1 ");
   
    	if(params!=null){
    		if(params.containsKey("name")){
    			sb.append("  and  pdc.name like :name ");
    		}
    		if(params.containsKey("divId")){
    			sb.append("  and  pdc.divId like :divId ");
    		}
	   	}
		Pager resPager = indexPageDataConfigDao.findPagerOrginialByHql(pager, "pdc", sb.toString(), params);
		return resPager;
    }
    
    @Override
	public IndexPageDataConfig saveIndexPageDataConfig(IndexPageDataConfig entityForm)throws Exception{
    	IndexPageDataConfig entity = null;
		if(entityForm!=null && StrUtils.isNotEmpty(entityForm.getId())){
			entity = indexPageDataConfigDao.findEntityById(IndexPageDataConfig.class, entityForm.getId());
		}
		if(entity == null){
			entity = new IndexPageDataConfig();
		}
		
		Date now = new Date();
		String currentUserAccountId = SecurityUtil.getCurrentUserId();
		
		if(entity.getId()==null){
			entity.setCreateTime(now);
		}
		entity.setUpdateTime(now);
		
		entity.setName(entityForm.getName());
		entity.setDisplayName(entityForm.getDisplayName());
		entity.setDivId(entityForm.getDivId());
		entity.setDivUrl(entityForm.getDivUrl());
		entity.setMemo(entityForm.getMemo());
		
		indexPageDataConfigDao.saveOrUpdateOriginal(entity);
		return entity;
	}

    
    
    @Override
	public Pager findSelectPdcByRolePager(Pager pager, String roleId,
			Map<String, Object> params) throws Exception {
		StringBuilder hql = new StringBuilder();
		Map<String,Object> param = new HashMap<String, Object>();
		hql.append("select  pdc from ").append(IndexPageDataConfig.class.getName()).append(" pdc ");
		if(roleId!=null){
			hql.append("inner join pdc.roles roles ");
		}
		hql.append(" where 1=1 ");
		
		if(roleId!=null){
			hql.append("roles.id != :roleId ");
			param.put("roleId", roleId);
		}
		
		if(params!=null && params.containsKey("name")){
			hql.append("  and  pdc.name like :name ");
		}
		
		if(params!=null && params.containsKey("divId")){
			hql.append(" and pdc.divId like :divId ");
			param.put("divId", "%"+params.get("divId")+"%");
		}
		//HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
    	//HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
    	
		pager = indexPageDataConfigDao.findPagerOrginialByHql(pager, "pdc", hql.toString(), param);
		return pager;
	}
    
    
	@Override
	public Pager findPdcByRolePager(Pager pager, String roleId,
			Map<String, Object> params) throws Exception {
		StringBuilder hql = new StringBuilder();
		Map<String,Object> param = new HashMap<String, Object>();
		hql.append("select  pdc from ").append(IndexPageDataConfig.class.getName()).append(" pdc ");
		hql.append("inner join pdc.roles roles ");
		hql.append(" where roles.id = :roleId ");
		param.put("roleId", roleId);
		
		if(params!=null && params.containsKey("name")){
			hql.append("  and  pdc.name like :name ");
		}
		
		if(params!=null && params.containsKey("divId")){
			hql.append(" and pdc.divId like :divId ");
			param.put("divId", "%"+params.get("divId")+"%");
		}
		//HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
    	//HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
    	
		pager = indexPageDataConfigDao.findPagerOrginialByHql(pager, "pdc", hql.toString(), param);
		return pager;
	}

	@Override
	public void saveForAddPdc(String roleId, String pdcIds) throws Exception {
		Role role = indexPageDataConfigDao.findEntityById(Role.class, roleId);
		
		if(role!=null){
			
			Set<IndexPageDataConfig> pdcs=role.getIndexPageDataConfigs();
			if( pdcs== null){
				pdcs= new HashSet<IndexPageDataConfig>();
			}
			//userAccountDao.evict(uaTmp);
			
			if(StringUtils.isNotEmpty(pdcIds)){
				for(String idS : pdcIds.split(",")){
					if(StringUtils.isNotEmpty(idS)){
						IndexPageDataConfig ipdc = indexPageDataConfigDao.findEntityById(IndexPageDataConfig.class, idS);
						pdcs.add(ipdc);
					}
				}
				
				indexPageDataConfigDao.saveOrUpdateOriginal(role);
			}
			
		}
		
	}
	
	
	
	@Override
	public void deletePdcForRole(String roleId, String pdcId)throws Exception{
		StringBuilder sql = new StringBuilder();
		Map<String,Object> params = new HashMap<String, Object>();
		sql.append("delete rc from CMN_SEC_ROLE_IPD_CONFIG rc ");
		sql.append("where rc.ROLE_ID = :roleId  ");
    	sql.append(" and rc.IPD_CONFIG_ID = :pdcId ");//and a.authType='M'
    	params.put("roleId", roleId);
    	params.put("pdcId", pdcId);
    	batchUpdateBySql(sql.toString(), params);
    	sql.setLength(0);
		params.clear();
	}
}
