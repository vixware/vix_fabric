package com.vix.common.security.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.security.dao.IModuleDao;
import com.vix.common.security.entity.Module;
import com.vix.common.security.service.IModuleService;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;

/**
 * 模块的业务层
 * @author shadow
 *
 */
@Service("moduleService")
@Transactional
public class ModuleServiceImpl extends BaseHibernateServiceImpl implements IModuleService {

	@Resource(name = "moduleDao")
    private IModuleDao moduleDao;
	
	//@Resource(name = "authorityDao")
    //private IAuthorityDao authorityDao;
	
	/* (non-Javadoc)
	 * @see com.vix.common.security.service.IModuleService#findModulePager(com.vix.core.web.Pager, java.util.Map)
	 */
	@Override
	public Pager findModulePager(Pager pager, Map<String, Object> params)throws Exception {
		return moduleDao.findModulePager(pager, params);
	}

	/* (non-Javadoc)
	 * @see com.vix.common.security.service.IModuleService#saveModule(com.vix.common.security.entity.Module)
	 */
	@Override
	public Module saveModule(Module entityForm)throws Exception{
		Module entity = null;
		if(entityForm!=null && StrUtils.isNotEmpty(entityForm.getId())){
			entity = moduleDao.findEntityById(Module.class, entityForm.getId());
		}
		if(entity == null){
			entity = new Module();
		}
		
		Date now = new Date();
		String currentUserAccountId = SecurityUtil.getCurrentUserId();
		
		if(entity.getId()==null){
			entity.setCreateTime(now);
		}
		entity.setUpdateTime(now);
		//entity.setCreateUser(new UserAccount(currentUserAccountId));
		
		entity.setName(entityForm.getName());
		entity.setModuleCode(entityForm.getModuleCode());
		//entity.setModuleType(entityForm.getModuleType());
		
		//moduleDao.saveOrUpdate(entity);
		moduleDao.saveOrUpdateOriginal(entity);
		return entity;
	}
	
	
    /* (non-Javadoc)
     * @see com.vix.common.security.service.IModuleService#findAuthorityWithModule(java.lang.String, java.lang.String, java.lang.String)
     
    public List<Authority> findAuthorityWithModule(Long moduleId,Long authorityId,String bizType)throws Exception{
    	StringBuffer hql = new StringBuffer();
    	Map<String,Object> param = new HashMap<String, Object>();
    	hql.append("select distinct a from Authority a ");
    	hql.append(" left join fetch a.parentAuthority left join fetch a.subAuthoritys ");
    	hql.append(" left join fetch a.modules ");
    	
    	hql.append(" where a.bizType = :bizType ");
    	param.put("bizType", bizType);
    	if(authorityId==null){
    		hql.append(" and a.parentAuthority is null ");
    	}else{
    		hql.append(" and a.parentAuthority.id = :parentId ");
    		param.put("parentId", authorityId);
    	}
    	
    	//Map<String,Object> filterParam = new HashMap<String, Object>();
    	//filterParam.put("filterRoleId", roleId);
    	//List<Authority> auList = authorityDao.findAllByHql("authorityRoleFilter",filterParam,hql.toString(), param);
    	
    	HqlTenantIdUtil.handleParamMap4TenantId(param);//不增加tenantId
    	HqlTenantIdUtil.handleParamMap4CompanyInnerCode(param);//不处理CompanyInnerCode;
    	
    	List<Authority> auList = authorityDao.findAllByHql2(hql.toString(), param);
    	//List<Authority> auList = authorityDao.findAllByHql(hql.toString(), param);
    	return auList;
    }
    */
	/*
	private List<Authority> findAllAuthorityWithModule(String topParentCode,String bizType)throws Exception{
    	StringBuffer hql = new StringBuffer();
    	Map<String,Object> param = new HashMap<String, Object>();
    	hql.append("select distinct a from Authority a ");
    	hql.append(" left join fetch a.parentAuthority left join fetch a.subAuthoritys ");
    	//hql.append(" left join fetch a.roles left join fetch a.resources ");
    	hql.append(" left join fetch a.modules ");
    	hql.append(" left join  a.roles role with role.id = :roleId ");
    	param.put("roleId", roleId);
    	
    	hql.append(" where a.bizType = :bizType ");
    	param.put("bizType", bizType);
    		
    	hql.append(" and a.topParentCode = :topParentCode ");
    	param.put("topParentCode", topParentCode);
    	
    	//List<Authority> auList = authorityDao.findAllByHql("authorityRoleFilter",filterParam,hql.toString(), param);
    	HqlTenantIdUtil.handleParamMap4TenantId(param);//不增加tenantId
    	HqlTenantIdUtil.handleParamMap4CompanyInnerCode(param);//不处理CompanyInnerCode;
    	
    	List<Authority> auList = authorityDao.findAllByHql2(hql.toString(), param);
    	//List<Authority> auList = authorityDao.findAllByHql(hql.toString(), param);
    	return auList;
    }
    */
    /* (non-Javadoc)
     * @see com.vix.common.security.service.IModuleService#saveForAuthority(java.lang.Long, java.lang.String, java.lang.Long, java.lang.String, java.util.List)
   
    public void saveForAuthority(Long moduleId,String bizType,Long topParentAuId,String topAuthorityIds,List<Long> authorityIdList)throws Exception{
    	StringBuffer sql = new StringBuffer();
    	Map<String,Object> params = new HashMap<String, Object>();
    	//1、保存左侧的节点
    	if(StringUtils.isNotEmpty(topAuthorityIds) ){
    		String[] topAuthorityIdsArray = topAuthorityIds.split("\\,");
    		sql.append("delete from CMN_SEC_MODULE_AUTHORITY where Module_ID=:moduleId ");
        	params.put("moduleId", moduleId);
        	sql.append(" and Authority_ID in (select a.id from cmn_sec_authority a where a.Parent_id is null  and a.BizType=:bizType )");//and a.authType='M'
        	params.put("bizType", bizType);
        	//authorityDao.jdbcBatchUpdate(sql.toString(), roleId);
        	moduleDao.batchUpdateBySql(sql.toString(), params);
        	
        	
        	sql.setLength(0);
        	params.clear();
        	sql.append("insert into CMN_SEC_MODULE_AUTHORITY(Module_ID,Authority_ID) values (?,?)");
        	List<Object[]> saveArrayParam = new LinkedList<Object[]>();
    		for(String aid:topAuthorityIdsArray){
    			saveArrayParam.add(new Object[]{moduleId,Long.parseLong(aid)});
        	}
        	
        	//authorityDao.jdbcBatchUpdate(sql.toString(), saveArrayParam);
    		moduleDao.batchUpdateBySql(sql.toString(), saveArrayParam);
    	}else{
    		sql.append("delete from CMN_SEC_MODULE_AUTHORITY where Module_ID=:moduleId ");
        	params.put("moduleId", moduleId);
        	//authorityDao.jdbcBatchUpdate(sql.toString(), roleId);
        	moduleDao.batchUpdateBySql(sql.toString(), params);
    	}
    	
    	//2、保存 右侧的节点
    	sql.setLength(0);
    	params.clear();
    	if(topParentAuId!=null){
    		Authority topAu = moduleDao.findEntityById(Authority.class, topParentAuId);
        	sql.append("delete from CMN_SEC_MODULE_AUTHORITY where Module_ID=:moduleId ");
        	params.put("moduleId", moduleId);
        	sql.append(" and Authority_ID in (select a.id from cmn_sec_authority a where a.TopParentCode = :topParentCode )");//and a.authType='M'
        	params.put("topParentCode", topAu.getAuthorityCode());
        	//authorityDao.jdbcBatchUpdate(sql.toString(), roleId);
        	moduleDao.batchUpdateBySql(sql.toString(), params);
    	}
    	
        if(authorityIdList!=null){
        	sql.setLength(0);
        	params.clear();
        	sql.append("insert into CMN_SEC_MODULE_AUTHORITY(Module_ID,Authority_ID) values (?,?)");
        	List<Object[]> saveArrayParam = new LinkedList<Object[]>();
        	if(authorityIdList!=null){
        		for(Long aid:authorityIdList){
        			saveArrayParam.add(new Object[]{moduleId,aid});
            	}
        	}
        	
        	//authorityDao.jdbcBatchUpdate(sql.toString(), saveArrayParam);
        	moduleDao.batchUpdateBySql(sql.toString(), saveArrayParam);
        }
    	
    }
      */
	
	
    /* (non-Javadoc)
     * @see com.vix.common.security.service.IModuleService#findModuleListForTree(java.lang.Long, java.lang.String)
     
    public List<ModuleVo> findModuleListForTree(Long compId,String bizType)throws Exception{
    	return moduleDao.findModuleListForTree(compId, bizType);
    }*/
}
