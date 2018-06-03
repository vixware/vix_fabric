package com.vix.common.security.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.vix.common.security.dao.IModuleDao;
import com.vix.common.security.entity.Module;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.core.persistence.hibernate.util.HqlTenantIdUtil;
import com.vix.core.web.Pager;
import com.vix.system.industrymanagement.entity.IndustryManagementModule;

@Repository("moduleDao")
public class ModuleDaoImpl extends BaseHibernateDaoImpl implements IModuleDao {

	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see com.vix.common.security.dao.IModuleDao#findModulePager(com.vix.core.web.Pager, java.util.Map)
	 */
	@Override
	public Pager findModulePager(Pager pager, Map<String, Object> params)throws Exception {
		StringBuilder hql = new StringBuilder();
		//hql.append("select r from Resource r left join r.authority au where au is null ");
		String ename = "module";
		hql.append("select module from ").append(Module.class.getName()).append(" module ");
		/*if(params.containsKey("industryMgtId")){
			hql.append(" left join module.industryManagementModules imm ");
			hql.append(" left join imm.industryManagement im ");
		}*/
		hql.append(" where 1=1 ");
		if(params!=null){
			if(params.containsKey("name")){
				hql.append(" and ").append(ename).append(".name like :name ");
			}
			if(params.containsKey("industryMgtId")){
				hql.append(" and module.id not in (");
				hql.append(" select imm.module.id from ").append(IndustryManagementModule.class.getName()).append(" imm ");
				hql.append(" where imm.industryManagement.id = :industryMgtId ");
				
				hql.append(") ");
			}
		}
		/*
		HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
		return findPager2ByHql(pager, ename, hql.toString(), params);
		*/
		return findPagerOrginialByHql(pager, ename, hql.toString(), params);
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.security.dao.IModuleDao#findAllModuleList()
	 */
	@Override
	public List<Module> findAllModuleList()throws Exception{
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		String ename = "module";
		hql.append("select ").append(ename).append(" from ").append(Module.class.getName()).append(" ").append(ename);
		hql.append(" where 1=1 ");
		hql.append(" order by ").append(ename).append(".moduleCode ");
		HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
		
		return findAllByHql2(hql.toString(), params);
	}

    /* (non-Javadoc)
     * @see com.vix.common.security.dao.IModuleDao#findModuleListForTree(java.lang.Long, java.lang.String)
    
    public List<ModuleVo> findModuleListForTree(Long compId,String bizType)throws Exception{
    	Organization comp = findEntityById(Organization.class, compId);
    	List<Module> moduleList = findAllModuleList();
    	if(moduleList==null) moduleList = new ArrayList<Module>();
    	
    	List<ModuleVo> resList = new LinkedList<ModuleVo>();
    	ModuleVo vo = null;
    	String bizStr = null;
    	Boolean hasData = false;
    	if(bizType.equals(BizConstant.COMMON_SECURITY_RESTYPE_P)){
    		//pc
    		if(comp!=null){
    			bizStr = comp.getUseModulePc();
    		}
    		hasData = true;
    	}else if(bizType.equals(BizConstant.COMMON_SECURITY_RESTYPE_D)){
    		//pad
    		if(comp!=null){
    			bizStr = comp.getUseModulePad();
    		}
    		hasData = true;
    	}else if(bizType.equals(BizConstant.COMMON_SECURITY_RESTYPE_M)){
    		//mobile
    		if(comp!=null){
    			bizStr = comp.getUseModuleMobile();
    		}
    		hasData = true;
    	}
    	
    	if(hasData){
    		for(Module md:moduleList){
    			vo = new ModuleVo();
    			//BeanUtils.copyProperties(md, vo, new String[]{"authoritys"});
    			vo.setId(md.getId());
    			vo.setName(md.getName());
    			vo.setModuleCode(md.getModuleCode());
    			if(StrUtils.isNotEmpty(bizStr) && bizStr.contains("#"+md.getModuleCode()+"#")){
    				vo.setIsCheck(true);
    			}else{
    				vo.setIsCheck(false);
    			}
    			resList.add(vo);
    		}
    	}
    	return resList;
    }
     */
}
